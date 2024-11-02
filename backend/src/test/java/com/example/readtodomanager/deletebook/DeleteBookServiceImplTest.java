package com.example.readtodomanager.deletebook;

import static org.junit.Assert.assertNull;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.jdbc.Sql;

import com.example.readtodomanager.common.ApiResponse;
import com.example.readtodomanager.model.Book;
import com.example.readtodomanager.repository.BookRepository;
import com.example.readtodomanager.service.DeleteBookService;
import com.example.readtodomanager.service.dto.DeleteBookServiceInDto;

@SpringBootTest
@ActiveProfiles("test")
public class DeleteBookServiceImplTest {
    @Autowired
    private DeleteBookService deleteBookService;

    @Autowired
    private BookRepository searchBookRepository;

    /**
     * 正常系
     */
    @Test
    @Sql(scripts = { "/deletebook/schema.sql", "/deletebook/data.sql" })
    public void testDeleteBook_Success() {
        // Arrange
        DeleteBookServiceInDto dto = new DeleteBookServiceInDto();
        dto.setId(1L);

        // Act
        ResponseEntity<ApiResponse<String>> response = deleteBookService.deleteBook(dto);

        // Assert
        assertEquals(200, response.getBody().getStatusCode());

        Book deletedBook = searchBookRepository.findById(1L);
        assertNull(deletedBook);

    }

    /**
     * 異常系
     * 存在しないID（id = 999）を指定した場合、空振りとなることを確認する
     */
    @Test
    @Sql(scripts = { "/deletebook/schema.sql", "/deletebook/data.sql" })
    public void testDeleteBook_NotFound() {
        // Arrange
        DeleteBookServiceInDto dto = new DeleteBookServiceInDto();
        dto.setId(999L);

        List<Book> defaultBooks = searchBookRepository.findByUserId("user");

        // Act
        ResponseEntity<ApiResponse<String>> response = deleteBookService.deleteBook(dto);

        // Assert
        assertEquals(400, response.getBody().getStatusCode());

        // Assert
        // 件数が変わっていないことの確認
        List<Book> books = searchBookRepository.findByUserId("user");
        assertEquals(defaultBooks.size(), books.size());
    }
}
