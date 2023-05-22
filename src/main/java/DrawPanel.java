
import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.JButton;

public class DrawPanel extends BazniPanel implements MouseListener {

    JButton play;
    Menu menu;
    Zmija zmija;
    Gumb btnPlay = new Gumb(BazniPanel.sirina / 2 - 85, 100, 190, 50, "Play");
    Gumb btnLeaderBoard = new Gumb(BazniPanel.sirina / 2 - 85, 200, 190, 50, "LeaderBoard");
    Gumb btnQuit = new Gumb(BazniPanel.sirina / 2 - 85, 300, 190, 50, "Quit");

    public DrawPanel(Menu menu) {
        this.menu = menu;
        addMouseListener(this);
    }

    //metoda za crtanje komponenata na menu panelu
    @Override
    public void paint(Graphics g) {
        Font fnt = new Font("PMingLiU-ExtB", Font.BOLD, 50);
        g.setFont(fnt);
        g.setColor(Color.white);
        String text = "SNAKE GAME";
        FontMetrics fm = g.getFontMetrics();

        g.drawString(text, ((getWidth() - fm.stringWidth(text)) / 2), 50);
        btnPlay.paint(g);
        btnLeaderBoard.paint(g);
        btnQuit.paint(g);

    }

    //@Override
    public void mouseClicked(MouseEvent me) {

    }
//metoda koja obavlja neke zadatke prilikom klika miša

    @Override
    public void mousePressed(MouseEvent me) {
        zmija = new Zmija();
        //varijable koje dohvaćaju poziciju miša
        int mx = me.getX();
        int my = me.getY();

        //play-dio koda koju provjerava da li je korisnik pritisnuo "Play" gumb 
        if (mx >= btnPlay.x && mx <= (btnPlay.x + btnPlay.w)) {
            if (my >= btnPlay.y && my <= (btnPlay.y + btnPlay.h)) {
                //ako je korisnik pritisnuo Play gumb poziva se metoda play() koja služi za pokretanje same igre
                menu.play();
            }

        }
        //Leaderboard-dio koda koju provjerava da li je korisnik pritisnuo "LeaderBoard" gumb
        if (mx >= btnLeaderBoard.x && mx <= (btnLeaderBoard.x + btnLeaderBoard.w)) {
            if (my >= btnLeaderBoard.y && my <= (btnLeaderBoard.y + btnLeaderBoard.h)) {
                //ako je korisnik pritisnuo LeaderBoard gumb poziva se metoda leaderBoardGUI() 
                //koja služi za prikazivanje leaderbord panela na kojem su prikazani rezultati
                menu.leaderBoardGUI();
            }

        }
        // Quit - dio koda koju provjerava da li je korisnik pritisnuo "Quit" gumb
        if (mx >= btnQuit.x && mx <= (btnQuit.x + btnQuit.w)) {
            if (my >= btnQuit.y && my <= (btnQuit.y + btnQuit.h)) {
                //ako je korisnik pritisnuo Quit gumb poziva se metoda kraj() koja služi za gašenje programa
                zmija.kraj();
            }

        }
    }

    //@Override
    public void mouseReleased(MouseEvent me) {
    }

    //@Override
    public void mouseEntered(MouseEvent me) {
    }

    //@Override
    public void mouseExited(MouseEvent me) {
    }
}
