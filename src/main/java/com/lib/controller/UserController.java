package com.lib.controller;

import com.lib.pojo.Book;
import com.lib.pojo.Borrow;
import com.lib.pojo.Page;
import com.lib.pojo.Sort;
import com.lib.service.BookService;
import com.lib.service.BorrowService;
import com.lib.service.UserService;
import com.lib.util.HostHolder;
import com.lib.vo.BorrowVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
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

    @Autowired
    private BorrowService borrowService;

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

    @PostMapping("book/borrow")
    @ResponseBody
    public Integer borrow(int bid) {
        borrowService.borrowBook(bid);
        return 0;
    }

    @GetMapping("borrow/list")
    public String borrowList(Model model) {
        List<Borrow> borrowList = borrowService.borrowsList(hostHolder.getUsers().getId());
        List<BorrowVo> borrowVoList = new ArrayList<>();
        if (borrowList != null) {
            for (Borrow borrow : borrowList) {
                Book book = bookService.findBookById(borrow.getBid());
                BorrowVo borrowVo = new BorrowVo(book.getName(), book.getSid(), book.getAuthor(), book.getPublish(), book.getEdition(), borrow.getStartTime(), borrow.getStatus());
                borrowVoList.add(borrowVo);
            }
        }
        model.addAttribute("borrowVoList", borrowVoList);
        return "user/borrow";
    }

    @GetMapping("back/list")
    public String backList(Model model) {
        List<Borrow> noReturnList = borrowService.noReturnList(hostHolder.getUsers().getId());
        List<BorrowVo> borrowVoList = new ArrayList<>();
        for (Borrow borrow : noReturnList) {
            Book book = bookService.findBookById(borrow.getBid());
            BorrowVo borrowVo = new BorrowVo(book.getName(), book.getSid(), book.getAuthor(), book.getPublish(), book.getEdition(), borrow.getStartTime(), borrow.getStatus());
            borrowVo.setEndTime(borrow.getEndTime());
            borrowVo.setId(borrow.getId());
            borrowVoList.add(borrowVo);
        }
        model.addAttribute("borrowVoList", borrowVoList);
        return "user/back";
    }

    @PostMapping("return")
    @ResponseBody
    public int returnBook(int id) {
        try {
            borrowService.returnBook(id);
        } catch (Exception e) {
            return -1;
        }
        return 0;
    }

    @PostMapping("renewal")
    @ResponseBody
    public int renewalBook(int id) {
        try {
            borrowService.renewalBook(id);
        } catch (Exception e) {
            return -1;
        }
        return 0;
    }
}
