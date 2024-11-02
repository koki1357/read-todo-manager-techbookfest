package com.example.readtodomanager.service;

import java.util.List;
import com.example.readtodomanager.model.Memo;
import com.example.readtodomanager.service.dto.GetMemoServiceInDto;

public interface GetMemoService {
    List<Memo> getMemoList(GetMemoServiceInDto getMemoServiceInDto);
}