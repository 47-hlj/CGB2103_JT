package com.jt.service;

import com.baomidou.mybatisplus.core.InjectorResolver;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.jt.mapper.ItemCatMapper;
import com.jt.pojo.ItemCat;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class ItemCatServiceImpl implements ItemCatService {

    @Autowired
    private ItemCatMapper itemCatMapper;

    /**
     * 问题分析:
     *      1.提高程序的效率  减少数据库交互的次数
     *      2.查询的方法最好单独的抽取
     * 问题: 如何有效的存储父子关系
     * 数据结构: Map<parentId,子级>
     * 说明:
     *      Map<0,List<ItemCat>>   map.get(0)
     *      Map<1,List<电子书刊,英文书籍>>
     *      Map<24,List<少儿,0-2岁....>>
     * @return
     */


    //1.封装一个Map集合
    public Map<Integer,List<ItemCat>> getMap(){
        Map<Integer,List<ItemCat>> map=new HashMap<Integer,List<ItemCat>>();
        //1.查询所有的数据信息
        List<ItemCat> list=itemCatMapper.selectList(null);
        //2.将List集合封装到Map集合中
        for(ItemCat itemCat:list){
            //规则: 判断map中是否有key
            // 没有key  该子级是第一个父级元素的孩子,应该声明父级并且将子级最为第一个子级保存
            // 有key    我找到父级的子级序列 将子级追加到序列中即可.
            if(map.containsKey(itemCat.getParentId())){
                //获取父级的所有已知子级
                map.get(itemCat.getParentId()).add(itemCat);
            }else {
                //没有父级
                List<ItemCat> initList=new ArrayList<>();
                initList.add(itemCat);
                map.put(itemCat.getParentId(),initList);
            }
        }
        return map;
    }

    @Override
    public List<ItemCat> findItemCatList(Integer type) {
        //获取数据封装的结果
        Map<Integer,List<ItemCat>> map=getMap();
        if(type==1){//获取一级商品分类信息
            return map.get(0);
        }
        if(type==2){
            return getLevel2(map);
        }
        return getLevel3(map);
    }

    private List<ItemCat> getLevel2(Map<Integer, List<ItemCat>> map) {
        //1.获取1级分类商品信息
        List<ItemCat> list=map.get(0);

        //2.封装2级菜单信息
        for(ItemCat itemCat:list){
            //获取2级菜单信息
            List<ItemCat> twoList=map.get(itemCat.getId());
            itemCat.setChildren(twoList);
        }
        return list;
    }

    private List<ItemCat> getLevel3(Map<Integer, List<ItemCat>> map) {
        //1.获取2级商品分类信息  1级里套二级
        List<ItemCat> list=getLevel2(map);

        for(ItemCat itemCat1:list){
            //获取2级商品分类信息
            List<ItemCat> list2=itemCat1.getChildren();
            //根据2级查询3级信息
            if(list2 == null) continue;//本次循环结束，直接下一次
            for (ItemCat itemCat2:list2){
                List<ItemCat> list3=map.get(itemCat2.getId());
                itemCat2.setChildren(list3);
            }
            //将3级已经封装完成，将2级封装到1级中
            itemCat1.setChildren(list2);
        }
        return list;
    }

    @Override
    @Transactional
    public void updateStatus(ItemCat itemCat) {
        itemCatMapper.updateById(itemCat);
    }

    @Override
    public void saveItemCat(ItemCat itemCat) {
        itemCat.setStatus(true);
        itemCatMapper.insert(itemCat);
    }

    @Override
    @Transactional
    public void deleteItemCat(Integer id, Integer level) {
        if(level == 3){
            //如果是三级商品分类菜单则直接删除
            itemCatMapper.deleteById(id);
        }

        if(level == 2){
            //先删除3级菜单
            QueryWrapper<ItemCat> queryWrapper = new QueryWrapper<>();
            queryWrapper.eq("parent_id",id);
            itemCatMapper.delete(queryWrapper);
            //先删除2级菜单
            itemCatMapper.deleteById(id);
        }

        if(level == 1) {
            //1.查询二级分类信息 parent_id=一级ID
            QueryWrapper<ItemCat> queryWrapper = new QueryWrapper<>();
            queryWrapper.eq("parent_id", id);
            //获取主键信息(第一列信息)
            List<Object> twoIdList = itemCatMapper.selectObjs(queryWrapper);
            //2.先删除3级
            for (Object twoId : twoIdList) {
                QueryWrapper<ItemCat> queryWrapper2 = new QueryWrapper<>();
                queryWrapper2.eq("parent_id", twoId);
                itemCatMapper.delete(queryWrapper2);
                //将2级删除
                Integer intTwoId = (Integer) twoId;
                itemCatMapper.deleteById(intTwoId);
            }
            //3.删除一级商品分类信息
            itemCatMapper.deleteById(id);
        }
    }
}
