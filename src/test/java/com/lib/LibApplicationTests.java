package com.lib;

import com.lib.dao.BookMapper;
import com.lib.dao.SortMapper;
import com.lib.dao.TicketMapper;
import com.lib.pojo.Book;
import com.lib.util.HostHolder;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

@SpringBootTest
class LibApplicationTests {

    @Autowired
    private HostHolder hostHolder;

    @Autowired
    private BookMapper bookMapper;
    @Autowired
    private SortMapper sortMapper;

    @Autowired
    private TicketMapper ticketMapper;

    @Test
    void contextLoads() {
        //hostHolder.setUsers(new AllUser());
        //System.out.println(hostHolder.getUsers());
        //ticketMapper.insertTicket(new LoginTicket(1, 1, "333", 3, new Date(System.currentTimeMillis() + 3600 * 1000)));
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(new Date());
        calendar.add(Calendar.MONTH, 1);
        System.out.println(simpleDateFormat.format(calendar.getTime()));

    }

    @Test
    void bookTest() {
        //List<Book> c = bookMapper.selectBookBySomething("C++", 0, null, "清华");
        List<Book> c = bookMapper.selectBook(0, 5);
        System.out.println(c);
    }

    @Test
    void sortTest() {
        System.out.println(sortMapper.selectSortByCondition("类"));
    }

}
