package client;

import java.util.Hashtable;

import client.FriendPage;

/**
 * ���������û��������б����
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
