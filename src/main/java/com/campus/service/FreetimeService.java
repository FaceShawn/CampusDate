package com.campus.service;

import java.util.List;

import com.campus.model.Freetime;

public interface FreetimeService {


	    void insert(Freetime freetime);

	    void update(Freetime freetime);

	    void delete(String id);

	    Freetime findById(String id);
	    
	    List<Freetime> findAll();
	    
	    List<Freetime> findAllByOwnerId(String ownerId);
	    
	    List<Freetime> findAllByReserveId(String ownerId);
	    
	    List<Freetime> findByFreeDateAndSectionCode(String freeDate, String sectionCode);
	    
}
