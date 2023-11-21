package arkanoid.game.fonts;

import java.awt.*;
import java.io.IOException;
import java.io.InputStream;

public class MenuFont {
    private Font menuFont;

    public MenuFont() {
        try {
            InputStream is = getClass().getResourceAsStream("/Teko-SemiBold.ttf");
            menuFont = Font.createFont(Font.TRUETYPE_FONT, is);
        } catch (FontFormatException | IOException e) {
            System.out.println(e.getMessage());
        }
    }

    public Font getFont() {
        return menuFont;
    }

 }
