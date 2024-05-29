package com.flyfiref.schedule.mapper;

import com.flyfiref.schedule.pojo.Item;

import java.util.List;

public interface ItemMapper {
    List<Item> selectAllItems();
    int deleteItemById(int id);
    int updateItemById(Item item);
    int insertItem(Item item);
    Item selectItemById(int id);
}
