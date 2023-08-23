package org.example.services;


import org.example.entities.Ticket;
import org.example.exceptions.FooException;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SourceService {
    Map<String , String> sources = new HashMap<>();

    public void addSources(String name, String fullname){
        sources.put(name, fullname);
    }

    public void existSource(List<Ticket> tickets) throws FooException {
        for(Ticket ticket: tickets){
            if(sources.get(ticket.getOrigin().getName()).equals(ticket.getOrigin().getFullName())){
                continue;
            }else{
                throw new FooException("данные json неверные");
            }
        }
    }
 }
