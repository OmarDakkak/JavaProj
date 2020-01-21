package Etudiants;

import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.ImageIcon;
import javax.swing.JButton;
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

public class GestionUsers extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 7670534799710977468L;
	private JPanel contentPane;
	private JTextField textField;
	private JTextField textField_1;
	
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
					GestionUsers frame = new GestionUsers();
					frame.setVisible(true);
					frame.setLocationRelativeTo(null);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public GestionUsers() {
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
		
		textField = new JTextField();
		textField.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
//				String sql = "SELECT password FROM users WHERE username = ?";
//				try {
//					preparedStatement = connection.prepareStatement(sql);
//					preparedStatement.setString(1, textField.getText().toString());
//					resultSet = preparedStatement.executeQuery();
//					if(resultSet.next()) {
//						String password = resultSet.getString("password");
//						textField_1.setText(password);
//					}
//				} catch (SQLException e1) {
//					e1.printStackTrace();
//				}
				
			}
		});
		textField.setBounds(130, 241, 130, 26);
		contentPane.add(textField);
		textField.setColumns(10);
		
		textField_1 = new JTextField();
		textField_1.setColumns(10);
		textField_1.setBounds(130, 289, 130, 26);
		contentPane.add(textField_1);
		
		JLabel lblUsername = new JLabel("Username : ");
		lblUsername.setBounds(31, 246, 99, 16);
		contentPane.add(lblUsername);
		
		JLabel lblPassword = new JLabel("Password : ");
		lblPassword.setBounds(31, 294, 99, 16);
		contentPane.add(lblPassword);
		
		JButton btnNewButton = new JButton("");
		btnNewButton.setIcon(new ImageIcon("/Users/macbookpro/Desktop/S3/TPJAVA/GestionEtudiants/Images/add_res.png"));
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				String sql = "INSERT INTO users (username, password) VALUES (?, ?)";
				
				try {
					preparedStatement = connection.prepareStatement(sql);
					preparedStatement.setString(1, textField.getText().toString());
					preparedStatement.setString(2, textField_1.getText().toString());
					if(!textField.getText().equals("") && !textField_1.getText().equals("")) {
						preparedStatement.execute();
						JOptionPane.showMessageDialog(null, "Utilisateur ajoute dans la base de donnees");
						textField.setText("");
						textField_1.setText("");
						updateTable();
					}else {
						JOptionPane.showMessageDialog(null, "Veuillez remplir tout les champs, SVP!");
					}
					
					
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
				
			}
		});
		btnNewButton.setBounds(344, 143, 80, 80);
		contentPane.add(btnNewButton);
		
		JButton btnModifier = new JButton("");
		btnModifier.setIcon(new ImageIcon("/Users/macbookpro/Desktop/S3/TPJAVA/GestionEtudiants/Images/update_res.png"));
		btnModifier.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String sql = "UPDATE users SET password = ? WHERE username = ?";
				try {
					preparedStatement = connection.prepareStatement(sql);
					preparedStatement.setString(1, textField_1.getText().toString());
					preparedStatement.setString(2, textField.getText().toString());
					preparedStatement.execute();
					
					if(!textField.getText().equals("") && !textField_1.getText().equals("")) {
						preparedStatement.execute();
						JOptionPane.showMessageDialog(null, "Utilisateur modifie avec succes!!");
						textField.setText("");
						textField_1.setText("");
						updateTable();
					}else {
						JOptionPane.showMessageDialog(null, "Veuillez remplir tout les champs, SVP!");
					}
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
				
			}
		});
		btnModifier.setBounds(344, 327, 80, 80);
		contentPane.add(btnModifier);
		
		JButton btnSupprimer = new JButton("");
		btnSupprimer.setIcon(new ImageIcon("/Users/macbookpro/Desktop/S3/TPJAVA/GestionEtudiants/Images/delete_res.png"));
		btnSupprimer.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String sql = "DELETE FROM users WHERE username = ? AND password = ?";
				try {
					preparedStatement = connection.prepareStatement(sql);
					preparedStatement.setString(1, textField.getText().toString());
					preparedStatement.setString(2, textField_1.getText().toString());
					if(!textField.getText().equals("") && !textField_1.getText().equals("")) {
						preparedStatement.execute();
						JOptionPane.showMessageDialog(null, "Utilisateur supprime avec succes!");
						textField.setText("");
						textField_1.setText("");
						updateTable();
					}else {
						JOptionPane.showMessageDialog(null, "Veuillez remplir tout les champs, SVP!");
					}
					
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
				
			}
		});
		btnSupprimer.setBounds(344, 235, 80, 80);
		contentPane.add(btnSupprimer);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(517, 175, 441, 284);
		contentPane.add(scrollPane);
		
		table = new JTable();
		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				int ligne = table.getSelectedRow();
				
				String username = table.getModel().getValueAt(ligne, 0).toString();
				String password = table.getModel().getValueAt(ligne, 1).toString(); 
				textField.setText(username);
				textField_1.setText(password);
			}
		});
		scrollPane.setViewportView(table);
		
		JLabel lblNewLabel_3 = new JLabel("New label");
		lblNewLabel_3.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				updateTable();
			}
		});
		
		JLabel lblNewLabel_4 = new JLabel("");
		lblNewLabel_4.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				MenuAdministrateur menuAdministrateur = new MenuAdministrateur();
				menuAdministrateur.setVisible(true);
				menuAdministrateur.setLocationRelativeTo(null);
				fermer();
			}
		});
		lblNewLabel_4.setIcon(new ImageIcon("/Users/macbookpro/Desktop/ProjectJava/GoBack_res.png"));
		lblNewLabel_4.setBounds(10, 102, 50, 50);
		contentPane.add(lblNewLabel_4);
		lblNewLabel_3.setIcon(new ImageIcon("/Users/macbookpro/Desktop/S3/TPJAVA/GestionEtudiants/Images/Releoad.png"));
		lblNewLabel_3.setBounds(515, 113, 50, 50);
		contentPane.add(lblNewLabel_3);
		
		JLabel lblNewLabel = new JLabel("New label");
		lblNewLabel.setBounds(0, 0, 1000, 478);
		lblNewLabel.setIcon(new ImageIcon("/Users/macbookpro/Desktop/S3/TPJAVA/GestionEtudiants/Images/Sans titre-1.png"));
		contentPane.add(lblNewLabel);
		
	}
	public void updateTable() {
		String sql = "SELECT username, password FROM users";
		try {
			preparedStatement = connection.prepareStatement(sql);
			resultSet = preparedStatement.executeQuery();
			table.setModel(DbUtils.resultSetToTableModel(resultSet));
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public void fermer() {
		dispose();
	}
}
