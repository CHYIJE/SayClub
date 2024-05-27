package Server;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.WindowConstants;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;

public class ServerFrame extends JFrame {
	 private JTextField inputPort;
	 
	    public JButton startButton;
	    public JButton stopButton;
	    
	    private JLabel background;
	    private JTextArea mainBoard;
	    private Font f;

	    private Server server;

	    public ServerFrame(Server server) {
	        this.server = server;
	        initData();
	        setInitLayout();
	    }

	    private void initData() {
	        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
	        setSize(400, 775);
	        setTitle("Chat Server");
	        
	        background = new JLabel(new ImageIcon("img/say.png"));
			setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			setContentPane(background);
			setSize(400, 775);
	        
	        // 메인패널
	        mainBoard = new JTextArea();
	        mainBoard.setEditable(false);

	        // 포트입력
	        inputPort = new JTextField(10);
	        inputPort.setText("7000");
	        startButton = new JButton("Start");
	        stopButton = new JButton("Stop");
	        JLabel portLabel = new JLabel("Port: ");
	        f = new Font("Serif", Font.BOLD, 18);

	        // 레이아웃 설정
	        background.setBackground(getForeground());
	        
	        JPanel panel = new JPanel();
	        panel.setBorder(new TitledBorder(new LineBorder(Color.GREEN, 5), "Server"));
	        panel.setBounds(40, 250, 305, 300);
	        panel.setBackground(Color.WHITE);

	        JScrollPane scrollPane = new JScrollPane(mainBoard);
	        scrollPane.setBounds(10, 20, 280, 260);
	        panel.setLayout(null);
	        panel.add(scrollPane);

	        // 서버 시작 버튼에 액션 리스너 추가
	        startButton.addActionListener(new ActionListener() {
	            @Override
	            public void actionPerformed(ActionEvent e) {
	                startServer(); // 서버 시작 메서드 호출
	            }
	        });

	        // 배치
	        setLayout(null);
	        add(portLabel);
	        portLabel.setBounds(60, 580, 120, 30);
	        portLabel.setFont(f);
	        add(inputPort);
	        inputPort.setBounds(140, 580, 190, 30);
	        add(startButton);
	        startButton.setBounds(60, 640, 100, 50);
	        add(stopButton);
	        stopButton.setBounds(230, 640, 100, 50);
	        add(panel);
	    }

	    private void startServer() {
	        server.startServer(); // 서버 시작 메서드 호출
	    }

	    public String getInputPort() {
	        return inputPort.getText();
	    }

	    public void appendLog(String message) {
	        mainBoard.append(message + "\n");
	    }

	    private void setInitLayout() {
	        setResizable(false);
	        setLocationRelativeTo(null);
	    }
	}


