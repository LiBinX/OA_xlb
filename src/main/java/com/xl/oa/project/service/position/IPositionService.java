package com.xl.oa.project.service.position;

import com.xl.oa.project.po.Position;

import java.util.List;

/**
 * @author 毕业设计
 * @date 2022/4/23 16:01
 * @描述
 */
public interface IPositionService {
    /**
     *
     * @描述 根据主键删除
     *
     * @date 2022/4/23 16:01
     */
    int deleteByPrimarysKey(Integer[] positionId);


    /**
     *
     * @描述 插入
     *
     * @date 2022/4/23 16:01
     */
    int insertSelective(Position record);

    /**
     *
     * @描述  根据主键查询
     *
     * @date 2022/4/23 16:01
     */
    Position selectByPrimaryKey(Integer positionId);

    /**
     *
     * @描述 字段不为空更新
     *
     * @date 2022/4/23 18:01
     */
    int updateByPrimaryKeySelective(Position record);


    /**
     *
     * @描述 根据对象所有字段查询
     *
     * @date 2022/4/23 18:01
     */
    List<Position> selectPositionList(Position position);


    /**
     *
     * @描述 校验名称是否唯一
     *
     * @date 2022/4/23 18:01
     */
    String checkPositionNameUnique(Position position);
}
