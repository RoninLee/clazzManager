package com.school.manager.pojo.dao;

import com.school.manager.pojo.bo.GroupLeaderBO;
import com.school.manager.pojo.entity.GroupUser;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Set;

/**
 * @author lizelong
 * @description 用户组
 */
@Mapper
public interface GroupUserDao {

    /**
     * 通过userId
     *
     * @param userId
     * @return 组长名称及组名
     */
    GroupLeaderBO getGroupNameByUser(@Param("userId") String userId);

    /**
     * 批量添加组和用户关系
     *
     * @param gus 用户组关系列表
     */
    void batchAdd(@Param("gus") List<GroupUser> gus);

    /**
     * 通过groupId查询
     *
     * @param groupId 组id
     * @return 用户组关系列表
     */
    List<GroupUser> getGroupByGid(@Param("groupId") String groupId);

    /**
     * 删除用户组关系
     *
     * @param id
     * @param delUsers
     */
    void batchDel(@Param("id") String id, @Param("delUsers") Set<Long> delUsers);

    /**
     * 根据组删除
     *
     * @param groupId 组ID
     */
    void delete(@Param("groupId") String groupId);

    /**
     * 根据用户查询组
     *
     * @param userId
     * @return
     */
    Integer getByUserId(@Param("userId") String userId);
}
