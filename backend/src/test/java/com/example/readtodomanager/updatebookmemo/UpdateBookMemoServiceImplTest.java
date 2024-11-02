package com.example.readtodomanager.updatebookmemo;

import static org.junit.Assert.assertEquals;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.jdbc.Sql;

import com.example.readtodomanager.common.ApiResponse;
import com.example.readtodomanager.model.Memo;
import com.example.readtodomanager.repository.MemoRepository;
import com.example.readtodomanager.service.UpdateMemoService;
import com.example.readtodomanager.service.dto.UpdateMemoServiceInDto;

@SpringBootTest
@ActiveProfiles("test")
public class UpdateBookMemoServiceImplTest {

    @Autowired
    private UpdateMemoService updateBookMemoService;

    @Autowired
    private MemoRepository memoRepository;

    // 正常系
    // 1. テスト対象メソッド: updateBookMemo
    // 2. 入力値: UpdateBookMemoServiceInDto(id=1, userId="user1", memo="メモ更新")
    // 3. 期待値: Book(id=1, memo=メモ更新)が取得できること
    @Test
    @Sql(scripts = { "/updatebookmemo/schema.sql", "/updatebookmemo/data.sql" })
    public void 正常系_メモ更新() {
        // Arrange
        UpdateMemoServiceInDto input = new UpdateMemoServiceInDto();
        input.setId(1L);
        input.setUserId("user1");
        input.setContent("メモ更新");

        // Act
        updateBookMemoService.updateBookMemo(input);

        // Assert
        Memo updatedBook = memoRepository.getMemo(input.getId());
        assertEquals(input.getContent(), updatedBook.getContent());

    }

    // 正常系 memoがnull
    // 1. テスト対象メソッド: updateBookMemo
    // 2. 入力値: UpdateBookMemoServiceInDto(id=1, request="user1", memo=null)
    // 3. 期待値: Book(id=1, memo=null)が取得できること
    @Test
    @Sql(scripts = { "/updatebookmemo/schema.sql", "/updatebookmemo/data.sql" })
    public void 正常系_メモ更新_メモがnull() {
        // Arrange
        UpdateMemoServiceInDto input = new UpdateMemoServiceInDto();
        input.setId(null);
        input.setUserId("user1");
        input.setContent(null);

        // Act
        updateBookMemoService.updateBookMemo(input);

        // Assert
        Memo updatedBook = memoRepository.getMemo(input.getId());
        assertEquals(input.getContent(), updatedBook.getContent());

    }

    // 異常系 idがnull
    // 1. テスト対象メソッド: updateBookMemo
    // 2. 入力値: UpdateBookMemoServiceInDto(id=null, request="user1", memo="メモ更新")
    // 3. 期待値: BadRequestExceptionが発生すること
    @Test
    @Sql(scripts = { "/updatebookmemo/schema.sql", "/updatebookmemo/data.sql" })
    public void 異常系_idがnull() {
        // Arrange
        UpdateMemoServiceInDto input = new UpdateMemoServiceInDto();
        input.setId(null);
        input.setUserId("user1");
        input.setContent("メモ更新");

        // Act
        ResponseEntity<ApiResponse<Long>> actual = updateBookMemoService.updateBookMemo(input);

        // Assert
        assertEquals(actual.getBody().getStatusCode(), 400);
    }

    // 異常系: memoが空文字
    // 1. テスト対象メソッド: updateBookMemo
    // 2. 入力値: UpdateBookMemoServiceInDto(id=1, request="user1", memo="")
    // 3. 期待値: BadRequestExceptionが発生すること

    // 異常系: idがDBに存在しない
    // 1. テスト対象メソッド: updateBookMemo
    // 2. 入力値: UpdateBookMemoServiceInDto(id=999, request="user1", memo="メモ更新")
    // 3. 期待値: BadRequestExceptionが発生すること

    // 異常系: リクエストのuserIdとDBのuserIdが異なる
    // 1. テスト対象メソッド: updateBookMemo
    // 2. 入力値: UpdateBookMemoServiceInDto(id=1, request="hoge", memo="メモ更新")
    // 3. 期待値: BadRequestExceptionが発生すること
    @Test
    @Sql(scripts = { "/updatebookmemo/schema.sql", "/updatebookmemo/data.sql" })
    public void 異常系_リクエストのuserIdとDBのuserIdが異なる() {
        // Arrange
        UpdateMemoServiceInDto input = new UpdateMemoServiceInDto();
        input.setId(1L);
        input.setUserId("hoge");
        input.setContent("メモ更新");

        // Act
        ResponseEntity<ApiResponse<Long>> actual = updateBookMemoService.updateBookMemo(input);

        // Assert
        assertEquals(actual.getBody().getStatusCode(), 400);
    }

}
