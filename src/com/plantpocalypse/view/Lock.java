package com.plantpocalypse.view;

import com.plantpocalypse.model.Game;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Lock implements ActionListener {
    private static JTextField inputBox;
    private static JFrame frame;
    private final Game game = Game.GAME_INSTANCE;

    public Lock(){}
//    public static void main(String[] args) {
//        try {
//            UIManager.setLookAndFeel("com.jtattoo.plaf.noire.NoireLookAndFeel");
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//        createWindow();
//    }

    private static void createWindow() {
        frame = new JFrame("Plantpocalypse");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        createUI(frame);
        frame.setSize(300, 300);
        frame.setLocationRelativeTo(null);
        frame.setVisible(false);
    }

    private static void createUI(JFrame frame) {
        JPanel panel = new JPanel();
        Lock lock = new Lock();
        GridBagLayout layout = new GridBagLayout();
        GridBagConstraints gbc = new GridBagConstraints();
        panel.setLayout(layout);

        inputBox = new JTextField(10);
        inputBox.setEditable(false);

        JButton button0 = new JButton("0");
        JButton button1 = new JButton("1");
        JButton button2 = new JButton("   2   ");
        JButton button3 = new JButton("3");
        JButton button4 = new JButton("4");
        JButton button5 = new JButton("   5   ");
        JButton button6 = new JButton("6");
        JButton button7 = new JButton("7");
        JButton button8 = new JButton("   8   ");
        JButton button9 = new JButton("9");
        JButton enter = new JButton("Enter");
        JButton clear = new JButton("Clear");

        button1.addActionListener(lock);
        button2.addActionListener(lock);
        button3.addActionListener(lock);
        button4.addActionListener(lock);
        button5.addActionListener(lock);
        button6.addActionListener(lock);
        button7.addActionListener(lock);
        button8.addActionListener(lock);
        button9.addActionListener(lock);
        button0.addActionListener(lock);
        enter.addActionListener(lock);
        clear.addActionListener(lock);

        frame.getContentPane().add(panel, BorderLayout.CENTER);
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.gridx = 0; gbc.gridy = 1; panel.add(button1, gbc);
        gbc.gridx = 1; gbc.gridy = 1; panel.add(button2, gbc);
        gbc.gridx = 2; gbc.gridy = 1; panel.add(button3, gbc);
        gbc.gridx = 0; gbc.gridy = 2; panel.add(button4, gbc);
        gbc.gridx = 1; gbc.gridy = 2; panel.add(button5, gbc);
        gbc.gridx = 2; gbc.gridy = 2; panel.add(button6, gbc);
        gbc.gridx = 0; gbc.gridy = 3; panel.add(button7, gbc);
        gbc.gridx = 1; gbc.gridy = 3; panel.add(button8, gbc);
        gbc.gridx = 2; gbc.gridy = 3; panel.add(button9, gbc);
        gbc.gridx = 0; gbc.gridy = 4; panel.add(clear, gbc);
        gbc.gridx = 1; gbc.gridy = 4; panel.add(button0, gbc);
        gbc.gridx = 2; gbc.gridy = 4; panel.add(enter, gbc);
        gbc.gridwidth = 3;
        gbc.gridx = 0; gbc.gridy = 0; panel.add(inputBox, gbc);

    }

    public void actionPerformed(ActionEvent e) {
        String command = e.getActionCommand().strip();
        if (command == "Clear") {
            inputBox.setText("");
        }else if (command == "Enter") {
            if(inputBox.getText().equals("135")) {
                inputBox.setText("Opened");
                Timer timer = new Timer(1000, close);
                timer.setRepeats(false);
                timer.start();
            } else {
                inputBox.setText("Wrong code");
                Timer timer = new Timer(1000, clear);
                timer.setRepeats(false);
                timer.start();
            }
        }else {
            inputBox.setText(inputBox.getText() + command);
        }
    }

    ActionListener close = new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            game.getPlayer().getCurrentRoom().getNeighboringRooms().get("east").toggleLock();
            frame.setVisible(false);
            frame.dispose();
        }
    };

    ActionListener clear = new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            inputBox.setText("");
        }
    };
}

