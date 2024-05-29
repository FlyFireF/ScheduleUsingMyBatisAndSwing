package com.flyfiref.schedule.service.impl;

import com.flyfiref.schedule.mapper.ItemMapper;
import com.flyfiref.schedule.pojo.Item;
import com.flyfiref.schedule.service.ItemService;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;

import java.util.List;
@Service("itemService")
public class ItemServiceImpl implements ItemService {
    @Resource
    private ItemMapper itemMapper;
    @Override
    public List<Item> queryAllItems() {
        return itemMapper.selectAllItems();
    }

    @Override
    public int deleteItemById(int id) {
        return itemMapper.deleteItemById(id);
    }

    @Override
    public int modifyItemById(String[] itemArgs) {
        Item item = new Item();
        item.setId(Integer.parseInt(itemArgs[0]));
        item.setStartTime(itemArgs[1]);
        item.setEndTime(itemArgs[2]);
        item.setDesc(itemArgs[3]);
        return itemMapper.updateItemById(item);
    }

    @Override
    public int addItem(String[] itemArgs) {
        Item item = new Item();
        item.setStartTime(itemArgs[0]);
        item.setEndTime(itemArgs[1]);
        item.setDesc(itemArgs[2]);
        return itemMapper.insertItem(item);
    }

    @Override
    public String[] queryItemById(int id) {
        String[] strs = new String[4];
        Item item = itemMapper.selectItemById(id);
        strs[0]=Integer.toString(item.getId());
        strs[1]=item.getStartTime();
        strs[2]=item.getEndTime();
        strs[3]=item.getDesc();
        return strs;
    }
}
