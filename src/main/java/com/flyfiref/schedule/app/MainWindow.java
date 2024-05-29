package com.flyfiref.schedule.app;

import com.flyfiref.schedule.pojo.Item;
import com.flyfiref.schedule.service.ItemService;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

public class MainWindow extends JFrame {
    private ItemService itemService;
    public JProgressBar progressBarA;
    JPanel componentPanelB;
    JCheckBox checkBoxC;
    JButton buttonD;
    ProgressBarThread t;
    public Set<Integer> setSeconds;
    public Set<Integer> startSeconds;
    public Set<Integer> endSeconds;
    public boolean isAutoMusic;
    public MainWindow(ItemService itemService){
        this.itemService=itemService;
        setTitle("作息时间表");
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        init();
    }
    private void init(){
        List<Item> itemList=itemService.queryAllItems();
        // 设置窗口的主布局为垂直布局
        setLayout(new BoxLayout(getContentPane(), BoxLayout.Y_AXIS));

        // 创建进度条A
        progressBarA = new JProgressBar(0, 100);
        progressBarA.setValue(0); // 示例值
        progressBarA.setStringPainted(true);
        add(progressBarA);

        // 组件B的容器，先创建JPanel，再设置BoxLayout
        componentPanelB = new JPanel();
        componentPanelB.setLayout(new BoxLayout(componentPanelB, BoxLayout.Y_AXIS));
        add(componentPanelB);

        // 创建复选框C
        checkBoxC = new JCheckBox("自动打铃");
        checkBoxC.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                JCheckBox checkBox = (JCheckBox) e.getSource();
                isAutoMusic=checkBox.isSelected();
            }
        });
        add(checkBoxC);

        // 创建按钮D
        buttonD = new JButton("添加");
        buttonD.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                JFrame frame = (JFrame) SwingUtilities.getWindowAncestor((Component) e.getSource());
                super.mouseClicked(e);
                new AddWindow(frame,"添加事件",true,itemService);
                refresh();
            }
        });
        add(buttonD);
        // 根据ItemList创建组件b
        addComponentB(itemList);
        pack(); // 自动调整窗口大小以适应内容
        setVisible(true);
        t = new ProgressBarThread(this);
        new Thread(t).start();
    }

    private void addComponentB(List<Item> itemList) {
        setSeconds=new TreeSet<>();
        startSeconds=new TreeSet<>();
        endSeconds=new TreeSet<>();
        for (Item item : itemList) {
            String startTime=item.getStartTime();
            String endTime=item.getEndTime();
            int secondOfStartTime=getSecondFromStrTime(startTime);
            int secondOfEndTime=getSecondFromStrTime(endTime);
            setSeconds.add(secondOfStartTime);
            if("学习".equals(item.getDesc())){
                startSeconds.add(secondOfStartTime);
            }
            setSeconds.add(secondOfEndTime);
            if("学习".equals(item.getDesc())) {
                endSeconds.add(secondOfEndTime);
            }
            // 创建b的容器，使用水平布局
            JPanel panelB = new JPanel(new FlowLayout(FlowLayout.LEFT, 0, 0));

            // 组件b1：标签
            JLabel labelB1 = new JLabel(startTime + "-" + endTime);
            labelB1.setPreferredSize(new Dimension(80,20));
            panelB.add(labelB1);

            // 组件b2：文本框
            JLabel labelB2 = new JLabel(item.getDesc());
            panelB.add(labelB2);

            // 组件b3：按钮
            JButton buttonB3 = new JButton("修改");
            buttonB3.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    super.mouseClicked(e);
                    JFrame frame = (JFrame) SwingUtilities.getWindowAncestor((Component) e.getSource());
                    super.mouseClicked(e);
                    new EditWindow(frame,"修改事件",true,itemService,item.getId());
                    refresh();
                }
            });
            panelB.add(buttonB3);

            // 组件b4：按钮
            JButton buttonB4 = new JButton("删除");
            buttonB4.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    super.mouseClicked(e);
                    int isConfirm = JOptionPane.showConfirmDialog(null,"确认删除这一项？","提示", JOptionPane.YES_NO_OPTION);
                    if(isConfirm==0){
                        itemService.deleteItemById(item.getId());
                        refresh();
                    }
                }
            });
            panelB.add(buttonB4);

            // 将b（即panelB）添加到componentPanelB中
            componentPanelB.add(panelB);

            // 如果有需要，可以为每个b添加间隔
//            componentPanelB.add(Box.createVerticalStrut(10));
        }
    }

    private int getSecondFromStrTime(String timeStr) {
        String[] hourAndMinute = timeStr.split(":");
        int hour = Integer.parseInt(hourAndMinute[0]);
        int minute = Integer.parseInt(hourAndMinute[1]);
        return hour*3600+minute*60;
    }

    private void refresh(){
        t.disabled=true;
        /*try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }*/
        componentPanelB.removeAll();
        addComponentB(itemService.queryAllItems());
        revalidate();
        repaint();
        pack();
        t = new ProgressBarThread(this);
        new Thread(t).start();
    }
}
