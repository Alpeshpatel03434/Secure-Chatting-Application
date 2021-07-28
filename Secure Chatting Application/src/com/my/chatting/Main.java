package com.my.chatting;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Main extends JFrame implements ActionListener {

    // Title
    JTextArea tittle;

    // Button
    JButton user1, user2, exit;

    Main(){

        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setTitle("Secure Chatting Application with AES Algorithm");
        this.setSize(500, 600);
        this.setLocation(450, 100);
        this.setLayout(null);

        // For title
        tittle = new JTextArea("Secure Chatting Application with " +
                "AES Algorithm End to End Encryption and Decryption");
        tittle.setBounds(60, 30, 360, 50);
        tittle.setFont(new Font("SENS_SERIF", Font.BOLD, 18));
        tittle.setBackground(Color.GRAY);
        tittle.setForeground(Color.GREEN);
        tittle.setLineWrap(true);
        tittle.setWrapStyleWord(true);
        tittle.setEditable(false);
        this.add(tittle);

        // User Server site
        user1 = new JButton("Server");
        user1.setFont(new Font("Serif", Font.BOLD, 16));
        user1.setBackground(Color.BLUE);
        user1.setBounds(80, 220, 100, 40);
        user1.addActionListener(this);
        this.add(user1);

        // User Client site
        user2 = new JButton("Client");
        user2.setFont(new Font("Serif", Font.BOLD, 16));
        user2.setBackground(Color.YELLOW);
        user2.setBounds(300, 220, 100, 40);
        user2.addActionListener(this);
        this.add(user2);

        // Exit
        exit = new JButton("Exit");
        exit.setFont(new Font("Serif", Font.BOLD, 16));
        exit.setBackground(Color.RED);
        exit.setBounds(200, 350, 100, 40);
        exit.addActionListener(this);
        this.add(exit);

        this.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        // Press user1 button
        if (e.getSource()==user1) {
            Server obj = new Server();
            obj.setVisible(true);
        }

        // Press user2 button
        if (e.getSource()==user2) {
            Client obj = new Client();
            obj.setVisible(true);
        }

        // System exit
        if (e.getSource()==exit) {
            System.exit(0);
        }

    }

    public static void main(String[] args) {

        new Main().setVisible(true);

    }
}
