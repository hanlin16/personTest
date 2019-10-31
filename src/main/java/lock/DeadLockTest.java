package lock;

public class DeadLockTest {
    public static void main(String[] args) {
        Tools banShou = new Tools("扳手");
        Tools qianZi = new Tools("钳子");

        new Thread(() -> {
            synchronized (banShou) {
                System.out.println("线程_员工NO1,拿到扳手并锁定扳手");
                try {
                    Thread.sleep(1000);
                    banShou.execute("拧螺丝");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                synchronized (qianZi) {
                    qianZi.execute("剪断钢筋");
                    System.out.println("线程_员工NO1,拿到钳子并锁定钳子");
                }
                System.out.println("线程_员工NO1完成工作并结束");
            }
        }).start();

        new Thread(() -> {
            synchronized (qianZi) {
                System.out.println("线程_员工NO2,拿到钳子并锁定钳子");
                try {
                    Thread.sleep(1000);
                    qianZi.execute("剪断钢筋");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                synchronized (banShou) {
                    banShou.execute("拧螺丝");
                    System.out.println("线程_员工NO1,拿到扳手并锁定扳手");
                }
                System.out.println("线程_员工NO2完成工作并结束");
            }
        }).start();
    }

}

class Tools {
    private String name;//姓名

    public String execute(String operation) {
        System.out.println("拿" + name + "完成" + operation);
        return "success";
    }

    public Tools() {
    }

    public Tools(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


}


