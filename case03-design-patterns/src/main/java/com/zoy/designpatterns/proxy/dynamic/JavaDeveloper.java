package com.zoy.designpatterns.proxy.dynamic;

/**
 * @author : owen
 * @email zhaoouyang163@gmail.com
 * @date : 2019/5/14
 */
public class JavaDeveloper implements Developer{

    private String name;

    JavaDeveloper(String name){
        this.name = name;
    }

    @Override
    public void code() {
        System.out.println(this.name + "is coding java");
    }

    @Override
    public void debug() {
        System.out.println(this.name + "is debugging java");
    }

}
