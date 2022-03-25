package ui;

// Represents a graphical user interface to edit cards

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
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CardEditorGUI extends JFrame implements ActionListener {
    private static final int WIDTH = 600;
    private static final int HEIGHT = 800;

    private final Card card;

    JPanel junk1;
    JPanel description;
    JPanel points;
    JPanel favourites;

    // EFFECTS: runs the card editor GUI
    public CardEditorGUI(String title, Card c) {
        super(title);
        card = c;

        junk1 = new JPanel();
        description = new JPanel();
        points = new JPanel();
        favourites = new JPanel();

        setPreferredSize(new Dimension(WIDTH, HEIGHT));
        setLayout(new GridLayout(4, 1));

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

    // EFFECTS: adds editing buttons to each panel
    private void initButtons() {
        makeButton("Edit Description", "updateDescription", description);

        points.add(new JLabel("Set the difficulty: "));

        makeButton("easy", "points1", points);
        makeButton("medium", "points2", points);
        makeButton("hard", "points3", points);

        makeButton("\"Favourite\" this card.", "favouriteTrue", favourites);
        makeButton("\"Unfavourite\" this card.", "favouriteFalse", favourites);
    }

    // EFFECTS: makes a button with a given title, action, and panel location
    private void makeButton(String s, String action, JPanel deckEditPanel) {
        JButton newButton = new JButton(s);
        newButton.setFont(new Font("Arial", Font.PLAIN, 30));
        newButton.setActionCommand(action);
        newButton.addActionListener(this);
        deckEditPanel.add(newButton);
    }

    // EFFECTS: responds to user actions
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
