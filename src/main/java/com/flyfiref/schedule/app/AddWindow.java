package com.flyfiref.schedule.app;
import com.flyfiref.schedule.service.ItemService;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.text.SimpleDateFormat;

public class AddWindow extends JDialog {
    private ItemService itemService;
    JPanel componentPanelB;
    JButton buttonD;
    String[] args;

    public AddWindow(Frame owner, String title, boolean modal, ItemService itemService) {
        super(owner, title, modal);
        this.itemService=itemService;
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        init();
    }
    private void init(){
        args=new String[3];
        setLayout(new BoxLayout(getContentPane(), BoxLayout.Y_AXIS));
        // 组件B的容器，先创建JPanel，再设置BoxLayout
        componentPanelB = new JPanel();
        componentPanelB.setLayout(new BoxLayout(componentPanelB, BoxLayout.Y_AXIS));
        String[] strs = {"*起始时间：","*结束时间：","描述："};
        for(int i = 0; i<3;i++) {
            // 创建b的容器，使用水平布局
            JPanel panelB = new JPanel(new FlowLayout(FlowLayout.LEFT, 5, 5));
            // 组件b1：标签
            JLabel labelB1 = new JLabel(strs[i]);
            labelB1.setPreferredSize(new Dimension(80,20));
            panelB.add(labelB1);
            // 组件b2：文本框
            JTextField textFieldB2 = new JTextField(10);
            int finalI = i;
            textFieldB2.addFocusListener(new FocusAdapter() {
                @Override
                public void focusLost(FocusEvent e) {
                    super.focusLost(e);
                    JTextField tf= (JTextField) e.getSource();
                    String arg=tf.getText();
                    args[finalI]=arg;
                }
            });
            panelB.add(textFieldB2);
            // 将b（即panelB）添加到componentPanelB中
            componentPanelB.add(panelB);
            // 如果有需要，可以为每个b添加间隔
//            componentPanelB.add(Box.createVerticalStrut(10));
        }
        add(componentPanelB);
        // 创建按钮D
        buttonD = new JButton("添加");
        buttonD.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                SimpleDateFormat sdf=new SimpleDateFormat("h:m");
                try{
                    sdf.parse(args[0]);
                    sdf.parse(args[1]);
                    itemService.addItem(args);
                    JDialog dialog = (JDialog) SwingUtilities.getWindowAncestor((Component) e.getSource());
                    dialog.dispose();
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(null,"日期输入格式有误！","错误", JOptionPane.ERROR_MESSAGE);
                }
            }
        });
        add(buttonD);

        pack(); // 自动调整窗口大小以适应内容
        setVisible(true);
    }
}
