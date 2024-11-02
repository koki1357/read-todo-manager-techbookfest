package com.example.readtodomanager.service.dto;

import java.time.LocalDate;
import javax.validation.constraints.NotNull;
import lombok.Data;

@Data
public class RegisterBookServiceInDto {
    @NotNull
    String title;

    Long id;
    String userId;
    String image;
    String author;
    LocalDate startDate;
    LocalDate finishDate;
    String daysPerWeek;
}