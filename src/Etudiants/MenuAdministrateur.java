package Etudiants;

import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.UIManager;
import javax.swing.border.EmptyBorder;
import javax.swing.plaf.nimbus.NimbusLookAndFeel;

public class MenuAdministrateur extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = -2656631129653085457L;
	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					UIManager.setLookAndFeel(new NimbusLookAndFeel());
					MenuAdministrateur frame = new MenuAdministrateur();
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
	public MenuAdministrateur() {
		setResizable(false);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 1000, 500);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
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
		
		JButton btnNewButton = new JButton("");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				GestionUsers gestionUsers = new GestionUsers();
				gestionUsers.setVisible(true);
				gestionUsers.setLocationRelativeTo(null);
				fermer();
			}
		});
		btnNewButton.setIcon(new ImageIcon("/Users/macbookpro/Desktop/S3/TPJAVA/GestionEtudiants/Images/AddStudent_res.png"));
		btnNewButton.setBounds(39, 112, 100, 100);
		contentPane.add(btnNewButton);
		
		JLabel lblGestionDesUti = new JLabel("Gestion des utilisateurs");
		lblGestionDesUti.setBounds(16, 215, 149, 16);
		contentPane.add(lblGestionDesUti);
		
		JButton button = new JButton("");
		button.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				GestionEtudiants gestionEtudiants = new GestionEtudiants();
				gestionEtudiants.setVisible(true);
				gestionEtudiants.setLocationRelativeTo(null);
				fermer();
			}
		});
		button.setIcon(new ImageIcon("/Users/macbookpro/Desktop/S3/TPJAVA/GestionEtudiants/Images/Student_res.png"));
		button.setBounds(445, 112, 100, 100);
		contentPane.add(button);
		
		JButton button_1 = new JButton("");
		button_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				GestionFiliere gestionFiliere = new GestionFiliere();
				gestionFiliere.setVisible(true);
				gestionFiliere.setLocationRelativeTo(null);
				fermer();
			}
		});
		button_1.setIcon(new ImageIcon("/Users/macbookpro/Desktop/S3/TPJAVA/GestionEtudiants/Images/Filieres_res.png"));
		button_1.setBounds(827, 112, 100, 100);
		contentPane.add(button_1);
		
		JButton button_2 = new JButton("");
		button_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				GestionAbsences gestionAbsences = new GestionAbsences();
				gestionAbsences.setVisible(true);
				gestionAbsences.setLocationRelativeTo(null);
				fermer();
			}
		});
		button_2.setIcon(new ImageIcon("/Users/macbookpro/Desktop/S3/TPJAVA/GestionEtudiants/Images/Abscence_res.png"));
		button_2.setBounds(39, 299, 100, 100);
		contentPane.add(button_2);
		
		JButton button_3 = new JButton("");
		button_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				GestionMatieres gestionMatieres = new GestionMatieres();
				gestionMatieres.setVisible(true);
				gestionMatieres.setLocationRelativeTo(null);
				fermer();
			}
		});
		button_3.setIcon(new ImageIcon("/Users/macbookpro/Desktop/S3/TPJAVA/GestionEtudiants/Images/Mat_res.png"));
		button_3.setBounds(445, 299, 100, 100);
		contentPane.add(button_3);
		
		JButton btnNewButton_1 = new JButton("");
		btnNewButton_1.setIcon(new ImageIcon("/Users/macbookpro/Desktop/S3/TPJAVA/GestionEtudiants/Images/Grade_res.png"));
		btnNewButton_1.setBounds(827, 299, 100, 100);
		contentPane.add(btnNewButton_1);
		
		JLabel lblGestionDesEtudiants = new JLabel("Gestion des étudiants");
		lblGestionDesEtudiants.setBounds(425, 215, 149, 16);
		contentPane.add(lblGestionDesEtudiants);
		
		JLabel lblGestionDesFilires = new JLabel("Gestion des Niveaux");
		lblGestionDesFilires.setBounds(815, 215, 140, 16);
		contentPane.add(lblGestionDesFilires);
		
		JLabel lblGestionDesAbscences = new JLabel("Gestion des abscences");
		lblGestionDesAbscences.setBounds(20, 411, 149, 16);
		contentPane.add(lblGestionDesAbscences);
		
		JLabel lblGestionDesMatires = new JLabel("Gestion des matières");
		lblGestionDesMatires.setBounds(425, 411, 149, 16);
		contentPane.add(lblGestionDesMatires);
		
		JLabel lblGestionDesNotes = new JLabel("Gestion des notes");
		lblGestionDesNotes.setBounds(827, 411, 120, 16);
		contentPane.add(lblGestionDesNotes);
		
		JLabel lblNewLabel = new JLabel("New label");
		lblNewLabel.setBounds(0, 0, 1000, 478);
		lblNewLabel.setIcon(new ImageIcon("/Users/macbookpro/Desktop/S3/TPJAVA/GestionEtudiants/Images/Sans titre-1.png"));
		contentPane.add(lblNewLabel);	
	}
	public void fermer() {
		dispose();
	}
}
