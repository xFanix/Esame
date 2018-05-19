package view;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

public class AccettaVoto extends JDialog {

	private final JPanel contentPanel = new JPanel();

	/**
	 * Create the dialog.
	 */
	public AccettaVoto(JFrame owner, boolean modal) {
		super(owner, modal);
		setTitle("Accetta Voto");
		setBounds(550, 250, 279, 138);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		
		JButton btnNewButton = new JButton("Prosegui");
		btnNewButton.setBounds(22, 65, 97, 23);
		contentPanel.add(btnNewButton);
		
		JButton btnAnnulla = new JButton("Annulla");
		btnAnnulla.setBounds(129, 65, 112, 23);
		contentPanel.add(btnAnnulla);
		
		JLabel lblStaiPerCancellare = new JLabel("Stai per accettare questo voto\r\n");
		lblStaiPerCancellare.setBounds(22, 11, 240, 20);
		contentPanel.add(lblStaiPerCancellare);
		
		JLabel lblSicuroDiVoler = new JLabel("Sicuro di voler continuare?");
		lblSicuroDiVoler.setBounds(23, 30, 163, 14);
		contentPanel.add(lblSicuroDiVoler);
	}
}