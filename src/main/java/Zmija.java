
import java.awt.EventQueue;
import javax.swing.JFrame;

public class Zmija extends JFrame {

    BazniPanel lbGUI;
    BazniPanel menu;
    BazniPanel gameOver;
    Ploca ploca;
    int score;
    String ime;
    public DB db;
//konstruktor u kojem se poziva metoda sucelje() 
    //koja služi za crtanje korisničkog prozora  
    //te se radi instanca klase DB() koja služi za povezivanje za bazom podataka

    public Zmija() {
        sucelje();
        this.score = 0;
        this.db = new DB();
    }
//metoda koja crta menu panel , postavlja naslov prozora , postavlja vrijednost lokacije prozora na "Null" 
    //što znači da će se prozor pojaviti na sredini  ekrana te će  se moći pomicati 
    //te još omogućava da se prozor zatvori na "exit close operator" odnosno X u gornjem desnom kutu

    public void sucelje() {
        menu = new Menu(this);
        add(menu);

        setResizable(false);
        pack();

        setTitle("Snake");
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
//metoda za gašenje svih panela - koristi se prilikom otvranja novog panela
    //jedino se menu panel ne ugasi skroz nego se samo učuni nevidljivim

    void resetPanels() {
        if (ploca != null) {
            remove(ploca);
            ploca = null;
        }
        if (gameOver != null) {
            remove(gameOver);
            gameOver = null;
        }
        if (lbGUI != null) {
            remove(lbGUI);
            lbGUI = null;
        }
        menu.setVisible(false);
        revalidate();
    }
//metoda za prikazivanje menu panela

    void prikaziMenu() {
        resetPanels();
        revalidate();
        // repaint();
        menu.setVisible(true);
    }
//metoda za pokretanje igre odnosno play panela

    void playPloca() {
        resetPanels();
        this.score = 0;
        ploca = new Ploca(this);
        add(ploca);
        ploca.requestFocus();
        revalidate();
    }

    //metoda koja se poziva kada partija završi
    //metoda prikazuje gameOver panel
    void krajPartije() {      
        resetPanels();   
        gameOver = new GameOver(this);
        add(gameOver);
        gameOver.requestFocus();
        revalidate();      
    }
//metoda za prikazivanje leaderBord panela

    void leaderBoardGUIZmija() {      
        resetPanels();       
        lbGUI = new LeaderBoard(this);
        add(lbGUI);
        lbGUI.requestFocus();
        revalidate();
    }
//metoda za gašenje programa

    void kraj() {       
        this.db.close();
        System.out.println("Kraj.");
        System.exit(0);
    }
//main metoda koja stvara Frame te pokreče sam program

    public static void main(String[] args) {
        EventQueue.invokeLater(() -> {
            JFrame jf = new Zmija();
            jf.setVisible(true);
        });

    }
}
