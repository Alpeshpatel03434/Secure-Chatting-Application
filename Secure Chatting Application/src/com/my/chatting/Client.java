package com.my.chatting;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class Client extends JFrame implements ActionListener {

    JPanel panel1;

    JTextField message;
    JButton send;
    static JTextArea textArea;

    // Communication Client site using Socket
    static Socket socket;

    BufferedReader bufferedReader;
    PrintWriter printWriter;

    Client(){

        CreateGUI();
        try {

            socket = new Socket("127.0.0.1", 1910);

            bufferedReader = new BufferedReader(new InputStreamReader(socket.getInputStream()));

            printWriter = new PrintWriter(socket.getOutputStream());

            StartReading();
            // StartWriting();

        } catch (Exception e) {

            e.printStackTrace();

        }


    }

    public void CreateGUI() {

        this.setSize(450, 700);
        this.setLocation(800, 40);
        this.setLayout(null);

        panel1 = new JPanel();
        panel1.setLayout(null);
        panel1.setBackground(new Color(0, 129, 129));
        panel1.setBounds(0, 0, 450, 70);

        // Left Arrow
        ImageIcon icon1 = new ImageIcon(ClassLoader.getSystemResource("img/leftArrow.png"));
        Image image1 = icon1.getImage().getScaledInstance(30, 30, Image.SCALE_DEFAULT);
        ImageIcon new_icon1 = new ImageIcon(image1);
        JLabel label1 = new JLabel(new_icon1);
        label1.setBounds(5, 18, 30, 30);
        panel1.add(label1);

        // Arrow Lister Activity
        label1.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {

                System.exit(0);

            }

            @Override
            public void mousePressed(MouseEvent e) {

            }

            @Override
            public void mouseReleased(MouseEvent e) {

            }

            @Override
            public void mouseEntered(MouseEvent e) {

            }

            @Override
            public void mouseExited(MouseEvent e) {

            }
        });


        ImageIcon icon2 = new ImageIcon(ClassLoader.getSystemResource("img/modi.png"));
        Image image2 = icon2.getImage().getScaledInstance(60, 60, Image.SCALE_DEFAULT);
        ImageIcon new_icon2 = new ImageIcon(image2);
        JLabel label2 = new JLabel(new_icon2);
        label2.setBounds(40, 5, 60, 60);
        panel1.add(label2);

        ImageIcon icon3 = new ImageIcon(ClassLoader.getSystemResource("img/video.png"));
        Image image3 = icon3.getImage().getScaledInstance(30, 30, Image.SCALE_DEFAULT);
        ImageIcon new_icon3 = new ImageIcon(image3);
        JLabel label3 = new JLabel(new_icon3);
        label3.setBounds(300, 20, 30, 30);
        panel1.add(label3);

        ImageIcon icon4 = new ImageIcon(ClassLoader.getSystemResource("img/phone.png"));
        Image image4 = icon4.getImage().getScaledInstance(35, 30, Image.SCALE_DEFAULT);
        ImageIcon new_icon4 = new ImageIcon(image4);
        JLabel label4 = new JLabel(new_icon4);
        label4.setBounds(350, 20, 35, 30);
        panel1.add(label4);

        ImageIcon icon5 = new ImageIcon(ClassLoader.getSystemResource("img/3dotte.png"));
        Image image5 = icon5.getImage().getScaledInstance(14, 25, Image.SCALE_DEFAULT);
        ImageIcon new_icon5 = new ImageIcon(image5);
        JLabel label5 = new JLabel(new_icon5);
        label5.setBounds(410, 20, 14, 25);
        panel1.add(label5);

        // Client name
        JLabel name = new JLabel("Narendra Modi");
        name.setFont(new Font("SAN_SERIF", Font.BOLD, 18));
        name.setForeground(Color.WHITE);
        name.setBounds(110, 15, 100, 18);
        panel1.add(name);

        JLabel active_label = new JLabel("Active Now");
        active_label.setFont(new Font("SAN_SERIF", Font.PLAIN, 14));
        active_label.setForeground(Color.WHITE);
        active_label.setBounds(110, 35, 100, 20);
        panel1.add(active_label);

        // Add panel in main frame
        this.add(panel1);


        // Message box
        message = new JTextField();
        message.setFont(new Font("SAN_SERIF", Font.PLAIN, 18));
        message.setBounds(8, 650, 320, 40);
        this.add(message);

        // send button
        send = new JButton("Send");
        send.setBackground(new Color(47, 79, 79));
        send.setForeground(Color.WHITE);
        send.setFont(new Font("PLAINTEXT", Font.BOLD, 16));
        send.setBounds(323, 650, 120, 40);
        send.addActionListener(this);
        this.add(send);

        // Text Area Interface
        textArea = new JTextArea();
        textArea.setFont(new Font("SAN_SERIF", Font.PLAIN, 16));
        textArea.setEditable(false);
        textArea.setLineWrap(true);
        textArea.setWrapStyleWord(true);
        textArea.setBounds(5, 75, 440, 570);
        this.add(textArea);

        // Close and title header hidden
        this.setUndecorated(true);

        this.setVisible(true);

    }

    @Override
    public void actionPerformed(ActionEvent e) {

        // After Pressing send button
        if (e.getSource()==send){

            try {

                // Get message
                String msg = message.getText();

                // Encrypted message
                String encrypted_msg = myAES.Encrypt(msg);

                // Send Encrypted message to Server User
                printWriter.println(encrypted_msg);
                textArea.setText(textArea.getText()+"\n\t\t"+msg);
                printWriter.flush();
                message.setText("");

            } catch (Exception exception) {

                exception.printStackTrace();

            }

        }

    }


    // Catch Server site Message
    public void StartReading() {

        Runnable runnable1=() -> {

            while (true) {

                try {

                    String msg = bufferedReader.readLine();

                    // Decrypted message
                    String decrypted_msg = myAES.Decrypt(msg);

                    if (decrypted_msg.equals("exit")) {
                        System.out.println("Server termenited");
                        JOptionPane.showMessageDialog(this, "Client Terminated");
                        message.setFocusable(false);

                        //close
                        socket.close();

                        break;
                    }

                    textArea.setText(textArea.getText()+"\n"+decrypted_msg);

                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

        };

        new Thread(runnable1).start();

    }

    public static void main(String[] args) {

        new Client();

    }

}