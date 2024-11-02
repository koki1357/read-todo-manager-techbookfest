package com.example.readtodomanager.deletememo;


import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.jdbc.Sql;

import com.example.readtodomanager.common.ApiResponse;
import com.example.readtodomanager.model.Memo;
import com.example.readtodomanager.repository.MemoRepository;
import com.example.readtodomanager.service.DeleteMemoService;
import com.example.readtodomanager.service.dto.DeleteMemoServiceInDto;

// 時間の都合上、一部のテストのみ実装
@SpringBootTest
@ActiveProfiles("test")
public class DeleteMemoServiceImplTest {

    @Autowired
    private DeleteMemoService deleteMemoService;

    @Autowired
    private MemoRepository memoRepository;

    // 正常系
    // 1. テスト対象メソッド: deleteMemo
    // 2. 入力値: DeleteMemoServiceInDto(id=1, userId="user1")
    // 3. 期待値: Book(id=1)が物理削除できること
    @Test
    @Sql(scripts = { "/deletememo/schema.sql", "/deletememo/data.sql" })
    public void 正常系_メモ削除() {
        // Arrange
        DeleteMemoServiceInDto input = new DeleteMemoServiceInDto();
        input.setId(1L);
        input.setUserId("user1");

        // Act
        ResponseEntity<ApiResponse<Long>> actual = deleteMemoService.deleteMemo(input);

        // Assert
        // 正常レスポンスが返却されることを確認する
        assertEquals(200, actual.getBody().getStatusCode());

        // id=1のメモが削除されていることを確認する
        Memo deletedMemo = memoRepository.getMemo(input.getId());
        assertNull(deletedMemo);

    }
}
