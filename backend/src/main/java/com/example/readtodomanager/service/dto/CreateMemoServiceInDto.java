package com.example.readtodomanager.service.dto;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import lombok.Data;

@Data
public class CreateMemoServiceInDto  {
    @NotNull
    private Long bookId;
    @Size(min = 1, max = 300)
    private String Content;
 
    private String userId;
}
