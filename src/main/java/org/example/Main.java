package org.example;


import org.example.entities.Source;
import org.example.entities.Ticket;
import org.example.services.SourceService;
import org.example.services.TicketParser;
import org.example.services.TicketService;
import org.example.services.TimeService;

import java.time.Duration;
import java.util.List;
import java.util.Map;

public class Main {
    public static void main(String[] args) {
        Map<String, Duration> durations;
        TicketParser ticketParser = new TicketParser();
        TimeService timeService = new TimeService();
        SourceService sourceService = new SourceService();
        TicketService ticketService = new TicketService();
        //добавляем аэропорты и разницу времени по Гринвичу
        timeService.addOffset(new Source("TLV", "Тель-Авив"), 3L);
        timeService.addOffset(new Source("VVO", "Владивосток"), 10L);
        timeService.addOffset(new Source("UFA", "Уфа"), 5L);
        timeService.addOffset(new Source("LRN", "Ларнака"), 3L);
        //добавляем города и аэропорта в "базу"
        sourceService.addSources("TLV", "Тель-Авив");
        sourceService.addSources("VVO", "Владивосток");
        sourceService.addSources("UFA", "Уфа");
        sourceService.addSources("LRN", "Ларнака");


        String filename = "src\\main\\resources\\tickets.json";
        List<Ticket> tickets = ticketParser.getTicketsFromJson(filename);
        // проверили, что данные в json корректны
        sourceService.existSource(tickets);
        durations = ticketService.minTime(tickets, timeService,"TLV", "VVO");

        durations.forEach((key, value) -> System.out.println(key + " " + value.toDays()/365 + " лет " + value.toDays()%365 +
                " дней " + value.toHours()%24 + " часов " + value.toMinutes()%60 + " минут " + value.toSeconds()%60 + " секунд"));

        // задание Б
        System.out.println(ticketService.difference(tickets, "TLV", "VVO") + " разница между средней ценой  и медианой для полета между городами  Владивосток и Тель-Авив");
    }
}
