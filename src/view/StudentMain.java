package view;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JList;
import javax.swing.border.BevelBorder;
import javax.swing.border.MatteBorder;
import java.awt.Color;
import javax.swing.border.LineBorder;
import javax.swing.AbstractListModel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.JLabel;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class StudentMain extends JFrame {

	private JPanel contentPane;
	private JTable table;
	private JScrollPane scrollPane;
	private JTable table_1;
	private ConfermaPrenotazione conpre = new ConfermaPrenotazione(this,true);
	private CancellaPrenotazione canpre = new CancellaPrenotazione(this,true);
	private AccettaVoto accvot = new AccettaVoto(this,true);
	private RifiutaVoto rifvot = new RifiutaVoto(this,true);

	public StudentMain() {
		setTitle("Men\u00F9 Studente");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(400, 200, 587, 500);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		{
		table = new JTable();
		table.setModel(new DefaultTableModel(
			new Object[][] {
				{"Mario Rossi", "Giovanni", "Via pelusia", "S", "30/02"},
			},
			new String[] {
				"Docente", "Corso", "Luogo", "Tipo", "Data"
			}
		));
		table.getColumnModel().getColumn(0).setPreferredWidth(141);
		table.getColumnModel().getColumn(1).setPreferredWidth(131);
		table.getColumnModel().getColumn(2).setPreferredWidth(192);
		table.getColumnModel().getColumn(3).setPreferredWidth(40);
		table.getColumnModel().getColumn(4).setPreferredWidth(60);
		table.setBounds(53, 205, 470, 180);
		}contentPane.add(table);
		
		contentPane.setLayout(null);{
		scrollPane = new JScrollPane(table);
		scrollPane.setBounds(38, 39, 510, 180);
		contentPane.add(scrollPane);
		}
		contentPane.add(scrollPane);
		
		JLabel lblNewLabel = new JLabel("Appelli d'esame");
		lblNewLabel.setLabelFor(scrollPane);
		lblNewLabel.setBounds(38, 14, 129, 14);
		contentPane.add(lblNewLabel);
		
		table_1 = new JTable();
		table_1.setModel(new DefaultTableModel(
			new Object[][] {
				{"Giacomo Verde", "Analisi 2", "Via dal divano", "O", "32/03", "30L"},
			},
			new String[] {
				"Docente", "Corso", "Luogo", "Tipo", "Data", "Voto"
			}
		));
		table_1.getColumnModel().getColumn(0).setPreferredWidth(131);
		table_1.getColumnModel().getColumn(1).setPreferredWidth(121);
		table_1.getColumnModel().getColumn(2).setPreferredWidth(162);
		table_1.getColumnModel().getColumn(3).setPreferredWidth(40);
		table_1.getColumnModel().getColumn(4).setPreferredWidth(60);
		table_1.getColumnModel().getColumn(5).setPreferredWidth(50);
		table_1.setBounds(38, 292, 470, 111);
		contentPane.add(table_1);
		
		JScrollPane scrollPane_1 = new JScrollPane(table_1);
		scrollPane_1.setBounds(38, 292, 510, 111);
		contentPane.add(scrollPane_1);
		
		JLabel lblNewLabel_1 = new JLabel("Esami prenotati");
		lblNewLabel_1.setLabelFor(scrollPane_1);
		lblNewLabel_1.setBounds(38, 269, 129, 14);
		contentPane.add(lblNewLabel_1);
		
		JButton btnPrenota = new JButton("Prenota");
		btnPrenota.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				conpre.setVisible(true);
			}
		});
		btnPrenota.setBounds(459, 230, 89, 23);
		contentPane.add(btnPrenota);
		
		JButton btnAccetta = new JButton("Accetta Voto");
		btnAccetta.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				accvot.setVisible(true);
			}
		});
		btnAccetta.setBounds(325, 414, 115, 23);
		contentPane.add(btnAccetta);
		
		JButton btnRifiutaVoto = new JButton("Rifiuta Voto");
		btnRifiutaVoto.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				rifvot.setVisible(true);
			}
		});
		btnRifiutaVoto.setBounds(446, 414, 102, 23);
		contentPane.add(btnRifiutaVoto);
		
		JButton btnAnnullaPrenotazione = new JButton("Annulla Prenotazione");
		btnAnnullaPrenotazione.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				canpre.setVisible(true);
			}
		});
		btnAnnullaPrenotazione.setBounds(155, 414, 160, 23);
		contentPane.add(btnAnnullaPrenotazione);
	}
}
