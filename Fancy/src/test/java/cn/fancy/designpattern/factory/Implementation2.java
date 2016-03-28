package cn.fancy.designpattern.factory;

/**
 * @Title: Implementation2.java
 * @Package cn.test
 * @Description: (描述该文件做什么)
 * @author 操圣
 * @date 2015-11-4 下午7:18:13
 * @version V1.0
 */
class Implementation2 implements Service {
    private Implementation2() {}

    public void method1() {
        System.out.println("Implementation2 method1");
    }

    public void method2() {
        System.out.println("Implementation2 method2");
    }

    public static ServiceFactory factory = new ServiceFactory() {
        public Service getService() {
            return new Implementation2();
        }
    };
}
