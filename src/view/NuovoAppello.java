package view;

import controller.FrontController;
import model.Corso;
import org.jdatepicker.impl.JDatePanelImpl;
import org.jdatepicker.impl.UtilDateModel;
import view.components.ComboBoxExtended;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.lang.reflect.Array;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.temporal.ChronoUnit;
import java.util.*;

import javax.swing.*;
import javax.swing.border.EmptyBorder;

import static java.util.Arrays.asList;

public class NuovoAppello extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private ComboBoxExtended<String> tipoText;
	private JTextField luogoText;
	private FrontController control = new FrontController();
	private JButton okButton = new JButton("OK");
	private JDatePanelImpl textData;
	private ComboBoxExtended<Corso> comboBoxCorso;
	private final int MIN_DAYS_EXAM = 20;

	public NuovoAppello(JFrame owner, boolean modal) {
		super(owner, modal);
		setTitle("Aggiunta Appello");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(400, 200, 450, 390);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);

		Vector<String> tipi = new Vector<String>();
		tipi.add("Scritto");
		tipi.add("Orale");
		tipi.add("Laboratorio");
		tipoText = new ComboBoxExtended<String>(tipi);
		tipoText.setBounds(152, 52, 230, 28);
		contentPanel.add(tipoText);

		luogoText = new JTextField();
		luogoText.setColumns(10);
		luogoText.setBounds(152, 91, 230, 28);
		contentPanel.add(luogoText);



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

				okButton.setActionCommand("OK");
				okButton.addActionListener(new ActionListener() {
					@Override
					public void actionPerformed(ActionEvent e) {
						creaAppello();
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
			Vector<Corso> corsi = control.getCorsiByUsername();
			if (corsi.size()==0) okButton.setEnabled(false);
				comboBoxCorso = new ComboBoxExtended(corsi);
			comboBoxCorso.setBounds(152, 15, 230, 26);
			contentPanel.add(comboBoxCorso);
		}
	}

	private void creaAppello() {
		String tipo = (String) tipoText.getModel().getElementAt(tipoText.getSelectedIndex());
		Corso corso = ((Corso) comboBoxCorso.getModel().getSelectedItem());
		String luogo = luogoText.getText();
		Date data=(Date)textData.getModel().getValue();
		LocalDate examDate;
		try {
			examDate = data.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
		}catch(Exception e) {
			JOptionPane.showMessageDialog(this, "Data non inserita", "Errore",JOptionPane.ERROR_MESSAGE);
			return;
		}
		LocalDate now = LocalDate.now();
		long days = ChronoUnit.DAYS.between(now, examDate);
		if(days < MIN_DAYS_EXAM) {
			JOptionPane.showMessageDialog(this, "La data d'esame deve essere ad almeno "+MIN_DAYS_EXAM+" giorni da oggi", "Errore",JOptionPane.ERROR_MESSAGE);
			return;
		} else if (luogo.trim().length() == 0) {
			JOptionPane.showMessageDialog(this, "Il luogo non può essere vuoto", "Errore",JOptionPane.ERROR_MESSAGE);
			return;
		} else {
			if(control.creaAppello(corso.getId(),tipo,examDate,luogo)) {
				JOptionPane.showMessageDialog(this,"Appello creato con successo");
			} else {
				JOptionPane.showMessageDialog(this,"Errore nella creazione dell'appello");

			}
		}
	}
}
