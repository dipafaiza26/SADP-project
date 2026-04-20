import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.border.Border;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.table.JTableHeader;

public class UITheme {

    // Color Palette based on "Aurora Haven" aesthetic
    public static final Color BG_CREAM = new Color(249, 246, 240);
    public static final Color ACCENT_SAGE = new Color(92, 113, 94);
    public static final Color TEXT_CHARCOAL = new Color(44, 62, 53);
    public static final Color CARD_WHITE = new Color(255, 255, 255);
    public static final Color BORDER_GRAY = new Color(210, 210, 200);

    // Typography
    public static final Font FONT_TITLE = new Font("Georgia", Font.BOLD, 26);
    public static final Font FONT_HEADER = new Font("Georgia", Font.BOLD, 18);
    public static final Font FONT_BODY = new Font("Segoe UI", Font.PLAIN, 14);
    public static final Font FONT_BODY_BOLD = new Font("Segoe UI", Font.BOLD, 14);

    public static void stylePanel(JPanel panel) {
        panel.setBackground(BG_CREAM);
    }

    public static void styleCard(JPanel panel) {
        panel.setBackground(CARD_WHITE);
        Border lineBorder = BorderFactory.createLineBorder(BORDER_GRAY, 1);
        Border margin = new EmptyBorder(20, 20, 20, 20);
        panel.setBorder(new CompoundBorder(lineBorder, margin));
    }

    public static void styleLabel(JLabel label) {
        label.setFont(FONT_BODY_BOLD);
        label.setForeground(TEXT_CHARCOAL);
    }

    public static void styleTitle(JLabel label) {
        label.setFont(FONT_TITLE);
        label.setForeground(TEXT_CHARCOAL);
    }

    public static void styleTextField(JTextField txt) {
        txt.setFont(FONT_BODY);
        txt.setForeground(TEXT_CHARCOAL);
        txt.setBackground(Color.WHITE);
        Border lineBorder = BorderFactory.createLineBorder(BORDER_GRAY, 1);
        Border padding = new EmptyBorder(5, 10, 5, 10);
        txt.setBorder(new CompoundBorder(lineBorder, padding));
    }

    public static void styleTable(JTable table, JScrollPane scrollPane) {
        table.setFont(FONT_BODY);
        table.setForeground(TEXT_CHARCOAL);
        table.setRowHeight(30);
        table.setBackground(Color.WHITE);
        table.setGridColor(BORDER_GRAY);
        table.setBorder(null);

        JTableHeader header = table.getTableHeader();
        header.setFont(FONT_BODY_BOLD);
        header.setBackground(ACCENT_SAGE);
        header.setForeground(Color.WHITE);
        header.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));

        scrollPane.setBorder(BorderFactory.createLineBorder(BORDER_GRAY, 1));
        scrollPane.getViewport().setBackground(Color.WHITE);
    }
}
