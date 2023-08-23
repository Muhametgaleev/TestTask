package org.example.services;


import org.example.entities.Ticket;
import org.example.exceptions.SourceServiceException;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SourceService {
    Map<String , String> sources = new HashMap<>();

    public void addSources(String name, String fullname){
        sources.put(name, fullname);
    }

    public void existSource(List<Ticket> tickets) throws RuntimeException {
        for(Ticket ticket: tickets){
            if(sources.get(ticket.getOrigin().getName()).equals(ticket.getOrigin().getFullName()) & sources.get(ticket.getDestination().getName()).equals(ticket.getDestination().getFullName())){
                continue;
            }else{
                SourceServiceException.invalidSource(ticket.getOrigin().getName(), ticket.getDestination().getName(), ticket.getOrigin().getFullName(), ticket.getDestination().getFullName());
            }
        }
    }
 }
