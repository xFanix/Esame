package view;

import java.awt.BorderLayout;

import controller.Loginfo;
import model.Appello;

import java.awt.EventQueue;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
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
	private DefaultTableModel dtm;

	public TeacherMain() {
		setTitle("Men\u00F9 Docente");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(400, 200, 550, 290);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		table = new JTable();
		this.createTable();
		table.getColumnModel().getColumn(0).setPreferredWidth(50);
		table.getColumnModel().getColumn(1).setPreferredWidth(100);
		table.getColumnModel().getColumn(2).setPreferredWidth(130);
		table.getColumnModel().getColumn(3).setPreferredWidth(40);
		table.getColumnModel().getColumn(4).setPreferredWidth(70);
		table.getColumnModel().getColumn(5).setPreferredWidth(50);
		table.setBounds(10, 21, 485, 166);
		contentPane.add(table);
		
		JButton btnAggiungiEsame = new JButton("Nuovo Appello");
		btnAggiungiEsame.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				appello.setVisible(true);
				createTable();
			}
		});
		btnAggiungiEsame.setBounds(10, 214, 128, 23);
		contentPane.add(btnAggiungiEsame);
		
		JButton btnModificaAppello = new JButton("Modifica Appello");
		btnModificaAppello.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				modapp.setVisible(true);
				createTable();
			}
		});
		btnModificaAppello.setBounds(142, 214, 134, 23);
		contentPane.add(btnModificaAppello);
		
		JButton btnCancellaAppello = new JButton("Cancella Appello");
		btnCancellaAppello.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				int row = table.getSelectedRow();
				int id = Integer.valueOf(table.getModel().getValueAt(row,0).toString());
				int dialogResult = JOptionPane.showConfirmDialog (null, "Sei sicuro di voler cancellare questo appello?","Attenzione",JOptionPane.YES_NO_OPTION);
				if(dialogResult == JOptionPane.YES_OPTION){
					fc.CancellaAppello(id);
					createTable();
				}
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

	private void createTable() {
		String[] nomiColonne = {"ID","Corso", "Luogo", "Tipo", "Data", "Iscritti"};
		dtm = new DefaultTableModel(nomiColonne,0);
		ArrayList<Appello> appelliList = new ArrayList<Appello>();
		appelliList = fc.getAppelloByProf(Loginfo.getId());
		for(Appello a : appelliList) {
			Object[] data = {a.getId(),a.getNome(),a.getLuogo(),a.getTipo(),a.getData(), "0"};
			dtm.addRow(data);
		}
		table.setModel(dtm);
	}
}
