
import java.awt.Dimension;
import javax.swing.JPanel;

public class BazniPanel extends JPanel {

    public static final int visina = 500;
    public static final int sirina = 500;

    public BazniPanel() {
        setPreferredSize(new Dimension(sirina, visina));
    }
}
