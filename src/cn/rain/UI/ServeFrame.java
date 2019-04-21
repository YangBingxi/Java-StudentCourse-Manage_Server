package cn.rain.UI;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

import cn.rain.utils.ConnectUtil;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * 服务器操作界面
 * 
 * @author SwYoung
 * @version V1.0
 * @since 2019-4-29
 */

public class ServeFrame extends JFrame implements ActionListener {

	private static final long serialVersionUID = 7370152456406055530L;

	private JPanel contentPane;

	private JButton startButton;

	private JButton stopButton;

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ServeFrame frame = new ServeFrame();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public ServeFrame() {

		try {
			UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");
		} catch (ClassNotFoundException e1) {
			e1.printStackTrace();
		} catch (InstantiationException e1) {
			e1.printStackTrace();
		} catch (IllegalAccessException e1) {
			e1.printStackTrace();
		} catch (UnsupportedLookAndFeelException e1) {
			e1.printStackTrace();
		}

		startButton = new JButton("启动");
		stopButton = new JButton("关闭");

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		setResizable(false);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		startButton.addActionListener(this);
		startButton.setBounds(99, 158, 72, 23);
		contentPane.add(startButton);

		JLabel label = new JLabel("学生成绩管理系统服务器");
		label.setFont(new Font("楷体", Font.PLAIN, 22));
		label.setHorizontalAlignment(SwingConstants.CENTER);
		label.setBounds(83, 24, 267, 47);
		contentPane.add(label);

		stopButton.addActionListener(this);
		stopButton.setBounds(262, 158, 72, 23);
		contentPane.add(stopButton);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		ConnectUtil server = new ConnectUtil();
		if (e.getSource() == startButton) {
			startButton.setEnabled(false);
			stopButton.setEnabled(true);
			server.start();
		} else {
			System.exit(0);
		}
	}
}
