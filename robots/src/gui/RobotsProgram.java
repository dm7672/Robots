package gui;

import java.awt.Frame;
import java.util.Locale;

import javax.swing.*;

public class RobotsProgram
{
    public static void main(String[] args) {
        Locale.setDefault(Locale.of("ru", "RU"));
        Localizer.applyLocale("swing");
      try {
        UIManager.setLookAndFeel("javax.swing.plaf.nimbus.NimbusLookAndFeel");
//        UIManager.setLookAndFeel("javax.swing.plaf.metal.MetalLookAndFeel");
//        UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
//        UIManager.setLookAndFeel(UIManager.getCrossPlatformLookAndFeelClassName());
      } catch (Exception e) {
        e.printStackTrace();
      }
      SwingUtilities.invokeLater(() -> {
        MainApplicationFrame frame = new MainApplicationFrame();
        frame.pack();
        frame.setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE);
        frame.setVisible(true);
        frame.setExtendedState(Frame.MAXIMIZED_BOTH);
      });
    }
}
