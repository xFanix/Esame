package view;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;

public class CancellaAppello extends JDialog {

	private final JPanel contentPanel = new JPanel();


	/**
	 * Create the dialog.
	 */
	public CancellaAppello(JFrame owner, boolean modal) {
		super(owner, modal);
		setTitle("Cancella Appello");
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
		{
			btnAnnulla.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					dispose();
				}
			});
			btnAnnulla.setActionCommand("Cancel");
			contentPanel.add(btnAnnulla);
		}
		
		JLabel lblStaiPerCancellare = new JLabel("Stai per cancellare questo appello d'esame \r\n");
		lblStaiPerCancellare.setBounds(22, 11, 240, 20);
		contentPanel.add(lblStaiPerCancellare);
		
		JLabel lblSicuroDiVoler = new JLabel("Sicuro di voler continuare?");
		lblSicuroDiVoler.setBounds(23, 30, 163, 14);
		contentPanel.add(lblSicuroDiVoler);
	}
}
