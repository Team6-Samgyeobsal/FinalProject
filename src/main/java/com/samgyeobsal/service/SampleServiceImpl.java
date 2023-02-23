package com.samgyeobsal.service;

import com.samgyeobsal.domain.sample.SampleVO;
import com.samgyeobsal.mapper.SampleMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class SampleServiceImpl implements SampleService{

    private final SampleMapper sampleMapper;

    @Override
    public SampleVO getSample(){
        return sampleMapper.getSample();
    }
}
