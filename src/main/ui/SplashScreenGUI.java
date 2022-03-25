package ui;

// Represents a graphical user interface that users can use to build decks

// Created with assistance from TellerApp and JsonSerializationDemo:
//   https://github.students.cs.ubc.ca/CPSC210/TellerApp
//   https://github.students.cs.ubc.ca/CPSC210/JsonSerializationDemo

// GUI created with assistance from following projects & code from Project Phase 3 page:
//   https://github.students.cs.ubc.ca/CPSC210/C3-LectureLabSolution
//   https://github.students.cs.ubc.ca/CPSC210/B02-SpaceInvadersBase
//   https://github.students.cs.ubc.ca/CPSC210/AlarmSystem
//   https://github.students.cs.ubc.ca/CPSC210/SimpleDrawingPlayer-Complete
//   https://learning.edge.edx.org/course/course-v1:UBC+CPSC210+all/block-v1:UBC+CPSC210+all+type@sequential+block
//    @2319e011dd3848d5940b8d7aa19ad5d9/block-v1:UBC+CPSC210+all+type@vertical+block@45c6cfa614d8417ebcf74d1fed323c24

// Also made with assistance from the following:
// https://stackoverflow.com/questions/15746984/how-to-run-jframe-maximized-in-java   (for window formatting tips)
// https://docs.oracle.com/javase/tutorial/uiswing/components/html.html               (for font editing)
// https://stackoverflow.com/questions/20462167/increasing-font-size-in-a-jbutton     (for button sizing)
// https://docs.oracle.com/javase/tutorial/uiswing/components/menu.html#create

// This specific class also used information from
// https://stackoverflow.com/questions/16134549/how-to-make-a-splash-screen-for-gui  (for general splash screen tips)
// https://stackoverflow.com/questions/8701716/how-to-remove-title-bar-in-jframe     (for removing title bars)
// https://stackoverflow.com/questions/11844927/java-transparent-window              (for making window transparent)

import javax.swing.*;
import java.awt.*;
import java.io.FileNotFoundException;

public class SplashScreenGUI extends JFrame {
    private static final ImageIcon IMAGE_ICON = new ImageIcon("./data/image/imageSplashScreen800Glow.png");
    private static final int WIDTH = IMAGE_ICON.getIconWidth();
    private static final int HEIGHT = IMAGE_ICON.getIconHeight();
    private static final int TIME = 1000;

    // EFFECTS: runs the splash screen to display image
    public SplashScreenGUI(String title) {
        super(title);

        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setPreferredSize(new Dimension(WIDTH + 100, HEIGHT + 100));

        showSplashScreen();

        setUndecorated(true);
        setBackground(new Color(0, 0, 0, 0));
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

    // EFFECTS: displays splash screen image
    private void showSplashScreen() {
        JLabel splash = new JLabel(IMAGE_ICON);
        add(splash);
    }
}
