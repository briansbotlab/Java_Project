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
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.Color;

public class ForgetPass_gui extends JFrame {

	private JPanel contentPane;
	private JTextField textFieldACC;
	private JTextField textFieldNAME;
	private JTextField textFieldVC;
	
	private ValidCode vcode;
	
	Controller aController =  Controller.getController();
	/**
	 * Launch the application.
	 */
	/*
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ForgetPass_gui frame = new ForgetPass_gui();
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
	public ForgetPass_gui() {
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
		
		JLabel label_1 = new JLabel("Name:");
		label_1.setFont(new Font("微軟正黑體", Font.PLAIN, 20));
		label_1.setBounds(10, 50, 98, 28);
		contentPane.add(label_1);
		
		textFieldACC = new JTextField();
		textFieldACC.setText((String) null);
		textFieldACC.setColumns(10);
		textFieldACC.setBounds(120, 10, 120, 28);
		contentPane.add(textFieldACC);
		
		textFieldNAME = new JTextField();
		textFieldNAME.setText((String) null);
		textFieldNAME.setColumns(10);
		textFieldNAME.setBounds(120, 48, 120, 28);
		contentPane.add(textFieldNAME);
		
		JLabel lblValidcode = new JLabel("ValidCode:");
		lblValidcode.setFont(new Font("微軟正黑體", Font.PLAIN, 20));
		lblValidcode.setBounds(10, 90, 120, 28);
		contentPane.add(lblValidcode);
		
		textFieldVC = new JTextField();
		textFieldVC.setText((String) null);
		textFieldVC.setColumns(10);
		textFieldVC.setBounds(120, 88, 120, 28);
		contentPane.add(textFieldVC);
		
		JButton btnBack = new JButton("Back");
		btnBack.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				aController.gui_Control("login");
			}
		});
		btnBack.setFont(new Font("微軟正黑體", Font.PLAIN, 18));
		btnBack.setBounds(164, 229, 100, 23);
		contentPane.add(btnBack);
		
		JButton btnGet = new JButton("Get Password");
		btnGet.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				if (!isValidCodeRight()) {
					JOptionPane.showMessageDialog(null, "ValidCode is incorrect！╮(╯▽╰)╭", "System Notice",JOptionPane.PLAIN_MESSAGE);
					System.out.println("VC INCORRECT!");
					vcode.nextCode();
				}
				if (isValidCodeRight()) {
					System.out.println("VC CORRECT!");
					String userACC = textFieldACC.getText();
					String userName = textFieldNAME.getText();
					if(userACC.length()>4 && userACC.length()<10) {
						if(userName.length()>4 && userName.length()<10) {
							aController.setSelectedAcc(userACC);
							String userPassInfo = aController.FP_UserPassword();
							
								JOptionPane.showMessageDialog(null, userPassInfo , "System Notice",JOptionPane.PLAIN_MESSAGE);
							
						}else {
							JOptionPane.showMessageDialog(null, "Name format error!", "System Notice",JOptionPane.PLAIN_MESSAGE);
						}
						
					}else {
						JOptionPane.showMessageDialog(null, "Account format error!", "System Notice",JOptionPane.PLAIN_MESSAGE);
					}

				}
				
			}
		});
		btnGet.setFont(new Font("微軟正黑體", Font.PLAIN, 18));
		btnGet.setBounds(274, 229, 150, 23);
		contentPane.add(btnGet);
		
		vcode = new ValidCode();
		vcode.setBounds(265, 85, 80, 40);
		contentPane.add(vcode);
		
		JLabel lblNewLabel = new JLabel("Notice: The VaildCode is case sensitive.");
		lblNewLabel.setForeground(Color.RED);
		lblNewLabel.setFont(new Font("微軟正黑體", Font.PLAIN, 18));
		lblNewLabel.setBounds(10, 150, 400, 20);
		contentPane.add(lblNewLabel);
		
		
		aController.setForgetPass_gui(this);
	}
	
	public boolean isValidCodeRight() {
		 
		if (textFieldVC == null) {
			return false;
		}
		if (vcode == null) {
			return true;
		}
		if (vcode.getCode().equals(textFieldVC.getText())) {
			return true;
		}
		return false;
	}
}
