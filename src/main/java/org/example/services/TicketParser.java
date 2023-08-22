package org.example.services;

import org.example.dto.Ticket;
import org.example.dto.Source;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;


public class TicketParser {
    public static List<Ticket> getTicketsFromJson(String filename) {
        JSONParser parser = new JSONParser();
        List<Ticket> tickets = new ArrayList<>();

        try  {
            Reader reader = new FileReader(filename);
            StringBuilder buffer = new StringBuilder();
            int c;
            boolean fl = false;
            while ((c = reader.read()) != -1) {
                if (fl) {
                    buffer.append((char) c);
                } else if (c == '{') {
                    fl = true;
                    buffer.append((char) c);
                }
            }
            JSONObject jsonObject = (JSONObject) parser.parse(String.valueOf(buffer));
            JSONArray jsonTickets = (JSONArray) jsonObject.get("tickets");

            for (Object o : jsonTickets) {
                JSONObject jsonTicket = (JSONObject) o;
                Ticket ticket = new Ticket();
                Source sourceOrigin = new Source(jsonTicket.get("origin").toString(), jsonTicket.get("origin_name").toString());
                Source sourceDestination = new Source(jsonTicket.get("destination").toString(), jsonTicket.get("destination_name").toString());

                ticket.setOrigin(sourceOrigin);
                ticket.setDestination(sourceDestination);

                ticket.setDepartureDateTime(
                        LocalDateTime.parse(
                                jsonTicket.get("departure_date") + " " + jsonTicket.get("departure_time"), DateTimeFormatter.ofPattern("d.M.y H:m")
                        ).plusYears(2000)
                );
                ticket.setArrivalDateTime(
                        LocalDateTime.parse(
                                jsonTicket.get("arrival_date") + " " + jsonTicket.get("arrival_time"), DateTimeFormatter.ofPattern("d.M.y H:m")
                        ).plusYears(2000)
                );
                ticket.setCarrier(jsonTicket.get("carrier").toString());
                ticket.setStops(Integer.parseInt(jsonTicket.get("stops").toString()));
                ticket.setPrice(Integer.parseInt(jsonTicket.get("price").toString()));
                tickets.add(ticket);

            }
            return tickets;
        } catch (ParseException | IOException parseException) {
            parseException.printStackTrace();
        }

        return null;
    }
}
