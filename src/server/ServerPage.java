
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
 * 服务器启动关闭界面
 */
public class ServerPage extends JFrame implements ActionListener ,MouseListener{

	private static final long serialVersionUID = 1L;

	JButton btn_start, btn_close;//功能按钮
	public static JTextArea textArea_log;//日志记录面板
	private JLabel label_log;//日志记录标签
    private static DateFormat df;//日期
    private Server s;

	public static void main(String[] args) {
		new ServerPage();
	}

	public ServerPage()
	{
        df = new SimpleDateFormat("yyyy-MM-dd a hh:mm:ss");
		//获取窗口容器
        Container c = this.getContentPane();
        //设置布局
        c.setLayout(null);
        //左上部分
        label_log = new JLabel();
        label_log.setText("服务器端");
        label_log.setBounds(155, 10, 100, 15);
        c.add(label_log);
        //日志记录面板
        textArea_log = new JTextArea();
        JScrollPane scrollPane_log = new JScrollPane(textArea_log);
        scrollPane_log.setBounds(10, 30, 380, 360);
        c.add(scrollPane_log);

        //服务器启动按钮
		btn_start = new JButton("启动");
		btn_start.setBounds(45, 420, 120, 24);
		btn_start.addActionListener(this);
		c.add(btn_start);
		//服务器关闭按钮
		btn_close = new JButton("关闭");
		btn_close.setBounds(230, 420, 120, 24);
		btn_close.addActionListener(this);
		c.add(btn_close);


		this.setResizable(false);//设置页面大小固定
		this.setSize(411, 530);//设置大小
		this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);//默认关闭方式
		this.setVisible(true);//页面可见
	}

	public void mouseClicked(MouseEvent e) {}
	public void mousePressed(MouseEvent e) {}
	public void mouseReleased(MouseEvent e) {}
	public void mouseEntered(MouseEvent e) {}
	public void mouseExited(MouseEvent e) {}

    // 按钮点击事件
	@Override
	public void actionPerformed(ActionEvent e) {

        if(e.getSource() == btn_start){//启动服务器
            s = new Server();
            showMsg("服务器已启动！欢迎！");
        }
        if(e.getSource() == btn_close){//关闭服务器
            beforeServerClose();
            showMsg("服务器已关闭！再见！");
        }
	}

	//在日志面板显示信息
    public static void showMsg(String s) {
        textArea_log.append(df.format(new Date())+": "+s+"\n"+"-".repeat(60)+"\n\n");
    }

    /**
     * 关闭服务器前，通知所有用户，并结束所有线程
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
            Thread.sleep(1000);//等待所有客户端下线
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        s.myStop();//服务器停止运行
    }

}
