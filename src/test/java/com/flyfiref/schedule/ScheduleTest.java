package com.flyfiref.schedule;

import com.flyfiref.schedule.app.MainWindow;
import com.flyfiref.schedule.app.Music;
import com.flyfiref.schedule.service.ItemService;
import jakarta.annotation.Resource;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalTime;
import java.util.Arrays;
import java.util.Date;

//@RunWith(SpringJUnit4ClassRunner.class)
//@ContextConfiguration("classpath:spring.xml")
public class ScheduleTest {
    public void test1(){
        /*SimpleDateFormat sdf=new SimpleDateFormat("h:m");
        try{
            Date date = sdf.parse("");
            System.out.println(date);
        } catch (ParseException ex) {
            throw new RuntimeException(ex);
        }*/
    }
    /*@public void test2(){
// 示例数组
        int[] hoursArray = {2, 4, 6, 8, 10, 12, 14, 16, 18, 20, 22, 0, 1};

        // 获取当前小时数
        int currentHour = LocalTime.now().getHour();

        // 插入当前小时数并排序
        int[] sortedArray = insertAndSortHours(hoursArray, currentHour);

        // 输出排序后的数组
        System.out.println("Sorted array with current hour inserted: " + Arrays.toString(sortedArray));

        // 查找当前小时数的位置
        int index = Arrays.binarySearch(sortedArray, currentHour);

        // 输出当前小时数的前后各一个元素
        if (index >= 0) {
            // 如果找到了，直接输出
            System.out.println("Current hour: " + currentHour);
            System.out.println("Previous hour: " + (index > 0 ? sortedArray[index - 1] : "Not available"));
            System.out.println("Next hour: " + (index < sortedArray.length - 1 ? sortedArray[index + 1] : "Not available"));
        } else {
            // 如果没有找到（即index为负数），则需要转换为正确的索引
            index = -index - 1;
            System.out.println("Current hour: " + currentHour);
            System.out.println("Previous hour: " + (index > 0 ? sortedArray[index - 1] : "Not available"));
            System.out.println("Next hour: " + (index < sortedArray.length ? sortedArray[index] : "Not available"));
        }
    }*/
    public void test3(){
        /*int s =getSecondFromStrTime("09:08");
        System.out.println(s);*/
    }
    public void test4(){
        /*new Music("上课铃.mp3").start();
        java.util.Scanner s = new java.util.Scanner(System.in);
        String str =s.next();*/
    }
    private int[] insertAndSortHours(int[] hoursArray, int currentHour) {
        // 创建一个新的数组，长度加1以容纳当前小时数
        int[] newArray = new int[hoursArray.length + 1];

        // 将原数组的元素复制到新数组
        System.arraycopy(hoursArray, 0, newArray, 0, hoursArray.length);

        // 插入当前小时数（假设直接插入到数组末尾）
        newArray[newArray.length - 1] = currentHour;

        // 对新数组进行排序
        Arrays.sort(newArray);

        return newArray;
    }
    private int getSecondFromStrTime(String timeStr) {
        String[] hourAndMinute = timeStr.split(":");
        int hour = Integer.parseInt(hourAndMinute[0]);
        int minute = Integer.parseInt(hourAndMinute[1]);
        return hour*3600+minute*60;
    }
}
