package com.lib.dao;

import com.lib.pojo.Borrow;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface BorrowMapper {

    int insertBorrow(Borrow borrow);

    List<Borrow> selectBorrowByUid(@Param("uid") int uid);

    List<Borrow> selectBorrowByUidAndStatus(@Param("uid") int uid, @Param("status") int status);

    List<Borrow> selectBorrowOther(@Param("uid") int uid);

    Borrow selectBorrowById(@Param("id") int id);

    List<Borrow> selectNoReturn(@Param("uid") int uid);

    int updateBorrow(Borrow borrow);
}
