package com.java.ticket.Service;

import com.java.ticket.Model.Ticket;
import com.java.ticket.Repository.TicketRepo;

import lombok.NonNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class TicketService {

    @Autowired
    private TicketRepo ticketRepo;

    public Ticket createTicket(@NonNull Ticket ticket) {
        return ticketRepo.save(ticket);
    }

    public List<Ticket> getAllTickets() {
        return ticketRepo.findAll();
    }

    public Ticket getTicketById(@NonNull Integer id) {
        return ticketRepo.findById(id).orElse(null);
    }

    public boolean updateTicket(int id, Ticket ticket) {
        if (getTicketById(id) == null) {
            return false;
        }
        try {
            ticketRepo.save(ticket);
        } catch (Exception e) {
            return false;
        }
        return true;
    }

    public boolean deleteTicket(int id) {
        if (getTicketById(id) == null) {
            return false;
        }
        ticketRepo.deleteById(id);
        return true;
    }
}