package com.lib.dao;

import com.lib.pojo.Admin;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface AdminMapper {
    Admin selectAdminByUsername(String username);

    Admin selectAdminById(int id);

    List<Admin> selectAllAdmin();

    int updateAdmin(Admin admin);

    int insertAdmin(Admin admin);

    int deleteAdmin(int id);
}
