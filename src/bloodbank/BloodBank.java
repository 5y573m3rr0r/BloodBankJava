package bloodbank;

import java.awt.*;
import java.awt.event.*;
import java.awt.image.*;
import java.io.*;
import javax.imageio.ImageIO;

public class BloodBank extends Frame {

    BloodBank() {

        setTitle("Blood Bank");
        Image icon = Toolkit.getDefaultToolkit().getImage("mainIcon.jpg");
        setIconImage(icon);
        setSize(760, 400);
        setVisible(true);
        setResizable(true);
        setLocation(300, 200);
        setLayout(new FlowLayout(FlowLayout.LEADING));
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent windowEvent) {
                dispose();
            }
        });
        Font getStartedButtonFont = new Font("Varenda", Font.BOLD, 20);
        Button getStartedButton = new Button("Get Started");

        getStartedButton.setBounds(10, 30, 85, 50);

        getStartedButton.setFont(getStartedButtonFont);
        getStartedButton.setBackground(Color.LIGHT_GRAY);
        getStartedButton.setForeground(Color.BLACK);
        add(getStartedButton);
        getStartedButton.addActionListener((ActionEvent actionEvent) -> {
            Object source;
            source = actionEvent.getSource();
            if (source == getStartedButton) {
                BloodBankOptions bloodBankOptions = new BloodBankOptions();
                bloodBankOptions.backgroundImage();
                dispose();
            }
        });
    }
    BufferedImage backgroundImage;

    public void backgroundImage() {
        try {

            backgroundImage = ImageIO.read(new File("backGroundImage.jpeg"));

        } catch (IOException ex) {
        }
        setSize(760, 400);
        setVisible(true);
        setResizable(true);
    }

    @Override
    public void paint(Graphics graphics) {
        super.paint(graphics);
        graphics.drawImage(backgroundImage, 0, 0, getWidth(), getHeight(), null);
    }

    public static void main(String[] args) {
        BloodBank bloodBank = new BloodBank();
        bloodBank.backgroundImage();
    }

}
