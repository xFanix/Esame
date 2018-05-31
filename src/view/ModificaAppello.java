package view;

import controller.FrontController;
import model.Appello;
import model.Corso;
import org.jdatepicker.impl.JDatePanelImpl;
import org.jdatepicker.impl.UtilDateModel;
import view.components.ComboBoxExtended;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.Properties;
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JTextField;
import javax.swing.JLabel;

public class ModificaAppello extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private ComboBoxExtended<String> tipoText;
	private ComboBoxExtended<Corso> comboBoxCorso;
	//private JTextField corsoText;
	private JTextField luogoText;
	//private JTextField tipoText;
	private JDatePanelImpl textData;
	private int appelloID;
	private JButton okButton;
	private JTextField nomeCorso;


	/**
	 * Create the dialog.
	 */
	public ModificaAppello(JFrame owner, boolean modal) {
		super(owner, modal);
		setTitle("Aggiunta Appello");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(400, 200, 450, 450);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		

		
		luogoText = new JTextField();
		luogoText.setColumns(10);
		luogoText.setBounds(152, 52, 230, 28);
		contentPanel.add(luogoText);

		Vector<String> tipi = new Vector<String>();
		tipi.add("Scritto");
		tipi.add("Orale");
		tipi.add("Laboratorio");
		tipoText = new ComboBoxExtended<String>(tipi);
		tipoText.setBounds(152, 91, 230, 28);
		contentPanel.add(tipoText);

		nomeCorso = new JTextField();
		nomeCorso.setColumns(10);
		nomeCorso.setBounds(152, 130, 230, 28);
		contentPanel.add(nomeCorso);
		
		JLabel lblCorso = new JLabel("Corso");
		lblCorso.setBounds(79, 18, 46, 14);
		contentPanel.add(lblCorso);
		
		JLabel lblLuogo = new JLabel("Luogo");
		lblLuogo.setBounds(79, 59, 46, 14);
		contentPanel.add(lblLuogo);
		
		JLabel lblTipo = new JLabel("Tipo");
		lblTipo.setBounds(79, 98, 46, 14);
		contentPanel.add(lblTipo);

		JLabel lblNome = new JLabel("Nome");
		lblNome.setBounds(79, 135, 46, 14);
		contentPanel.add(lblNome);

		UtilDateModel model = new UtilDateModel();
		Properties p = new Properties();
		p.put("text.today", "oggi");
		p.put("text.month", "mese");
		p.put("text.year", "anno");
		textData = new JDatePanelImpl(model, p);
		textData.setBounds(152, 172, 230, 175);
		contentPanel.add(textData);
		
		JLabel lblData = new JLabel("Data");
		lblData.setBounds(79, 174, 46, 14);
		contentPanel.add(lblData);
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				okButton = new JButton("OK");
				okButton.setActionCommand("OK");
				okButton.addActionListener(new ActionListener() {
					@Override
					public void actionPerformed(ActionEvent e) {
						modificaAppello();
					}
				});
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

	private void modificaAppello(){

	}

	public void setCorsi(Vector<Corso> corsi) {
		if (corsi.size()==0) okButton.setEnabled(false);
		comboBoxCorso = new ComboBoxExtended(corsi);
		comboBoxCorso.setBounds(152, 11, 230, 28);
		contentPanel.add(comboBoxCorso);
	}
	public void setAppello(Appello appello){
		appelloID = Integer.parseInt(appello.getId());
		luogoText.setText(appello.getLuogo());
		LocalDate date = LocalDate.parse(appello.getData());
		textData.getModel().setDay(date.getDayOfMonth());
		textData.getModel().setMonth(date.getMonthValue());
		textData.getModel().setYear(date.getYear());
		tipoText.getModel().setSelectedItem(appello.getTipo());

		comboBoxCorso.getModel().setSelectedItem(appello.getCorso());
	}
}