package gui;

import javax.swing.*;
import java.io.Console;
import java.util.Locale;
import java.util.MissingResourceException;
import java.util.ResourceBundle;

public class Localizer {
    private static final String APP_BASE = "app";
    private static final String SWING_BASE = "swing"; 
    private static ResourceBundle appBundle;

    public static void applyLocale() {
        Locale locale = Locale.getDefault();
        appBundle = ResourceBundle.getBundle(APP_BASE, locale);
        ResourceBundle bundle = ResourceBundle.getBundle(SWING_BASE, locale);
        for (String s : bundle.keySet()) {
            System.out.println(bundle.getString(s));
        }
        bundle.keySet().forEach(key -> {
            UIManager.put(key, bundle.getString(key));
        });
    }

    public static String getString(String key) {
        if (appBundle == null) return key;
        try {
            return appBundle.getString(key);
        } catch (MissingResourceException e) {
            return key;
        }
    }
}
