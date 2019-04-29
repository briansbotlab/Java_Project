package userMangementSystem;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.JButton;
import javax.swing.JTextField;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class Modifyinfo_gui extends JFrame {

	private JPanel contentPane;
	private JTextField textAccount;
	private JTextField textName;
	private JTextField textLevel;

	Controller aController =  Controller.getController();
	
	
	
	/**
	 * Launch the application.
	 */
	/*
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Modifyinfo_gui frame = new Modifyinfo_gui();
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
	public Modifyinfo_gui() {
		setResizable(false);
		setTitle("User Management System");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Account:");
		lblNewLabel.setFont(new Font("微軟正黑體", Font.PLAIN, 20));
		lblNewLabel.setBounds(10, 30, 98, 28);
		contentPane.add(lblNewLabel);
		
		JLabel lblName = new JLabel("Name:");
		lblName.setFont(new Font("微軟正黑體", Font.PLAIN, 20));
		lblName.setBounds(10, 70, 98, 28);
		contentPane.add(lblName);
		
		JLabel lblLevel = new JLabel("Level:");
		lblLevel.setFont(new Font("微軟正黑體", Font.PLAIN, 20));
		lblLevel.setBounds(10, 110, 98, 28);
		contentPane.add(lblLevel);
		
		JButton btnModify = new JButton("Modify");
		btnModify.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				aController.setUserName(textName.getText());
				
				String info = aController.updateUserData();
				JOptionPane.showMessageDialog(null, info , "System Notice",JOptionPane.PLAIN_MESSAGE);
			}
		});
		btnModify.setFont(new Font("微軟正黑體", Font.PLAIN, 18));
		btnModify.setBounds(324, 229, 100, 23);
		contentPane.add(btnModify);
		
		JButton btnCancel = new JButton("Back");
		btnCancel.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				aController.gui_Control("selfinfo");
			}
		});
		btnCancel.setFont(new Font("微軟正黑體", Font.PLAIN, 18));
		btnCancel.setBounds(195, 229, 100, 23);
		contentPane.add(btnCancel);
		
		textAccount = new JTextField();
		textAccount.setEditable(false);
		textAccount.setBounds(104, 30, 120, 28);
		contentPane.add(textAccount);
		textAccount.setColumns(10);
		textAccount.setText(aController.getUserAccount());
		
		textName = new JTextField();
		textName.setColumns(10);
		textName.setBounds(104, 68, 120, 28);
		contentPane.add(textName);
		textName.setText(aController.getUserName());
		
		textLevel = new JTextField();
		textLevel.setEditable(false);
		textLevel.setColumns(10);
		textLevel.setBounds(104, 110, 120, 28);
		contentPane.add(textLevel);
		textLevel.setText(Integer.toString(aController.getUserLevel()));
		
		aController.setModifyinfo_gui(this);
		
	}
}
