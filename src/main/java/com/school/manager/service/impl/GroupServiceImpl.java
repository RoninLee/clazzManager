package com.school.manager.service.impl;

import com.school.manager.common.constant.LongConstant;
import com.school.manager.enums.StatusCode;
import com.school.manager.exception.SysServiceException;
import com.school.manager.pojo.bo.GroupInfoBO;
import com.school.manager.pojo.bo.GroupLeaderBO;
import com.school.manager.pojo.bo.GroupLeaderInfoBO;
import com.school.manager.pojo.dao.GroupDao;
import com.school.manager.pojo.dao.GroupUserDao;
import com.school.manager.pojo.dao.UserDao;
import com.school.manager.pojo.dao.UserGradeSubjectDao;
import com.school.manager.pojo.dto.common.BaseDTO;
import com.school.manager.pojo.dto.req.GroupAddReq;
import com.school.manager.pojo.dto.req.GroupUpdateReq;
import com.school.manager.pojo.dto.resp.GroupLessonListResp;
import com.school.manager.pojo.dto.resp.GroupListInfoResp;
import com.school.manager.pojo.entity.Group;
import com.school.manager.pojo.entity.GroupUser;
import com.school.manager.service.GroupService;
import com.school.manager.utils.IdWorker;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.*;
import java.util.stream.Collectors;

/**
 * @author lizelong
 * @description 组模块接口实现
 */
@Service
public class GroupServiceImpl implements GroupService {

    @Resource
    private UserDao userDao;

    @Resource
    private UserGradeSubjectDao userGradeSubjectDao;

    @Resource
    private GroupDao groupDao;

    @Resource
    private GroupUserDao groupUserDao;

    @Autowired
    private IdWorker idWorker;

    /**
     * 查询组长列表
     *
     * @return 组长列表
     */
    @Override
    public List<BaseDTO<String>> leaderList() {
        return userDao.leaderList();
    }

    /**
     * 查询课程列表
     *
     * @param leaderId 组长ID
     * @return 课程列表
     */
    @Override
    public List<GroupLessonListResp> lessonList(String leaderId) {
        return userGradeSubjectDao.lessonList(leaderId);
    }

    /**
     * 查询组员列表
     *
     * @param gradeId   年级ID
     * @param subjectId 学科ID
     * @return 组员列表
     */
    @Override
    public List<BaseDTO<String>> memberList(String gradeId, String subjectId) {
        return userDao.memberList(gradeId, subjectId);
    }

    /**
     * 组列表
     *
     * @param name      模糊查询 组员名字或工号
     * @param pageIndex
     * @param pageSize
     * @return 组列表
     */
    @Override
    public List<GroupListInfoResp> list(String name, Integer pageIndex, Integer pageSize) {
        int offset = (pageIndex - 1) * pageSize;
        List<GroupInfoBO> groupInfoBOList = groupDao.getByFuzzyName(name);
        Map<String, List<GroupInfoBO>> collect = groupInfoBOList.stream().collect(Collectors.groupingBy(GroupInfoBO::getId, Collectors.toList()));
        List<List<GroupInfoBO>> pageList = new ArrayList<>(collect.values()).stream().skip(offset).limit(pageSize).collect(Collectors.toList());
        List<GroupListInfoResp> result = new LinkedList<>();
        pageList.forEach(gs -> {
            GroupListInfoResp resp = listToGroup(gs);
            if (Objects.nonNull(resp)) {
                result.add(resp);
            }
        });
        return result;
    }

    /**
     * 组详情信息
     *
     * @param id 组ID
     * @return 组信息
     */
    @Override
    public GroupListInfoResp info(String id) {
        List<GroupInfoBO> groupInfoBOList = groupDao.getById(id);
        return Optional.ofNullable(listToGroup(groupInfoBOList)).orElseThrow(() -> new SysServiceException(StatusCode.DATA_NOT_EXIST.getDesc()));
    }

    /**
     * 添加组信息
     *
     * @param request 请求对象
     */
    @Override
    @Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.DEFAULT, rollbackFor = Exception.class)
    public void add(GroupAddReq request) {
        // 添加组
        Group group = new Group();
        String groupId = String.valueOf(idWorker.nextId());
        group.setId(groupId);
        group.setName(request.getName());
        group.setGradeId(request.getGradeId());
        group.setSubjectId(request.getSubjectId());
        group.setVersion(LongConstant.ONE);
        groupDao.add(group);
        checkLeader(request.getLeaderId());
        // 组长
        List<GroupUser> groupUsers = new LinkedList<>();
        GroupUser leader = new GroupUser();
        leader.setUserId(request.getLeaderId());
        leader.setGroupId(groupId);
        groupUsers.add(leader);
        Set<String> memberIds = Optional.ofNullable(request.getMembers()).orElse(new HashSet<>());
        memberIds.forEach(memberId -> {
            GroupUser member = new GroupUser();
            member.setGroupId(groupId);
            member.setUserId(memberId);
            groupUsers.add(member);
        });
        groupUserDao.batchAdd(groupUsers);
    }

    /**
     * 更新组信息
     *
     * @param request 请求对象
     */
    @Override
    @Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.DEFAULT, rollbackFor = Exception.class)
    public void update(GroupUpdateReq request) {

        GroupLeaderInfoBO groupLeader = Optional.ofNullable(groupDao.infoWithLeader(request.getId())).orElseThrow(() -> new SysServiceException(StatusCode.DATA_NOT_EXIST.getDesc()));
        if (!Objects.equals(groupLeader.getVersion(), request.getVersion())) {
            throw new SysServiceException(StatusCode.DATA_CHANGED.getDesc());
        }
        Group newGroup = new Group();
        newGroup.setId(request.getId());
        newGroup.setName(request.getName());
        newGroup.setGradeId(request.getGradeId());
        newGroup.setSubjectId(request.getSubjectId());
        newGroup.setVersion(request.getVersion() + LongConstant.ONE);
        groupDao.update(newGroup);
        // 校验组长
        List<GroupUser> groupUserAddList = new LinkedList<>();
        if (!Objects.equals(request.getLeaderId(), groupLeader.getLeaderId())) {
            checkLeader(request.getLeaderId());
        }
        List<GroupUser> groupUserList = groupUserDao.getGroupByGid(request.getId());
        Set<String> nowUser = groupUserList.stream().map(GroupUser::getUserId).collect(Collectors.toSet());
        Set<String> members = Optional.ofNullable(request.getMembers()).orElse(new HashSet<>());
        members.add(request.getLeaderId());
        // 需删除的绑定关系
        Set<String> delUsers = groupUserList.stream().filter(groupUser -> !members.contains(groupUser.getUserId())).map(GroupUser::getUserId).collect(Collectors.toSet());
        // 删除现有 剩余增加
        members.removeAll(nowUser);
        members.forEach(memberId -> {
            GroupUser member = new GroupUser();
            member.setGroupId(request.getId());
            member.setUserId(memberId);
            groupUserAddList.add(member);
        });
        if (CollectionUtils.isNotEmpty(groupUserAddList)) {
            groupUserDao.batchAdd(groupUserAddList);
        }
        if (CollectionUtils.isNotEmpty(delUsers)) {
            groupUserDao.batchDel(request.getId(), delUsers);
        }
    }

    /**
     * 删除组
     *
     * @param id 组id
     */
    @Override
    @Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.DEFAULT, rollbackFor = Exception.class)
    public void delete(String id, Long version) {
        GroupLeaderInfoBO groupLeader = Optional.ofNullable(groupDao.infoWithLeader(id)).orElseThrow(() -> new SysServiceException(StatusCode.DATA_NOT_EXIST.getDesc()));
        if (!Objects.equals(groupLeader.getVersion(), version)) {
            throw new SysServiceException(StatusCode.DATA_CHANGED.getDesc());
        }
        groupDao.delete(id);
        groupUserDao.delete(id);
    }

    private void checkLeader(String leaderId) {
        // 校验是否已经绑定为某组
        GroupLeaderBO groupNameByUser = groupUserDao.getGroupNameByUser(leaderId);
        if (Objects.nonNull(groupNameByUser)) {
            throw new SysServiceException(groupNameByUser.getUserName() + " 已绑定到 " + groupNameByUser.getGroupName());
        }
    }

    /**
     * 将组信息列表转换成组响应对象
     *
     * @param groupInfoBOList 组信息列表
     * @return 组响应对象
     */
    private GroupListInfoResp listToGroup(List<GroupInfoBO> groupInfoBOList) {
        if (CollectionUtils.isNotEmpty(groupInfoBOList)) {
            GroupListInfoResp resp = new GroupListInfoResp();
            List<BaseDTO<String>> members = new LinkedList<>();
            groupInfoBOList.forEach(g -> {
                if (g.getGroupLeaderFlag()) {
                    resp.setId(g.getId());
                    resp.setName(g.getName());
                    resp.setGradeId(g.getGradeId());
                    resp.setLeaderId(g.getUserId());
                    resp.setLessonId(g.getGradeId() + g.getSubjectId());
                    resp.setLeaderName(g.getUserName());
                    resp.setGradeId(g.getGradeId());
                    resp.setSubjectId(g.getSubjectId());
                    resp.setVersion(g.getVersion());
                    resp.setLessonName(g.getLessonName());
                } else {
                    BaseDTO<String> baseDTO = new BaseDTO<>();
                    baseDTO.setId(g.getUserId());
                    baseDTO.setName(g.getUserName());
                    members.add(baseDTO);
                }
            });
            resp.setMembers(members);
            return resp;
        }
        return null;
    }
}
