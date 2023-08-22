package org.example.services;

import org.example.dto.Source;
import org.example.exceptions.FooException;

import java.time.temporal.TemporalAmount;
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
