
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

//klasa za spajanje na bazu i dohvaćanje i upisivanje rezultata
public class DB {

    Connection conn = null;
    String url = "jdbc:sqlite:snake.db";

    public DB() {
        this.conn = connect();
    }

    //metoda za povezivanje sa bazom
    private Connection connect() {
        try {
            conn = DriverManager.getConnection(url);
            System.out.println("Connection to SQLite has been established.");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return conn;
    }
//metoda za zatvaranje konekcije sa bazom

    public void close() {
        try {
            this.conn.close();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
//metoda za upis rezultata 

    public void unesi_rezultat(String ime, int score) {
        String sql = "INSERT INTO highscore (ime,rezultat) VALUES(?,?)";
        try {
            PreparedStatement pstmt = this.conn.prepareStatement(sql);
            // Postavi parametre
            pstmt.setString(1, ime);
            pstmt.setInt(2, score);
            // Izvrši sql
            pstmt.executeUpdate();
            System.out.println("Izvršen update : " + ime);//radi
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
//metoda za dohvaćanje rezultata

    public List<Rezultat> dohvati_rezultate() {
        List<Rezultat> rezultati = new ArrayList<>();
        String sql = "SELECT * FROM highscore ORDER BY rezultat DESC;";
        try {
            Statement stmt = this.conn.createStatement();
            ResultSet rs = stmt.executeQuery(sql);
            // prođi kroz sve dohvaćene rezultate i spremi ih u listu
            while (rs.next()) {
                System.out.println(rs.getInt("id") + "\t"
                        + rs.getString("ime") + "\t"
                        + rs.getInt("rezultat"));

                rezultati.add(new Rezultat(rs.getString("ime"), rs.getInt("rezultat")));
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return rezultati;
    }
}
