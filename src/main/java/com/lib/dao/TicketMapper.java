package com.lib.dao;

import com.lib.pojo.LoginTicket;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface TicketMapper {
    int insertTicket(LoginTicket ticket);

    LoginTicket selectTicket(String ticket);

    int updateTicket(LoginTicket loginTicket);
}
