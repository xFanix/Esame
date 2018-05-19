package view;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.util.Date;
import java.util.Properties;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import org.jdatepicker.JDatePicker;
import org.jdatepicker.impl.JDatePanelImpl;
import org.jdatepicker.impl.UtilDateModel;

import controller.FrontController;
import controller.Validator;

import java.awt.event.ActionListener;
import java.time.LocalDate;
import java.time.Year;
import java.time.ZoneId;
import java.time.temporal.ChronoUnit;
import java.awt.event.ActionEvent;


	public class AggiungiStudente extends JDialog {

		private final JPanel contentPanel = new JPanel();
		private JTextField textNome;
		private JTextField textCognome;
		private JTextField textEmail;
		private JDatePanelImpl textData;
		private FrontController control = new FrontController();

		private void startCreaStudente(){
			String nome=textNome.getText();
			String cognome=textCognome.getText();
			String email=textEmail.getText();
			Date data=(Date)textData.getModel().getValue();
			LocalDate birthdate;
			try {
				birthdate = data.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();}
			catch(Exception e) {
					JOptionPane.showMessageDialog(this, "Data non inserita", "Errore",JOptionPane.ERROR_MESSAGE);
					return;
				}
			LocalDate now = LocalDate.now();
			long age = ChronoUnit.YEARS.between(birthdate, now);
			if(nome.trim().isEmpty() || cognome.trim().isEmpty() || email.trim().isEmpty()){
				JOptionPane.showMessageDialog(this, "Uno o piu' campi sono stati lasciati vuoti", "Errore",JOptionPane.ERROR_MESSAGE);
			} else if (!Validator.validateemail(email)){
				JOptionPane.showMessageDialog(this, "Email non valida", "Errore",JOptionPane.ERROR_MESSAGE);
			}
			else if(age<18){
				JOptionPane.showMessageDialog(this, "Data di nascita non valida", "Errore",JOptionPane.ERROR_MESSAGE);
			}
			else
			{
				boolean success=control.CreaStudente(nome,cognome,email,data);
				if(success){
					JOptionPane.showMessageDialog(this,"Studente creato con successo");
				} else {
					JOptionPane.showMessageDialog(this,"Errore nella creazione dello studente");
				}
			}
		}
		
		public AggiungiStudente(JFrame owner, boolean modal) {
			super(owner, modal);
			setTitle("Aggiunta Studente");
			setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
			setBounds(400, 200, 450, 383);
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
			
			UtilDateModel model = new UtilDateModel();
			Properties p = new Properties();
			p.put("text.today", "oggi");
			p.put("text.month", "mese");
			p.put("text.year", "anno");
			textData = new JDatePanelImpl(model, p);
			textData.setBounds(152, 130, 230, 175);
			contentPanel.add(textData);
			
			JLabel lblDataDiNascita = new JLabel("Data di nascita");
			lblDataDiNascita.setBounds(40, 137, 85, 14);
			contentPanel.add(lblDataDiNascita);

			{
				JPanel buttonPane = new JPanel();
				buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
				getContentPane().add(buttonPane, BorderLayout.SOUTH);
				{
					JButton okButton = new JButton("OK");
					okButton.addActionListener(new ActionListener() {
						public void actionPerformed(ActionEvent arg0) {
							startCreaStudente();
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