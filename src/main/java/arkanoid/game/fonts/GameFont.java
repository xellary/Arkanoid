package arkanoid.game.fonts;

import java.awt.*;
import java.io.IOException;
import java.io.InputStream;

public class GameFont {
    private Font gameFont;

    public GameFont() {
        try {
            InputStream is = getClass().getResourceAsStream("/fontfiles/BarcadeBrawlRegular.TTF");
            gameFont = Font.createFont(Font.TRUETYPE_FONT, is);
        } catch (FontFormatException | IOException e) {
            System.out.println(e.getMessage());
        }
    }

    public Font getFont() {
        return gameFont;
    }
}
