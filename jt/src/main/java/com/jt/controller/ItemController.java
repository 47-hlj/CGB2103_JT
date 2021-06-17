package com.jt.controller;

import com.jt.service.ItemService;
import com.jt.vo.PageResult;
import com.jt.vo.SysResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin
@RequestMapping("/item")
public class ItemController {

    @Autowired
    private ItemService itemService;

    /**
     * 业务说明: 实现商品列表分页查询
     * URL: /item/getItemList
     * 参数: queryItemInfo  分页对象接收
     * 返回值: SysResult(分页对象)
     */
    @GetMapping("/getItemList")
    public SysResult getItemList(PageResult pageResult){

        pageResult = itemService.findItemByPage(pageResult);
        return SysResult.success(pageResult);
    }

}
