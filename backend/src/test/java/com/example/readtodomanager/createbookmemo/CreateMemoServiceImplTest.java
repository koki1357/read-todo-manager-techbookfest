package com.example.readtodomanager.createbookmemo;

import static org.junit.Assert.assertEquals;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.jdbc.Sql;

import com.example.readtodomanager.model.Memo;
import com.example.readtodomanager.repository.MemoRepository;
import com.example.readtodomanager.service.CreateMemoService;
import com.example.readtodomanager.service.dto.CreateMemoServiceInDto;

// 時間の都合上、一部のテストのみ実装
@SpringBootTest
@ActiveProfiles("test")
public class CreateMemoServiceImplTest {

    @Autowired
    private CreateMemoService createMemoService;

    @Autowired
    private MemoRepository memoRepository;

    // 正常系
    // 1. テスト対象メソッド: createMemo
    // 2. 入力値: CreateMemoServiceInDto(id=1, userId="user1", memo="登録")
    // 3. 期待値: Book(id=1, userId=1, memo=メモ更新)が取得できること
    @Test
    @Sql(scripts = { "/createbookmemo/schema.sql", "/createbookmemo/data.sql" })
    public void 正常系_メモ登録() {
        // Arrange
        CreateMemoServiceInDto input = new CreateMemoServiceInDto();
        input.setBookId(1L);
        input.setUserId("user1");
        input.setContent("登録");

        // Act
        createMemoService.createMemo(input);

        // Assert
        Memo createdBook = memoRepository.getMemo(4L);
        assertEquals(input.getBookId(), createdBook.getBookId());
        assertEquals(input.getContent(), createdBook.getContent());
        assertEquals(input.getUserId(), createdBook.getUserId());
    }
}
