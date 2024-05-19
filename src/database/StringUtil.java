//判断文本框是否为空

package database;

import javax.swing.ButtonGroup;

public class StringUtil {

	public static boolean isEmpty(String str){
		if("".equals(str)|| str == null){
			return true;
		}
		return false;
	}
}
