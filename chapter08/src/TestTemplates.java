public class TestTemplates {
    // main模板生成main类声明
    public static void main(String[] args) {
        // sout模板
        System.out.println("sout模板");
        // 自定义soutpt模板
        System.out.print("soutpt模板");

        // fori模板
        for (int i = 0; i < 10; i++) {
            i = 0;
        }
        // itar遍历数组
        for (int i = 0; i < args.length; i++) {
            String arg = args[i];
        }
    }
}
