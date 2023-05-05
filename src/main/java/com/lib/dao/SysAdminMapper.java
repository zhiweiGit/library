package com.lib.dao;

import com.lib.pojo.SysAdmin;
import org.apache.ibatis.annotations.Mapper;


@Mapper
public interface SysAdminMapper {
    SysAdmin selectSysByUsername(String username);

    SysAdmin selectSysById(int id);
}
