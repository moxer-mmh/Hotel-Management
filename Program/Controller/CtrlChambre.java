package Controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

import Model.Chambre;
import Model.EtatChambres;
import Model.TypeChambre;
import View.JAdmin;

public class CtrlChambre {
	public static int roomNumber;
	static int selectedRow;

	public static void actionAddRoom(JButton btnAddRoom, JComboBox<TypeChambre> typeChambre, JTextField numChambre,
        DefaultTableModel model, JTable tableChambre, ActionListener actionListener) {

    try {
        int roomNumber = Integer.parseInt(numChambre.getText().trim());

        if (!Chambre.chambres.containsKey(roomNumber)) {
            Chambre.chambres.put(roomNumber, new Chambre(roomNumber, (TypeChambre) typeChambre.getSelectedItem()));
            System.out.println("Chambre ajoutée avec succès !");

            Object[] data = { roomNumber, typeChambre.getSelectedItem(), EtatChambres.LIBRE };
            model.addRow(data);
            tableChambre.setModel(model);
        } else {
            JOptionPane.showMessageDialog(null,
                    "La chambre spécifiée existe déjà", "Erreur", JOptionPane.ERROR_MESSAGE);
        }
    } catch (NumberFormatException e) {
        JOptionPane.showMessageDialog(null,
                "Veuillez entrer un numéro de chambre valide", "Erreur", JOptionPane.ERROR_MESSAGE);
    }
}

	public static void listeRoom(DefaultTableModel model, JTable table) {
		for (Chambre rowList : Chambre.chambres.values()) {
			Object[] data = { rowList.getNumero(), rowList.getType(), rowList.getEtatChambre() };
			System.out.println(rowList.getNumero());
			model.addRow(data);
		}

		// Création d'un JTable
		table.setModel(model);

	}

	public static void actionModifRoom(JButton btnModifRoom, JTable table, DefaultTableModel model,
			JComboBox<TypeChambre> typeChambre, JComboBox<EtatChambres> etatChambre) {

		btnModifRoom.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				selectedRow = table.getSelectedRow();

				Chambre chambre = Chambre.chambres.get(selectedRow + 1);

				if (chambre != null) {
					if (chambre.getEtatChambre() == EtatChambres.LIBRE) {
						chambre.setType((TypeChambre) typeChambre.getSelectedItem());
						chambre.setEtatChambre((EtatChambres) etatChambre.getSelectedItem());

						table.setModel(model);

						model.setValueAt(typeChambre.getSelectedItem(), selectedRow, 1);
						model.setValueAt(etatChambre.getSelectedItem(), selectedRow, 2);
					} else {
						try {
							throw new ExeptionChambre(
									"Impossible de modifier une chambre déjà réservée ou en attente.");
						} catch (ExeptionChambre e1) {
							JOptionPane.showMessageDialog(null, e1.getMessage(), "Erreur", JOptionPane.ERROR_MESSAGE);
						}
					}
				}
			}
		});
	}

	public static void actionSupprimeRoom(JButton btnSupprimer, JTable table, DefaultTableModel model) {
		btnSupprimer.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				table.setModel(model);
				int selectedRow = table.getSelectedRow();
				if (selectedRow != -1) {
					Chambre chambre = Chambre.chambres.get(selectedRow + 1);
					if (chambre.getEtatChambre() == EtatChambres.LIBRE) {
						model.removeRow(selectedRow);
						table.setModel(model);
					} else {
						try {
							throw new ExeptionChambre(
									"Impossible de supprimer une chambre déjà réservée ou en attente.");
						} catch (ExeptionChambre e1) {
							JOptionPane.showMessageDialog(null, e1.getMessage(), "Erreur", JOptionPane.ERROR_MESSAGE);
						}
					}
				}
			}
		});
	}

	public static void actionSelectRoom(JTable table, DefaultTableModel model, JComboBox<TypeChambre> typeChambre,
			JComboBox<EtatChambres> etatChambre,
			JTextField numChambre) {
		table.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				if (e.getClickCount() == 2) { // Check for double click

					selectedRow = table.getSelectedRow();
					// Assuming you want the value from the second column

					// DefaultTableModel model = (DefaultTableModel) table.getModel();
					Object value1 = ((TableModel) model).getValueAt(selectedRow, 0);
					Object value2 = ((TableModel) model).getValueAt(selectedRow, 1);
					Object value3 = ((TableModel) model).getValueAt(selectedRow, 2);

					typeChambre.removeAllItems();
					etatChambre.removeAllItems();
					if (selectedRow != -1) {
						numChambre.setText(value1 == null ? "" : value1.toString());

						typeChambre.addItem((TypeChambre) value2);

						etatChambre.addItem((EtatChambres) value3);

						// typeChambre.setModel(new DefaultComboBoxModel(TypeChambre.values()));

					} else {
						numChambre.setText(""); // Or display an error message
					}
				}
			}

		});

	}

	public static void actionRetour(JButton retour, JFrame frame) {

		retour.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JAdmin windowToBeClosed = new JAdmin();
				windowToBeClosed.setVisible(true);
				frame.dispose();

			}
		});

	}

}
