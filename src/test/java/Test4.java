/**
 * @author liuxd
 * @version 1.0
 * @date 2020-03-11 14:17
 */
public class Test4 {

    public static void main(String[] args) {
        String str ="abc dd";
        System.out.println(str.substring(0,str.indexOf(" ")));
        System.out.println(str.substring(0,str.indexOf(" ")).length());
    }
}
