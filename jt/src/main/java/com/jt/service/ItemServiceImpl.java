package com.jt.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.jt.mapper.ItemMapper;
import com.jt.pojo.Item;
import com.jt.vo.PageResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.List;

@Service
public class ItemServiceImpl implements ItemService{

    @Autowired
    private ItemMapper itemMapper;

    @Override
    public PageResult findItemByPage(PageResult pageResult) {
        IPage<Item> page = new Page<>(pageResult.getPageNum(), pageResult.getPageSize());
        QueryWrapper<Item> queryWrapper = new QueryWrapper<>();
        boolean flag = StringUtils.hasLength(pageResult.getQuery());
        queryWrapper.like(flag,"title",pageResult.getQuery());
        //经过程序分页,其中的数据全部获取
        page = itemMapper.selectPage(page,queryWrapper);
        long total = page.getTotal();
        List<Item> itemList = page.getRecords();
        return pageResult.setTotal(total).setRows(itemList);
    }
}

