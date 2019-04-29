package userMangementSystem;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class Login_gui extends JFrame {

	private JPanel contentPane;
	private JTextField accountField;
	private JPasswordField passwordField;
	
	Controller aController =  Controller.getController();
	
	
	

	/**
	 * Launch the application.
	 */
	
	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Login_gui frame =  new Login_gui();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	/**
	 * Create the frame.
	 */
	public Login_gui() {
		setResizable(false);
		setTitle("User Management System");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		accountField = new JTextField();
		accountField.setBounds(138, 88, 143, 21);
		contentPane.add(accountField);
		accountField.setColumns(10);
		
		passwordField = new JPasswordField();
		passwordField.setBounds(138, 143, 143, 21);
		contentPane.add(passwordField);
		
		JButton btnLogin = new JButton("Login");
		btnLogin.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				aController.setUserAccount(accountField.getText());
				char[] pass = passwordField.getPassword();
				String passString = new String(pass);
				aController.setUserPassword(passString);
				aController.checkUser();
			}
		});
		btnLogin.setFont(new Font("敺株�迤暺��", Font.PLAIN, 24));
		btnLogin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			}
		});
		btnLogin.setBounds(314, 88, 110, 76);
		contentPane.add(btnLogin);
		
		JLabel lblAccount = new JLabel("Account:");
		lblAccount.setFont(new Font("敺株�迤暺��", Font.PLAIN, 24));
		lblAccount.setBounds(10, 84, 122, 28);
		contentPane.add(lblAccount);
		
		JLabel lblPassword = new JLabel("Password:");
		lblPassword.setFont(new Font("敺株蔓����", Font.PLAIN, 24));
		lblPassword.setBounds(10, 139, 122, 28);
		contentPane.add(lblPassword);
		
		JLabel lblNewLabel = new JLabel("Login System");
		lblNewLabel.setFont(new Font("�蝝唳���", Font.BOLD, 32));
		lblNewLabel.setBounds(98, 27, 232, 35);
		contentPane.add(lblNewLabel);
		
		JButton btnCreateAccount = new JButton("Create a new account");
		btnCreateAccount.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				aController.gui_Control("createaccount");
			}
		});
		btnCreateAccount.setFont(new Font("敺株�迤暺��", Font.PLAIN, 18));
		btnCreateAccount.setBounds(32, 190, 249, 28);
		contentPane.add(btnCreateAccount);
		
		JButton btnIForgetMy = new JButton("I forget my password");
		btnIForgetMy.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				aController.gui_Control("forgetpass");
			}
		});
		btnIForgetMy.setFont(new Font("敺株�迤暺��", Font.PLAIN, 18));
		btnIForgetMy.setBounds(32, 224, 249, 28);
		contentPane.add(btnIForgetMy);
		
		aController.setLogin_gui(this);
		
	}
}
