package view;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import database.DbConnect;

import javax.swing.JButton;
import javax.swing.JLabel;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class AdminMain extends JFrame {

	private JPanel contentPane;
	private AggiungiStudente aggstud = new AggiungiStudente(this,true);
	private AggiungiProfessore aggdoc = new AggiungiProfessore(this,true);
	private AggiungiCorso aggcors = new AggiungiCorso(this,true);
	private AssegnaCorso asscors = new AssegnaCorso(this,true);
	
	
	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					AdminMain frame = new AdminMain();
					DbConnect.getinstance().connect();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public AdminMain() {
		setTitle("Admin men\u00F9");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 265, 232);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JButton btnAggiungiStudente = new JButton("Aggiungi Studente");
		btnAggiungiStudente.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				aggstud.setVisible(true);
			}
		});
		btnAggiungiStudente.setBounds(54, 46, 145, 23);
		contentPane.add(btnAggiungiStudente);
		
		JButton button = new JButton("Aggiungi Docente");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				aggdoc.setVisible(true);
			}
		});
		button.setBounds(54, 80, 145, 23);
		contentPane.add(button);
		
		JButton btnAggiungiCorso = new JButton("Aggiungi Corso");
		btnAggiungiCorso.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				aggcors.setVisible(true);
			}
		});
		btnAggiungiCorso.setBounds(54, 114, 145, 23);
		contentPane.add(btnAggiungiCorso);
		
		JButton btnAssegnaCorso = new JButton("Assegna Corso");
		btnAssegnaCorso.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				asscors.setVisible(true);
			}
		});
		btnAssegnaCorso.setBounds(54, 148, 145, 23);
		contentPane.add(btnAssegnaCorso);
		
		JLabel lblBenvenuto = new JLabel("Benvenuto!");
		lblBenvenuto.setBounds(33, 11, 145, 14);
		contentPane.add(lblBenvenuto);
	}
}
