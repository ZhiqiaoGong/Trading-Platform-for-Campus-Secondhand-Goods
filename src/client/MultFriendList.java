package client;

import java.util.Hashtable;

import client.FriendPage;

/**
 * 管理所有用户的聊天列表界面
 */
public class MultFriendList {

    private static Hashtable<String, FriendPage> friendListFrames = new Hashtable<>();

    public static void addFriendListFrame(String frameName,FriendPage fl){
        friendListFrames.put(frameName,fl);
    }

    public static FriendPage getFriendListFrame(String frameName){
        return friendListFrames.get(frameName);
    }

    public static FriendPage removeFriendListFrame(String frameName){
        return friendListFrames.remove(frameName);
    }
}
