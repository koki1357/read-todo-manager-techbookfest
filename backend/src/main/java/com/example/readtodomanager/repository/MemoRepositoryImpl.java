package com.example.readtodomanager.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.example.readtodomanager.mapper.MemoMapper;
import com.example.readtodomanager.model.Memo;
import java.util.List;


@Repository
public class MemoRepositoryImpl implements MemoRepository {

    @Autowired
    private MemoMapper memoMapper;

    public Memo getMemo(Long bookId) {
        return memoMapper.getMemo(bookId);
    }

    public List<Memo> getMemoList(Long bookId, String userId) {
        return memoMapper.getMemoList(bookId, userId);
    }

    @Override
    public void update(Memo memo) {
        memoMapper.update(memo);
    }

    @Override
    public void insert(Memo memo) {
        memoMapper.insert(memo);
    }

    @Override
    public void delete(Long id) {
        memoMapper.delete(id);
    }

}
