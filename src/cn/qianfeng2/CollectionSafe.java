package cn.qianfeng2;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * 手写线程安全的集合
 * @author liuweijie
 *
 */
public class CollectionSafe {

	public static void main(String[] args) {
		List<String> list = new ArrayList<String>();
		List<String> safeList = Collections.synchronizedList(list);
		safeList.add("a");
	}
}

//class Collections {
//	
//	// 3、此时该list指向synchronizedList方法中传过来的list
//	private Collection list;
//	// 2、构造中将传过来的list赋值给自己的成员变量list
//	public Collections(Collection list) {this.list=list;}
//	
//	public static List synchronizedList(Collection list){
//		// 1、通过构造创建实例，传参集合
//		Collections c = new Collections(list);
//		return (List) list;
//	}
//	
//	// 4、不改变之前的集合，只是做了一层封装
//	public void add(Object obj) {
//		synchronized(this) {
//			// 这里的add方法设置同步，调用ArrayList的add
//			list.add(obj);
//		}
//	}
//}
