package com.example.readtodomanager.service.dto;

import javax.validation.constraints.NotNull;

import lombok.Data;

@Data
public class DeleteMemoServiceInDto {
    @NotNull
    private Long id;
    
    private String userId;
}
