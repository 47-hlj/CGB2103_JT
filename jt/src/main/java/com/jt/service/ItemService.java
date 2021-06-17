package com.jt.service;

import com.jt.vo.PageResult;

public interface ItemService {
    PageResult findItemByPage(PageResult pageResult);
}
