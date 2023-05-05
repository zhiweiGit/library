package com.lib.dao;

import com.lib.pojo.Admin;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface AdminMapper {
    Admin selectAdminByUsername(String username);

    Admin selectAdminById(int id);
}
