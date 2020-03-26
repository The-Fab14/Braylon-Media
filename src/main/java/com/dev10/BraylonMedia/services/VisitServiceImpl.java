package com.dev10.BraylonMedia.services;

import com.dev10.BraylonMedia.entities.Visit;
import com.dev10.BraylonMedia.repositories.VisitRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class VisitServiceImpl implements VisitService {
    
    @Autowired
    VisitRepository visitRepo;

    @Override
    public Visit addVisit(Visit visit) {
        return visitRepo.save(visit);
    }

    @Override
    public Visit getVisit(int id) {
        return visitRepo.findById(id).orElse(null);
    }

    @Override
    public List<Visit> getAllVisits() {
        return visitRepo.findAll();
    }
    
    @Override
    public List<Visit> getVisitsByClientId(int id) 
    {
        return visitRepo.findAllByClientId(id);
    }

    @Override
    public List<Visit> getVisitsByUserId(int id) {
        return visitRepo.getVisitsByUserId(id);
    }

    @Override
    public List<Visit> getVisitsByStateId(String id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Visit editVisit(Visit editedVisit) {
        return visitRepo.save(editedVisit);
    }
    
    @Override
    public List<Visit> findAllByClientAndUser(int clientId, int userId)
    {
        return visitRepo.findAllByClientAndUser(clientId, userId);
    }
}