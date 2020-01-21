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

import com.toedter.calendar.JCalendar;

import net.proteanit.sql.DbUtils;

import com.toedter.calendar.JDateChooser;
import java.awt.BorderLayout;

import javax.swing.AbstractButton;
import javax.swing.DefaultComboBoxModel;

public class GestionAbsences extends JFrame {

	/**
	 * 
	 */
	private JComboBox NomEtudBox;
	private JComboBox RaisonBox ;
	private JDateChooser dateChooser;
	private static final long serialVersionUID = 3470608360216950713L;

	private JPanel contentPane;
	
	Connection connection = null;
	PreparedStatement preparedStatement = null;
	ResultSet resultSet = null;
	private JTable table;
	

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					UIManager.setLookAndFeel(new NimbusLookAndFeel());
					GestionAbsences frame = new GestionAbsences();
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
	@SuppressWarnings("unchecked")
	public GestionAbsences() {
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
		label.setIcon(new ImageIcon("/Users/macbookpro/Desktop/S3/TPJAVA/GestionEtudiants/Images/uae.png"));
		contentPane.add(label);
		
		JLabel lblNewLabel_1 = new JLabel("New label");
		lblNewLabel_1.setBounds(0, 0, 1000, 100);
		lblNewLabel_1.setIcon(new ImageIcon("/Users/macbookpro/Desktop/S3/TPJAVA/GestionEtudiants/Images/head1.png"));
		contentPane.add(lblNewLabel_1);
		
		JLabel lblNom = new JLabel("Date d'abscence : ");
		lblNom.setBounds(89, 257, 114, 16);
		contentPane.add(lblNom);
		
		JLabel lblDepartement = new JLabel("Justificatif : ");
		lblDepartement.setBounds(89, 288, 110, 16);
		contentPane.add(lblDepartement);
		
		
		
	
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(517, 175, 441, 284);
		contentPane.add(scrollPane);
		
		table = new JTable();
		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				int ligne = table.getSelectedRow();
				String id = table.getModel().getValueAt(ligne, 0).toString();
				
				String sql = "SELECT * FROM Abscences where id_abs = '"+id+"'";
				try {
					preparedStatement = connection.prepareStatement(sql);
					resultSet = preparedStatement.executeQuery();
					if (resultSet.next()) {
						NomEtudBox.setSelectedItem(resultSet.getString("NomEtud"));
						dateChooser.setDateFormatString(resultSet.getString("DateAbs"));
						RaisonBox.setSelectedItem(resultSet.getString("Raison"));
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
		
		JLabel lblNewLabel_3 = new JLabel("Table des Abscences : ");
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
		label_2.setIcon(new ImageIcon("/Users/macbookpro/Desktop/S3/TPJAVA/GestionEtudiants/Images/GoBack_res.png"));
		label_2.setBounds(10, 113, 50, 50);
		contentPane.add(label_2);
		
		JButton btnNewButton = new JButton("");
		btnNewButton.addActionListener(new ActionListener() {
			@SuppressWarnings("unlikely-arg-type")
			public void actionPerformed(ActionEvent e) {
				String nomEtud = NomEtudBox.getSelectedItem().toString();
				String DateAbs = ((JTextField) dateChooser.getDateEditor().getUiComponent()).getText();
				String Raison = RaisonBox.getSelectedItem().toString();
				String sql = "INSERT INTO Abscences (NomEtud, DateAbs, Raison) VALUES (?, ?, ?)";
				
				try {
					if (RaisonBox.getSelectedItem().equals("Selectionnez") || DateAbs.equals("") || NomEtudBox.getSelectedItem().equals("Selectionnez")) {
						JOptionPane.showMessageDialog(null, "Veuillez remplir les champs!!");
					}else {
						preparedStatement = connection.prepareStatement(sql);
						preparedStatement.setString(1, nomEtud);
						preparedStatement.setString(2, DateAbs);
						preparedStatement.setString(3, Raison);
						preparedStatement.execute();
						
						updateTable();
						
						RaisonBox.setSelectedItem("Selectonnez");
						NomEtudBox.setSelectedItem("Selectonnez");
//						dateChooser.setDateFormatString("");
						
						JOptionPane.showMessageDialog(null, "Abscence ajoutee avec succes!");
					}
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
			
			}
		});
		btnNewButton.setIcon(new ImageIcon("/Users/macbookpro/Desktop/S3/TPJAVA/GestionEtudiants/Images/absAdd-res.png"));
		btnNewButton.setBounds(21, 370, 80, 80);
		contentPane.add(btnNewButton);
		
		JButton button = new JButton("");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int ligne = table.getSelectedRow();
				
				String nomEtud = NomEtudBox.getSelectedItem().toString();
				String DateAbs = ((JTextField) dateChooser.getDateEditor().getUiComponent()).getText();
				String Raison = RaisonBox.getSelectedItem().toString();
				
				if (ligne == -1) {
					JOptionPane.showMessageDialog(null, "Selectionez une Abscence!");
				}else {
						String id = table.getModel().getValueAt(ligne, 0).toString();
						
						String sql = "UPDATE Abscences SET NomEtud = ?, DateAbs = ?, Raison = ? WHERE id_abs = '"+id+"'";
						
						try {
							preparedStatement = connection.prepareStatement(sql);
							
							preparedStatement.setString(1, nomEtud);
							preparedStatement.setString(2, DateAbs);
							preparedStatement.setString(3, Raison);
							preparedStatement.execute();
							
							updateTable();
							
							JOptionPane.showMessageDialog(null, "Abscence Modifiee avec succes!!");
							
							
						} catch (SQLException e1) {
							e1.printStackTrace();
						}
				}
				
			}
		});
		button.setIcon(new ImageIcon("/Users/macbookpro/Desktop/S3/TPJAVA/GestionEtudiants/Images/absMod.png"));
		button.setBounds(150, 370, 80, 80);
		contentPane.add(button);
		
		JButton button_1 = new JButton("");
		button_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int ligne = table.getSelectedRow();
				String id = table.getModel().getValueAt(ligne, 0).toString();
				
				if (ligne == -1) {
					JOptionPane.showMessageDialog(null, "Selectionnez une Abscence!!");
				}else {
					String sql = "DELETE FROM Abscences WHERE id_abs = '"+id+"'";
					try {
						preparedStatement = connection.prepareStatement(sql);
						preparedStatement.execute();
						
						
						
						JOptionPane.showMessageDialog(null, "Abscence supprimee avec succes!!");
						updateTable();
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}
				
				
				
			}
		});
		button_1.setIcon(new ImageIcon("/Users/macbookpro/Desktop/S3/TPJAVA/GestionEtudiants/Images/absDel-res.png"));
		button_1.setBounds(299, 370, 80, 80);
		contentPane.add(button_1);
		
		JLabel lblNomEtudiant = new JLabel("Nom Etudiant :");
		lblNomEtudiant.setBounds(89, 218, 110, 16);
		contentPane.add(lblNomEtudiant);
		
		NomEtudBox = new JComboBox(new Object[]{});
		NomEtudBox.setModel(new DefaultComboBoxModel(new String[] {"Selectionnez"}));
		NomEtudBox.setBounds(210, 214, 134, 27);
		contentPane.add(NomEtudBox);
		FillComboBox();
		
		dateChooser = new JDateChooser();
		dateChooser.getCalendarButton().addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		dateChooser.setBounds(215, 247, 130, 26);
		contentPane.add(dateChooser);
		
		RaisonBox = new JComboBox<String>();
		RaisonBox.addItem("Selectionnez");
		RaisonBox.addItem("Maladie");
		RaisonBox.addItem("Retard");
		RaisonBox.setBounds(211, 284, 133, 27);
		contentPane.add(RaisonBox);
		
		JLabel raison = new JLabel("New label");
		raison.setBounds(0, 0, 1000, 478);
		raison.setIcon(new ImageIcon("/Users/macbookpro/Desktop/S3/TPJAVA/GestionEtudiants/Images/Sans titre-1.png"));
		contentPane.add(raison);
		
	}
	public void fermer() {
		dispose();
	}
	public void updateTable() {
		String sql = "SELECT * FROM Abscences";
		try {
			preparedStatement = connection.prepareStatement(sql);
			resultSet = preparedStatement.executeQuery();
			table.setModel(DbUtils.resultSetToTableModel(resultSet));
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public void FillComboBox() {
		String sql = "Select * from Etudiant";
		try {
			preparedStatement = connection.prepareStatement(sql);
			resultSet = preparedStatement.executeQuery();
			while (resultSet.next()) {
				String nom = resultSet.getString("Nom").toString();
				String prenom = resultSet.getString("Prenom").toString();
				NomEtudBox.addItem(prenom+" "+nom);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public String getDateChooserDateFormatString() {
		return dateChooser.getDateFormatString();
	}
	public void setDateChooserDateFormatString(String dateFormatString) {
		dateChooser.setDateFormatString(dateFormatString);
	}
}


