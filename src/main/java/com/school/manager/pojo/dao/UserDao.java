package com.school.manager.pojo.dao;

import com.school.manager.pojo.dto.common.BaseDTO;
import com.school.manager.pojo.dto.resp.UserDropdownListResp;
import com.school.manager.pojo.dto.resp.UserResp;
import com.school.manager.pojo.entity.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author RoninLee
 * @description 用户
 */
@Mapper
public interface UserDao {
    /**
     * 通过工号查人员
     *
     * @param jobNumber 工号
     * @return 人员信息
     */
    User findUserByJobNumber(@Param("jobNumber") String jobNumber);

    /**
     * [新增]
     *
     * @author RoninLee
     * @date 2020/01/04
     **/
    int save(User user);

    /**
     * [刪除]
     *
     * @author RoninLee
     * @date 2020/01/04
     **/
    int delete(String id);

    /**
     * [更新]
     *
     * @author RoninLee
     * @date 2020/01/04
     **/
    int update(User user);

    /**
     * [查询] 根据主键 id 查询
     *
     * @author RoninLee
     * @date 2020/01/04
     **/
    User info(String id);

    /**
     * [查询] 分页查询
     *
     * @author RoninLee
     * @date 2020/01/04
     **/
    List<UserResp> pageList(@Param("fuzzyName") String fuzzyName, @Param("pageIndex") int pageIndex, @Param("pageSize") int pageSize);

    /**
     * [查询] 分页查询 count
     *
     * @author RoninLee
     * @date 2020/01/04
     **/
    Long pageListCount(@Param("fuzzyName") String fuzzyName);

    /**
     * 用户下拉列表
     *
     * @param name 模糊查询
     * @return 用户下拉列表
     */
    List<UserDropdownListResp> dropdownList(@Param("name") String name);

}
