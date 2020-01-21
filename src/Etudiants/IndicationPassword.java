package Etudiants;

import java.awt.EventQueue;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.UIManager;
import javax.swing.border.EmptyBorder;
import javax.swing.plaf.nimbus.NimbusLookAndFeel;

public class IndicationPassword extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4877900356343802459L;
	private JPanel contentPane;
	private JTextField textField;
	private JTextField textField_1;
	
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
					IndicationPassword frame = new IndicationPassword();
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
	public IndicationPassword() {
		setResizable(false);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 522, 205);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		connection = ConnexionMySql.ConnectionDB();
		
		JLabel lblUsername = new JLabel("Username : ");
		lblUsername.setBounds(62, 41, 74, 16);
		contentPane.add(lblUsername);
		
		textField = new JTextField();
		textField.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				String username = textField.getText().toString();
				String sql = "SELECT * FROM users WHERE username = ? ;";
				
				try {
					preparedStatement = connection.prepareStatement(sql);
					preparedStatement.setString(1, username);
					resultSet = preparedStatement.executeQuery();
					if(resultSet.next()) {
						String pass = resultSet.getString("password");
						String parsedPass = pass.substring(0,3);
						textField_1.setText("first 3 letters of your password are : "+parsedPass);
					}
					
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
			}
		});
		textField.setBounds(148, 36, 194, 26);
		contentPane.add(textField);
		textField.setColumns(10);
		
		textField_1 = new JTextField();
		textField_1.setEditable(false);
		textField_1.setBounds(148, 86, 267, 60);
		contentPane.add(textField_1);
		textField_1.setColumns(10);
	}

}
