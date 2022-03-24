package ui;

import model.Card;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CardEditorGUI extends JFrame implements ActionListener {
    private static final int WIDTH = 600;
    private static final int HEIGHT = 800;

    private Card card;
    JPanel junk1;
    JPanel description;
    JPanel points;
    JPanel favourites;

    // EFFECTS: runs the Card Deck Builder application
    public CardEditorGUI(String title, Card c) {
        super(title);
        card = c;

        junk1 = new JPanel();
        description = new JPanel();
        points = new JPanel();
        favourites = new JPanel();

        setPreferredSize(new Dimension(WIDTH, HEIGHT));
        setLayout(new GridLayout(4,1));

        initButtons();

        add(junk1);
        add(description);
        add(points);
        add(favourites);

        pack();
        setLocationRelativeTo(null);
        setVisible(true);
        setResizable(true);
    }

    private void initButtons() {
        makeButton("Edit Description", "updateDescription", description);

        points.add(new JLabel("Set the difficulty: "));

        makeButton("easy", "points1", points);
        makeButton("medium", "points2", points);
        makeButton("hard", "points3", points);

        makeButton("\"Favourite\" this card.", "favouriteTrue", favourites);
        makeButton("\"Unfavourite\" this card.", "favouriteFalse", favourites);
    }

    private void makeButton(String s, String action, JPanel deckEditPanel) {
        JButton newButton = new JButton(s);
        newButton.setFont(new Font("Arial", Font.PLAIN, 30));
        newButton.setActionCommand(action);
        newButton.addActionListener(this);
        deckEditPanel.add(newButton);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getActionCommand().equals("updateDescription")) {
            String newDescription = JOptionPane.showInputDialog(null, "Enter a new description:");
            card.setDescription(newDescription);
        } else if (e.getActionCommand().equals("points1")) {
            card.setPointsFromCode(1);
            JOptionPane.showMessageDialog(this, "Updated difficulty to 'Easy'");
        } else if (e.getActionCommand().equals("points2")) {
            card.setPointsFromCode(2);
            JOptionPane.showMessageDialog(this, "Updated difficulty to 'Medium'");
        } else if (e.getActionCommand().equals("points3")) {
            card.setPointsFromCode(3);
            JOptionPane.showMessageDialog(this, "Updated difficulty to 'Hard'");
        } else if (e.getActionCommand().equals("favouriteTrue")) {
            card.setIsFavourite(true);
            JOptionPane.showMessageDialog(this, "Card has been marked as a favourite.");
        } else if (e.getActionCommand().equals("favouriteFalse")) {
            card.setIsFavourite(true);
            JOptionPane.showMessageDialog(this, "Card is not marked as a favourite.");
        }
    }
}
