package com.lib.dao;

import com.lib.pojo.Book;
import com.lib.pojo.Sort;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface BookMapper {

    int selectRows();

    int selectRowsByCondition(@Param("keyword") String keyword, @Param("sid") int sid);

    List<Book> selectBook(@Param("offset") int offset, @Param("limit") int limit);

    List<Book> selectBookBySomething(@Param("keyword") String keyword, @Param("sid") int sid, @Param("offset") int offset, @Param("limit") int limit);

    List<Sort> selectAllSort();

    int insertBook(Book book);

    int updateBookNumber(@Param("id") int id, @Param("number") int number);

    Book selectBookById(@Param("id") int id);

}
