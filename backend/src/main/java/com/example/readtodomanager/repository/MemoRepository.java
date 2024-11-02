package com.example.readtodomanager.repository;

import java.util.List;
import com.example.readtodomanager.model.Memo;

public interface MemoRepository {
    /**
     * メモを取得する
     * 
     * @param bookId
     * @return
     */
    public Memo getMemo(Long bookId);

    /**
     * メモリストを取得する
     * 
     * @param bookId
     * @return
     */
    public List<Memo> getMemoList(Long bookId, String userId);

    /**
     * メモを登録する
     * 
     * @param memo
     */
    public void insert(Memo memo);

    /**
     * 本を更新する
     * 
     * @param book
     */
    public void update(Memo memo);

    /**
     * 本を削除する
     * 
     * @param id
     */
    public void delete(Long id);
}
