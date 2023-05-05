package com.lib.controller;

import com.lib.form.LoginForm;
import com.lib.service.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;

@Controller
public class HomeController {
    @Autowired
    private LoginService loginService;

    @GetMapping("/")
    public String getLogin() {
        return "login";
    }

    @PostMapping("/login")
    public String login(LoginForm loginForm, Model model, HttpServletResponse response) {
        String url = "login";
        if (loginForm.getType() == 0) {
            model.addAttribute("msg", "未选择身份");
        } else {
            Map<String, String> map = loginService.login(loginForm.getUsername(), loginForm.getPassword(), loginForm.getType());
            if (map.containsKey("msg")) {
                model.addAttribute("msg", map.get("msg"));
            } else {
                Cookie cookie = new Cookie("ticket", map.get("ticket"));
                response.addCookie(cookie);
                switch (loginForm.getType()) {
                    case 1:
                        url = "redirect:/user/";
                        break;
                    case 2:
                        url = "redirect:/admin/";
                        break;
                    case 3:
                        url = "redirect:/sysadmin/";
                        break;
                }
            }
        }
        return url;
    }

    @GetMapping("logout")
    public String logout(@CookieValue("ticket") String ticket) {
        loginService.logout(ticket);
        return "redirect:/";
    }
}
