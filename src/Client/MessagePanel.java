package Client;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.ScrollPane;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;

import lombok.Data;

@Data
public class MessagePanel extends JPanel {

	// 백그라운드 이미지 컴포넌트
	private Image backgroundImage;
	private JPanel backgroundPanel;

	// 패널
	private JPanel mainPanel;
	private JPanel bottomPanel;

	// 스크롤
	private ScrollPane scrollPane;

	// 텍스트 컴포넌트
	private JTextArea mainMessageBox;
	private JTextField writeMessageBox;

	// 메세지 보내기 버튼
	private JButton sendMessageBtn;

	// 서버 알림
	private JPanel adminPanel;
	private JTextField adminMsg;
	private JButton adminBt;

	private CallBackClientService callBackService;

	public MessagePanel(CallBackClientService callBackService) {
		this.callBackService = callBackService;
		initObject();
		initSetting();
		initListener();
	}

	private void initObject() {
		backgroundImage = new ImageIcon("img/say.png").getImage();
		backgroundPanel = new JPanel();

		mainPanel = new JPanel();
		bottomPanel = new JPanel();

		scrollPane = new ScrollPane();

		mainMessageBox = new JTextArea();
		writeMessageBox = new JTextField(17);
		sendMessageBtn = new JButton("전송");

		// 어드민 공지 메시지
		adminPanel = new JPanel();
		adminMsg = new JTextField(20);
	}

	private void initSetting() {
		setSize(getWidth(), getHeight());
		setLayout(null);

		backgroundPanel.setSize(getWidth(), getHeight());
		backgroundPanel.setLayout(null);
//		add(backgroundPanel); // bottomPanel 대신 backgroundPanel을 추가

//		add(bottomPanel);

		mainMessageBox.setEnabled(false);
		mainMessageBox.setDisabledTextColor(Color.BLACK); // 비활성화 상태에서 텍스트 색상 설정
		mainPanel.setBounds(40, 300, 300, 350);
		mainPanel.setBorder(new TitledBorder(new LineBorder(new Color(174, 193, 132), 5), "Message"));
		mainPanel.setBackground(new Color(249, 248, 240));
		scrollPane.setBounds(45, 15, 280, 310);
		scrollPane.add(mainMessageBox);
		mainPanel.add(scrollPane);
		add(mainPanel);

		sendMessageBtn.setBackground(Color.WHITE);
		sendMessageBtn.setPreferredSize(new Dimension(60, 20));
		sendMessageBtn.setEnabled(false);
		bottomPanel.setBounds(43, 655, 294, 35);
		bottomPanel.setBackground(new Color(249, 248, 240));
		bottomPanel.setBorder(new LineBorder(new Color(174, 193, 132), 2));
		bottomPanel.add(writeMessageBox);
		bottomPanel.add(sendMessageBtn);
		add(bottomPanel);

		// 서버 메세지 컴포넌트
		adminPanel.add(adminMsg);
		adminPanel.setBorder(new TitledBorder(new LineBorder(new Color(174, 193, 132), 5), "전체공지"));
		adminPanel.setBounds(40, 225, 305, 63);
		adminPanel.setBackground(new Color(249, 248, 240));
		adminMsg.setEnabled(false);
		adminMsg.setDisabledTextColor(Color.BLUE);
		add(adminPanel);
	}

	private void initListener() {
		sendMessageBtn.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				sendMessage();
			}
		});

		writeMessageBox.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if (e.getKeyCode() == KeyEvent.VK_ENTER) {
					sendMessage();
				}
			}
		});
	}

	private void sendMessage() {
		if (!writeMessageBox.getText().equals(null)) {
			String msg = writeMessageBox.getText();
			callBackService.clickSendMessageBtn(msg);
			writeMessageBox.setText("");
			writeMessageBox.requestFocus();
		}
	}

	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		g.drawImage(backgroundImage, 0, 0, getWidth(), getHeight(), null);
	}
}
