package com.lib.dao;

import com.lib.pojo.Sort;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface SortMapper {

    int insertSort(Sort sort);

    int updateSort(Sort sort);

    int deleteSort(int id);

    List<Sort> selectSortByCondition(@Param("keyword") String keyword);
}
