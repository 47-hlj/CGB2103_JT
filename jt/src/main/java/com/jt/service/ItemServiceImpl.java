package com.jt.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.jt.mapper.ItemDescMapper;
import com.jt.mapper.ItemMapper;
import com.jt.pojo.Item;
import com.jt.pojo.ItemDesc;
import com.jt.vo.ItemVO;
import com.jt.vo.PageResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import java.util.List;

@Service
public class ItemServiceImpl implements ItemService{

    @Autowired
    private ItemMapper itemMapper;
    @Autowired
    private ItemDescMapper itemDescMapper;

    /**
     * PageResult {query,pageNum,pageSize,rows分页数据,total总数}
     *   :业务数据的VO对象
     * page: MP中的分页对象 官方API
     *
     * @param pageResult
     * @return
     */
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


    /**
     * 修改商品信息
     * @param item
     */
    @Override
    @Transactional
    public void updateItem(Item item) {

        itemMapper.updateById(item);
    }

    /**
     * 修改商品状态
     * @param item
     */
    @Override
    public void updateItemStatus(Item item) {

        itemMapper.updateById(item);
    }

    /**
     * 删除商品
     * @param item
     */
    @Override
    public void deleteItemById(Item item) {

        itemMapper.deleteById(item.getId());
        itemDescMapper.deleteById(item.getId());

    }

    /**
     * 完成2张表的入库操作
     * 1.item表
     * 2.item_desc表   要求ID一致.
     * @param itemVO
     */
    @Override
    @Transactional //控制事务
    public void saveItem(ItemVO itemVO) {
        //1.获取商品对象
        Item item = itemVO.getItem();
        item.setStatus(true);
        //MP已经实现了主键的自动回显 所以ID有值的
        itemMapper.insert(item);

        //2.获取商品详情
        ItemDesc itemDesc = itemVO.getItemDesc();
        itemDesc.setId(item.getId());
        itemDescMapper.insert(itemDesc);
    }


    //删除操作，也要删除两张表的信息
}

