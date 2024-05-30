package Server;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.ScrollPane;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;

import lombok.Data;

@Data
public class ServerFrame extends JFrame {
	private Server mContext;

	private ScrollPane scrollPane;

	// 백그라운드 패널
	private BackgroundPanel backgroundPanel;

	// 메인보드
	private JPanel mainPanel;
	private JTextArea mainBoard;

	// 포트패널
	private JPanel portPanel;
	private JLabel portLabel;
	private JTextField inputPort;
	private JButton connectBtn;
	private JButton dismissBtn;
	//어드민
	private JPanel adminPanel;
	private JTextField adminMsg;
	private JButton adminBt;
	
	public ServerFrame(Server mContext) {
		this.mContext = mContext;
		initObject();
		initSetting();
		initListener();
	}

	private void initObject() {
		// 백그라운드 패널
		backgroundPanel = new BackgroundPanel();

		// 메인 패널
		mainPanel = new JPanel();
		mainBoard = new JTextArea();

		scrollPane = new ScrollPane();

		// 포트패널
		portPanel = new JPanel();
		portLabel = new JLabel("PORT NUMBER");
		inputPort = new JTextField(10);
		connectBtn = new JButton("연결");
		dismissBtn = new JButton("해제");
		
		//어드민 공지 메시지
		adminPanel = new JPanel();
		adminMsg = new JTextField(20);
		adminBt = new JButton("전체 보내기");
		// 테스트 코드
//		inputPort.setText("10000");
	}

	private void initSetting() {
		setTitle("[ 세이클럽 ] - 서버관리자");
		setSize(400, 775);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLayout(null);

		// 백그라운드 패널
		backgroundPanel.setSize(getWidth(), getHeight());
		backgroundPanel.setLayout(null);
		add(backgroundPanel);

		// 포트패널 컴포넌트
		portPanel.setBounds(60, 560, 250, 70);
		portPanel.setBackground(new Color(249, 248, 240));
		portPanel.add(portLabel);
		portPanel.add(inputPort);
		portPanel.add(connectBtn);
		portPanel.add(dismissBtn);
		backgroundPanel.add(portPanel);

		// 메인패널 컴포넌트
		mainPanel.setBorder(new TitledBorder(new LineBorder(new Color(174, 193, 132), 5), "Server"));
		mainPanel.setBounds(40, 250, 305, 300);
		mainPanel.setBackground(new Color(249, 248, 240));
		
		mainBoard.setEditable(false);
		mainBoard.setDisabledTextColor(Color.BLUE);
		
		mainPanel.add(scrollPane);
		scrollPane.setBounds(10, 20, 280, 260);
		scrollPane.add(mainBoard);
		backgroundPanel.add(mainPanel);
		
		
		adminPanel.setBorder(new TitledBorder(new LineBorder(new Color(174, 193, 132), 5), "전체공지"));
		adminPanel.setBounds(40, 632, 305, 90);
		adminPanel.setBackground(new Color(249,248,240));
		adminPanel.add(adminMsg);
		adminPanel.add(adminBt);
		backgroundPanel.add(adminPanel);
		

		setVisible(true);
	}

	private void initListener() {
		connectBtn.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				mContext.startServer();
			}
		});
		dismissBtn.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if (dismissBtn.isEnabled() == true) {
					mContext.stopServer();
				}
			}
		});
		
		adminBt.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				mContext.allMessage(adminMsg.getText());
			}
		});
	}

	private class BackgroundPanel extends JPanel {
		private JPanel backgroundPanel;
		private Image backgroundImage;

		public BackgroundPanel() {
			backgroundImage = new ImageIcon("img/say.png").getImage();
			backgroundPanel = new JPanel();
			add(backgroundPanel);
		}

		@Override
		protected void paintComponent(Graphics g) {
			super.paintComponent(g);
			g.drawImage(backgroundImage, 0, 0, getWidth(), getHeight(), null);
		}
	}

}
