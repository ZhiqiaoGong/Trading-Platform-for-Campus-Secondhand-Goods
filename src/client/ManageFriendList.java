package client;

import javax.swing.*;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeCellRenderer;

import common.Message;
import common.MsgType;

import java.awt.*;

/** 
 * �Զ�����������,������ÿ���ڵ����óɲ�ͬ��ͼ��
 * ��Ҫ���ڶԺ����Ƿ����ߵ�������ʾ
 * 
 */  
public class ManageFriendList extends DefaultTreeCellRenderer {

    private static final long serialVersionUID = 1L;
    private Message msg;

    public ManageFriendList(Message msg){
        this.msg = msg;
    }

    /**
     * ��дgetTreeCellRendererComponent()
     */
    public Component getTreeCellRendererComponent(JTree tree, Object value,
            boolean sel, boolean expanded, boolean leaf, int row,
            boolean hasFocus) {

        //ִ�и���ԭ�Ͳ���
        super.getTreeCellRendererComponent(tree, value, sel, expanded, leaf,
                row, hasFocus);

        //�õ�ÿ���ڵ��TreeNode
        DefaultMutableTreeNode node = (DefaultMutableTreeNode) value;
        //�õ�ÿ���ڵ��text
        String str = node.toString();
        //��¼�ɹ�ʱ��ʼ���б�
		if(msg.getType() == MsgType.LOGIN_SUCCEED) {
            if (node.isLeaf()) {
                this.setIcon(new ImageIcon("src/chatimage/off.png"));
            }else
                this.setIcon(new ImageIcon("src/chatimage/friendlist/lie.png"));
        //�ѻ�ȡ�����ߺ����б�
        }else if(msg.getType() == MsgType.RET_ONLINE_FRIENDS) {
        	String [] onlineFriends = msg.getContent().split(" ");
        	if (node.isLeaf()) {
                //�õ����е�id����
                str = str.split("\\(")[1];
                str = str.substring(0,str.length()-1);
        		this.setIcon(new ImageIcon("src/chatimage/off.png"));
                for (String onlineFriend : onlineFriends) {//���и��²���
                    if (str.equals(onlineFriend))
                        this.setIcon(new ImageIcon("src/chatimage/on.png"));
                }

        	}else
        		this.setIcon(new ImageIcon("src/chatimage/friendlist/lie.png"));
        }
        return this;  
    }  
  
}  