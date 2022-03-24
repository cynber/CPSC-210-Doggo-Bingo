package ui;

// TOOK FROM:       C3-LectureLabSolution IntersectionGUI    & Sample code from project P3 page
// https://stackoverflow.com/questions/16134549/how-to-make-a-splash-screen-for-gui
// https://stackoverflow.com/questions/8701716/how-to-remove-title-bar-in-jframe
// https://stackoverflow.com/questions/11844927/java-transparent-window

import javax.swing.*;
import java.awt.*;
import java.io.FileNotFoundException;

public class SplashScreenGUI extends JFrame {
    private static final ImageIcon IMAGE_ICON = new ImageIcon("./data/image/imageSplashScreen800Glow.png");
    private static final int WIDTH = IMAGE_ICON.getIconWidth();
    private static final int HEIGHT = IMAGE_ICON.getIconHeight();
    private static final int TIME = 1000;

    // EFFECTS: runs the Card Deck Builder application
    public SplashScreenGUI(String title) {
        super(title);

        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setPreferredSize(new Dimension(WIDTH + 100, HEIGHT + 100));

        showSplashScreen();

        setUndecorated(true);
        setBackground(new Color(0,0,0,0));
        pack();
        setLocationRelativeTo(null);
        setVisible(true);
        setResizable(true);

        try {
            Thread.sleep(TIME);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        setVisible(false);
    }

    private void showSplashScreen() {
        JLabel splash = new JLabel(IMAGE_ICON);
        add(splash);
    }
}
