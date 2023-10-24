package fonts;

import java.awt.*;
import java.io.IOException;
import java.io.InputStream;

public class MenuFont {
    private Font menuFont;

    {
        try {
            InputStream is = getClass().getResourceAsStream("Galaxus.ttf");
            menuFont = Font.createFont(Font.TRUETYPE_FONT, is);
        } catch (FontFormatException | IOException e) {
            e.printStackTrace();
        }
    }

    public Font getFont() {
        return menuFont;
    }

 }
