
package common;

/**
 * 用户信息类
 */
public class ChatUser implements java.io.Serializable {

    private String uid;
    private String pwd;

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public String getPwd() {
        return pwd;
    }

    public void setPwd(String pwd) {
        this.pwd = pwd;
    }

    public ChatUser(String uid, String pwd) {
        this.uid = uid;
        this.pwd = pwd;
    }

    @Override
    public String toString() {
        return "User{" +
                "uid='" + uid + '\'' +
                ", pwd='" + pwd + '\'' +
                '}';
    }
}
