package org.example.services;

import org.example.dto.Ticket;
import org.example.exceptions.FooException;

import java.time.Duration;
import java.util.*;

public class TicketService implements TicketServiceInterface{
    @Override
    public Map<String, Duration> minTime(List<Ticket> tickets, TimeService timeService, String nameDestination, String nameOrigin) {
        Map<String, Duration> minTimeMap = new HashMap<>();


        Duration duration;
        List<Ticket> ticktsByDepartureAndOrigin = getTicketsByDepartureAndOrigin(nameDestination, nameOrigin, tickets);
        // массив уже нужных билетов
        for (Ticket ticket: ticktsByDepartureAndOrigin){
            // разница во времени с учетом часовых зон
            duration = Duration.between(ticket.getDepartureDateTime().minusHours(timeService.getOffset(ticket.getOrigin())), ticket.getArrivalDateTime().minusHours(timeService.getOffset(ticket.getDestination())));
            //добавление в массив минимального времени
            if(minTimeMap.get(ticket.getCarrier())==null){
                minTimeMap.put(ticket.getCarrier(), duration);
            }
            else if(minTimeMap.get(ticket.getCarrier()).compareTo(duration) > 0){
                minTimeMap.put(ticket.getCarrier(), duration);
            }


        }
        return minTimeMap;
    }

    @Override
    public float difference(List<Ticket> tickets, String nameDestination, String nameOrigin) {
        List<Ticket> ticktsByDepartureAndOrigin = getTicketsByDepartureAndOrigin(nameDestination, nameOrigin, tickets);
        if(!ticktsByDepartureAndOrigin.isEmpty()) {
            int averagePrice = 0;
            int medianPrice;
            int numberOfTickets = 0;

            Collections.sort(ticktsByDepartureAndOrigin);

            for (Ticket ticket : ticktsByDepartureAndOrigin) {
                averagePrice += ticket.getPrice();
                numberOfTickets += 1;

            }
            averagePrice /= numberOfTickets;
            if (numberOfTickets % 2 == 0) {
                medianPrice = (ticktsByDepartureAndOrigin.get(numberOfTickets / 2).getPrice() + ticktsByDepartureAndOrigin.get(numberOfTickets / 2 - 1).getPrice()) / 2;
            } else {
                medianPrice = ticktsByDepartureAndOrigin.get(numberOfTickets / 2).getPrice();
            }
            return averagePrice - medianPrice;
        } else {
            System.out.println("Таких билетов нет");
            return 0;
        }
    }

    // возвращает массив билетов от nameOrigin до nameDestination
    public List<Ticket> getTicketsByDepartureAndOrigin(String nameDestination, String nameOrigin, List<Ticket> tickets) {
        List<Ticket> ticktsByDepartureAndOrigin = new ArrayList<>();
        for (Ticket ticket: tickets){
            if (ticket.getDestination().getName().equals(nameDestination) & ticket.getOrigin().getName().equals(nameOrigin)) {
                ticktsByDepartureAndOrigin.add(ticket);
            }
        }
        return ticktsByDepartureAndOrigin;
    }
}

