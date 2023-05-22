
import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Stroke;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import javax.swing.ImageIcon;
import javax.swing.Timer;

public class Ploca extends BazniPanel implements ActionListener {

    private final int velicina_tocke = 10;//velicina jednog polja
    private final int offset_up = 50;
    private final int offset = 30;
    //broj polja na ploci (slicno sahovnici)
    private final int broj_tocka = ((sirina * visina) - (sirina * offset_up) - 2
            * (offset * (visina - offset_up)) - (offset * (sirina - 2 * offset))) / 100;
    private final int rand_poz = 49;//random pozicija jabuke
    private final int DELAY = 70;//brzina igre
    //kordinate svakog komada tijela
    private final int xTijela[] = new int[broj_tocka];
    private final int yTijela[] = new int[broj_tocka];

    private int score = 0;
    private int duzinaTijela;
    //pocetne vrijednosti za kretanje
    private boolean lijevo = false;
    private boolean desno = true;
    private boolean gore = false;
    private boolean dolje = false;

    private boolean inGame = true;

    private Timer timer;
    private Image rep;
    private Image hrana;
    private Image glava;

    Menu menu;
    GameOver gameOver;
    Zmija zmija;
    Hrana jabuka;

    public Ploca(Zmija zmija) {
        super();//sa super pozivom zovem konstruktor metodu od roditelja
        this.zmija = zmija;
        this.jabuka = new Hrana(0, 0);
        naPloci();
        System.out.println("Broj točaka=" + broj_tocka);
    }

    //pozivanje svih metoda za crtanje i kretanje
    private void naPloci() {
        addKeyListener(new tipke());
        setBackground(Color.black);      
        crtanjeSlika();
        pocetakIgre();
    }
//metoda za crtanje slika koje predstavljaju tijelo,rep i hranu

    public void crtanjeSlika() {
        ImageIcon slikaRepa = new ImageIcon("src\\main\\java\\download.gif");
        rep = slikaRepa.getImage();

        ImageIcon slikaGlave = new ImageIcon("src\\main\\java\\download.gif");
        glava = slikaGlave.getImage();

        ImageIcon slikaHrane = new ImageIcon("src\\main\\java\\jabuka.jpg");
        hrana = slikaHrane.getImage();
    }
//metoda za crtanje

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        nacrtaj(g);

    }
//metoda za crtanje okvira na "play" panelu,ispisivanje rezultata,crtanje same zmije i hrane

    public void nacrtaj(Graphics g) {
        Graphics2D g2 = (Graphics2D) g;
        Stroke stroke1 = new BasicStroke(5f);
        if (inGame) {
            //crtanje okvira
            g2.setStroke(stroke1);
            g2.setColor(Color.white);
            g2.drawRect(offset, offset_up, sirina - 2 * offset, visina - offset - offset_up);
            // g.drawImage(hrana, hrana_x, hrana_y, this);
            g.drawImage(hrana, jabuka.x, jabuka.y, this);
            g.drawString("Score : " + score, 30, 30);
            for (int i = duzinaTijela; i > 0; i--) {
                if (i == 0) {
                    g.drawImage(glava, xTijela[0], yTijela[0], this);
                } else {
                    g.drawImage(rep, xTijela[i], yTijela[i], this);
                }
            }
            Toolkit.getDefaultToolkit().sync();
        } else {
            gameOver();
        }
    }
//ako se zmija sudari sa zidom ili sa tijelom uključuje se ova metoda koja predaje glavnoj 
    //klasi vrijednost rezultata te poziva metodu kraj partije također iz klase zmija

    private void gameOver() {
        this.zmija.score = score;
        this.zmija.krajPartije();
    }
//metoda koja postavlja početne koordinate zmije i njezinog tijela, dužinu tijela i crta jednu hranu 

    private void pocetakIgre() {
        duzinaTijela = 3;

        for (int i = 0; i <= duzinaTijela; i++) {
            //zmija ce stajati vodoravno na koordinati 100,100
            xTijela[i] = 100 - i * 10;
            yTijela[i] = 100;
        }
        //stvoriHranu(randX(), randY());
        stvoriHranu(randX(), randY());
        timer = new Timer(DELAY, this);
        timer.start();
    }
//metoda koja provjerava je li zmija pojela hranu. Ako ju pojede zmija se poveća za 1 i rezultat se poveća za 1

    private void provjeraJedenjaHrane() {

        System.out.println(xTijela[0] + "," + yTijela[0] + " == " + jabuka.x + ", " + jabuka.y);
        if (xTijela[0] == jabuka.x && yTijela[0] == jabuka.y) {
            duzinaTijela++;
            score++;

            stvoriHranu(randX(), randY());
        }
    }

    //dio koda za stvaranje hrane na random pozicijama na ploči - prva metoda "stvoriHranu" samo 
    //pridružuje vrijednosti x i y objektu jabuka koji predstavlja hranu
    public void stvoriHranu(int x, int y) {
        this.jabuka.x = x;
        this.jabuka.y = y;
    }

    //metoda koja vraća y koordinatu 
    public int randY() {
        int min = offset_up+10;
        int max = visina - (offset+10);
        // Ref: https://www.baeldung.com/java-generating-random-numbers-in-range
        int x = (int) ((Math.random() * (max - min)) + min);
        return x - x % 10;
    }

    //metoda koja vraća x koordinatu 
    public int randX() {
        int min = offset+10;
        int max = sirina - (offset+10);
        int x = (int) ((Math.random() * (max - min)) + min);
        return x - x % 10;
    }

    //metoda koja mijenja smijer kretanja zmije
    private void kretanje() {
        for (int i = duzinaTijela; i > 0; i--) {
            xTijela[i] = xTijela[(i - 1)];
            yTijela[i] = yTijela[(i - 1)];
        }
        if (lijevo) {
            xTijela[0] -= velicina_tocke;
        }
        if (desno) {
            xTijela[0] += velicina_tocke;
        }
        if (gore) {
            yTijela[0] -= velicina_tocke;
        }
        if (dolje) {
            yTijela[0] += velicina_tocke;
        }
    }

    //metoda koja provjerava sudare zmije sa zidovima i sa tijelom
    public void provjeraSudara() {

        for (int i = duzinaTijela; i > 0; i--) {
            if (i != 0 && (xTijela[0] == xTijela[i]) && (yTijela[0] == yTijela[i])) {
                inGame = false;
            }
            //okvir je 20 px 
            if (xTijela[0] >= (sirina - offset) || xTijela[0] < offset) {
                inGame = false;
            }
            //gore je 50 px okvir a dolje 20 px
            if (yTijela[0] >= (visina - offset) || yTijela[0] < offset_up) {
                inGame = false;
            }
            if (!inGame) {
                timer.stop();
            }
        }
    }

    private class tipke extends KeyAdapter {
        //metoda za kretanje. Kada korisnik pritisne jednu od tipki sa strelicama zmija se kreće u smijeru strelice
        @Override
        public void keyPressed(KeyEvent e) {
            int key = e.getKeyCode();
            if ((key == KeyEvent.VK_LEFT) && (!desno)) {
                lijevo = true;
                gore = false;
                dolje = false;
            }
            if ((key == KeyEvent.VK_RIGHT) && (!lijevo)) {
                desno = true;
                gore = false;
                dolje = false;
            }
            if ((key == KeyEvent.VK_UP) && (!dolje)) {
                gore = true;
                desno = false;
                lijevo = false;
            }
            if ((key == KeyEvent.VK_DOWN) && (!gore)) {
                dolje = true;
                desno = false;
                lijevo = false;
            }
            if ((key == KeyEvent.VK_ESCAPE)) {               
                zmija.prikaziMenu();
            }
        }
    }

    @Override

    //metoda koja vrši sve ostale metode ako je korisnik u igri
    public void actionPerformed(ActionEvent e) {

        if (inGame) {
            provjeraSudara();
            provjeraJedenjaHrane();
            kretanje();
        }
        repaint();
    }
}
