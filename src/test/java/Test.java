import java.sql.Timestamp;
import java.text.DecimalFormat;
import java.util.Date;

/**
 * Created by Liuxd on 2018-06-21.
 */
public class Test {

    public static void main(String[] args) {
        Date date = new Date();

        System.out.println(date.getTime());

        Timestamp a = Timestamp.valueOf("2018-05-18 09:32:32");
        Timestamp b = Timestamp.valueOf("2018-05-11 09:32:32");
        if (b.before(a)) {
            System.out.println("b时间比a时间早");
        }
        //Date转换Timestamp
        Timestamp timestamp = new Timestamp((new Date()).getTime());
        //Timestamp转换Date
        Timestamp timestamp1 = new Timestamp(System.currentTimeMillis());
        Date date2 = new Date(timestamp1.getTime());
        System.out.println(a);
        System.out.println(b);
        System.out.println(date2);

        String st = null;
        String abc ="234";
        System.out.println(abc.equals(st));

        DecimalFormat df = new DecimalFormat("#.00");
        String totalPremium = df.format(0);
        System.out.println(totalPremium);

    }

}
