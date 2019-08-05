package com.zoy.jvm.outmemory;

import java.util.ArrayList;
import java.util.List;

/**
 * @author : owen
 * @email zhaoouyang163@gmail.com
 * @date : 2019/5/12
 */
public class OutMemory {

    public static void main(String args[]){

        List<Demo> list=new ArrayList<Demo>();
        while(true){
            list.add(new Demo());
        }

    }

}
