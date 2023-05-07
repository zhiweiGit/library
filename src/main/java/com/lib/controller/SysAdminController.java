package com.lib.controller;

import com.lib.pojo.*;
import com.lib.service.*;
import com.lib.vo.UserVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("sysadmin")
public class SysAdminController {

    @Autowired
    private BookService bookService;

    @Autowired
    private SortService sortService;

    @Autowired
    private UserService userService;

    @Autowired
    private AdminService adminService;

    @Autowired
    private SysAdminService sysAdminService;

    @GetMapping("")
    public String getIndex() {
        return "sysadmin/index";
    }

    @GetMapping("userList")
    public String userList(Model model) {
        List<UserVo> userVoList = new ArrayList<>();
        List<User> allUser = userService.findAllUser();
        for (User user : allUser) {
            UserVo userVo = new UserVo("普通用户", user.getId(), user.getUsername(), user.getPassword());
            userVoList.add(userVo);
        }
        List<Admin> allAdmin = adminService.findAllAdmin();
        for (Admin admin : allAdmin) {
            userVoList.add(new UserVo("管理员", admin.getId(), admin.getUsername(), admin.getPassword()));
        }
        model.addAttribute("list", userVoList);
        return "sysadmin/user";
    }

    @GetMapping("bookList")
    public String bookList(Model model, Page page, String keyword) {
        List<Book> books = bookService.findBookByCondition(keyword, 0, page.getOffset(), page.getLimit());
        model.addAttribute("books", books);
        List<Sort> sorts = bookService.findAllSort();
        model.addAttribute("sorts", sorts);
        model.addAttribute("keyword", keyword);
        //设置分页
        page.setRows(bookService.findRowsByCondition(keyword, 0));
        return "sysadmin/book";
    }

    @GetMapping("sortList")
    public String sortList(Model model) {
        List<Sort> sorts = bookService.findAllSort();
        model.addAttribute("sorts", sorts);
        return "sysadmin/sort";
    }


    @PostMapping("searchUser")
    public String searchUser(String username, Model model) {
        List<AllUser> allUsers = sysAdminService.searchUser(username);
        List<UserVo> userVoList = new ArrayList<>();
        for (AllUser allUser : allUsers) {
            if (allUser instanceof User) {
                userVoList.add(new UserVo("普通用户", allUser.getId(), allUser.getUsername(), allUser.getPassword()));
            } else if (allUser instanceof Admin) {
                userVoList.add(new UserVo("管理员", allUser.getId(), allUser.getUsername(), allUser.getPassword()));
            }
        }
        model.addAttribute("list", userVoList);
        return "sysadmin/user";
    }

    @PostMapping("add")
    public String addUser(UserVo userVo, Model model) {
        if (sysAdminService.add(userVo) == -1) {
            model.addAttribute("msg", "用户名已存在");
            model.addAttribute("username", userVo.getUsername());
            model.addAttribute("password", userVo.getPassword());
            return "sysadmin/addUser";
        }
        return "redirect:userList";
    }

    @GetMapping("update/{type}/{id}")
    public String updateUser(@PathVariable("type") String type, @PathVariable("id") int id, Model model) {
        UserVo userVo = null;
        if ("user".equals(type)) {
            User user = userService.findUserById(id);
            userVo = new UserVo("普通用户", id, user.getUsername(), user.getPassword());
        } else if ("amdin".equals(type)) {
            Admin admin = adminService.findAdminById(id);
            userVo = new UserVo("管理员", id, admin.getUsername(), admin.getPassword());
        }
        model.addAttribute("user", userVo);
        return "sysadmin/updateUser";
    }

    @PostMapping("update")
    public String update(String type, int id, String username, String password) {
        if ("普通用户".equals(type)) {
            User user = userService.findUserById(id);
            user.setUsername(username);
            user.setPassword(password);
            userService.updateUser(user);
        } else if ("管理员".equals(type)) {
            Admin admin = adminService.findAdminById(id);
            admin.setUsername(username);
            admin.setPassword(password);
            adminService.updateAdmin(admin);
        }
        return "redirect:userList";
    }

    @PostMapping("delete")
    @ResponseBody()
    public Map<String, Object> delete(String type, int id) {
        Map<String, Object> map = new HashMap<>();
        if (sysAdminService.delete(type, id) == -1) {
            map.put("code", -1);
        }
        map.put("code", 0);
        return map;
    }

    @GetMapping("addBook")
    public String addBook(Model model) {
        List<Sort> sorts = bookService.findAllSort();
        model.addAttribute("sorts", sorts);
        return "sysadmin/addBook";
    }

    @PostMapping("addBook")
    public String addBook(Book book) {
        bookService.insertBook(book);
        return "redirect:bookList";
    }

    @GetMapping("updateBook/{id}")
    public String updateBook(@PathVariable("id") int id, Model model) {
        Book book = bookService.findBookById(id);
        model.addAttribute("sorts", bookService.findAllSort());
        model.addAttribute("book", book);
        return "sysadmin/updateBook";
    }

    @PostMapping("updateBook")
    public String updateBook(Book book) {
        bookService.updateBook(book);
        return "redirect:bookList";
    }

    @PostMapping("deleteBook")
    public String deleteBook(int id) {
        bookService.deleteBook(id);
        return "redirect:bookList";
    }

    @GetMapping("updateSort/{id}")
    public String updateSort(@PathVariable("id") int id, Model model) {
        List<Sort> sorts = bookService.findAllSort();
        for (Sort sort : sorts) {
            if (id == sort.getId()) {
                model.addAttribute("sort", sort);
            }
        }
        return "sysadmin/updateSort";
    }

    @PostMapping("updateSort")
    public String updateSort(Sort sort) {
        sortService.updateSort(sort);
        return "redirect:sortList";
    }

    @PostMapping("deleteSort")
    public String deleteSort(int id) {
        sortService.deleteSort(id);
        return "redirect:sortList";
    }

    @GetMapping("addSort")
    public String addSort() {
        return "sysadmin/addSort";
    }

    @PostMapping("addSort")
    public String addSort(Sort sort) {
        sortService.insertSort(sort);
        return "redirect:sortList";
    }

    @PostMapping("searchSort")
    public String searchSort(String keyword, Model model) {
        List<Sort> sorts = sortService.findSortByCondition(keyword);
        model.addAttribute("sorts", sorts);
        return "/sysadmin/sort";
    }

}
