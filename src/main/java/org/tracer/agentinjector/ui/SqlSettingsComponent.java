package org.tracer.agentinjector.ui;


import com.intellij.openapi.project.Project;
import com.intellij.ui.components.JBCheckBox;
import com.intellij.ui.components.JBTextField;

import javax.swing.*;
import java.awt.*;

public class SqlSettingsComponent {
    private final JBCheckBox enableFeatureCheckBox = new JBCheckBox("控制台打印日志");

    private final JBTextField statusTextField = new JBTextField(20);
    private final JPanel panel;

    private static SqlSettingsComponent instance;

    public SqlSettingsComponent(Project project) {

        instance = this; // 设置单例


        // 使用垂直布局
        panel = new JPanel();
        panel.setLayout(new FlowLayout(FlowLayout.LEFT, 5, 5)); // 左上对齐


        // 创建一个垂直 Box，用于存放标题和 checkbox
        Box verticalBox = Box.createVerticalBox();
        verticalBox.add(Box.createRigidArea(new Dimension(30, 10))); // 添加间距
        panel.add(verticalBox);

        // 初始化 checkbox 状态
        boolean currentState = SqlPluginStore.getInstance(project).isFeatureEnabled();
        enableFeatureCheckBox.setSelected(currentState);
        // 添加监听器，保存状态
        enableFeatureCheckBox.addItemListener(e -> {
            boolean selected = e.getStateChange() == java.awt.event.ItemEvent.SELECTED;
            SqlPluginStore.getInstance(project).setFeatureEnabled(selected);
        });
        verticalBox.add(enableFeatureCheckBox);

        JPanel textPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        textPanel.add(new JLabel("状态："));
        // 设置文本框为只读
        statusTextField.setEditable(false);
        statusTextField.setEnabled(false); // 禁用编辑
        textPanel.add(statusTextField);
        verticalBox.add(textPanel);
    }

    public JPanel getPanel() {
        return panel;
    }

    public boolean isFeatureEnabled() {
        return enableFeatureCheckBox.isSelected();
    }

    public void setFeatureEnabled(boolean enabled) {
        enableFeatureCheckBox.setSelected(enabled);
    }

    // 更新文本框内容
    // 实例方法
    private void updateStatusText(String text) {
        statusTextField.setText(text);
    }

    // ✅ 全局可用的静态方法
    public static void updateGlobalStatusText(String text) {
        if (instance != null) {
            instance.updateStatusText(text);
        }
    }
}
