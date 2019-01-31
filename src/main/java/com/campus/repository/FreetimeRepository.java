package com.campus.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.campus.model.Freetime;
import com.campus.model.User;

public interface FreetimeRepository extends JpaRepository<Freetime, Long> {

	@Query("select ft from Freetime ft where ft.id = ?1") 
	Freetime findById(String id);
	
	List<Freetime> findAllByOwnerId(String ownerId);
	
	@Query("select ft from Freetime ft where ft.freeDate = ?1 and ft.sectionCode = ?2")
	List<Freetime> findByFreeDateAndSectionCode(String freeDate, String sectionCode);
	
	@Query("select ft from Freetime ft where ft.reserverId = ?1") 
	List<Freetime> findAllByReserveId(String reserverId);
	
//	User findByEmail(String email);
	
}
