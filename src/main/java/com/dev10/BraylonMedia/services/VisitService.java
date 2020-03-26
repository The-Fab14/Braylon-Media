package com.dev10.BraylonMedia.services;

import com.dev10.BraylonMedia.entities.Visit;
import java.util.List;

public interface VisitService {
    
    Visit addVisit(Visit visit);
    Visit getVisit(int id);
    List<Visit> getAllVisits();
    List<Visit> getVisitsByClientId(int id);
    List<Visit> getVisitsByUserId(int id);
    List<Visit> getVisitsByStateId(String id);
    Visit editVisit(Visit editedVisit);
    List<Visit> findAllByClientAndUser(int clientId, int userId);
}