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
import javax.swing.JPasswordField;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class ChangePass_gui extends JFrame {

	private JPanel contentPane;
	private JTextField textFieldACC;
	private JPasswordField passwordField;
	private JPasswordField passwordField_1;
	
	Controller aController =  Controller.getController();
	
	/**
	 * Launch the application.
	 */
	/*
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ChangePass_gui frame = new ChangePass_gui();
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
	public ChangePass_gui() {
		setResizable(false);
		setTitle("User Management System");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel label = new JLabel("Account:");
		label.setFont(new Font("微軟正黑體", Font.PLAIN, 20));
		label.setBounds(10, 10, 98, 28);
		contentPane.add(label);
		
		JLabel lblPassword = new JLabel("New Password:");
		lblPassword.setFont(new Font("微軟正黑體", Font.PLAIN, 20));
		lblPassword.setBounds(10, 50, 158, 28);
		contentPane.add(lblPassword);
		
		JLabel lblPassword_1 = new JLabel("Enter New Password Again:");
		lblPassword_1.setFont(new Font("微軟正黑體", Font.PLAIN, 20));
		lblPassword_1.setBounds(10, 130, 258, 28);
		contentPane.add(lblPassword_1);
		
		textFieldACC = new JTextField();
		textFieldACC.setText((String) null);
		textFieldACC.setEditable(false);
		textFieldACC.setColumns(10);
		textFieldACC.setBounds(94, 10, 120, 28);
		contentPane.add(textFieldACC);
		textFieldACC.setText(aController.getUserAccount());
		
		JButton button = new JButton("Back");
		button.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				aController.gui_Control("selfinfo");
			}
			
		});
		button.setFont(new Font("微軟正黑體", Font.PLAIN, 18));
		button.setBounds(195, 229, 100, 23);
		contentPane.add(button);
		
		JButton button_1 = new JButton("Modify");
		button_1.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				//New Pass
				char[] pass = passwordField.getPassword();
				String passString = new String(pass);
				//New Pass Again
				char[] pass_1 = passwordField_1.getPassword();
				String passString_1 = new String(pass_1);
				
				if(verifyPwd(passString,passString_1)) {
					aController.setUserPassword(passString);
					aController.updateUserData();
					
					String info = aController.updateUserData();
					JOptionPane.showMessageDialog(null, info , "System Notice",JOptionPane.PLAIN_MESSAGE);
					
					
				}else {
					JOptionPane.showMessageDialog(null, "Please check your input！╮(╯▽╰)╭", "System Notice",JOptionPane.PLAIN_MESSAGE);
				}
				
			}
		});
		button_1.setFont(new Font("微軟正黑體", Font.PLAIN, 18));
		button_1.setBounds(324, 229, 100, 23);
		contentPane.add(button_1);
		
		passwordField = new JPasswordField();
		passwordField.setBounds(10, 90, 120, 28);
		contentPane.add(passwordField);
		
		passwordField_1 = new JPasswordField();
		passwordField_1.setBounds(10, 170, 120, 28);
		contentPane.add(passwordField_1);
		
		
		aController.setChangePass_gui(this);
	}
	
	
	private boolean verifyPwd(String a,String b) {
		if(a.length() > 4 && a.length() <10) {
			if(a.equals(b)) {
				return true;
			}else {
				return false;
			}
		}else {
			return false;
		}
		
	}
}
