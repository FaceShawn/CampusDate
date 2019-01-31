package com.campus.service.impl;

import org.springframework.stereotype.Service;

import com.campus.model.Section;
import com.campus.repository.SectionRepository;
import com.campus.service.SectionService;

import javax.annotation.Resource;
import java.util.List;

/**
 * 
 */
@Service("sectionService")
public class SectionServiceImpl implements SectionService {

    @Resource
    private SectionRepository sectionRepository;

    @Override
    public Section findBySectionCode(String sectionCode) {
        return sectionRepository.findBySectionCode(sectionCode);
    }
    
    @Override
    public List<Section> findAll() {
        return sectionRepository.findAll();
    }

}
