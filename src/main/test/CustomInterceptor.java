package main.test;

import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;


public class CustomInterceptor implements MethodInterceptor {
    @Override
    public Object intercept(Object o, Method method, Object[] objects, MethodProxy methodProxy) throws Throwable {
        System.out.println("custom doing sth");
        methodProxy.invokeSuper(method, objects);
        System.out.println("custom doing sth end.");
        return null;
    }

    public static void main(String[] args) {
        // 创建Enhancer对象
        Enhancer enhancer = new Enhancer();

        // 设置目标类（被代理类）
        enhancer.setSuperclass(GenericExample.class);

        // 设置方法拦截器
        enhancer.setCallback(new CustomInterceptor());

        // 创建代理对象
        GenericExample proxy = (GenericExample) enhancer.create();

        // 调用代理对象的方法
        proxy.foo(null);

    }
}
