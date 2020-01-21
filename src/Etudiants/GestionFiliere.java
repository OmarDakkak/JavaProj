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

public class GestionFiliere extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 3470608360916950713L;

	private JPanel contentPane;
	
	Connection connection = null;
	PreparedStatement preparedStatement = null;
	ResultSet resultSet = null;
	private JTable table;
	private JTextField NiveauField;
	

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					UIManager.setLookAndFeel(new NimbusLookAndFeel());
					GestionFiliere frame = new GestionFiliere();
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
	public GestionFiliere() {
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
		
		NiveauField = new JTextField();
		NiveauField.setBounds(199, 246, 130, 26);
		contentPane.add(NiveauField);
		NiveauField.setColumns(10);
		
		JLabel lblNom = new JLabel("Nom du Niveau : ");
		lblNom.setBounds(89, 251, 110, 16);
		contentPane.add(lblNom);
		
		JLabel lblDepartement = new JLabel("Departement : ");
		lblDepartement.setBounds(89, 288, 110, 16);
		contentPane.add(lblDepartement);
		
		Object[] depts = new Object[]{"Selectionnez","Maths Info", "Genie Civil", "GEE", "GEER"};
		@SuppressWarnings({ "rawtypes", "unchecked" })
		JComboBox Departement = new JComboBox(depts);
		Departement.setBounds(199, 284, 134, 27);
		contentPane.add(Departement);
		
		
	
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(517, 175, 441, 284);
		contentPane.add(scrollPane);
		
		table = new JTable();
		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				int ligne = table.getSelectedRow();
				String id = table.getModel().getValueAt(ligne, 0).toString();
				
				String sql = "SELECT * FROM Niveaux where id_niveau = '"+id+"'";
				try {
					preparedStatement = connection.prepareStatement(sql);
					resultSet = preparedStatement.executeQuery();
					if (resultSet.next()) {
						NiveauField.setText(resultSet.getString("Niveau"));
						Departement.setSelectedItem(resultSet.getString("Departement"));
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
		
		JLabel lblNewLabel_3 = new JLabel("Table des niveaux : ");
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
				String niveau = NiveauField.getText().toString();
				String departement = Departement.getSelectedItem().toString();
				
				String sql = "INSERT INTO Niveaux (Niveau, Departement) VALUES (?, ?)";
				try {
					if (!NiveauField.equals("") && !Departement.getSelectedItem().toString().equals("Selectionnez")) {
						preparedStatement = connection.prepareStatement(sql);
						preparedStatement.setString(1, niveau);
						preparedStatement.setString(2, departement);
						preparedStatement.execute();
						
						NiveauField.setText("");
						Departement.setSelectedItem("Selectionnez");
						JOptionPane.showMessageDialog(null, "Niveau Ajoute avec succes!");
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
						
						String sql = "UPDATE Niveaux SET Niveau = ?, Departement = ? WHERE id_niveau = '"+id+"'";
						
						try {
							preparedStatement = connection.prepareStatement(sql);
							preparedStatement.setString(1, NiveauField.getText().toString());
							preparedStatement.setString(2, Departement.getSelectedItem().toString());
							preparedStatement.execute();
							
							NiveauField.setText("");
							Departement.setSelectedItem("Selectionnez");
							JOptionPane.showMessageDialog(null, "Niveau Modifie avec succes!!");
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
					String sql = "DELETE FROM Niveaux WHERE id_niveau = '"+id+"'";
					try {
						preparedStatement = connection.prepareStatement(sql);
						preparedStatement.execute();
						
						NiveauField.setText("");
						Departement.setSelectedItem("Selectionnez");
						
						JOptionPane.showMessageDialog(null, "Niveau supprime avec succes!!");
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
		
		JLabel lblNewLabel = new JLabel("New label");
		lblNewLabel.setBounds(0, 0, 1000, 478);
		lblNewLabel.setIcon(new ImageIcon("/Users/macbookpro/Desktop/S3/TPJAVA/GestionEtudiants/Images/Sans titre-1.png"));
		contentPane.add(lblNewLabel);
		
	}
	public void fermer() {
		dispose();
	}
	public void updateTable() {
		String sql = "SELECT * FROM Niveaux";
		try {
			preparedStatement = connection.prepareStatement(sql);
			resultSet = preparedStatement.executeQuery();
			table.setModel(DbUtils.resultSetToTableModel(resultSet));
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}


