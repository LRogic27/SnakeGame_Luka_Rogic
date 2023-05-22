
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import static java.lang.String.valueOf;
import java.util.List;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.JTableHeader;

//klasa za crtanje LeaderBord panela i tablice
public class LeaderBoard extends BazniPanel implements ActionListener {

    public static final int MAX_NUM_PLAYERS = 10;
    Zmija zmija;
    JLabel tablica;
    JLabel razmak;
    //lista rezultata koja sadrži ime i rezultat svakog igrača
    List<Rezultat> rezultati;
    String[][] ime_rezultat = new String[MAX_NUM_PLAYERS][3];
    String kolone[] = {"#", "Name", "Score"};
    JButton naMeni;

    public LeaderBoard(Zmija zmija) {
        super();
        this.zmija = zmija;
        setBackground(Color.black);
        this.rezultati = this.zmija.db.dohvati_rezultate();
        int i = 0;
        //dio koda koji sprema podatke u dvodimenzionalno polje koje se predaje tablici
        for (i = 0; i < 10; i++) {
            ime_rezultat[i][0] = Integer.toString(i + 1);
            ime_rezultat[i][1] = rezultati.get(i).ime;
            if (rezultati.get(i).ime == null) {
                ime_rezultat[i][1] = ".....";
            }
            ime_rezultat[i][2] = valueOf(rezultati.get(i).rezultat);
            if (valueOf(rezultati.get(i).rezultat) == null) {
                ime_rezultat[i][2] = "0";
            }
        }
//crtanje i uređivanje tablice
        JTable table = new JTable(ime_rezultat, kolone);
        table.setFont(new Font("PMingLiU-ExtB", Font.BOLD, 20));
        table.setForeground(Color.white);
        table.setBackground(Color.black);
        int j = 0;
        for (i = 0; i < 10; i++) {
            for (j = 0; j < 3; j++) {
                table.getColumnModel().getColumn(j).setPreferredWidth(134);
                table.setRowHeight(35);
            }
        }
        //sirina je takva radi prezicnosti 400 se nemoze podijeliti na 3 cijela broja zato sam dodao jos 2
        add(table);
        //dodavanje zaglavlja tablice 
        JTableHeader tableHeader = table.getTableHeader();
        tableHeader.setFont(new Font("arial", Font.BOLD, 30));
        JScrollPane sp = new JScrollPane(table);
        sp.setPreferredSize(new Dimension(402, 390));
        sp.setBorder(BorderFactory.createEmptyBorder());
        add(sp);
        //dio koda koji crta dodaje i pozicionira gumb "Back to menu"
        naMeni = new JButton("Back to menu");
        naMeni.addActionListener((e) -> {
            zmija.prikaziMenu();
        });
        naMeni.setBounds(sirina / 2, 400, 30, 200);
        naMeni.setForeground(Color.white);
        naMeni.setBackground(Color.black);
        add(naMeni);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
