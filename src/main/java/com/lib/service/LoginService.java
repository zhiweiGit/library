package com.lib.service;

import com.lib.dao.TicketMapper;
import com.lib.pojo.*;
import com.lib.util.HostHolder;
import com.lib.util.LibraryUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Service
public class LoginService {
    @Autowired
    private UserService userService;
    @Autowired
    private AdminService adminService;

    @Autowired
    private SysAdminService sysAdminService;

    @Autowired
    private TicketMapper ticketMapper;

    @Autowired
    private HostHolder hostHolder;

    public Map<String, String> login(String username, String password, int type) {
        Map<String, String> map = new HashMap<>();
        switch (type) {
            case 1:
                User user = userService.findUserByUsername(username);
                map = check(user, password);
                if (map.isEmpty()) {
                    String uuid = LibraryUtil.getUUID();
                    saveTicket(user.getId(), type, uuid);
                    map.put("ticket", uuid);
                }
                break;
            case 2:
                Admin admin = adminService.findAdminByUsername(username);
                map = check(admin, password);
                if (map.isEmpty()) {
                    String uuid = LibraryUtil.getUUID();
                    saveTicket(admin.getId(), type, uuid);
                    map.put("ticket", uuid);
                }
                break;
            case 3:
                SysAdmin sysAdmin = sysAdminService.findSysAdminByUsername(username);
                map = check(sysAdmin, password);
                if (map.isEmpty()) {
                    String uuid = LibraryUtil.getUUID();
                    saveTicket(sysAdmin.getId(), type, uuid);
                    map.put("ticket", uuid);
                }
                break;
        }


        return map;
    }

    public void logout(String ticket) {
        LoginTicket loginTicket = ticketMapper.selectTicket(ticket);
        if (loginTicket != null) {
            loginTicket.setStatus(1);
            ticketMapper.updateTicket(loginTicket);
        }
    }

    private Map<String, String> check(AllUser allUser, String password) {
        Map<String, String> map = new HashMap<>();
        if (allUser == null) {
            map.put("msg", "用户名错误");
            return map;
        }
        if (!allUser.getPassword().equals(password)) {
            map.put("msg", "密码错误");
        }
        hostHolder.setUsers(allUser);
        return map;
    }

    private void saveTicket(int userid, int type, String uuid) {
        ticketMapper.insertTicket(
                new LoginTicket(userid, type, uuid, 0, new Date(System.currentTimeMillis() + 3600 * 1000)));
    }
}
