package field;

import org.apache.commons.lang.time.DateUtils;

import java.util.Calendar;
import java.util.Date;

public class TestTime {

    public  static  void  main(String[] args)
    {
        Date date1 = DateUtils.addDays(DateUtils.truncate(new Date(), Calendar.DATE),-7);
        Date date2 = DateUtils.addDays(new Date(),1);
        System.out.println(date1.getTime()/1000);
        System.out.println(date2.getTime()/1000);

        String str = "abc";
        String[] array = str.split(",");
        System.out.println(array[0]);
    }
}
