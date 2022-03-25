package ui;

// Represents a graphical user interface to view card details

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

import model.Card;

import javax.swing.*;
import java.awt.*;

public class CardViewerGUI extends JFrame {
    private static final int WIDTH = 600;
    private static final int HEIGHT = 800;

    private final Card card;

    JLabel junk1;
    JLabel junk2;
    JLabel junk3;
    JLabel junk4;

    // EFFECTS: runs the Card viewer GUI
    public CardViewerGUI(String title, Card c) {
        super(title);

        card = c;

        setPreferredSize(new Dimension(WIDTH, HEIGHT));
        setLayout(new GridLayout(6, 2));

        initDetails();

        pack();
        setLocationRelativeTo(null);
        setVisible(true);
        setResizable(true);
    }

    // EFFECTS: initializes the card details
    private void initDetails() {
        //JLabel imgTitle = new JLabel(new ImageIcon("./data/image/cardViewerTitle.png"));
        //JLabel imgDescription = new JLabel(new ImageIcon("./data/image/cardViewerDescription.png"));
        //JLabel imgDifficulty = new JLabel(new ImageIcon("./data/image/cardViewerDifficulty.png"));
        //JLabel imgFavourite = new JLabel(new ImageIcon("./data/image/cardViewerFavourite.png"));

        JLabel imgTitle = makeTitleField("Card Title:  ");
        JLabel imgDescription = makeTitleField("Card description:  ");
        JLabel imgDifficulty = makeTitleField("Card Difficulty:  ");
        JLabel imgFavourite = makeTitleField("Card favourite status:  ");

        JTextArea title = makeDetailField(card.getTitle());
        JTextArea description = makeDetailField(card.getDescription());
        JTextArea difficulty = makeDetailField(card.getDifficulty());
        JTextArea favourite = makeDetailField(getFavouritePhrase(card.isFavourite()));

        add(junk1 = new JLabel(""));
        add(junk2 = new JLabel(""));

        add(imgTitle);
        add(title);

        add(imgDescription);
        add(description);

        add(imgDifficulty);
        add(difficulty);

        add(imgFavourite);
        add(favourite);

        add(junk3 = new JLabel(""));
        add(junk4 = new JLabel(""));
    }

    // EFFECTS: creates JLabel for title field
    private JLabel makeTitleField(String s) {
        JLabel imgTitle = new JLabel(s);
        imgTitle.setFont(new Font("Tahoma", Font.BOLD, 24));
        imgTitle.setHorizontalAlignment(JLabel.RIGHT);
        imgTitle.setVerticalAlignment(JLabel.TOP);
        return imgTitle;
    }

    // EFFECTS: creates JLabel for detail field
    private JTextArea makeDetailField(String fieldName) {
        JTextArea field = new JTextArea(fieldName);
        field.setFont(new Font("Tahoma", Font.PLAIN, 24));
        field.setEditable(false);
        field.setLineWrap(true);
        field.setWrapStyleWord(true);
        field.setBackground(UIManager.getColor("Label.background"));
        field.setAlignmentY(CENTER_ALIGNMENT);
        return field;
    }

    // EFFECTS: gets the phrase corresponding to favourite status
    private String getFavouritePhrase(Boolean favourite) {
        String output;
        if (favourite) {
            output = "This card IS marked as a favourite.";
        } else {
            output = "This card is NOT marked as a favourite.";
        }
        return output;
    }
}
