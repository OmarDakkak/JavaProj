package Etudiants;

import java.awt.Desktop;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.MalformedURLException;
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

import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.Image;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;

import net.proteanit.sql.DbUtils;

public class GestionEtudiants extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = -3473826215216270002L;
	private JPanel contentPane;
	private JTextField Nom;
	private JTextField Prenom;
	private JTextField CNE;
	private JTextField CIN;
	private JTextField Num_tel;
	private JTextField Adresse;
	private JTextField DateNaissance;
	private JComboBox<String> niveau;
	
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
					GestionEtudiants frame = new GestionEtudiants();
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
	public GestionEtudiants() {
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
		
		JLabel lblNom = new JLabel("Nom                 : ");
		lblNom.setBounds(124, 139, 110, 16);
		contentPane.add(lblNom);
		
		JLabel lblPrenom = new JLabel("Prenom             :");
		lblPrenom.setBounds(124, 167, 110, 16);
		contentPane.add(lblPrenom);
		
		JLabel lblCne = new JLabel("CNE                  :");
		lblCne.setBounds(124, 195, 110, 16);
		contentPane.add(lblCne);
		
		JLabel lblCin = new JLabel("CIN                   :");
		lblCin.setBounds(124, 223, 110, 16);
		contentPane.add(lblCin);
		
		JLabel lblNumTel = new JLabel("Num Tel            :");
		lblNumTel.setBounds(124, 247, 110, 16);
		contentPane.add(lblNumTel);
		
		JLabel lblAdresse = new JLabel("Adresse             :");
		lblAdresse.setBounds(124, 275, 110, 16);
		contentPane.add(lblAdresse);
		
		JLabel lblDateNaissance = new JLabel("Date Naissance :");
		lblDateNaissance.setBounds(124, 303, 110, 16);
		contentPane.add(lblDateNaissance);
		
		JLabel lblFilire = new JLabel("Filière                :");
		lblFilire.setBounds(124, 331, 110, 16);
		contentPane.add(lblFilire);
		
		Nom = new JTextField();
		Nom.setBounds(234, 134, 130, 26);
		contentPane.add(Nom);
		Nom.setColumns(10);
		
		Prenom = new JTextField();
		Prenom.setColumns(10);
		Prenom.setBounds(234, 162, 130, 26);
		contentPane.add(Prenom);
		
		CNE = new JTextField();
		CNE.setColumns(10);
		CNE.setBounds(234, 190, 130, 26);
		contentPane.add(CNE);
		
		CIN = new JTextField();
		CIN.setColumns(10);
		CIN.setBounds(234, 218, 130, 26);
		contentPane.add(CIN);
		
		Num_tel = new JTextField();
		Num_tel.setColumns(10);
		Num_tel.setBounds(234, 242, 130, 26);
		contentPane.add(Num_tel);
		
		Adresse = new JTextField();
		Adresse.setColumns(10);
		Adresse.setBounds(234, 270, 130, 26);
		contentPane.add(Adresse);
		
		DateNaissance = new JTextField();
		DateNaissance.setColumns(10);
		DateNaissance.setBounds(234, 298, 130, 26);
		contentPane.add(DateNaissance);
		
		niveau = new JComboBox<String>();
		niveau.setBounds(234, 327, 130, 27);
		contentPane.add(niveau);
		FillComboBox();
		
		JButton button = new JButton("");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String cne = CNE.getText().toString();
				String nom = Nom.getText().toString();
				String prenom = Prenom.getText().toString();
				String cin = CIN.getText().toString();
				String numTel = Num_tel.getText().toString();
				String dateNaiss = DateNaissance.getText().toString();
				String adresse = Adresse.getText().toString();
				
				String sql = "INSERT INTO Etudiant(CNE, Nom, Prenom, CIN, Tel, Date_naiss, Adresse, Niveau) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
				try {
					
					if (!cne.equals("") && !nom.equals("")&& !prenom.equals("")&&!cin.equals("")&&!numTel.equals("")&&!dateNaiss.equals("")&&!adresse.equals("") ) {
						preparedStatement = connection.prepareStatement(sql);
						preparedStatement.setString(1, cne);
						preparedStatement.setString(2, nom);
						preparedStatement.setString(3, prenom);
						preparedStatement.setString(4, cin);
						preparedStatement.setString(5, numTel);
						preparedStatement.setString(6, dateNaiss);
						preparedStatement.setString(7, adresse);
						preparedStatement.setString(8, niveau.getSelectedItem().toString());
						
						preparedStatement.execute();
						updateTable();
						
						CNE.setText("");
						Nom.setText("");
						Prenom.setText("");
						CIN.setText("");
						Num_tel.setText("");
						DateNaissance.setText("");
						Adresse.setText("");
						
						JOptionPane.showMessageDialog(null, "Etudiant ajoute avec succes!");
					} else {
						JOptionPane.showMessageDialog(null, "Veuillez remplir tout les champs!!");
					}
					
					
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
				
				
			}
		});
		button.setIcon(new ImageIcon("/Users/macbookpro/Desktop/S3/TPJAVA/GestionEtudiants/Images/add_res.png"));
		button.setBounds(41, 376, 80, 80);
		contentPane.add(button);
		
		JButton button_1 = new JButton("");
		button_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int ligne = table.getSelectedRow();
				if (ligne == -1) {
					JOptionPane.showMessageDialog(null, "Selectionez un Etudiant!!");
				}else {
						String id = table.getModel().getValueAt(ligne, 0).toString();
						
						String sql = "UPDATE Etudiant SET CNE = ?, Nom = ?, Prenom = ?, CIN = ?, Tel = ?, Date_naiss = ?, Adresse = ?, Niveau = ? WHERE id_etudiant = '"+id+"'";
						
						try {
							preparedStatement = connection.prepareStatement(sql);
							preparedStatement.setString(1, CNE.getText().toString());
							preparedStatement.setString(2, Nom.getText().toString());
							preparedStatement.setString(3, Prenom.getText().toString());
							preparedStatement.setString(4, CIN.getText().toString());
							preparedStatement.setString(5, Num_tel.getText().toString());
							preparedStatement.setString(6, DateNaissance.getText().toString());
							preparedStatement.setString(7, Adresse.getText().toString());
							preparedStatement.setString(8, niveau.getSelectedItem().toString());
							preparedStatement.execute();
							
							JOptionPane.showMessageDialog(null, "Etudiant Modifie avec succes!!");
							updateTable();
							
							
						} catch (SQLException e1) {
							e1.printStackTrace();
						}
				}
				
			}
		});
		button_1.setIcon(new ImageIcon("/Users/macbookpro/Desktop/S3/TPJAVA/GestionEtudiants/Images/update_res.png"));
		button_1.setBounds(153, 376, 80, 80);
		contentPane.add(button_1);
		
		JButton button_2 = new JButton("");
		button_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int ligne = table.getSelectedRow();
				if (ligne == -1) {
					JOptionPane.showMessageDialog(null, "Selectionez un Etudiant!!");
				}else {
					String id = table.getModel().getValueAt(ligne, 0).toString();
					
					String sql = "DELETE FROM Etudiant WHERE id_etudiant ='"+id+"'";
					try {
						preparedStatement = connection.prepareStatement(sql);
						preparedStatement.execute();
						JOptionPane.showMessageDialog(null, "Etudiant supprime de la base de donnees!!");
						updateTable();
					} catch (SQLException e1) {
						e1.printStackTrace();
					}
				}
				
				
			}
		});
		button_2.setIcon(new ImageIcon("/Users/macbookpro/Desktop/S3/TPJAVA/GestionEtudiants/Images/delete_res.png"));
		button_2.setBounds(263, 376, 80, 80);
		contentPane.add(button_2);
		
		JButton button_3 = new JButton("");
		button_3.addActionListener(new ActionListener() {
			@SuppressWarnings("static-access")
			public void actionPerformed(ActionEvent e) {
				Document document = new Document();
				
				String sql = "SELECT * FROM Etudiant";
				
				try {
					preparedStatement = connection.prepareStatement(sql);
					resultSet = preparedStatement.executeQuery();
					
					PdfWriter.getInstance(document, new FileOutputStream("/Users/macbookpro/Desktop/FilesJava/Etudiant.pdf"));
					document.open();
					
					Image logo = Image.getInstance("/Users/macbookpro/Desktop/S3/TPJAVA/GestionEtudiants/Images/HeaderPdf.png");
					logo.scaleAbsoluteWidth(600);
					logo.scaleAbsoluteHeight(92);
					logo.setAlignment(logo.ALIGN_CENTER);
					document.add(logo);
					
					document.add(new Paragraph(" "));
					document.add(new Paragraph("Liste des Etudiants :"));
					document.add(new Paragraph(" "));
					document.add(new Paragraph("----------------------------------------------------------------------------------------------------------------------------------"));
					document.add(new Paragraph(" "));
					
					PdfPTable table = new PdfPTable(8);
					table.setWidthPercentage(100);
					
					PdfPCell cell;
					////////////////////////////////////////////////////////////////////////////////////
					cell = new PdfPCell(new Phrase("Nom", FontFactory.getFont("Arial", 11)));
					cell.setHorizontalAlignment(Element.ALIGN_CENTER);
					cell.setBackgroundColor(BaseColor.GRAY);
					table.addCell(cell);  
					
					cell = new PdfPCell(new Phrase("Prenom", FontFactory.getFont("Arial", 11)));
					cell.setHorizontalAlignment(Element.ALIGN_CENTER);
					cell.setBackgroundColor(BaseColor.GRAY);
					table.addCell(cell);
					
					cell = new PdfPCell(new Phrase("CNE", FontFactory.getFont("Arial", 11)));
					cell.setHorizontalAlignment(Element.ALIGN_CENTER);
					cell.setBackgroundColor(BaseColor.GRAY);
					table.addCell(cell);
					
					cell = new PdfPCell(new Phrase("CIN", FontFactory.getFont("Arial", 11)));
					cell.setHorizontalAlignment(Element.ALIGN_CENTER);
					cell.setBackgroundColor(BaseColor.GRAY);
					table.addCell(cell);
					
					cell = new PdfPCell(new Phrase("Num Tel", FontFactory.getFont("Arial", 11)));
					cell.setHorizontalAlignment(Element.ALIGN_CENTER);
					cell.setBackgroundColor(BaseColor.GRAY);
					table.addCell(cell);
					
					cell = new PdfPCell(new Phrase("Adresse", FontFactory.getFont("Arial", 11)));
					cell.setHorizontalAlignment(Element.ALIGN_CENTER);
					cell.setBackgroundColor(BaseColor.GRAY);
					table.addCell(cell);
					
					cell = new PdfPCell(new Phrase("Date Naissance", FontFactory.getFont("Arial", 10)));
					cell.setHorizontalAlignment(Element.ALIGN_CENTER);
					cell.setBackgroundColor(BaseColor.GRAY);
					table.addCell(cell);
					
					cell = new PdfPCell(new Phrase("Niveau", FontFactory.getFont("Arial", 11)));
					cell.setHorizontalAlignment(Element.ALIGN_CENTER);
					cell.setBackgroundColor(BaseColor.GRAY);
					table.addCell(cell);
					
					////////////////////////////////////////////////////////////////////////////////////
					
					while (resultSet.next()) {
					cell = new PdfPCell(new Phrase(resultSet.getString("Nom").toString(), FontFactory.getFont("Arial", 10)));
					cell.setHorizontalAlignment(Element.ALIGN_CENTER);
					//cell.setBackgroundColor(BaseColor.LIGHT_GRAY);
					table.addCell(cell);  
					
					cell = new PdfPCell(new Phrase(resultSet.getString("Prenom").toString(), FontFactory.getFont("Arial", 10)));
					cell.setHorizontalAlignment(Element.ALIGN_CENTER);
					//cell.setBackgroundColor(BaseColor.LIGHT_GRAY);
					table.addCell(cell);
					
					cell = new PdfPCell(new Phrase(resultSet.getString("CNE").toString(), FontFactory.getFont("Arial", 10)));
					cell.setHorizontalAlignment(Element.ALIGN_CENTER);
					//cell.setBackgroundColor(BaseColor.LIGHT_GRAY);
					table.addCell(cell);
					
					cell = new PdfPCell(new Phrase(resultSet.getString("CIN").toString(), FontFactory.getFont("Arial", 10)));
					cell.setHorizontalAlignment(Element.ALIGN_CENTER);
					//cell.setBackgroundColor(BaseColor.LIGHT_GRAY);
					table.addCell(cell);
					
					cell = new PdfPCell(new Phrase(resultSet.getString("Tel").toString(), FontFactory.getFont("Arial", 10)));
					cell.setHorizontalAlignment(Element.ALIGN_CENTER);
					//cell.setBackgroundColor(BaseColor.LIGHT_GRAY);
					table.addCell(cell);
					
					cell = new PdfPCell(new Phrase(resultSet.getString("Adresse").toString(), FontFactory.getFont("Arial", 10)));
					cell.setHorizontalAlignment(Element.ALIGN_CENTER);
					//cell.setBackgroundColor(BaseColor.LIGHT_GRAY);
					table.addCell(cell);
					
					cell = new PdfPCell(new Phrase(resultSet.getString("Date_naiss").toString(), FontFactory.getFont("Arial", 10)));
					cell.setHorizontalAlignment(Element.ALIGN_CENTER);
					//cell.setBackgroundColor(BaseColor.LIGHT_GRAY);
					table.addCell(cell);
					
					cell = new PdfPCell(new Phrase(resultSet.getString("Niveau").toString(), FontFactory.getFont("Arial", 10)));
					cell.setHorizontalAlignment(Element.ALIGN_CENTER);
					//cell.setBackgroundColor(BaseColor.LIGHT_GRAY);
					table.addCell(cell);
					}
					////////////////////////////////////////////////////////////////////////////////////////////////////////////
					document.add(table);
					
					document.close();
					Desktop.getDesktop().open(new File("/Users/macbookpro/Desktop/FilesJava/Etudiant.pdf"));
					
					
				} catch (FileNotFoundException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} catch (DocumentException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} catch (MalformedURLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		button_3.setIcon(new ImageIcon("/Users/macbookpro/Desktop/S3/TPJAVA/GestionEtudiants/Images/printUser_res.png"));
		button_3.setBounds(365, 376, 80, 80);
		contentPane.add(button_3);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(517, 175, 441, 284);
		contentPane.add(scrollPane);
		
		table = new JTable();
		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				int ligne = table.getSelectedRow();
				String id = table.getModel().getValueAt(ligne, 0).toString();
				
				String sql = "SELECT * FROM Etudiant where id_etudiant = '"+id+"'";
				try {
					preparedStatement = connection.prepareStatement(sql);
					resultSet = preparedStatement.executeQuery();
					if (resultSet.next()) {
						CNE.setText(resultSet.getString("CNE"));
						Nom.setText(resultSet.getString("Nom"));
						Prenom.setText(resultSet.getString("Prenom"));
						CIN.setText(resultSet.getString("CIN"));
						Num_tel.setText(resultSet.getString("Tel"));
						DateNaissance.setText(resultSet.getString("Date_naiss"));
						Adresse.setText(resultSet.getString("Adresse"));
						
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
		
		JLabel lblNewLabel_3 = new JLabel("Table des etudiants : ");
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
		
		JLabel lblNewLabel = new JLabel("New label");
		lblNewLabel.setBounds(0, 0, 1000, 478);
		lblNewLabel.setIcon(new ImageIcon("/Users/macbookpro/Desktop/S3/TPJAVA/GestionEtudiants/Images/Sans titre-1.png"));
		contentPane.add(lblNewLabel);
		
	}
	public void fermer() {
		dispose();
	}
	public void updateTable() {
		String sql = "SELECT * FROM Etudiant";
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
		String sql = "Select * from Niveaux";
		try {
			preparedStatement = connection.prepareStatement(sql);
			resultSet = preparedStatement.executeQuery();
			while (resultSet.next()) {
				String nom = resultSet.getString("Niveau").toString();
				niveau.addItem(nom);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
