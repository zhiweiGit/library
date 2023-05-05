package com.lib.service;

import com.lib.dao.SysAdminMapper;
import com.lib.pojo.SysAdmin;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SysAdminService {
    @Autowired
    private SysAdminMapper sysAdminMapper;

    public SysAdmin findSysAdminByUsername(String username) {
        return sysAdminMapper.selectSysByUsername(username);
    }

    public SysAdmin findSysAdminById(int id) {
        return sysAdminMapper.selectSysById(id);
    }
}
