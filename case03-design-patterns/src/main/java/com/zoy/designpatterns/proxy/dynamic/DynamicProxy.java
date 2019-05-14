package com.zoy.designpatterns.proxy.dynamic;

import java.lang.reflect.Proxy;

/**
 * @author : owen
 * @email zhaoouyang163@gmail.com
 * @date : 2019/5/14
 */
public class DynamicProxy {

    public static void main(String args[]){

        JavaDeveloper zack=new JavaDeveloper("Zack");
        /**
         * 通过jdk动态代理，调用实现类Developer的方法
         * 原理：把接口复制出来，通过这些接口和类加载器，拿到代理类，通过反射复制代理类的构造函数，然后通过构造函数new一个对象，同时用InvocationHandler 绑定这个对象
         */
        Developer zackProxy= (Developer) Proxy.newProxyInstance(zack.getClass().getClassLoader(),zack.getClass().getInterfaces(),((proxy, method, args1) -> {
            if (method.getName().equals("code")){
                System.out.println("Zack is praying for the code!");
                return method.invoke(zack,args);
            }
            if(method.getName().equals("debug")){
                System.out.println("Zack's have no bug ! No need to debug!");
                return null;
            }
            return null;
        }));

        zackProxy.code();
        zackProxy.debug();
    }

}
