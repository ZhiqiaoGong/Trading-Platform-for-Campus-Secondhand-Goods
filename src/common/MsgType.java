package common;

/**
 * ����ͨ���漰����ȫ����Ϣ����
 */
public enum MsgType {
    LOGIN_SUCCEED,//��¼�ɹ�
    LOGIN_FAILED,//��¼ʧ��
    ALREADY_LOGIN,//�ѵ�¼
    UNLOAD_LOGIN,//�˳���¼
    GET_ONLINE_FRIENDS,//��ȡ���ߺ����б�
    REPRESH_FRIENDS_LIST,//ˢ�º����б�
    GET_OFFLINE_MEAG,//��ȡ������Ϣ
    OFFLINE_MESG,//������Ϣ
    SEND_OFFLINE_MESG,//����������Ϣ
    RET_ONLINE_FRIENDS,//�������ߺ���
    ADD_FRIEND,//��Ӻ���
    NOT_ONLINE,//������
    IS_ONLINE,//����
    JUDGE_STATE,//�ж�����״̬
    SERVER_CLOSE,//�������ر�
    COMMON_MESSAGE//��ͨ��Ϣ
}
