package userMangementSystem;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class CreateAccount_gui extends JFrame {

	private JPanel contentPane;
	private JTextField textAccount;
	private JTextField textName;
	private JTextField textPassword;

	Controller aController =  Controller.getController();
	
	/**
	 * Launch the application.
	 */
	/*
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					CreateAccount_gui frame = new CreateAccount_gui();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	*/
	/**
	 * Create the frame.
	 */
	public CreateAccount_gui() {
		setTitle("Login System");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblAccount = new JLabel("Account:");
		lblAccount.setFont(new Font("微軟正黑體", Font.PLAIN, 20));
		lblAccount.setBounds(10, 30, 98, 28);
		contentPane.add(lblAccount);
		
		JLabel lblName = new JLabel("Name:");
		lblName.setFont(new Font("微軟正黑體", Font.PLAIN, 20));
		lblName.setBounds(10, 70, 98, 28);
		contentPane.add(lblName);
		
		JLabel lblPassword = new JLabel("Password:");
		lblPassword.setFont(new Font("微軟正黑體", Font.PLAIN, 20));
		lblPassword.setBounds(10, 110, 98, 28);
		contentPane.add(lblPassword);
		
		textAccount = new JTextField();
		textAccount.setText((String) null);
		textAccount.setColumns(10);
		textAccount.setBounds(110, 30, 120, 28);
		contentPane.add(textAccount);
		
		textName = new JTextField();
		textName.setText((String) null);
		textName.setColumns(10);
		textName.setBounds(110, 70, 120, 28);
		contentPane.add(textName);
		
		textPassword = new JTextField();
		textPassword.setText((String) null);
		textPassword.setColumns(10);
		textPassword.setBounds(110, 110, 120, 28);
		contentPane.add(textPassword);
		
		JButton btnCreate = new JButton("Create");
		btnCreate.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				aController.setUserAccount(textAccount.getText());
				aController.setUserName(textName.getText()); 
				aController.setUserPassword(textPassword.getText());
				
				String info = aController.createUserData();
				JOptionPane.showMessageDialog(null, info , "System Notice",JOptionPane.PLAIN_MESSAGE);
			}
		});
		btnCreate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			}
		});
		btnCreate.setFont(new Font("微軟正黑體", Font.PLAIN, 18));
		btnCreate.setBounds(324, 229, 100, 23);
		contentPane.add(btnCreate);
		
		JButton btnBack = new JButton("Back");
		btnBack.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				aController.gui_Control("login");
			}
		});
		btnBack.setFont(new Font("微軟正黑體", Font.PLAIN, 18));
		btnBack.setBounds(214, 229, 100, 23);
		contentPane.add(btnBack);
	}
}
