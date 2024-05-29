package com.flyfiref.schedule.service;

import com.flyfiref.schedule.pojo.Item;

import java.util.List;

public interface ItemService {
    List<Item> queryAllItems();
    int deleteItemById(int id);
    int modifyItemById(String[] itemArgs);
    int addItem(String[] itemArgs);
    String[] queryItemById(int id);
}
