package userMangementSystem;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.JTable;
import javax.swing.JScrollPane;
import javax.swing.JButton;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

public class Userlist_gui extends JFrame {

	private JPanel contentPane;
	private JTable table;
	
	Controller aController =  Controller.getController();
	
	

	/**
	 * Launch the application.
	 */
	/*
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Userlist_gui frame = new Userlist_gui();
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
	public Userlist_gui() {
		setTitle("User Management System");
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 600, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(18, 70, 550, 150);
		contentPane.add(scrollPane_1);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane_1.setViewportView(scrollPane);
		
		table = new JTable();
		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				System.out.println("Table clicked");
				String sAcc = (String) table.getModel().getValueAt(table.getSelectedRow(), 2);
				System.out.println("Table data ACC:"+ sAcc);
				aController.setSelectedAcc(sAcc);
			}
		});
		scrollPane.setViewportView(table);
		
		JButton btnNewButton = new JButton("Back");
		btnNewButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				aController.gui_Control("selfinfo");
			}
		});
		btnNewButton.setFont(new Font("微軟正黑體", Font.PLAIN, 18));
		btnNewButton.setBounds(468, 227, 100, 25);
		contentPane.add(btnNewButton);
		
		JButton btnReflash = new JButton("Refresh");
		btnReflash.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.out.println("Table actionPerformed");
			}
		});
		btnReflash.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				
				DefaultTableModel model = aController.getDBUserList();
				
				table.setModel(model);
				
			}
		});
		btnReflash.setFont(new Font("微軟正黑體", Font.PLAIN, 18));
		btnReflash.setBounds(358, 227, 100, 25);
		contentPane.add(btnReflash);
		
		JLabel lblNewLabel = new JLabel("Member Information");
		lblNewLabel.setFont(new Font("微軟正黑體", Font.BOLD, 24));
		lblNewLabel.setBounds(172, 10, 243, 50);
		contentPane.add(lblNewLabel);
		
		JButton btnLvUp = new JButton("Lv \u2191");
		btnLvUp.setVisible(aController.get_UserChain_Uplevelenable());
		btnLvUp.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				String info = aController.upUserLevel();
				JOptionPane.showMessageDialog(null, info , "System Notice",JOptionPane.PLAIN_MESSAGE);
			}
		});
		btnLvUp.setFont(new Font("微軟正黑體", Font.PLAIN, 18));
		btnLvUp.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			}
		});
		btnLvUp.setBounds(248, 227, 100, 25);
		contentPane.add(btnLvUp);
		
		JButton btnDelete = new JButton("Delete");
		btnDelete.setVisible(aController.get_UserChain_Deleteenable());
		btnDelete.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				String info = aController.deleteUserData();
				JOptionPane.showMessageDialog(null, info , "System Notice",JOptionPane.PLAIN_MESSAGE);
			}
		});
		btnDelete.setFont(new Font("微軟正黑體", Font.PLAIN, 18));
		btnDelete.setBounds(28, 227, 100, 25);
		contentPane.add(btnDelete);
		
		JButton btnLvDown = new JButton("Lv \u2193");
		btnLvDown.setVisible(aController.get_UserChain_Downlevelenable());
		btnLvDown.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				String info = aController.downUserLevel();
				JOptionPane.showMessageDialog(null, info , "System Notice",JOptionPane.PLAIN_MESSAGE);
			}
		});
		btnLvDown.setFont(new Font("微軟正黑體", Font.PLAIN, 18));
		btnLvDown.setBounds(138, 227, 100, 23);
		contentPane.add(btnLvDown);
		
		aController.setUserlist_gui(this);
		
	}
}
