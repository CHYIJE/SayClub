package Client;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.SwingConstants;
import javax.swing.WindowConstants;
import javax.swing.border.EmptyBorder;

public class ClientFrame extends JFrame {
	private JTabbedPane tabPane;
	private JPanel mainPanel;

	private Client mContext;

	// 로그인 창
//	private IndexPanel indexPanel;

	// 대기실 창
//	private WaitingRoomPanel waitingRoomPanel;

	// 메세지 창
//	private MessagePanel messagePanel;

	// 기능 인터페이스
//	private CallBackClientService callBackService;

	public ClientFrame(Client mContext) {
		this.mContext = mContext;
		initData();
		setInitLayout();
	}

	private void initData() {
//		indexPanel = new IndexPanel(callBackService);
//		waitingRoomPanel = new WaitingRoomPanel(callBackService);
//		messagePanel = new MessagePanel(callBackService);
		tabPane = new JTabbedPane(SwingConstants.TOP);
		mainPanel = new JPanel();
	}

	private void setInitLayout() {
		setTitle("[ 세이클럽 클라이언트 ]");
		setSize(400, 750);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

		mainPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		mainPanel.setLayout(null);
		setContentPane(mainPanel);

		tabPane.setBounds(0, 0, getWidth(), getHeight());
		mainPanel.add(tabPane);

//		indexPanel.setLayout(null);
//		tabPane.addTab("로그인", null, indexPanel, null);

//		tabPane.addTab("대기실", null, waitingRoomPanel, null);

//		tabPane.addTab("채팅", null, messagePanel, null);

		setVisible(true);
	}
}

