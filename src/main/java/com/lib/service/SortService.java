package com.lib.service;

import com.lib.dao.SortMapper;
import com.lib.pojo.Sort;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SortService {
    @Autowired
    private SortMapper sortMapper;

    public void updateSort(Sort sort) {
        sortMapper.updateSort(sort);
    }

    public void deleteSort(int id) {
        sortMapper.deleteSort(id);
    }

    public void insertSort(Sort sort) {
        sortMapper.insertSort(sort);
    }

    public List<Sort> findSortByCondition(String keyword) {
        return sortMapper.selectSortByCondition(keyword);
    }
}
