package ui;

import model.Card;

import javax.swing.*;
import java.awt.*;

public class CardViewerGUI extends JFrame {
    private static final int WIDTH = 600;
    private static final int HEIGHT = 800;

    private Card card;

    JLabel junk1;
    JLabel junk2;
    JLabel junk3;
    JLabel junk4;

    // EFFECTS: runs the Card Deck Builder application
    public CardViewerGUI(String title, Card c) {
        super(title);

        card = c;

        setPreferredSize(new Dimension(WIDTH, HEIGHT));
        setLayout(new GridLayout(6,2));

        initDetails();

        pack();
        setLocationRelativeTo(null);
        setVisible(true);
        setResizable(true);
    }

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

    private JLabel makeTitleField(String s) {
        JLabel imgTitle = new JLabel(s);
        imgTitle.setFont(new Font("Tahoma", Font.BOLD, 24));
        imgTitle.setHorizontalAlignment(JLabel.RIGHT);
        imgTitle.setVerticalAlignment(JLabel.TOP);
        return imgTitle;
    }

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
