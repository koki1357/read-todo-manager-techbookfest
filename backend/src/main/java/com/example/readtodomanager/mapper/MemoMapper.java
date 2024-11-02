package com.example.readtodomanager.mapper;

import org.apache.ibatis.annotations.Mapper;

import com.example.readtodomanager.model.Memo;
import java.util.List;

@Mapper
public interface MemoMapper {
    Memo getMemo(Long bookId);

    List<Memo> getMemoList(Long bookId,String userId);

    void update(Memo memo);

    void insert(Memo memo);

    void delete(Long id);
}
