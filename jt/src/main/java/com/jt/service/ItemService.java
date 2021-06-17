package com.jt.service;

import com.jt.pojo.Item;
import com.jt.vo.ItemVO;
import com.jt.vo.PageResult;

public interface ItemService {
    PageResult findItemByPage(PageResult pageResult);

    void saveItem(ItemVO itemVO);

    void updateItem(Item item);

    void updateItemStatus(Item item);

    void deleteItemById(Item item);
}
