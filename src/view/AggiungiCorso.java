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
import controller.Validator;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

	public class AggiungiCorso extends JDialog {

		private final JPanel contentPanel = new JPanel();
		private JTextField textNomeCorso;
		private JTextField textCFU;
		private FrontController control = new FrontController();

		private void startAggiungiCorso(){
			String nomeCorso=textNomeCorso.getText();
			String CFU=textCFU.getText();
			
			if(nomeCorso.trim().isEmpty() || CFU.trim().isEmpty()){
				JOptionPane.showMessageDialog(this, "Uno o piu' campi sono stati lasciati vuoti", "Errore",JOptionPane.ERROR_MESSAGE);
			} else if(!Validator.validatecfu(CFU)){
				JOptionPane.showMessageDialog(this, "CFU non numerico", "Errore",JOptionPane.ERROR_MESSAGE);
			}
				else{
				boolean success=control.MakeCorso(nomeCorso, CFU);
				if (success){
					JOptionPane.showMessageDialog(this, "Corso creato con successo");
				}
				else {
					JOptionPane.showMessageDialog(this, "Errore nella creazione di un corso");
				}
			}
		}
		
		public AggiungiCorso(JFrame owner, boolean modal) {
			super(owner, modal);
			setTitle("Aggiunta Corso");
			setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
			setBounds(400, 200, 450, 172);	
			getContentPane().setLayout(new BorderLayout());
			contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
			getContentPane().add(contentPanel, BorderLayout.CENTER);
			contentPanel.setLayout(null);
			
			textNomeCorso = new JTextField();
			textNomeCorso.setBounds(152, 11, 230, 28);
			contentPanel.add(textNomeCorso);
			textNomeCorso.setColumns(10);
			
			textCFU = new JTextField();
			textCFU.setColumns(10);
			textCFU.setBounds(152, 52, 230, 28);
			contentPanel.add(textCFU);
			
			JLabel lblCorso = new JLabel("Nome Corso");
			lblCorso.setBounds(40, 18, 85, 14);
			contentPanel.add(lblCorso);
			
			JLabel lblLuogo = new JLabel("CFU");
			lblLuogo.setBounds(40, 59, 85, 14);
			contentPanel.add(lblLuogo);
			{
				JPanel buttonPane = new JPanel();
				buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
				getContentPane().add(buttonPane, BorderLayout.SOUTH);
				{
					JButton okButton = new JButton("OK");
					okButton.addActionListener(new ActionListener() {
						public void actionPerformed(ActionEvent arg0) {
							startAggiungiCorso();
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