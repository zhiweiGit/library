package com.lib.controller;

import com.lib.pojo.Book;
import com.lib.pojo.Page;
import com.lib.pojo.Sort;
import com.lib.service.BookService;
import com.lib.service.UserService;
import com.lib.util.HostHolder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("user")
public class UserController {
    @Autowired
    private HostHolder hostHolder;

    @Autowired
    private UserService userService;

    @Autowired
    private BookService bookService;

    @GetMapping("")
    public String getIndex() {
        return "user/index";
    }

    @GetMapping("book/list")
    public String list(Model model, Page page, String keyword, Integer sid) {
        if (sid == null) {
            sid = 0;
        }
        List<Book> books = bookService.findBookByCondition(keyword, sid, page.getOffset(), page.getLimit());
        //List<Book> books = bookService.findBook(page.getOffset(), page.getLimit());
        model.addAttribute("books", books);
        List<Sort> sorts = bookService.findAllSort();
        model.addAttribute("sorts", sorts);
        model.addAttribute("keyword", keyword);
        model.addAttribute("sid_", sid);

        //设置分页
        page.setRows(bookService.findRowsByCondition(keyword, sid));
        return "user/list";
    }

    @PostMapping("book/search")
    public String search(String keyword, int sid, Page page, Model model) {
        List<Book> books = bookService.findBookByCondition(keyword, sid, page.getOffset(), page.getLimit());
        page.setRows(bookService.findRowsByCondition(keyword, sid));
        model.addAttribute("books", books);
        List<Sort> sorts = bookService.findAllSort();
        model.addAttribute("sorts", sorts);
        model.addAttribute("keyword", keyword);
        model.addAttribute("sid_", sid);
        return "user/list";
    }

    @GetMapping("book/borrow")
    public String borrow() {
        return "user/borrow";
    }
}
