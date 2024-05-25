package View;

import java.awt.EventQueue;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import Controller.CtrlReservation;

public class JReservation extends JFrame {

    private static final long serialVersionUID = 1L;
    private JPanel contentPane;
    static JTable table;

    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    JReservation frame = new JReservation();
                    frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    public JReservation() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 905, 600);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);

        DefaultTableModel model = new DefaultTableModel(
                new Object[][] {},
                new String[] { "ID", "Client", "Chambre", "Date Début", "Date Fin", "État" } // Noms des colonnes
        );

        CtrlReservation.actionInitReservation(model);

        table = new JTable(model);

        // Créer le JScrollPane avec le JTable
        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.setBounds(10, 10, 760, 495); // Ajustez les dimensions et la position selon vos besoins

        // Ajouter le JScrollPane au JPanel principal
        contentPane.add(scrollPane);
        CtrlReservation.actionSelectReservation(model, table);

        JButton retour = new JButton("RETOUR");
        CtrlReservation.actionRetour(retour, this);

        int buttonWidth = 100;
        int buttonHeight = 30;
        retour.setBounds(670, 520, buttonWidth, buttonHeight);
        contentPane.add(retour);

        JButton btnAcceptReserv = new JButton("ACCEPTER");
        CtrlReservation.actionAcceptReserv(btnAcceptReserv, model, table);

        btnAcceptReserv.setBounds(780, 40, 89, 23);
        contentPane.add(btnAcceptReserv);

        JButton btndeclineReserv = new JButton("DECLINER");
        CtrlReservation.actionDeclineReserv(btndeclineReserv, model, table);

        btndeclineReserv.setBounds(780, 74, 89, 23);
        contentPane.add(btndeclineReserv);
    }
}