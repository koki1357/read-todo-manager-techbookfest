package com.example.readtodomanager.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Book {
    private Long id;
    private String userId;
    private String title;
    private String image;
    private String author;
    private LocalDate startDate;
    private LocalDate finishDate;
    private String daysPerWeek;
}
