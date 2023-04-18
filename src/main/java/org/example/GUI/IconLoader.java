package org.example.GUI;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.*;

public class IconLoader {
    public static Icon loadIcon() {
        BufferedImage icon = null;
        try {
            File file = new File("resources/icon.png");
            icon = ImageIO.read(file);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return (Icon) icon;
    }


}
