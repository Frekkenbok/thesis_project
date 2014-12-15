/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package test;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

/**
 *
 * @author Евгения
 */
public class HashTest {
    public static void main(String args[]){  
    HashMap <String, Integer > tap = new HashMap <>();
  
    tap.put("lol", 1);
    tap.put("lol2", 2);
    tap.put("tomat",3);
        System.out.println(tap.keySet());
        //for (String str : tap.keySet() )
        System.out.println(tap.get("lol"));
        for (Map.Entry<String, Integer> entry : tap.entrySet())
        {
            System.out.println(entry.getKey()+""+entry.getValue());
        }
        
}
}