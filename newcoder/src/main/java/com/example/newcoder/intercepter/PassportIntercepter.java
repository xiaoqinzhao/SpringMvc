package com.example.newcoder.intercepter;

import com.example.newcoder.dao.TicketDao;
import com.example.newcoder.dao.UserDao;
import com.example.newcoder.model.HostHolder;
import com.example.newcoder.model.Ticket;
import com.example.newcoder.model.User;
import com.example.newcoder.service.TicketService;
import com.example.newcoder.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.util.Date;

@Component
public class PassportIntercepter implements HandlerInterceptor {

    @Autowired
    HostHolder hostHolder;

    @Autowired
    TicketService ticketService;

    @Autowired
    UserService userService;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object o) throws Exception {
       String ticket=null;
        if(request.getCookies()!=null)
        {
            for(Cookie cookie :request.getCookies())
            {
                if(cookie.getName().equals("ticket"))
                {
                    ticket=cookie.getValue();
                    Ticket ticketEntity =ticketService.searchTicketByticket(ticket);
                    if(ticketEntity!=null) {
                        if (ticketEntity.getStatus() == 1 && new Date().before(ticketEntity.getExpire())) {
                            User user = userService.selectbyid(ticketEntity.getUserid());
                            hostHolder.setUser(user);
                        }
                    }
                    break;
                }
            }
        }
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, Exception e) throws Exception {

    }
}
