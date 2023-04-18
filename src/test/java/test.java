import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class test {

    public static void main(String[] args) {

ImageIcon icon = new ImageIcon("Images/Exit.png");

        JButton button = new JButton();
        button.setBounds(100, 100, 50, 50);
        button.setIcon(icon);


        JFrame frame = new JFrame();

        frame.add(button);

        frame.setSize(450, 450);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
        frame.setLayout(new FlowLayout());
}
}
