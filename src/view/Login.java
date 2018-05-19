package view;

import java.awt.EventQueue;

import javax.swing.*;
import java.awt.BorderLayout;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.layout.RowSpec;
import com.jgoodies.forms.layout.FormSpecs;
import controller.FrontController;
import database.DbConnect;
import net.miginfocom.swing.MigLayout;


public class Login extends JFrame{

	private JTextField txtUid;
	private JPasswordField txtPassword;
	private FrontController control = new FrontController();
	private JComboBox comboBox = new JComboBox();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					DbConnect.getinstance().connect();
					Login window = new Login();
					window.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public Login() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		setBounds(100, 100, 450, 300);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		JButton btnLogin = new JButton("Login");
		btnLogin.setBounds(110, 174, 232, 36);
		btnLogin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String password=new String(txtPassword.getPassword());
				dologin(txtUid.getText(), password);

			}
		});
		getContentPane().setLayout(null);
		getContentPane().add(btnLogin);
		
		txtUid = new JTextField();
		txtUid.setBounds(147, 35, 232, 28);
		getContentPane().add(txtUid);
		txtUid.setColumns(10);
		
		JLabel lblUserId = new JLabel("User ID");
		lblUserId.setBounds(67, 39, 70, 20);
		getContentPane().add(lblUserId);
		
		JLabel lblPassword = new JLabel("Password");
		lblPassword.setBounds(67, 81, 70, 20);
		getContentPane().add(lblPassword);
		
		txtPassword = new JPasswordField();
		txtPassword.setBounds(147, 81, 232, 28);
		getContentPane().add(txtPassword);


		comboBox.setModel(new DefaultComboBoxModel(new String[] {"Studente", "Docente", "Admin"}));
		comboBox.setBounds(147, 123, 230, 26);
		getContentPane().add(comboBox);
	}

	private void dologin(String username, String password){
		if(username.trim().isEmpty() || password.trim().isEmpty()){
			JOptionPane.showMessageDialog(this, "Uno o piu' campi sono stati lasciati vuoti", "Errore",JOptionPane.ERROR_MESSAGE);
		} else {
			String selected_text = (String) comboBox.getItemAt(comboBox.getSelectedIndex());
			boolean success= false;
			try {
				success = control.login(username, password, selected_text);
			} catch (Exception e) {
				JOptionPane.showMessageDialog(this, "Errore", "Errore",JOptionPane.ERROR_MESSAGE);
			}
			if (success){
				switch (selected_text){
					case "Studente":
						StudentMain framestud = new StudentMain();
						dispose();
						framestud.setVisible(true);
						break;
					case "Docente":
						TeacherMain framedoce = new TeacherMain();
						dispose();
						framedoce.setVisible(true);
						break;
					case "Admin":
						break;
				}
			}
			else {
				JOptionPane.showMessageDialog(this, "ID o password errati");
			}
		}
	}
}
