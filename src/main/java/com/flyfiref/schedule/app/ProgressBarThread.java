package com.flyfiref.schedule.app;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.Arrays;
import java.util.Set;

public class ProgressBarThread implements Runnable{
    public volatile boolean disabled = false;
    private MainWindow mw;
    private float INIT_TIME=0;
    private float FINAL_TIME=24*60*60;
    private float CURRENT_TIME;
    private Set<Integer> setSeconds;
    private Set<Integer> startSeconds;
    private Set<Integer> endSeconds;
    public ProgressBarThread(MainWindow mw) {
        this.mw=mw;
        this.setSeconds=mw.setSeconds;
        this.startSeconds=mw.startSeconds;
        this.endSeconds=mw.endSeconds;
        this.setSeconds.add(0);
        this.setSeconds.add(24*60*60);
    }

    @Override
    public void run() {
        while (!disabled){
            // 获取当前日期时间
            LocalDateTime now = LocalDateTime.now();
            // 获取今天的开始时间（00:00）
            LocalDateTime startOfDay = now.toLocalDate().atStartOfDay();
            // 计算已过去的秒数
            CURRENT_TIME = (int)ChronoUnit.SECONDS.between(startOfDay, now);
            int[] timeRange=getTimeRange(setSeconds.toArray(new Integer[0]),(int)CURRENT_TIME);
            INIT_TIME=timeRange[0];
            FINAL_TIME=timeRange[1];
            int v = (int)((CURRENT_TIME - INIT_TIME) / (FINAL_TIME - INIT_TIME) * 100);
            mw.progressBarA.setValue(v);
            if (startSeconds.contains((int)CURRENT_TIME) && mw.isAutoMusic){
                new Music("上课铃.mp3").start();
            }
            if (endSeconds.contains((int)CURRENT_TIME) && mw.isAutoMusic){
                new Music("下课铃.mp3").start();
            }
            try {
                Thread.sleep(999);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }
    private int[] getTimeRange(Integer[] secondsArray,int currentSecond){
        // 插入当前小时数并排序
        int[] sortedArray = insertAndSortSeconds(secondsArray, currentSecond);
        // 查找当前小时数的位置
        int index = Arrays.binarySearch(sortedArray, currentSecond);
        // 输出当前小时数的前后各一个元素
        int[] prevAndNext=new int[2];
        if (index >= 0) {
            prevAndNext[0]=index > 0 ? sortedArray[index - 1] : 0;
            prevAndNext[1]=index < sortedArray.length - 1 ? sortedArray[index + 1] : 24*60*60;
        }
        return prevAndNext;
    }
    private int[] insertAndSortSeconds(Integer[] secondArray, int currentSecond) {
        // 创建一个新的数组，长度加1以容纳当前小时数
        int[] newArray = new int[secondArray.length + 1];

        // 将原数组的元素复制到新数组
        System.arraycopy(integerArrayToIntArray(secondArray), 0, newArray, 0, secondArray.length);

        // 插入当前小时数（假设直接插入到数组末尾）
        newArray[newArray.length - 1] = currentSecond;

        // 对新数组进行排序
        Arrays.sort(newArray);

        return newArray;
    }
    private int[] integerArrayToIntArray(Integer[] integers){
        return Arrays.stream(integers).mapToInt(Integer::valueOf).toArray();
    }
}
