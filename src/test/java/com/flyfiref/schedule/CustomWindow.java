package com.flyfiref.schedule;
import javax.swing.*;
import java.awt.*;
import java.util.List;

public class CustomWindow extends JFrame {

    private JProgressBar progressBarA;
    JPanel componentPanelB;
    JCheckBox checkBoxC;
    JButton buttonD;

    public CustomWindow(List<String> itemList) {
        super("Custom Window");

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // 设置窗口的主布局为垂直布局
        setLayout(new BoxLayout(getContentPane(), BoxLayout.Y_AXIS));

        // 创建进度条A
        progressBarA = new JProgressBar(0, 100);
        progressBarA.setValue(50); // 示例值
        progressBarA.setStringPainted(true);
        add(progressBarA);

        // 组件B的容器，先创建JPanel，再设置BoxLayout
        componentPanelB = new JPanel();
        componentPanelB.setLayout(new BoxLayout(componentPanelB, BoxLayout.Y_AXIS));
        add(componentPanelB);

        // 根据ItemList创建组件b
        for (String item : itemList) {
            addComponentB(item);
        }

        // 创建复选框C
        checkBoxC = new JCheckBox("Check Box C");
        add(checkBoxC);

        // 创建按钮D
        buttonD = new JButton("Button D");
        add(buttonD);

        pack(); // 自动调整窗口大小以适应内容
        setVisible(true);
    }

    private void addComponentB(String item) {
        // 创建b的容器，使用水平布局
        JPanel panelB = new JPanel(new FlowLayout(FlowLayout.LEFT, 5, 5));

        // 组件b1：标签
        JLabel labelB1 = new JLabel(item);
        panelB.add(labelB1);

        // 组件b2：文本框
        JTextField textFieldB2 = new JTextField(10);
        panelB.add(textFieldB2);

        // 组件b3：按钮
        JButton buttonB3 = new JButton("Button b3");
        panelB.add(buttonB3);

        // 将b（即panelB）添加到componentPanelB中
        componentPanelB.add(panelB);

        // 如果有需要，可以为每个b添加间隔
        componentPanelB.add(Box.createVerticalStrut(10));
    }

    /*public static void main(String[] args) {
        // 示例ItemList
        List<String> itemList = List.of("Item 1", "Item 2", "Item 3");

        // 创建并显示窗口
        SwingUtilities.invokeLater(() -> new CustomWindow(itemList));
    }*/
}