package userMangementSystem;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class Selfinfo_gui extends JFrame {

	private JPanel contentPane;
	
	Controller aController =  Controller.getController();
	
	

	/**
	 * Launch the application.
	 */
	/*
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Selfinfo_gui frame = new Selfinfo_gui();
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
	public Selfinfo_gui() {
		setResizable(false);
		setTitle("User Management System");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JButton btnModifyInfo = new JButton("Modify Info");
		btnModifyInfo.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				aController.gui_Control("modifyinfo");
			}
		});

		btnModifyInfo.setFont(new Font("微軟正黑體", Font.PLAIN, 24));
		btnModifyInfo.setBounds(10, 165, 190, 40);
		contentPane.add(btnModifyInfo);
		
		JButton btnMemberInfo = new JButton("Member Info");
		btnMemberInfo.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				aController.gui_Control("userlist");
			}
		});
		btnMemberInfo.setFont(new Font("微軟正黑體", Font.PLAIN, 24));
		btnMemberInfo.setBounds(10, 212, 190, 40);
		contentPane.add(btnMemberInfo);
		
		JButton btnLogout = new JButton("Logout");
		btnLogout.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				aController.gui_Control("login");
			}
		});
		btnLogout.setFont(new Font("微軟正黑體", Font.PLAIN, 24));
		btnLogout.setBounds(237, 212, 190, 40);
		contentPane.add(btnLogout);
		
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
		
		JLabel lblAccountText = new JLabel("Account Text");
		lblAccountText.setFont(new Font("微軟正黑體", Font.PLAIN, 20));
		lblAccountText.setBounds(118, 30, 200, 28);
		contentPane.add(lblAccountText);
		lblAccountText.setText(aController.getUserAccount());
		
		JLabel lblNameText = new JLabel("Name Text");
		lblNameText.setFont(new Font("微軟正黑體", Font.PLAIN, 20));
		lblNameText.setBounds(118, 70, 200, 28);
		contentPane.add(lblNameText);
		lblNameText.setText(aController.getUserName());
		
		JLabel lblLevelText = new JLabel("Level Text");
		lblLevelText.setFont(new Font("微軟正黑體", Font.PLAIN, 20));
		lblLevelText.setBounds(118, 108, 200, 28);
		contentPane.add(lblLevelText);
		lblLevelText.setText(Integer.toString(aController.getUserLevel()));
		
		JButton btnCP = new JButton("Change Pwd");
		btnCP.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				aController.gui_Control("changepass");
			}
		});
		btnCP.setFont(new Font("微軟正黑體", Font.PLAIN, 24));
		btnCP.setBounds(237, 165, 190, 40);
		contentPane.add(btnCP);
		
		aController.setSelfinfo_gui(this);
		
	}
}
