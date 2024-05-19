package client;

import java.util.Hashtable;

import client.ChatPage;

/**
 * 管理全部客户端打开的的聊天界面
 */
public class MultiChatFrame {
    private static Hashtable<String,ChatPage> chatFrames = new Hashtable<>();

    public static void addChatFrame(String frameName,ChatPage chat){
    	chatFrames.put(frameName,chat);
    }

    public static ChatPage getChatFrame(String frameName){
        return chatFrames.get(frameName);
    }
    
    public static ChatPage removeChatFrame(String frameName){
        return chatFrames.remove(frameName);
    }
}
