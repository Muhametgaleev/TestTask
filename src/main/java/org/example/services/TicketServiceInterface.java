package org.example.services;

import org.example.entities.Ticket;

import java.time.Duration;
import java.util.List;
import java.util.Map;

public interface TicketServiceInterface {
    // стринга и разница во времени
    Map<String, Duration> minTime(List<Ticket> tickets, TimeService timeService, String nameDestination, String nameOrigin);
    float difference(List<Ticket> tickets, String nameDestination, String nameOrigin);
}
