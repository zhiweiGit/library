package com.lib.controller;

import com.lib.pojo.Book;
import com.lib.pojo.Borrow;
import com.lib.service.AdminService;
import com.lib.service.BookService;
import com.lib.service.BorrowService;
import com.lib.service.UserService;
import com.lib.vo.BorrowVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("admin")
public class AdminController {
    @Autowired
    private AdminService adminService;

    @Autowired
    private UserService userService;

    @Autowired
    private BookService bookService;

    @Autowired
    private BorrowService borrowService;

    @GetMapping("/")
    public String getIndex() {
        return "admin/index";
    }

    @GetMapping("borrowList")
    public String borrowList(Model model) {
        List<Borrow> borrows = adminService.findBorrows();
        List<BorrowVo> borrowVoList = new ArrayList<>();
        for (Borrow borrow : borrows) {
            Book book = bookService.findBookById(borrow.getBid());
            BorrowVo borrowVo = new BorrowVo(borrow.getId(), userService.findUserById(borrow.getUid()).getUsername(),
                    book.getName(), book.getSid(), book.getAuthor(), book.getAuthor(), book.getEdition(), borrow.getStartTime(),
                    null, borrow.getStatus());
            borrowVoList.add(borrowVo);
        }
        model.addAttribute("borrowVoList", borrowVoList);
        return "admin/borrow";
    }

    @GetMapping("backList")
    public String backList(Model model) {
        List<Borrow> backs = adminService.findBacks();
        List<BorrowVo> borrowVoList = new ArrayList<>();
        for (Borrow borrow : backs) {
            Book book = bookService.findBookById(borrow.getBid());
            BorrowVo borrowVo = new BorrowVo(borrow.getId(), userService.findUserById(borrow.getUid()).getUsername(),
                    book.getName(), book.getSid(), book.getAuthor(), book.getPublish(), book.getEdition(), null, null, borrow.getStatus());
            borrowVoList.add(borrowVo);
        }
        model.addAttribute("list", borrowVoList);
        return "admin/back";
    }

    @PostMapping("allow/{id}")
    @ResponseBody
    public Integer allow(@PathVariable("id") int id) {
        try {
            borrowService.allowBorrow(id);
        } catch (Exception e) {
            return -1;
        }
        return 0;
    }

    @PostMapping("notAllow/{id}")
    @ResponseBody
    public Integer notAllow(@PathVariable("id") int id) {
        try {
            borrowService.notAllowBorrow(id);
        } catch (Exception e) {
            return -1;
        }
        return 0;
    }

    @PostMapping("confirmBack/{id}")
    @ResponseBody
    public Integer confirmBack(@PathVariable("id") int id) {
        try {
            borrowService.confirmBack(id);
        } catch (Exception e) {
            return -1;
        }
        return 0;
    }
}
