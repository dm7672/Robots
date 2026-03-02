package gui;

import javax.swing.*;
import java.io.Console;
import java.util.Locale;
import java.util.ResourceBundle;

public class Localizer {
    public static void applyLocale(String name) {
        Locale locale = Locale.getDefault();
        ResourceBundle bundle = ResourceBundle.getBundle(name, locale);
        for (String s : bundle.keySet()) {
            System.out.println(bundle.getString(s));
        }
        bundle.keySet().forEach(key -> {
            UIManager.put(key, bundle.getString(key));
        });
    }
}
