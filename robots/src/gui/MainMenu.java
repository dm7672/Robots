package gui;

import log.Logger;

import javax.swing.*;
import java.awt.event.*;
import java.util.function.Supplier;


public class MainMenu {
    public static JMenuBar createMenuBar(JFrame owner) {
        JMenuBar menuBar = new JMenuBar();
        menuBar.add(createLookAndFeelMenu(owner));
        menuBar.add(createTestMenu());
        menuBar.add(createExitButton(owner));
        return menuBar;
    }

    private static JMenu createLookAndFeelMenu(JFrame owner) {
        JMenu menu = createMenu("menu.view", KeyEvent.VK_V);
        addLookAndFeelOption(menu, "menu.view.system", owner, () -> UIManager.getSystemLookAndFeelClassName());
        addLookAndFeelOption(menu, "menu.view.cross", owner, () -> UIManager.getCrossPlatformLookAndFeelClassName());
        return menu;
    }

    private static JMenu createTestMenu() {
        JMenu menu = createMenu("menu.tests", KeyEvent.VK_T);
        JMenuItem addLogMessageItem = createMenuItem("menu.tests.addlog", KeyEvent.VK_S,
                e -> Logger.debug("Новая строка"));

        menu.add(addLogMessageItem);
        return menu;
    }

    private static JButton createExitButton(JFrame owner) {
        JButton btn = new JButton(Localizer.getString("button.exit"));
        btn.setMnemonic(KeyEvent.VK_Q);
        btn.addActionListener(e -> MainApplicationFrame.confirmAndExit(owner));
        return btn;
    }

    private static JMenu createMenu(String textKey, int mnemonic) {
        String text = Localizer.getString(textKey);
        JMenu menu = new JMenu(text);
        menu.setMnemonic(mnemonic);
        menu.getAccessibleContext().setAccessibleDescription(text);
        return menu;
    }

    private static JMenuItem createMenuItem(String textKey, int mnemonic, ActionListener action) {
        String text = Localizer.getString(textKey);
        JMenuItem item = new JMenuItem(text, mnemonic);
        item.addActionListener(action);
        return item;
    }

    private static void addLookAndFeelOption(JMenu menu, String labelKey, JFrame owner, Supplier<String> lafSupplier) {
        JMenuItem item = createMenuItem(labelKey, KeyEvent.VK_S, e -> {
            String lafClass = lafSupplier.get();
            setLookAndFeel(owner, lafClass);
            owner.setJMenuBar(createMenuBar(owner));
            owner.invalidate();
        });
        menu.add(item);
    }

    private static void setLookAndFeel(JFrame owner, String className) {
        try {
            UIManager.setLookAndFeel(className);
            SwingUtilities.updateComponentTreeUI(owner);
        }
        catch (ClassNotFoundException | InstantiationException
               | IllegalAccessException | UnsupportedLookAndFeelException e)
        {
            // just ignore
        }
    }
}