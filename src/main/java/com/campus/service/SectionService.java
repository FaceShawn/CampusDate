package com.campus.service;

import java.util.List;

import com.campus.model.Section;

/**
 * 
 */
public interface SectionService {

    Section findBySectionCode(String sectionCode);
    
    List<Section> findAll();
}
