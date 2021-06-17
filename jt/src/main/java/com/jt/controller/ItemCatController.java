package com.jt.controller;

import com.jt.pojo.ItemCat;
import com.jt.service.ItemCatService;
import com.jt.vo.SysResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/itemCat")
public class ItemCatController {

    @Autowired
    private ItemCatService itemCatService;

    /**
     * 实现三级商品分类查询
     * URL:  /itemCat/findItemCatList/3
     * 请求参数: type=level    请求级别
     * 返回值:  SysResult对象
     */
    @GetMapping("/findItemCatList/{type}")
    public SysResult findItemCatList(@PathVariable Integer type){
        //要求实现数据的嵌套
        List<ItemCat> catList = itemCatService.findItemCatList(type);
        return SysResult.success(catList);
    }

    /**
     * 需求: 实现商品分类状态更新
     * URL地址: /itemCat/status/{id}/{status}
     * 参数:  id/status
     * 返回值: SysResult对象
     */
    @PutMapping("/status/{id}/{status}")
    public SysResult updateStatus(ItemCat itemCat){

        itemCatService.updateStatus(itemCat);
        return SysResult.success();
    }


    /**
     * 业务说明: 商品分类新增
     * URL地址:   /itemCat/saveItemCat
     * 请求参数:  整个form表单  JSON串
     * 返回值:    SysResult对象
     */
    @PostMapping("/saveItemCat")
    public SysResult saveItemCat(@RequestBody ItemCat itemCat){

        itemCatService.saveItemCat(itemCat);
        return SysResult.success();
    }


    /**
     * 删除商品分类信息
     * URL: /itemCat/deleteItemCat
     * 参数: id,level
     * 返回值: SysResult
     */
    @DeleteMapping("/deleteItemCat")
    public SysResult deleteItemCat(Integer id,Integer level){

        itemCatService.deleteItemCat(id,level);
        return SysResult.success();
    }


}
