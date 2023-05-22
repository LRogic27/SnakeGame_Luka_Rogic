
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JTextField;

public class GameOver extends BazniPanel implements ActionListener {

    Zmija zmija;
    int rezultat;

    private final String btm = "Back to menu";
    private final String newGame = "Play again";
    JButton backToMenu = new JButton(btm);
    JTextField ime = new JTextField(10);
    JButton startNewGame = new JButton(newGame);
    String unesiIme = "Name :";
    String score;
    JLabel unesi = new JLabel(unesiIme);
    JLabel scoreL ;
    String gameOverString = "GAME OVER";
    JLabel gameOver = new JLabel(gameOverString);

    //konstruktor GameOver klase u kojem se dodaju elementi na GameOver panelu kao što su gumbi tekst itd.
    public GameOver(Zmija zmija) {

        super();
        this.zmija = zmija;
        setBackground(Color.black);
        this.setLayout(null);
        
        add(gameOver);
        score = "Score : "+zmija.score;
        scoreL= new JLabel(score);
        add(scoreL);
        scoreL.setBounds((sirina - score.length()*10) / 2, 150, score.length() * 30, 30);
        scoreL.setForeground(Color.white);
      
        add(ime);
        add(unesi);
        JButton btnSpremi = new JButton("Save!");
        // dio koda koji služi za spremanje rezultata i imena u bazu podatka pritiskom na gumb "Save"
        btnSpremi.addActionListener((e) -> {
            zmija.db.unesi_rezultat(ime.getText(), zmija.score);
            ime.setText("");
        });
        add(btnSpremi);
        
        
        //dio koda koji služi za vraćanje na Menu panel pritiskom na gumb "back to menu"
        backToMenu.addActionListener((e) -> {
            zmija.prikaziMenu();
        });
        add(backToMenu);
        
        //dio koda koji služi za pokretanje nove igre pritiskom na gumb "Play again"
        startNewGame.addActionListener((e) -> {
            zmija.playPloca();
        });
        add(startNewGame);
        
        
        //dio koda za premještanje elemenata i centriranje teksta u svakom elementu
        gameOver.setBounds((sirina - gameOverString.length() * 26) / 2, 100, gameOverString.length() * 30, 50);
        gameOver.setFont(new Font("arial", Font.BOLD, 40));
        gameOver.setForeground(Color.white);
        ime.setBounds(200, 200, 100, 20);
        ime.setBackground(Color.black);
        ime.setForeground(Color.white);
        unesi.setBounds(100, 200, 100, 20);
        unesi.setForeground(Color.white);
        btnSpremi.setBounds(305, 200, 80, 20);
        unesi.setFont(new Font("arial", Font.BOLD, 20));
        startNewGame.setBounds((sirina - btm.length() * 10) / 2, 260, btm.length() * 10, 30);
        backToMenu.setBounds((sirina - btm.length() * 10) / 2, 300, btm.length() * 10, 30);
        revalidate();
    }

    @Override
    public void actionPerformed(ActionEvent ae) {
        //repaint();
    }
}
