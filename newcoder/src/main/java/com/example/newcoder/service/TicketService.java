package com.example.newcoder.service;

import com.example.newcoder.dao.TicketDao;
import com.example.newcoder.dao.UserDao;
import com.example.newcoder.model.Ticket;
import com.example.newcoder.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import java.util.Date;
import java.util.UUID;

@Service
public class TicketService {
    @Autowired
    TicketDao ticketDao;

    public String addTicketForUser(User user)
    {
        Ticket ticket=new Ticket();
        ticket.setUserid(user.getId());
        ticket.setStatus(1);
        Date date=new Date();
        date.setTime(date.getTime()+5*60*60*1000);
        ticket.setExpire(date);
        String t= UUID.randomUUID().toString();
        ticket.setTicket(t);

        ticketDao.insertTicket(ticket);

        return t;
    }
    public Ticket searchTicketByticket(String ticket)
    {
        return ticketDao.selectTicketbyTicket(ticket);
    }
    public boolean Logoff(String t, HttpServletResponse response)
    {//下线功能
        try {
            Ticket ticket = new Ticket();
            ticket.setTicket(t);
            ticket.setStatus(0);
            ticket.setExpire(new Date());
            ticket.setUserid(ticketDao.selectTicketbyTicket(t).getUserid());
            ticketDao.UpdateTicket(ticket);

            response.addCookie(new Cookie("ticket"," "));
            return true;
        }catch (Exception e)
        {
            e.printStackTrace();
            return false;
        }
    }
}
