package com.example.readtodomanager.service;

import com.example.readtodomanager.service.dto.GetMemoServiceInDto;
import com.example.readtodomanager.model.Memo;
import com.example.readtodomanager.repository.MemoRepository;

import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class GetMemoServiceImpl implements GetMemoService {

    private final MemoRepository memoRepository;

    public GetMemoServiceImpl(MemoRepository memoRepository) {
        this.memoRepository = memoRepository;
    }

    @Override
    public List<Memo> getMemoList(GetMemoServiceInDto getMemoServiceInDto) {
        Long bookId = getMemoServiceInDto.getBookId();
        String userId = getMemoServiceInDto.getUserId();
        return memoRepository.getMemoList(bookId, userId);
    }
}