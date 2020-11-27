package com.cgfy.user.base.util;

import com.cgfy.user.base.bean.select.Condition;

import java.util.List;

public class CommonUtil {
    public static  boolean isExists(List<Condition> condition, String prop){
        for (Condition con:condition) {
            if(con.getProperty().equals(prop))return true;

        }
        return false;
    }

    public static boolean isExistsClass(List<Condition> condition,String prop,Class classes){
        for (Condition con:condition) {
            if(con.getProperty().equals(prop)){
                if(con.getClass().equals(classes)){
                    return true;
                }
            }


        }
        return false;
    }
}
