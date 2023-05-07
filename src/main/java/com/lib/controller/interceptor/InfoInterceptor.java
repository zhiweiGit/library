package com.lib.controller.interceptor;

import com.lib.dao.TicketMapper;
import com.lib.pojo.*;
import com.lib.service.AdminService;
import com.lib.service.SysAdminService;
import com.lib.service.UserService;
import com.lib.util.HostHolder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Date;

@Component
public class InfoInterceptor implements HandlerInterceptor {


    @Autowired
    private HostHolder hostHolder;

    @Autowired
    private TicketMapper ticketMapper;
    @Autowired
    private UserService userService;

    @Autowired
    private AdminService adminService;
    @Autowired
    private SysAdminService sysAdminService;


    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        Cookie[] cookies = request.getCookies();
        String ticket = null;
        if (cookies != null) {
            for (Cookie cookie : cookies) {
                if ("ticket".equals(cookie.getName())) {
                    ticket = cookie.getValue();
                }
            }
        }

        if (ticket != null) {
            LoginTicket loginTicket = ticketMapper.selectTicket(ticket);
            if (loginTicket != null && loginTicket.getStatus() == 0 && loginTicket.getExpired().after(new Date())) {
                switch (loginTicket.getUserType()) {
                    case 1:
                        User user = userService.findUserById(loginTicket.getUserId());
                        hostHolder.setUsers(user);
                        break;
                    case 2:
                        Admin admin = adminService.findAdminById(loginTicket.getUserId());
                        hostHolder.setUsers(admin);
                        break;
                    case 3:
                        SysAdmin sysAdmin = sysAdminService.findSysAdminById(loginTicket.getUserId());
                        hostHolder.setUsers(sysAdmin);
                        break;
                }
            } else {
                if (!request.getRequestURI().equals("/")
                        && !request.getRequestURI().contains("error")
                        && !request.getRequestURI().contains("login")) {
                    response.sendRedirect("/");
                    return false;
                }
            }
        } else {
            if (!request.getRequestURI().equals("/")
                    && !request.getRequestURI().contains("error")
                    && !request.getRequestURI().contains("login")) {
                //System.out.println(request.getRequestURI());
                response.sendRedirect("/");
                return false;
            }
        }
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        AllUser user = hostHolder.getUsers();
        if (user != null && modelAndView != null) {
            if (user instanceof User) {
                modelAndView.addObject("user", user);
            }
            if (user instanceof Admin) {
                modelAndView.addObject("admin", user);
            }
            if (user instanceof SysAdmin) {
                modelAndView.addObject("sysadmin", user);
            }
        }
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        hostHolder.clear();
    }
}
