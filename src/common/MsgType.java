package common;

/**
 * 定义通信涉及到的全部消息类型
 */
public enum MsgType {
    LOGIN_SUCCEED,//登录成功
    LOGIN_FAILED,//登录失败
    ALREADY_LOGIN,//已登录
    UNLOAD_LOGIN,//退出登录
    GET_ONLINE_FRIENDS,//获取在线好友列表
    REPRESH_FRIENDS_LIST,//刷新好友列表
    GET_OFFLINE_MEAG,//获取离线信息
    OFFLINE_MESG,//离线信息
    SEND_OFFLINE_MESG,//发送离线信息
    RET_ONLINE_FRIENDS,//返回在线好友
    ADD_FRIEND,//添加好友
    NOT_ONLINE,//不在线
    IS_ONLINE,//在线
    JUDGE_STATE,//判断在线状态
    SERVER_CLOSE,//服务器关闭
    COMMON_MESSAGE//普通信息
}
