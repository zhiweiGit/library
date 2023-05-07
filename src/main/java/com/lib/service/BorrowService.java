package com.lib.service;

import com.lib.dao.BookMapper;
import com.lib.dao.BorrowMapper;
import com.lib.pojo.AllUser;
import com.lib.pojo.Book;
import com.lib.pojo.Borrow;
import com.lib.util.HostHolder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

@Service
public class BorrowService {
    @Autowired
    private BorrowMapper borrowMapper;

    @Autowired
    private BookMapper bookMapper;

    @Autowired
    private HostHolder hostHolder;

    public int borrowBook(int bid) {
        AllUser user = hostHolder.getUsers();
        if (user == null) {
            throw new IllegalArgumentException("未登录");
        }
        Book book = bookMapper.selectBookById(bid);
        if (book.getNumber() <= 0) {
            throw new IllegalArgumentException("数量错误");
        }
        bookMapper.updateBookNumber(book.getId(), book.getNumber() - 1);
        borrowMapper.insertBorrow(new Borrow(user.getId(), bid, new Date(), null, 0));
        return 0;
    }

    public List<Borrow> borrowsList(int uid) {
        List<Borrow> borrowList = new ArrayList<>(borrowMapper.selectBorrowOther(uid));
        borrowList.addAll(borrowMapper.selectBorrowByUidAndStatus(uid, 4));
        return borrowList;
    }

    public List<Borrow> noReturnList(int uid) {
        return borrowMapper.selectNoReturn(uid);
    }

    public void returnBook(int id) {
        AllUser users = hostHolder.getUsers();
        Borrow borrow = borrowMapper.selectBorrowById(id);
        if (borrow.getUid() != users.getId()) {
            throw new IllegalArgumentException("用户错误");
        }
        borrow.setStatus(3);
        borrowMapper.updateBorrow(borrow);
    }

    public void renewalBook(int id) {
        AllUser users = hostHolder.getUsers();
        Borrow borrow = borrowMapper.selectBorrowById(id);
        if (borrow.getUid() != users.getId()) {
            throw new IllegalArgumentException("用户错误");
        }
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(borrow.getEndTime());
        calendar.add(Calendar.MONTH, 1);
        borrow.setEndTime(calendar.getTime());
        borrowMapper.updateBorrow(borrow);
    }

    public void allowBorrow(int id) {
        Borrow borrow = borrowMapper.selectBorrowById(id);
        if (borrow == null) {
            throw new IllegalArgumentException("参数错误");
        }
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(new Date());
        calendar.add(Calendar.MONTH, 1);
        borrow.setEndTime(calendar.getTime());
        borrow.setStatus(1);
        borrowMapper.updateBorrow(borrow);
    }

    public void notAllowBorrow(int id) {
        Borrow borrow = borrowMapper.selectBorrowById(id);
        if (borrow == null) {
            throw new IllegalArgumentException("参数错误");
        }
        borrow.setStatus(2);
        borrowMapper.updateBorrow(borrow);
    }

    public void confirmBack(int id) {
        Borrow borrow = borrowMapper.selectBorrowById(id);
        if (borrow == null) {
            throw new IllegalArgumentException("参数错误");
        }
        borrow.setStatus(4);
        borrowMapper.updateBorrow(borrow);
    }
}
