package view;

import controller.FrontController;
import model.Corso;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JSpinner;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;

public class NuovoAppello extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JTextField textField_1;
	private JTextField textField_2;
	private JTextField textField_3;
	private FrontController control = new FrontController();
	private JButton okButton = new JButton("OK");


	public NuovoAppello(JFrame owner, boolean modal) {
		super(owner, modal);
		setTitle("Aggiunta Appello");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(400, 200, 450, 250);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		
		textField_1 = new JTextField();
		textField_1.setColumns(10);
		textField_1.setBounds(152, 52, 230, 28);
		contentPanel.add(textField_1);
		
		textField_2 = new JTextField();
		textField_2.setColumns(10);
		textField_2.setBounds(152, 91, 230, 28);
		contentPanel.add(textField_2);
		
		textField_3 = new JTextField();
		textField_3.setColumns(10);
		textField_3.setBounds(152, 130, 230, 28);
		contentPanel.add(textField_3);
		
		JLabel lblCorso = new JLabel("Corso");
		lblCorso.setBounds(79, 18, 46, 14);
		contentPanel.add(lblCorso);
		
		JLabel lblLuogo = new JLabel("Luogo");
		lblLuogo.setBounds(79, 59, 46, 14);
		contentPanel.add(lblLuogo);
		
		JLabel lblTipo = new JLabel("Tipo");
		lblTipo.setBounds(79, 98, 46, 14);
		contentPanel.add(lblTipo);
		
		JLabel lblData = new JLabel("Data");
		lblData.setBounds(79, 137, 46, 14);
		contentPanel.add(lblData);


		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{

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
			Vector<Corso> corsi = control.getCorsiByUsername();
			if (corsi.size()==0) okButton.setEnabled(false);
				ComboBoxCorso comboBox = new ComboBoxCorso(corsi);
			comboBox.setBounds(152, 15, 230, 26);
			contentPanel.add(comboBox);
		}
	}
}
