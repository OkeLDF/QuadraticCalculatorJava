package menu;

import java.awt.Color;
import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.GraphicsEnvironment;
import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;

public interface Style {
    public static final Color darkBlueColor = new Color(12, 12, 28);
    public static final Color darkGreenColor = new Color(17, 0, 28);
    
    public static final Color blueColor = new Color(9, 153, 247);
    public static final Color lightBlueColor = new Color(132, 210, 251);
    
    public static final Color greenColor = new Color(153, 91, 213);
    public static final Color lightGreenColor = new Color(191, 153, 242);

    public static final Path mathFontPath = Paths.get("font", "GFSArtemisiaBold.otf");
    public static final Path pixelFontPath = Paths.get("font", "VCR_OSD_MONO_1.001.ttf");

    default Font getMathFont(){
        Font mathFont = new Font("Arial", Font.PLAIN, 26);;
        try {
            mathFont = Font.createFont(Font.TRUETYPE_FONT, new File(mathFontPath.toString()));
            GraphicsEnvironment ge = 
                GraphicsEnvironment.getLocalGraphicsEnvironment();
            ge.registerFont(mathFont);
            return new Font(mathFont.getName(), Font.PLAIN, 26);
        } catch (IOException|FontFormatException e) {
            mathFont = new Font("Arial", Font.PLAIN, 26);
            e.printStackTrace();
        }

        return mathFont;
    }

    default Font getPixelFont(){
        Font pixelFont = new Font("Arial Bold", Font.PLAIN, 24);;
        try {
            pixelFont = Font.createFont(Font.TRUETYPE_FONT, new File(pixelFontPath.toString()));
            GraphicsEnvironment ge = 
                GraphicsEnvironment.getLocalGraphicsEnvironment();
            ge.registerFont(pixelFont);
            return new Font(pixelFont.getName(), Font.PLAIN, 24);
        } catch (IOException|FontFormatException e) {
            pixelFont = new Font("Arial Bold", Font.PLAIN, 24);
            e.printStackTrace();
        }

        return pixelFont;
    }
}
