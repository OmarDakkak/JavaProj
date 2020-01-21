package Etudiants;

import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.UIManager;
import javax.swing.border.EmptyBorder;
import javax.swing.plaf.nimbus.NimbusLookAndFeel;

import net.proteanit.sql.DbUtils;
import javax.swing.DefaultComboBoxModel;

public class GestionMatieres extends JFrame {


	/**
	 * 
	 */
	private static final long serialVersionUID = 7272968866479767561L;

	private JPanel contentPane;
	
	Connection connection = null;
	PreparedStatement preparedStatement = null;
	ResultSet resultSet = null;
	
	private JTable table;
	private JTextField MatiereField;
	private JTextField coefField;
	private JComboBox<String> ProfBox; 
	private JLabel lblCoef;
	private JComboBox<String> niveauBox;
	

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					UIManager.setLookAndFeel(new NimbusLookAndFeel());
					GestionMatieres gestionMatieres = new GestionMatieres();
					gestionMatieres.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public GestionMatieres() {
		setResizable(false);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 1000, 500);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		connection = ConnexionMySql.ConnectionDB();
		
		JLabel lblNewLabel_2 = new JLabel("Gestion Des Etudiants");
		lblNewLabel_2.setBounds(394, 6, 211, 16);
		lblNewLabel_2.setFont(new Font("Roboto", Font.PLAIN, 20));
		contentPane.add(lblNewLabel_2);
		
		JLabel lblcoleNationaleDes = new JLabel("École Nationale Des Sciences Appliquées - Al Hoceïma");
		lblcoleNationaleDes.setBounds(323, 60, 354, 16);
		contentPane.add(lblcoleNationaleDes);
		
		JLabel lblLogo = new JLabel("");
		lblLogo.setBounds(16, 0, 140, 100);
		lblLogo.setIcon(new ImageIcon("/Users/macbookpro/Desktop/S3/TPJAVA/GestionEtudiants/Images/Webp.net-resizeimage.png"));
		contentPane.add(lblLogo);
		
		JLabel label = new JLabel("");
		label.setBounds(882, 0, 94, 100);
		label.setIcon(new ImageIcon("/Users/macbookpro/Desktop/ProjectJava/uae.png"));
		contentPane.add(label);
		
		JLabel lblNewLabel_1 = new JLabel("New label");
		lblNewLabel_1.setBounds(0, 0, 1000, 100);
		lblNewLabel_1.setIcon(new ImageIcon("/Users/macbookpro/Desktop/S3/TPJAVA/GestionEtudiants/Images/head1.png"));
		contentPane.add(lblNewLabel_1);
		
		MatiereField = new JTextField();
		MatiereField.setBounds(203, 171, 130, 26);
		contentPane.add(MatiereField);
		MatiereField.setColumns(10);
		
		JLabel lblNom = new JLabel("Nom de Matière : ");
		lblNom.setBounds(89, 176, 116, 16);
		contentPane.add(lblNom);
		
		JLabel lblNiveau = new JLabel("Niveau : ");
		lblNiveau.setBounds(89, 288, 110, 16);
		contentPane.add(lblNiveau);
		
		
	
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(517, 175, 441, 284);
		contentPane.add(scrollPane);
		
		table = new JTable();
		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				int ligne = table.getSelectedRow();
				String id = table.getModel().getValueAt(ligne, 0).toString();
				
				String sql = "SELECT * FROM Matieres where id_matiere = '"+id+"'";
				try {
					preparedStatement = connection.prepareStatement(sql);
					resultSet = preparedStatement.executeQuery();
					if (resultSet.next()) {
						MatiereField.setText(resultSet.getString("NomMatiere"));
						coefField.setText(resultSet.getString("Coef"));
						ProfBox.setSelectedItem(resultSet.getString("NomProf"));
						niveauBox.setSelectedItem(resultSet.getString("Niveau"));
					}
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
			}
		});
		
		scrollPane.setViewportView(table);
		
		JLabel label_1 = new JLabel("");
		label_1.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				updateTable();
			}
		});
		label_1.setIcon(new ImageIcon("/Users/macbookpro/Desktop/S3/TPJAVA/GestionEtudiants/Images/Releoad.png"));
		label_1.setBounds(892, 110, 50, 50);
		contentPane.add(label_1);
		
		JLabel lblNewLabel_3 = new JLabel("Table des Matieres : ");
		lblNewLabel_3.setBounds(517, 134, 148, 16);
		contentPane.add(lblNewLabel_3);
		
		JLabel label_2 = new JLabel("");
		label_2.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				MenuAdministrateur menuAdministrateur = new MenuAdministrateur();
				menuAdministrateur.setVisible(true);
				menuAdministrateur.setLocationRelativeTo(null);
				fermer();
			}
		});
		label_2.setIcon(new ImageIcon("/Users/macbookpro/Desktop/ProjectJava/GoBack_res.png"));
		label_2.setBounds(10, 113, 50, 50);
		contentPane.add(label_2);
		
		JButton btnNewButton = new JButton("");
		btnNewButton.addActionListener(new ActionListener() {
			@SuppressWarnings("unlikely-arg-type")
			public void actionPerformed(ActionEvent e) {
				String nomMatiere = MatiereField.getText().toString();
				String Coef = coefField.getText().toString();
				String NomProf = ProfBox.getSelectedItem().toString();
				String Niveau = niveauBox.getSelectedItem().toString();
				
				String sql = "INSERT INTO Matieres (NomMatiere, Coef, NomProf, Niveau) VALUES (?, ?, ?, ?)";
				try {
					if (!MatiereField.equals("") && !niveauBox.getSelectedItem().toString().equals("Selectionnez") && !ProfBox.getSelectedItem().equals("Selectionnez") && !coefField.equals("")) {
						preparedStatement = connection.prepareStatement(sql);
						preparedStatement.setString(1, nomMatiere);
						preparedStatement.setString(2, Coef);
						preparedStatement.setString(3, NomProf);
						preparedStatement.setString(4, Niveau);
						preparedStatement.execute();
						
						updateTable();
						MatiereField.setText("");
						niveauBox.setSelectedItem("Selectionnez");
						JOptionPane.showMessageDialog(null, "Matiere Ajoutee avec succes!");
					}else {
						JOptionPane.showMessageDialog(null, "Erreur!");
					}
					
					
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
			}
		});
		btnNewButton.setIcon(new ImageIcon("/Users/macbookpro/Desktop/S3/TPJAVA/GestionEtudiants/Images/add-branche-res.png"));
		btnNewButton.setBounds(21, 370, 80, 80);
		contentPane.add(btnNewButton);
		
		JButton button = new JButton("");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int ligne = table.getSelectedRow();
				if (ligne == -1) {
					JOptionPane.showMessageDialog(null, "Selectionez un Niveau!");
				}else {
						String id = table.getModel().getValueAt(ligne, 0).toString();
						
						String sql = "UPDATE Matieres SET NomMatiere = ?, coef = ?, NomProf = ?, Niveau = ? WHERE id_matiere = '"+id+"'";
						
						try {
							preparedStatement = connection.prepareStatement(sql);
							preparedStatement.setString(1, MatiereField.getText().toString());
							preparedStatement.setString(2, coefField.getText().toString());
							preparedStatement.setString(3, ProfBox.getSelectedItem().toString());
							preparedStatement.setString(4, niveauBox.getSelectedItem().toString());
							preparedStatement.execute();
							
							updateTable();
							MatiereField.setText("");
							niveauBox.setSelectedItem("Selectionnez");
							JOptionPane.showMessageDialog(null, "Matiere Modifiee avec succes!!");
							updateTable();
							
							
						} catch (SQLException e1) {
							e1.printStackTrace();
						}
				}
				
			}
		});
		button.setIcon(new ImageIcon("/Users/macbookpro/Desktop/S3/TPJAVA/GestionEtudiants/Images/Exchange-branche.png"));
		button.setBounds(150, 370, 80, 80);
		contentPane.add(button);
		
		JButton button_1 = new JButton("");
		button_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int ligne = table.getSelectedRow();
				String id = table.getModel().getValueAt(ligne, 0).toString();
				
				if (ligne == -1) {
					JOptionPane.showMessageDialog(null, "Selectionnez un Niveau!!");
				}else {
					String sql = "DELETE FROM Matieres WHERE id_matiere = '"+id+"'";
					try {
						preparedStatement = connection.prepareStatement(sql);
						preparedStatement.execute();
						
						MatiereField.setText("");
						niveauBox.setSelectedItem("Selectionnez");
						
						updateTable();
						JOptionPane.showMessageDialog(null, "Matiere supprimee avec succes!!");
						updateTable();
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}
				
				
				
			}
		});
		button_1.setIcon(new ImageIcon("/Users/macbookpro/Desktop/S3/TPJAVA/GestionEtudiants/Images/delete-branche-res.png"));
		button_1.setBounds(299, 370, 80, 80);
		contentPane.add(button_1);
		
		JLabel lblProfesseur = new JLabel("Professeur : ");
		lblProfesseur.setBounds(89, 249, 110, 16);
		contentPane.add(lblProfesseur);
		
		ProfBox = new JComboBox<String>();
		ProfBox.setModel(new DefaultComboBoxModel<String>(new String[] {"Selectionnez"}));
		ProfBox.setBounds(203, 245, 134, 27);
		contentPane.add(ProfBox);
		FillProfBox();
		
		lblCoef = new JLabel("coef : ");
		lblCoef.setBounds(89, 209, 110, 16);
		contentPane.add(lblCoef);
		
		coefField = new JTextField();
		coefField.setColumns(10);
		coefField.setBounds(203, 204, 130, 26);
		contentPane.add(coefField);
		
		niveauBox = new JComboBox<String>();
		niveauBox.setModel(new DefaultComboBoxModel<String>(new String[] {"Selectionnez"}));
		niveauBox.setBounds(203, 284, 134, 27);
		contentPane.add(niveauBox);
		FillNiveauBox();
		
		JLabel lblNewLabel = new JLabel("New label");
		lblNewLabel.setBounds(0, 0, 1000, 478);
		lblNewLabel.setIcon(new ImageIcon("/Users/macbookpro/Desktop/S3/TPJAVA/GestionEtudiants/Images/Sans titre-1.png"));
		contentPane.add(lblNewLabel);
		
	}
	public void fermer() {
		dispose();
	}
	public void updateTable() {
		String sql = "SELECT * FROM Matieres";
		try {
			preparedStatement = connection.prepareStatement(sql);
			resultSet = preparedStatement.executeQuery();
			table.setModel(DbUtils.resultSetToTableModel(resultSet));
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public void FillProfBox() {
		String sql = "Select * from Professeurs";
		try {
			preparedStatement = connection.prepareStatement(sql);
			resultSet = preparedStatement.executeQuery();
			while (resultSet.next()) {
				String nom = resultSet.getString("Nom_prof").toString();
				ProfBox.addItem(nom);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
	}	
}
	public void FillNiveauBox() {
		String sql = "Select * from Niveaux";
		try {
			preparedStatement = connection.prepareStatement(sql);
			resultSet = preparedStatement.executeQuery();
			while (resultSet.next()) {
				String nom = resultSet.getString("Niveau").toString();
				niveauBox.addItem(nom);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
