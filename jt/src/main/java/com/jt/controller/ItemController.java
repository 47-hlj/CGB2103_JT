package com.jt.controller;

import com.jt.pojo.Item;
import com.jt.service.ItemService;
import com.jt.vo.ItemVO;
import com.jt.vo.PageResult;
import com.jt.vo.SysResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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

    /**
     *  url: /item/updateItem
     *  参数: updateItem: { id: ''  title: '', sellPoint: '', price:  '',  num:  ''}
     *  返回值: SysResult对象
     */
    @PutMapping("updateItem")
    public SysResult updateItem(@RequestBody Item item){

        itemService.updateItem(item);
        return SysResult.success();
    }

    /**
     * 实现商品新增操作
     * URL: http://localhost:8091/item/saveItem
     * 参数: {item,itemDesc}    JSON
     * 返回值: SysResult对象
     */
    @PostMapping("/saveItem")
    public SysResult saveItem(@RequestBody ItemVO itemVO){

        itemService.saveItem(itemVO);
        return SysResult.success();
    }

    /**
     * 修改商品状态
     * URL: /item/updateItemStatus
     * 返回值：SysResult对象
     */
    @PutMapping("/updateItemStatus")
    public SysResult updateItemStatus(@RequestBody Item item){
        itemService.updateItemStatus(item);
        return SysResult.success();
    }


    /**
     * 删除商品信息
     * 1.url:/item/deleteItemById
     * 2.请求类型：delete
     * 3.请求参数：id
     * 4.返回值：SysResult对象
     */

    @DeleteMapping("/deleteItemById")
    public SysResult deleteItem(Item item){
        itemService.deleteItemById(item);
        return SysResult.success();
    }
}
