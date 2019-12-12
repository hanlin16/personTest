import java.text.SimpleDateFormat;

public class DateTimeCheck {


    /**
     * 验证日期时间格式
     *
     * @param args
     */
    public static void main(String[] args) {
        String date1 = "2018-11-22";
        String date2 = "2018-11-22 12:00:00";

        boolean flag1 = validTime(date1);
        boolean flag2 = validTime(date2);

        System.out.println(flag1);
        System.out.println(flag2);

    }

    public static boolean validTime(String timeStr) {
        boolean flag1 = true;
        boolean flag2 = true;
        SimpleDateFormat df1 = new SimpleDateFormat("yyyy-MM-dd");
        SimpleDateFormat df2 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        df1.setLenient(false);
        df2.setLenient(false);
        try {
            df1.parse(timeStr);
        } catch (Exception e) {
            flag1 = false;
        }

        try {
            df2.parse(timeStr);
        } catch (Exception e) {
            flag2 = false;
        }

        return flag1 || flag2;

    }
}
