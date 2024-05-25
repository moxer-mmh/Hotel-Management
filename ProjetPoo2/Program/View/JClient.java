package View;

import java.awt.Color;
import java.awt.EventQueue;
import java.util.Map;
import java.util.TreeMap;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import Model.Client;

import javax.swing.JTextField;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.Font;

public class JClient extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private static JTextField nomClient;
	private static JTextField prenomClient;

	/**
	 * Launch the application.
	 */

	private static Map<Integer, Client> clients = new TreeMap<>();
	static int row = 1;
	private JButton btnNewButton_1;

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					JClient frame = new JClient();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public JClient() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 800, 600);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel background = new JLabel();
		background.setBounds(400, 0, 386, 563);
		contentPane.add(background);
		background.setIcon(new ImageIcon(getClass().getResource("hotel1.png")));

		JLabel textt = new JLabel("Entre votre nom et prenom");
		textt.setFont(new Font("Tahoma", Font.BOLD, 18));
		textt.setForeground(Color.white);
		textt.setBounds(80, 120, 360, 57);
		background.add(textt);

		JLabel background2 = new JLabel();
		background2.setBounds(0, 0, 401, 563);
		background2.setOpaque(true);
		background2.setBackground(Color.white);
		contentPane.add(background2);

		JLabel logo = new JLabel();
		logo.setIcon(new ImageIcon(getClass().getResource("logo.png")));
		logo.setBounds(70, 136, 236, 256);
		background2.add(logo);

		JPanel panel = new JPanel();
		panel.setOpaque(false);
		panel.setBounds(50, 200, 300, 148);
		panel.setLayout(null);

		nomClient = new JTextField();
		nomClient.setBackground(new Color(0, 21, 43));
		nomClient.setForeground(Color.white);
		nomClient.setBounds(129, 10, 161, 34);
		panel.add(nomClient);
		nomClient.setColumns(10);

		prenomClient = new JTextField();
		prenomClient.setBackground(new Color(0, 21, 43));
		prenomClient.setForeground(Color.white);
		prenomClient.setColumns(10);
		prenomClient.setBounds(129, 54, 161, 34);
		panel.add(prenomClient);

		JLabel lblNewLabel = new JLabel("Nom");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 18));
		lblNewLabel.setForeground(Color.white);
		lblNewLabel.setBounds(0, 13, 98, 27);
		panel.add(lblNewLabel);
		lblNewLabel.setHorizontalAlignment(SwingConstants.RIGHT);

		JLabel lblPrenom = new JLabel("Prenom");
		lblPrenom.setFont(new Font("Tahoma", Font.BOLD, 18));
		lblPrenom.setForeground(Color.white);
		lblPrenom.setBounds(0, 50, 98, 35);
		panel.add(lblPrenom);
		lblPrenom.setHorizontalAlignment(SwingConstants.RIGHT);

		JButton btnNewButton = Design.createButton("Entrer", 120, 98, 80, 44);
		btnNewButton.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				addClient();
				dispose();
			}
		});
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				addClient();
				dispose();
			}
		});

		btnNewButton_1 = Design.createButton("Retour", 220, 98, 80, 44);
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				JMain windowToBeClosed = new JMain();
				windowToBeClosed.setVisible(true);
				dispose();

			}
		});

		panel.add(btnNewButton);
		panel.add(btnNewButton_1);

		background.add(panel);
	}

	public static void addClient() {
		String nom = nomClient.getText();
		String prenom = prenomClient.getText();

		// Vérifier si le client existe déjà dans la map
		for (Client c : clients.values()) {
			if (c.getNom().equals(nom) && c.getPrenom().equals(prenom)) {
				System.out.println("Le client existe déjà.");
				JResrvationClient f = new JResrvationClient(nom, prenom);
				f.setVisible(true);
				return; // Sortir de la méthode après avoir ouvert la fenêtre JMenuClient
			}
		}

		// Si le client n'existe pas, créer un nouveau client
		Client client = new Client(nom, prenom, row);
		clients.put(row, client);
		System.out.println("Nouveau client créé avec l'ID : " + row);
		++row;

		JResrvationClient f = new JResrvationClient(nom, prenom);
		f.setVisible(true);
	}

	public static void listeClient() {
		for (Client rowList : clients.values()) {

			System.out.println(rowList.getNom());
		}

		// Création d'un JTable
		// table.setModel(modelT);

	}
}