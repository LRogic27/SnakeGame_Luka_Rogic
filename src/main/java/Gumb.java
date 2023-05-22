
import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;

//klasa za kreiranje gumba na menu panelu - crta se pravokutnik sa tekstom u sredini
public class Gumb {

    int x, y, w, h;
    String text;

    public Gumb(int x, int y, int w, int h, String text) {
        this.x = x;
        this.y = y;
        this.w = w;
        this.h = h;
        this.text = text;
    }
//metoda za crtanje samog gumba

    public void paint(Graphics g) {
        Font fnt = new Font("Arial", Font.BOLD, 30);
        g.setFont(fnt);
        g.setColor(Color.white);
        FontMetrics metrics = g.getFontMetrics(fnt);
        g.drawRect(x, y, w, h);
        g.drawString(this.text, this.x + ((this.w - metrics.stringWidth(text)) / 2),
                this.y + ((this.h - metrics.getHeight()) / 2) + metrics.getAscent());
    }
}
