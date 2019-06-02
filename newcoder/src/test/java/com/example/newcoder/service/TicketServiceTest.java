package com.example.newcoder.service;

import com.example.newcoder.model.Ticket;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static org.junit.Assert.*;

public class TicketServiceTest {

    @Autowired
    TicketService ticketService;
    @Test
    public void searchTicketByticket() {
        Ticket ticket=ticketService.searchTicketByticket("21445a38-f60e-4687-992c-128a0bc9b277");
        assertNotNull(ticket);
    }
}