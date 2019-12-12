import java.util.ArrayList;
import java.util.List;

/**
 * @author liuxd
 * @version 1.0
 * @date 2019-11-15 14:54
 */
public class Test2 {
    /**
     * @Description: 取交集
     */
    public static void handelList(){
        List<String> list1 = new ArrayList<String>();
        list1.add("1380");
        list1.add("1381");
        list1.add("1382");
        list1.add("1383");

        List<String> list2 = new ArrayList<String>();

        list2.add("1380");
        list2.add("1384");
        list2.add("1385");
        list2.add("1386");

        System.out.println("list1: "+String.join(",", list1));
        System.out.println("list2: "+String.join(",", list2));

        // retainAll 表示取集合中有相同的值
        boolean contain = list2.removeAll(list1);
        System.out.println("差集："+String.join(",", list2));
    }

    public static void main(String[] args) {
        handelList();
    }

}
