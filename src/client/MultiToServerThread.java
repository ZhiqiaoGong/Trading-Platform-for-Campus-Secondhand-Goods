package client;

import java.util.Hashtable;

/**
 * 使用哈希表管理所有与服务器通信的线程
 */
public class MultiToServerThread {

	//用uid做key，
    private static Hashtable<String,ClientMesgIdentifier> threads = new Hashtable<>();

    public static void addThread(String uid,ClientMesgIdentifier thread){
        threads.put(uid,thread);
    }

    public static ClientMesgIdentifier getThread(String uid){
        return threads.get(uid);
    }

    public static void removeThread(String uid){
        threads.remove(uid);
    }
}
