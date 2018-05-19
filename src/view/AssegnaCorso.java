package view;

import java.awt.BorderLayout;
import java.awt.Color;
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
import java.util.ArrayList;
import java.awt.event.ActionEvent;
import controller.AutoSuggestor;

	public class AssegnaCorso extends JDialog {

		private final JPanel contentPanel = new JPanel();
		private JTextField textNomeCorso;
		private JTextField textDocente;
		private FrontController control = new FrontController();

		private void startAssegnaCorso(){
			String nomeCorso=textNomeCorso.getText();
			String docente=textDocente.getText();
			
			if(nomeCorso.trim().isEmpty() || docente.trim().isEmpty()){
				JOptionPane.showMessageDialog(this, "Uno o piu' campi sono stati lasciati vuoti", "Errore",JOptionPane.ERROR_MESSAGE);
			} else {
				boolean success=control.AssegnaCorso(nomeCorso, docente);
				if (success){
					JOptionPane.showMessageDialog(this, "Corso assegnato con successo");
				}
				else {
					JOptionPane.showMessageDialog(this, "Errore nell'assegnazione di un corso");
				}
			}
		}
		
		public AssegnaCorso(JFrame owner, boolean modal) {
			super(owner, modal);
			setTitle("Assegnamento Corso");
			setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
			setBounds(400, 200, 450, 175);	
			getContentPane().setLayout(new BorderLayout());
			contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
			getContentPane().add(contentPanel, BorderLayout.CENTER);
			contentPanel.setLayout(null);
			
			textNomeCorso = new JTextField();
			
			AutoSuggestor autoSuggestorCorso = new AutoSuggestor(textNomeCorso, this, null, Color.WHITE.brighter(), Color.BLUE, Color.RED, 0.75f) {
	            @Override
				protected
	            boolean wordTyped(String typedWord) {

	                //create list for dictionary this in your case might be done via calling a method which queries db and returns results as arraylist
	                ArrayList<String> words = new ArrayList<>();
	                words=control.getCorsoSuggestion(typedWord);

	                setDictionary(words);
	                //addToDictionary("bye");//adds a single word

	                return super.wordTyped(typedWord);//now call super to check for any matches against newest dictionary
	            }
	        };
			
			textNomeCorso.setBounds(152, 11, 230, 28);
			contentPanel.add(textNomeCorso);
			textNomeCorso.setColumns(10);
			
			textDocente = new JTextField();
			
			AutoSuggestor autoSuggestor = new AutoSuggestor(textDocente, this, null, Color.WHITE.brighter(), Color.BLUE, Color.RED, 0.75f) {
	            @Override
				protected
	            boolean wordTyped(String typedWord) {

	                //create list for dictionary this in your case might be done via calling a method which queries db and returns results as arraylist
	                ArrayList<String> words = new ArrayList<>();
	                words=control.getProfSuggestion(typedWord);

	                setDictionary(words);
	                //addToDictionary("bye");//adds a single word

	                return super.wordTyped(typedWord);//now call super to check for any matches against newest dictionary
	            }
	        };
			
			
			textDocente.setColumns(10);
			textDocente.setBounds(152, 52, 230, 28);
			contentPanel.add(textDocente);
			
			JLabel lblCorso = new JLabel("Nome Corso");
			lblCorso.setBounds(40, 18, 85, 14);
			contentPanel.add(lblCorso);
			
			JLabel lblLuogo = new JLabel("Docente");
			lblLuogo.setBounds(40, 59, 85, 14);
			contentPanel.add(lblLuogo);
			{
				JPanel buttonPane = new JPanel();
				buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
				getContentPane().add(buttonPane, BorderLayout.SOUTH);
				{
					JButton okButton = new JButton("OK");
					okButton.addActionListener(new ActionListener() {
						public void actionPerformed(ActionEvent e) {
							startAssegnaCorso();
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