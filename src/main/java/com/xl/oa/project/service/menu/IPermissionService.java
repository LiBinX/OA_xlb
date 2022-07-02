package com.xl.oa.project.service.menu;

import com.xl.oa.project.po.Permission;
import com.xl.oa.project.po.dto.MenuTree;
import com.xl.oa.project.po.dto.PermTree;

import java.util.List;

/**
 * @author 毕业设计
 * @date 2022/4/23 16:01
 * @描述
 */
public interface IPermissionService {

    int deleteByPrimaryKeys(Integer[] permissionId) throws Exception;

    int insertSelective(Permission record);

    Permission selectByPrimaryKey(Integer permissionId);

    int updateByPrimaryKeySelective(Permission record);

    String checkMenuNameUnique(Permission permission);

    /**
     *
     * @描述 返回集合
     *
     * @date 2022/4/23 16:01
     */
    List<Permission> selectPersissionList(Permission record);

    List<MenuTree> selectMenuTree(String uid);

    /**
     *  查询权限结构
     */
    List<PermTree> selectPermTree();
}
