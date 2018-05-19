package view;

import org.jdatepicker.impl.JDatePanelImpl;
import org.jdatepicker.impl.UtilDateModel;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Properties;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JTextField;
import javax.swing.JLabel;

public class ModificaAppello extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;
	private JDatePanelImpl textData;


	/**
	 * Create the dialog.
	 */
	public ModificaAppello(JFrame owner, boolean modal) {
		super(owner, modal);
		setTitle("Aggiunta Appello");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(400, 200, 450, 390);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		
		textField = new JTextField();
		textField.setBounds(152, 11, 230, 28);
		contentPanel.add(textField);
		textField.setColumns(10);
		
		textField_1 = new JTextField();
		textField_1.setColumns(10);
		textField_1.setBounds(152, 52, 230, 28);
		contentPanel.add(textField_1);
		
		textField_2 = new JTextField();
		textField_2.setColumns(10);
		textField_2.setBounds(152, 91, 230, 28);
		contentPanel.add(textField_2);

		
		JLabel lblCorso = new JLabel("Corso");
		lblCorso.setBounds(79, 18, 46, 14);
		contentPanel.add(lblCorso);
		
		JLabel lblLuogo = new JLabel("Luogo");
		lblLuogo.setBounds(79, 59, 46, 14);
		contentPanel.add(lblLuogo);
		
		JLabel lblTipo = new JLabel("Tipo");
		lblTipo.setBounds(79, 98, 46, 14);
		contentPanel.add(lblTipo);

		UtilDateModel model = new UtilDateModel();
		Properties p = new Properties();
		p.put("text.today", "oggi");
		p.put("text.month", "mese");
		p.put("text.year", "anno");
		textData = new JDatePanelImpl(model, p);
		textData.setBounds(152, 130, 230, 175);
		contentPanel.add(textData);
		
		JLabel lblData = new JLabel("Data");
		lblData.setBounds(79, 137, 46, 14);
		contentPanel.add(lblData);
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton okButton = new JButton("OK");
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