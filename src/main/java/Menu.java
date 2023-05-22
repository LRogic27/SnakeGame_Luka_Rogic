
import java.awt.Color;
import java.awt.Font;
import javax.swing.JLabel;

//klasa za crtanje menu panela
public class Menu extends BazniPanel {

    Zmija zmija;
    JLabel snakeGame;
    DrawPanel dp;
    Ploca ploca;

    //konstruktor u kojem se crta naslov te instancira novi objekt DrawPanel-a koji crta gumbe
    public Menu(Zmija zmija) {
        super();
        this.zmija = zmija;
       setBackground(Color.black);
        //snakeGame = new JLabel("Snake Game");
        //snakeGame.setFont(new Font("arial", Font.BOLD, 50));

        dp = new DrawPanel(this);
        add(dp);

    }
//metoda za pokretanje igre

    void play() {
        this.setVisible(false);
        zmija.playPloca();
    }
//metoda za otvaranje leaderBord panela na kojem je tablica rezultata

    void leaderBoardGUI() {
        this.setVisible(false);
        zmija.leaderBoardGUIZmija();
    }
}
