package fonts;

import java.awt.*;
import java.io.IOException;
import java.io.InputStream;

public class GameFont {
    private Font gameFont;

    {
        try {
            InputStream is = getClass().getResourceAsStream("Arcade.TTF");
            gameFont = Font.createFont(Font.TRUETYPE_FONT, is);
        } catch (FontFormatException | IOException e) {
            e.printStackTrace();
        }
    }

    public Font getFont() {
        return gameFont;
    }
}
