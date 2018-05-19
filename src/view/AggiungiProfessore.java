package view;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import controller.FrontController;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;


	public class AggiungiProfessore extends JDialog {

		private final JPanel contentPanel = new JPanel();
		private JTextField textNome;
		private JTextField textCognome;
		private JTextField textEmail;
		private FrontController control = new FrontController();

		private void startAggiungiProfessore(){
			String nome=textNome.getText();
			String cognome=textCognome.getText();
			String email=textEmail.getText();
			
			if(nome.trim().isEmpty() || cognome.trim().isEmpty() || email.trim().isEmpty()){
				JOptionPane.showMessageDialog(this, "Uno o più campi sono stati lasciati vuoti", "Errore",JOptionPane.ERROR_MESSAGE);
			} else {
				boolean success=control.CreaProfessore(nome, cognome, email);
				if(success){
					JOptionPane.showMessageDialog(this, "Docente creato con successo");
				} else {
					JOptionPane.showMessageDialog(this, "Errore nella creazione del docente");
				}
			}
		}
		
		public AggiungiProfessore(JFrame owner, boolean modal) {
			super(owner, modal);
			setTitle("Aggiunta Professore");
			setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
			setBounds(400, 200, 450, 204);	
			getContentPane().setLayout(new BorderLayout());
			contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
			getContentPane().add(contentPanel, BorderLayout.CENTER);
			contentPanel.setLayout(null);
			
			textNome = new JTextField();
			textNome.setBounds(152, 11, 230, 28);
			contentPanel.add(textNome);
			textNome.setColumns(10);
			
			textCognome = new JTextField();
			textCognome.setColumns(10);
			textCognome.setBounds(152, 52, 230, 28);
			contentPanel.add(textCognome);
			
			textEmail = new JTextField();
			textEmail.setColumns(10);
			textEmail.setBounds(152, 91, 230, 28);
			contentPanel.add(textEmail);
			
			JLabel lblCorso = new JLabel("Nome");
			lblCorso.setBounds(40, 18, 85, 14);
			contentPanel.add(lblCorso);
			
			JLabel lblLuogo = new JLabel("Cognome");
			lblLuogo.setBounds(40, 59, 85, 14);
			contentPanel.add(lblLuogo);
			
			JLabel lblTipo = new JLabel("Indirizzo email");
			lblTipo.setBounds(40, 98, 85, 14);
			contentPanel.add(lblTipo);
			{
				JPanel buttonPane = new JPanel();
				buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
				getContentPane().add(buttonPane, BorderLayout.SOUTH);
				{
					JButton okButton = new JButton("OK");
					okButton.addActionListener(new ActionListener() {
						public void actionPerformed(ActionEvent arg0) {
							startAggiungiProfessore();
						}
					});
					okButton.setActionCommand("OK");
					buttonPane.add(okButton);
					getRootPane().setDefaultButton(okButton);
				}
				{
					JButton cancelButton = new JButton("Cancel");
					cancelButton.addActionListener(new ActionListener() {
						public void actionPerformed(ActionEvent arg0) {
							dispose();
						}
					});
					cancelButton.setActionCommand("Cancel");
					buttonPane.add(cancelButton);
				}
			}
		}
	}