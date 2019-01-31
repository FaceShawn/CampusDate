package com.campus.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.campus.model.Section;

public interface SectionRepository extends JpaRepository<Section, String> {

	Section findBySectionCode(String sectionCode);
	
}


