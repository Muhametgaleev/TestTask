package org.example.services;

import org.example.entities.Source;

import java.util.HashMap;
import java.util.Map;


public class TimeService {
    Map<String, Long> offsets = new HashMap<>();

    public void addOffset(Source source, Long offset) {
        offsets.put(source.getName(), offset);
    }

    public Long getOffset(Source source) {
            return offsets.get(source.getName());
    }

}
