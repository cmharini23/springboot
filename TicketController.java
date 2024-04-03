package com.java.ticket.Controller;

import com.java.ticket.Model.Ticket;
import com.java.ticket.Service.TicketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class TicketController {

    @Autowired
    private TicketService ticketService;

    @PostMapping("/ticket")
    public ResponseEntity<Ticket> add(@RequestBody Ticket ticket) {
        Ticket newTicket = ticketService.createTicket(ticket);
        if (newTicket != null) {
            return new ResponseEntity<>(newTicket, HttpStatus.CREATED);
        } else {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/tickets")
    public ResponseEntity<List<Ticket>> getAllTickets() {
        List<Ticket> tickets = ticketService.getAllTickets();
        if (!tickets.isEmpty()) {
            return new ResponseEntity<>(tickets, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    
    @PutMapping("/ticket/{ticketId}")
    public ResponseEntity<Ticket> updateTicket(@PathVariable int ticketId, @RequestBody Ticket ticket) {
        boolean updated = ticketService.updateTicket(ticketId, ticket);
        if (updated) {
            return new ResponseEntity<>(ticket, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/ticket/{ticketId}")
    public ResponseEntity<Boolean> deleteTicket(@PathVariable int ticketId) {
        boolean deleted = ticketService.deleteTicket(ticketId);
        if (deleted) {
            return new ResponseEntity<>(true, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(false, HttpStatus.NOT_FOUND);
        }
    }
}