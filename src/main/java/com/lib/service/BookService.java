package com.lib.service;

import com.lib.dao.BookMapper;
import com.lib.pojo.Book;
import com.lib.pojo.Sort;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookService {
    @Autowired
    private BookMapper bookMapper;

    public int findRows() {
        return bookMapper.selectRows();
    }

    public int findRowsByCondition(String keyword, int sid) {
        return bookMapper.selectRowsByCondition(keyword, sid);
    }

    public List<Book> findBook(int offset, int limit) {
        return bookMapper.selectBook(offset, limit);
    }

    public List<Book> findBookByCondition(String keyword, int sid, int offset, int limit) {
        return bookMapper.selectBookBySomething(keyword, sid, offset, limit);
    }

    public List<Sort> findAllSort() {
        return bookMapper.selectAllSort();
    }

}
