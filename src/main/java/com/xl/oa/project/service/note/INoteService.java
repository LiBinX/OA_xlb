package com.xl.oa.project.service.note;

import com.xl.oa.project.po.Note;

import java.util.List;

/**
 * @author 毕业设计
 */
public interface INoteService{
    int deleteByPrimaryKeys(Integer[] id);

    int insertSelective(Note record);

    Note selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Note record);

    List<Note> selectNoteList(Note note);
}
