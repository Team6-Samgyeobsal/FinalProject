package com.samgyeobsal.service;

import com.samgyeobsal.domain.fame.FameCriteria;
import com.samgyeobsal.domain.fame.FameVO;

import java.util.List;

public interface FameService {

    List<FameVO> getFameList(FameCriteria criteria);
}
