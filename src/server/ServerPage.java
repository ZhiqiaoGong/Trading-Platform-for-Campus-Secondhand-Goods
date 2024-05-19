
package server;

import common.Message;
import common.MsgType;

import javax.swing.*;

import java.awt.*;
import java.awt.event.*;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * �����������رս���
 */
public class ServerPage extends JFrame implements ActionListener ,MouseListener{

	private static final long serialVersionUID = 1L;

	JButton btn_start, btn_close;//���ܰ�ť
	public static JTextArea textArea_log;//��־��¼���
	private JLabel label_log;//��־��¼��ǩ
    private static DateFormat df;//����
    private Server s;

	public static void main(String[] args) {
		new ServerPage();
	}

	public ServerPage()
	{
        df = new SimpleDateFormat("yyyy-MM-dd a hh:mm:ss");
		//��ȡ��������
        Container c = this.getContentPane();
        //���ò���
        c.setLayout(null);
        //���ϲ���
        label_log = new JLabel();
        label_log.setText("��������");
        label_log.setBounds(155, 10, 100, 15);
        c.add(label_log);
        //��־��¼���
        textArea_log = new JTextArea();
        JScrollPane scrollPane_log = new JScrollPane(textArea_log);
        scrollPane_log.setBounds(10, 30, 380, 360);
        c.add(scrollPane_log);

        //������������ť
		btn_start = new JButton("����");
		btn_start.setBounds(45, 420, 120, 24);
		btn_start.addActionListener(this);
		c.add(btn_start);
		//�������رհ�ť
		btn_close = new JButton("�ر�");
		btn_close.setBounds(230, 420, 120, 24);
		btn_close.addActionListener(this);
		c.add(btn_close);


		this.setResizable(false);//����ҳ���С�̶�
		this.setSize(411, 530);//���ô�С
		this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);//Ĭ�Ϲرշ�ʽ
		this.setVisible(true);//ҳ��ɼ�
	}

	public void mouseClicked(MouseEvent e) {}
	public void mousePressed(MouseEvent e) {}
	public void mouseReleased(MouseEvent e) {}
	public void mouseEntered(MouseEvent e) {}
	public void mouseExited(MouseEvent e) {}

    // ��ť����¼�
	@Override
	public void actionPerformed(ActionEvent e) {

        if(e.getSource() == btn_start){//����������
            s = new Server();
            showMsg("����������������ӭ��");
        }
        if(e.getSource() == btn_close){//�رշ�����
            beforeServerClose();
            showMsg("�������ѹرգ��ټ���");
        }
	}

	//����־�����ʾ��Ϣ
    public static void showMsg(String s) {
        textArea_log.append(df.format(new Date())+": "+s+"\n"+"-".repeat(60)+"\n\n");
    }

    /**
     * �رշ�����ǰ��֪ͨ�����û��������������߳�
     */
    private void beforeServerClose(){
        Message msg = new Message();
        msg.setType(MsgType.SERVER_CLOSE);
        for(Object o: MultiToClientThread.getClientThreads().keySet()){
            String toId = o.toString();
            msg.setGetterId(toId);
            ServerMesgIdentifier th = MultiToClientThread.getClientThread(toId);
            try {
                ObjectOutputStream out = new ObjectOutputStream(th.getClient().getOutputStream());
                out.writeObject(msg);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        try {
            Thread.sleep(1000);//�ȴ����пͻ�������
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        s.myStop();//������ֹͣ����
    }

}
