package gui;

import log.Logger;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;


public class MainMenu {
    public static JMenuBar createMenuBar(JFrame owner) {
        JMenuBar menuBar = new JMenuBar();
        menuBar.add(createLookAndFeelMenu(owner));
        menuBar.add(createTestMenu());
        menuBar.add(createExitButton());
        return menuBar;
    }


    private static JMenu createLookAndFeelMenu(JFrame owner){
        JMenu lookAndFeelMenu = new JMenu("Режим отображения");
        lookAndFeelMenu.setMnemonic(KeyEvent.VK_V);
        lookAndFeelMenu.getAccessibleContext().setAccessibleDescription(
                "Управление режимом отображения приложения");

        {
            JMenuItem systemLookAndFeel = new JMenuItem("Системная схема", KeyEvent.VK_S);
            systemLookAndFeel.addActionListener((event) -> {
                setLookAndFeel(owner, UIManager.getSystemLookAndFeelClassName());
                owner.invalidate();
            });
            lookAndFeelMenu.add(systemLookAndFeel);
        }

        {
            JMenuItem crossplatformLookAndFeel = new JMenuItem("Универсальная схема", KeyEvent.VK_S);
            crossplatformLookAndFeel.addActionListener((event) -> {
                setLookAndFeel(owner, UIManager.getCrossPlatformLookAndFeelClassName());
                owner.invalidate();
            });
            lookAndFeelMenu.add(crossplatformLookAndFeel);
        }
        return lookAndFeelMenu;
    }

    private static JMenu createTestMenu() {
        JMenu testMenu = new JMenu("Тесты");
        testMenu.setMnemonic(KeyEvent.VK_T);
        testMenu.getAccessibleContext().setAccessibleDescription(
                "Тестовые команды");

        {
            JMenuItem addLogMessageItem = new JMenuItem("Сообщение в лог", KeyEvent.VK_S);
            addLogMessageItem.addActionListener((event) -> {
                Logger.debug("Новая строка");
            });
            testMenu.add(addLogMessageItem);
        }

        return testMenu;
    }

    private static JButton createExitButton(){
        JButton quitMenuButton = new JButton("Выйти");
        quitMenuButton.setMnemonic(KeyEvent.VK_Q);
        quitMenuButton.addActionListener(new exitApp());

        return quitMenuButton;
    }

    private static void setLookAndFeel(JFrame owner, String className)
    {
        try
        {
            UIManager.setLookAndFeel(className);
            SwingUtilities.updateComponentTreeUI(owner);
        }
        catch (ClassNotFoundException | InstantiationException
               | IllegalAccessException | UnsupportedLookAndFeelException e)
        {
            // just ignore
        }
    }

    static class exitApp implements ActionListener
    {
        public void actionPerformed(ActionEvent e)
        {
            MainApplicationFrame.confirmAndExit();
        }
    }
}
