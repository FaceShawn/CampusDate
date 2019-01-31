package com.campus.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.campus.model.Freetime;
import com.campus.repository.FreetimeRepository;
import com.campus.service.FreetimeService;


/**
 * 
 */
@Service("freetimeService")
public class FreetimeServiceImpl implements FreetimeService {

    @Resource
    private FreetimeRepository freetimeRepository;

    @Override
    public void insert(Freetime freetime) {
    	freetimeRepository.save(freetime);
    }

    @Override
    public void update(Freetime freetime) {
    	freetimeRepository.save(freetime);
    }

    @Override
    public void delete(String id) {
    	Freetime freetime = freetimeRepository.findById(id);
        if (freetime != null){
        	freetimeRepository.delete(freetime);
        }
    }

    @Override
    public Freetime findById(String id) {
        return freetimeRepository.findById(id);
    }

    @Override
    public List<Freetime> findAll() {
        return freetimeRepository.findAll();
    }
    
    @Override
    public List<Freetime> findAllByOwnerId(String ownerId) {
    	return freetimeRepository.findAllByOwnerId(ownerId);
    }
    
    @Override
    public List<Freetime> findAllByReserveId(String reserverId) {
    	return freetimeRepository.findAllByReserveId(reserverId);
    }
    
    @Override
    public List<Freetime> findByFreeDateAndSectionCode(String freeDate, String sectionCode) {
    	return freetimeRepository.findByFreeDateAndSectionCode(freeDate, sectionCode);
    }
}
