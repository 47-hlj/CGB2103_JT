package com.jt.service;


import com.jt.pojo.ItemCat;

import java.util.List;

public interface ItemCatService {
    List<ItemCat> findItemCatList(Integer type);

    void updateStatus(ItemCat itemCat);

    void saveItemCat(ItemCat itemCat);

    void deleteItemCat(Integer id, Integer level);

    void updateItemCat(ItemCat itemCat);
}
