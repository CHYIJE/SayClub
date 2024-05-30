package Client;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Vector;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;

import lombok.Data;

@Data
public class WaitingRoomPanel extends JPanel implements ActionListener {

	private Image backgroundImage;
	private JPanel backgroundPanel;

	private JPanel userListPanel;
	private JPanel roomListPanel;
	private JPanel roomBtnPanel;
	private JPanel sendMessagePanel;

	private JList<String> userList;
	private JList<String> roomList;

	private JTextField inputSecretMsg;
	private JButton secretMsgBtn;

	private JButton makeRoomBtn;
	private JButton outRoomBtn;
	private JButton enterRoomBtn;

	private Vector<String> userIdVector = new Vector<>();
	private Vector<String> roomNameVector = new Vector<>();

	// 서버 알림
	private JPanel adminPanel;
	private JTextField adminMsg;
	private JButton adminBt;

	private CallBackClientService callBackService;

	public WaitingRoomPanel(CallBackClientService callBackService) {
		this.callBackService = callBackService;
		initObject();
		initSetting();
		initListener();
	}

	private void initObject() {
		backgroundImage = new ImageIcon("img/say.png").getImage();
		backgroundPanel = new JPanel();

		userListPanel = new JPanel();
		roomListPanel = new JPanel();
		roomBtnPanel = new JPanel();
		sendMessagePanel = new JPanel();

		userList = new JList<>();
		roomList = new JList<>();

		inputSecretMsg = new JTextField();
		secretMsgBtn = new JButton("귓속말 보내기");
		makeRoomBtn = new JButton("MakeRoom");
		outRoomBtn = new JButton("방 나가기");
		enterRoomBtn = new JButton("방 들어가기");

		// 어드민 공지 메시지
		adminPanel = new JPanel();
		adminMsg = new JTextField(20);
	}

	private void initSetting() {
		setSize(getWidth(), getHeight());
		setLayout(null);

		userListPanel.setBounds(50, 300, 120, 260);
		userListPanel.setBackground(Color.WHITE);
		userListPanel.setBorder(new TitledBorder(new LineBorder(new Color(174, 193, 132), 3), "유저 목록"));

		userListPanel.add(userList);
		add(userListPanel);

		roomListPanel.setBounds(230, 300, 120, 260);
		roomListPanel.setBackground(Color.WHITE);
		roomListPanel.setBorder(new TitledBorder(new LineBorder(new Color(174, 193, 132), 3), "방 목록"));
		roomListPanel.add(roomList);
		add(roomListPanel);

		roomBtnPanel.setBounds(50, 580, 300, 30);
		roomBtnPanel.setBackground(new Color(249, 248, 240));
		roomBtnPanel.setLayout(null);

		makeRoomBtn.setBackground(Color.WHITE);
		makeRoomBtn.setBounds(0, 5, 100, 25);
		makeRoomBtn.setEnabled(false);

		outRoomBtn.setBackground(Color.WHITE);
		outRoomBtn.setBounds(105, 5, 90, 25);
		outRoomBtn.setEnabled(false);

		enterRoomBtn.setBackground(Color.WHITE);
		enterRoomBtn.setBounds(200, 5, 100, 25);
		enterRoomBtn.setEnabled(false);

		roomBtnPanel.add(makeRoomBtn);
		roomBtnPanel.add(outRoomBtn);
		roomBtnPanel.add(enterRoomBtn);
		add(roomBtnPanel);

		inputSecretMsg.setBounds(30, 5, 240, 23);
		secretMsgBtn.setBounds(30, 35, 240, 20);
		secretMsgBtn.setBackground(Color.WHITE);
		secretMsgBtn.setEnabled(false);

		sendMessagePanel.setBounds(50, 620, 300, 60);
		sendMessagePanel.setBackground(new Color(249, 248, 240));
//		sendMessagePanel.setBorder(new TitledBorder(new LineBorder(Color.BLACK, 2), "secret Message"));
		sendMessagePanel.setLayout(null);
		sendMessagePanel.add(inputSecretMsg);
		sendMessagePanel.add(secretMsgBtn);
		add(sendMessagePanel);

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
		makeRoomBtn.addActionListener(this);
		outRoomBtn.addActionListener(this);
		secretMsgBtn.addActionListener(this);
		enterRoomBtn.addActionListener(this);
	}

	@Override
	public void actionPerformed(ActionEvent e) {

		if (e.getSource() == secretMsgBtn) {

			String msg = inputSecretMsg.getText();
			if (!msg.equals(null)) {
				callBackService.clickSendSecretMessageBtn(msg);
				inputSecretMsg.setText("");
				userList.setSelectedValue(null, false);
			}

		} else if (e.getSource() == makeRoomBtn) {
			System.out.println("드루와");
			String roomName = JOptionPane.showInputDialog("[ 방 이름 설정 ]");

			if (!roomName.equals(null)) {
				callBackService.clickMakeRoomBtn(roomName);
			}

		} else if (e.getSource() == outRoomBtn) {

			String roomName = roomList.getSelectedValue();
			callBackService.clickOutRoomBtn(roomName);
			roomList.setSelectedValue(null, false);

		} else if (e.getSource() == enterRoomBtn) {

			String roomName = roomList.getSelectedValue();
			callBackService.clickEnterRoomBtn(roomName);
			roomList.setSelectedValue(null, false);
		}
	}

	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		g.drawImage(backgroundImage, 0, 0, getWidth(), getHeight(), null);
	}
}
