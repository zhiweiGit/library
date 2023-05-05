package com.lib.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("sysadmin")
public class SysAdminController {
    @GetMapping("")
    public String getIndex(){
        return "sysadmin/index";
    }
}
