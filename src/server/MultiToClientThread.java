package server;

import java.util.Hashtable;
import java.util.Iterator;

/**
 * ����������ͻ��˵��߳�
 */
public class MultiToClientThread {

    private static Hashtable<String,ServerMesgIdentifier> threads = new Hashtable<>();

    public static Hashtable<String, ServerMesgIdentifier> getClientThreads() {
        return threads;
    }

    public static void addClientThread(String uid, ServerMesgIdentifier thread){
        threads.put(uid,thread);
    }

    public static ServerMesgIdentifier getClientThread(String uid){
        return threads.get(uid);
    }

    public static void removeClientThread(String uid){
        threads.remove(uid);
    }

    /**
     * ���ص�ǰ����ȫ���û�
     * @return
     */
    public static String getOnLineList(){
        StringBuilder sb = new StringBuilder();
        Iterator it = threads.keySet().iterator();
        while(it.hasNext()){
            sb.append(it.next()+" ");
        }
        return sb.toString();
    }
}
