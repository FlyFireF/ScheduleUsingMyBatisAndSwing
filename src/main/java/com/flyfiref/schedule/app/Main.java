package com.flyfiref.schedule.app;

import com.flyfiref.schedule.service.ItemService;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Main {
    public static void main(String[] args) {
        ApplicationContext context = new ClassPathXmlApplicationContext("spring.xml");
        ItemService itemService = context.getBean("itemService", ItemService.class);
        new MainWindow(itemService);
    }
}
