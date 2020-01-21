package Etudiants;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.ImageIcon;
import javax.swing.JTextField;
import javax.swing.UIManager;
import javax.swing.plaf.nimbus.NimbusLookAndFeel;
import javax.swing.JButton;
import java.awt.Font;
import javax.swing.JPasswordField;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class Authentification extends JFrame{

	/**
	 * 
	 */
	private static final long serialVersionUID = -4896841933679168232L;
	private JFrame frame;
	private JTextField usernamefield;
	private JPasswordField passwordfield;
	
	Connection connection = null;
	PreparedStatement preparedStatement = null;
	ResultSet resultSet = null;
	

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					UIManager.setLookAndFeel(new NimbusLookAndFeel());
					Authentification window = new Authentification();
					window.frame.setVisible(true);
					window.frame.setLocationRelativeTo(null);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public Authentification() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setResizable(false);
		frame.setBounds(100, 100, 1000, 500);
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		connection = ConnexionMySql.ConnectionDB();
		
		
		usernamefield = new JTextField();
		usernamefield.setBounds(300, 215, 130, 26);
		frame.getContentPane().add(usernamefield);
		usernamefield.setColumns(10);
		
		passwordfield = new JPasswordField();
		passwordfield.setBounds(300, 299, 130, 26);
		frame.getContentPane().add(passwordfield);
		
		JLabel lblUsername = new JLabel("");
		lblUsername.setIcon(new ImageIcon("/Users/macbookpro/Desktop/S3/TPJAVA/GestionEtudiants/Images/username_res.png"));
		lblUsername.setBounds(216, 202, 50, 50);
		frame.getContentPane().add(lblUsername);
		
		JLabel lblPassword = new JLabel("");
		lblPassword.setIcon(new ImageIcon("/Users/macbookpro/Desktop/S3/TPJAVA/GestionEtudiants/Images/pass_res.png"));
		lblPassword.setBounds(216, 281, 50, 50);
		frame.getContentPane().add(lblPassword);
		
		JButton btnNewButton = new JButton("Se Connecter");
		btnNewButton.setBounds(300, 354, 130, 29);
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				String username = usernamefield.getText().toString();
				@SuppressWarnings("deprecation")
				String password = passwordfield.getText().toString();
				
				String sql = "SELECT username , password FROM users";
				try {
					preparedStatement = connection.prepareStatement(sql);
					resultSet = preparedStatement.executeQuery();
					
					int i =0;
					if(username.equals("") || password.equals("")) {
						JOptionPane.showMessageDialog(null, "Veullez remplir tout les champs!! ;)");
					}else {
						while (resultSet.next()) {
							String username1 = resultSet.getString("username");
							String password1 = resultSet.getString("password");
							
							if(username1.equals(username) && password1.equals(password)) {
								JOptionPane.showMessageDialog(null, "Connexion reussie :D");
								i = 1;
								MenuAdministrateur menuAdministrateur = new MenuAdministrateur();
								menuAdministrateur.setVisible(true);
								menuAdministrateur.setLocationRelativeTo(null);
								fermer();
							}
							
							
						}
						if(i==0) {
							JOptionPane.showMessageDialog(null, "Connexion echouee, informations incorrectes :(  ");
						}
					}
					
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
				
			}
		});
		frame.getContentPane().add(btnNewButton);
		
		JLabel lblNewLabel_2 = new JLabel("Gestion Des Etudiants");
		lblNewLabel_2.setBounds(394, 6, 211, 16);
		lblNewLabel_2.setFont(new Font("Roboto", Font.PLAIN, 20));
		frame.getContentPane().add(lblNewLabel_2);
		
		JLabel lblcoleNationaleDes = new JLabel("École Nationale Des Sciences Appliquées - Al Hoceïma");
		lblcoleNationaleDes.setBounds(323, 60, 354, 16);
		frame.getContentPane().add(lblcoleNationaleDes);
		
		JLabel lblNewLabel_3 = new JLabel("Mot de passe oublié?!");
		lblNewLabel_3.setBounds(329, 385, 101, 16);
		lblNewLabel_3.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				IndicationPassword indicationPassword = new IndicationPassword();
				indicationPassword.setVisible(true);
				indicationPassword.setLocationRelativeTo(null);
			}
		});
		lblNewLabel_3.setFont(new Font("Arial", Font.PLAIN, 9));
		frame.getContentPane().add(lblNewLabel_3);
		
		JLabel lblLogo = new JLabel("");
		lblLogo.setBounds(16, 0, 140, 100);
		lblLogo.setIcon(new ImageIcon("/Users/macbookpro/Desktop/S3/TPJAVA/GestionEtudiants/Images/Webp.net-resizeimage.png"));
		frame.getContentPane().add(lblLogo);
		
		JLabel label = new JLabel("");
		label.setBounds(882, 0, 94, 100);
		label.setIcon(new ImageIcon("/Users/macbookpro/Desktop/S3/TPJAVA/GestionEtudiants/Images/uae.png"));
		frame.getContentPane().add(label);
		
		JLabel lblNewLabel_1 = new JLabel("New label");
		lblNewLabel_1.setBounds(0, 0, 1000, 100);
		lblNewLabel_1.setIcon(new ImageIcon("/Users/macbookpro/Desktop/S3/TPJAVA/GestionEtudiants/Images/head1.png"));
		frame.getContentPane().add(lblNewLabel_1);
		
		JLabel lblNewLabel_4 = new JLabel("");
		lblNewLabel_4.setIcon(new ImageIcon("/Users/macbookpro/Desktop/S3/TPJAVA/GestionEtudiants/Images/UIHere.png"));
		lblNewLabel_4.setBounds(621, 123, 314, 314);
		frame.getContentPane().add(lblNewLabel_4);
		
		JLabel lblNewLabel = new JLabel("New label");
		lblNewLabel.setBounds(0, 0, 1000, 478);
		lblNewLabel.setIcon(new ImageIcon("/Users/macbookpro/Desktop/S3/TPJAVA/GestionEtudiants/Images/Sans titre-1.png"));
		frame.getContentPane().add(lblNewLabel);
	}
	public void fermer() {
		frame.dispose();
	}
}
