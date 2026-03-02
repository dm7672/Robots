package gui;

import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.*;
import java.util.Locale;

import javax.swing.*;

import log.Logger;

import static gui.MainMenu.createMenuBar;

public class MainApplicationFrame extends JFrame
{
    private final JDesktopPane desktopPane = new JDesktopPane();

    public MainApplicationFrame() {
        int inset = 50;
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        setBounds(inset, inset,
                screenSize.width  - inset*2,
                screenSize.height - inset*2);

        setContentPane(desktopPane);

        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                confirmAndExit(MainApplicationFrame.this);
            }
        });

        LogWindow logWindow = createLogWindow();
        addWindow(logWindow);

        GameWindow gameWindow = new GameWindow();
        gameWindow.setSize(400,  400);
        addWindow(gameWindow);

        setJMenuBar(createMenuBar(this));
        setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);

        // Привязка Ctrl+Q для выхода
        JRootPane root = getRootPane();
        root.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(
                KeyStroke.getKeyStroke(KeyEvent.VK_Q, InputEvent.CTRL_DOWN_MASK), "appQuit");
        root.getActionMap().put("appQuit", new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                confirmAndExit(MainApplicationFrame.this);
            }
        });
    }

    protected LogWindow createLogWindow()
    {
        LogWindow logWindow = new LogWindow(Logger.getDefaultLogSource());
        logWindow.setLocation(10,10);
        logWindow.setSize(300, 800);
        setMinimumSize(logWindow.getSize());
        logWindow.pack();
        Logger.debug("Протокол работает");
        return logWindow;
    }

    protected void addWindow(JInternalFrame frame)
    {
        desktopPane.add(frame);
        frame.setVisible(true);
    }

    public static void confirmAndExit(java.awt.Component parent) {
        String message = Localizer.getString("app.exit.confirm");
        String title = Localizer.getString("app.exit.title");
        int result = JOptionPane.showConfirmDialog(
                parent,
                message,
                title,
                JOptionPane.YES_NO_OPTION);

        if (result == JOptionPane.YES_OPTION) {
            System.exit(0);
        }
    }
}