package com.school.manager.service.impl;

import com.school.manager.common.constant.LongConstant;
import com.school.manager.enums.StatusCode;
import com.school.manager.exception.SysServiceException;
import com.school.manager.jwt.LoginUserInfo;
import com.school.manager.jwt.LoginUserUtil;
import com.school.manager.pojo.dao.ChapterDao;
import com.school.manager.pojo.dto.req.ChapterSaveReq;
import com.school.manager.pojo.dto.req.ChapterUpdateReq;
import com.school.manager.pojo.dto.resp.ChapterInfoResp;
import com.school.manager.pojo.dto.resp.GradeSubjectResp;
import com.school.manager.pojo.entity.Chapter;
import com.school.manager.service.ChapterService;
import com.school.manager.utils.BeanMapper;
import com.school.manager.utils.IdWorker;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

/**
 * @author RoninLee
 * @description 章节管理实现类
 */
@Service
public class ChapterServiceImpl implements ChapterService {
    @Resource
    private ChapterDao chapterDao;
    @Autowired
    private IdWorker idWorker;

    /**
     * 新增
     *
     * @param request 章节对象
     * @return id
     */
    @Override
    public String save(ChapterSaveReq request) {
        // 转换对象
        Chapter chapter = BeanMapper.def().map(request, Chapter.class);
        chapter.setId(String.valueOf(idWorker.nextId()));
        // 若为当前节点顶级节点，则rootId设为当前节点id
        String rootId = StringUtils.equals("-1", chapter.getParentId()) ? chapter.getId() : Optional.ofNullable(chapterDao.info(chapter.getParentId())).map(Chapter::getRootId).orElseThrow(() -> new SysServiceException(StatusCode.PARENT_NOT_EXIST.getDesc()));
        chapter.setRootId(rootId);
        chapter.setVersion(LongConstant.ONE);
        int insert = chapterDao.save(chapter);
        if (insert == 0) {
            throw new SysServiceException(StatusCode.SAVE_FAILED.getDesc());
        }
        return chapter.getId();
    }

    /**
     * 删除
     *
     * @param id 主键id
     */
    @Override
    public void delete(String id) {
        if (chapterDao.childNodes(id) > 0) {
            throw new SysServiceException(StatusCode.EXIST_CHILD_NODE.getDesc());
        }
        chapterDao.delete(id);
    }

    /**
     * 更新
     *
     * @param request 章节id
     * @return 主键id
     */
    @Override
    public String update(ChapterUpdateReq request) {
        Chapter source = Optional.ofNullable(chapterDao.info(request.getId())).orElseThrow(() -> new SysServiceException(StatusCode.DATA_NOT_EXIST.getDesc()));
        // 乐观锁
        if (!Objects.equals(source.getVersion(), request.getVersion())) {
            throw new SysServiceException(StatusCode.DATA_CHANGED.getDesc());
        }
        Chapter chapter = BeanMapper.def().map(request, Chapter.class);
        String rootId = StringUtils.equals("-1", chapter.getParentId()) ? chapter.getId() : Optional.ofNullable(chapterDao.info(chapter.getParentId())).map(Chapter::getRootId).orElseThrow(() -> new SysServiceException(StatusCode.PARENT_NOT_EXIST.getDesc()));
        chapter.setVersion(chapter.getVersion() + LongConstant.ONE);
        chapter.setRootId(rootId);
        chapterDao.update(chapter);
        return chapter.getId();
    }

    /**
     * 根据主键 id 查询
     *
     * @param id 主键id
     * @return 返回对象
     */
    @Override
    public ChapterInfoResp info(String id) {
        Chapter chapter = Optional.ofNullable(chapterDao.info(id)).orElseThrow(() -> new SysServiceException(StatusCode.DATA_NOT_EXIST.getDesc()));
        return BeanMapper.def().map(chapter, ChapterInfoResp.class);
    }

    /**
     * 当前用户所绑定的年级学科下拉列表
     *
     * @return 当前用户所绑定的年级学科下拉列表
     */
    @Override
    public List<GradeSubjectResp> gradeSubjectDropdownList() {
        String userId = Optional.ofNullable(LoginUserUtil.getLoginUserInfo()).map(LoginUserInfo::getId).orElseThrow(() -> new SysServiceException(StatusCode.NO_LOGIN_INFO.getDesc()));
        return chapterDao.gradeSubjectDropdownList(userId);
    }
}
