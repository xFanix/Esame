package view;

import java.awt.BorderLayout;
import database.DbConnect;
import model.Appello;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JTable;
import javax.swing.JButton;
import javax.swing.JScrollPane;
import javax.swing.table.DefaultTableModel;

import controller.FrontController;

import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.awt.event.ActionEvent;

public class TeacherMain extends JFrame {

	private JPanel contentPane;
	private JTable table;
	private Iscritti iscritti = new Iscritti(this, true);
	private NuovoAppello appello = new NuovoAppello(this, true);
	private ModificaAppello modapp = new ModificaAppello (this,true);
	private CancellaAppello canapp = new CancellaAppello (this,true);
	private FrontController fc = new FrontController();

	public TeacherMain() {
		setTitle("Men\u00F9 Docente");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(400, 200, 550, 290);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		table = new JTable();
		String[] nomiColonne = {"Corso", "Luogo", "Tipo", "Data", "Iscritti"};
		DefaultTableModel dtm = new DefaultTableModel(nomiColonne,0);
		ArrayList<Appello> appelliList = new ArrayList<Appello>();
		appelliList = fc.getAppelloByProf("1");
		for(Appello a : appelliList) {
			 Object[] data = {a.getNome(),a.getLuogo(),a.getTipo(),a.getData(), "0"};
			 dtm.addRow(data);
			}
		table.setModel(dtm);
		table.getColumnModel().getColumn(0).setPreferredWidth(100);
		table.getColumnModel().getColumn(1).setPreferredWidth(150);
		table.getColumnModel().getColumn(2).setPreferredWidth(40);
		table.getColumnModel().getColumn(3).setPreferredWidth(50);
		table.getColumnModel().getColumn(4).setPreferredWidth(50);
		table.setBounds(10, 21, 485, 166);
		contentPane.add(table);
		
		JButton btnAggiungiEsame = new JButton("Nuovo Appello");
		btnAggiungiEsame.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				appello.setVisible(true);
			}
		});
		btnAggiungiEsame.setBounds(10, 214, 128, 23);
		contentPane.add(btnAggiungiEsame);
		
		JButton btnModificaAppello = new JButton("Modifica Appello");
		btnModificaAppello.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				modapp.setVisible(true);
			}
		});
		btnModificaAppello.setBounds(142, 214, 134, 23);
		contentPane.add(btnModificaAppello);
		
		JButton btnCancellaAppello = new JButton("Cancella Appello");
		btnCancellaAppello.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				canapp.setVisible(true);
			}
		});
		btnCancellaAppello.setBounds(281, 214, 128, 23);
		contentPane.add(btnCancellaAppello);
		
		JButton btnVediIscritti = new JButton("Vedi Iscritti");
		btnVediIscritti.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				iscritti.setVisible(true);
			}
		});
		btnVediIscritti.setBounds(415, 214, 109, 23);
		contentPane.add(btnVediIscritti);
		
		JScrollPane scrollPane = new JScrollPane(table);
		scrollPane.setBounds(10, 21, 514, 166);
		contentPane.add(scrollPane);
	}
}
