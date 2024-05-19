package client;

import java.util.Hashtable;

/**
 * ʹ�ù�ϣ����������������ͨ�ŵ��߳�
 */
public class MultiToServerThread {

	//��uid��key��
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
