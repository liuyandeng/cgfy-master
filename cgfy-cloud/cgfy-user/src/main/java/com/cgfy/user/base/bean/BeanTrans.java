package com.cgfy.user.base.bean;

import com.cgfy.user.base.bean.select.Direction;
import com.cgfy.user.base.bean.select.Order;
import com.cgfy.user.base.bean.select.condition.*;
import org.apache.ibatis.session.RowBounds;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class BeanTrans {
    public static SelectInputBean zgcfzBeanToNormal(CgfySelectInputBean input){

        SelectInputBean sib = new SelectInputBean();
        if(input!=null){
            if(input.getOrderBy()!=null&&input.getOrderBy().length()>0) {
                List<Order> orderList = new ArrayList<Order>();
                Order order = new Order();
                order.setProperty(input.getOrderBy());
                order.setDirection(input.isDesc() ? Direction.DESC : Direction.ASC);


                orderList.add(order);
                sib.setSort(orderList);
            }
            if(input.getPageSize()<Integer.MAX_VALUE){
                RowBounds rb=new RowBounds((input.getPage()-1)*input.getPageSize(),input.getPageSize());
                sib.setRowBounds(rb);
            }


            List<Map<String,Object>> conditionList = input.getOptions();
            for (Map<String,Object> map:
                    conditionList) {

                if(map.get("filter").toString().equals("EQUAL")){
                    Equal eq = new Equal();
                    eq.setProperty(map.get("field").toString());
                    eq.setValue(map.get("value").toString());
                    sib.getCondition().add(eq);
                }
                else if(map.get("filter").toString().equals("LIKE")){
                    Like like = new Like();
                    like.setProperty(map.get("field").toString());
                    like.setValue(map.get("value").toString());
                    sib.getCondition().add(like);
                }
                else if(map.get("filter").toString().equals("BETWEEN")){

                    if(map.get("value")!=null&&!(map.get("value").toString().equals(""))){
                        String val1 = map.get("value").toString().replace("-","").replace(" ","").replace(":","");
                        if(val1.length()==8)val1=val1+"000000";
                        GreatEqual ge = new GreatEqual();
                        ge.setProperty(map.get("field").toString());
                        ge.setValue(val1);
                        sib.getCondition().add(ge);
                    }

                    if(map.get("value2")!=null&&!(map.get("value2").toString().equals(""))){
                        String val2 = map.get("value2").toString().replace("-","").replace(" ","").replace(":","");
                        if(val2.length()==8)val2=val2+"235959";
                        LessEqual le = new LessEqual();
                        le.setProperty(map.get("field").toString());
                        le.setValue(val2);
                        sib.getCondition().add(le);
                    }

//                  String val1 = map.get("value").toString().replace("-","").replace(" ","").replace(":","");
//                  String val2 = map.get("value2").toString().replace("-","").replace(" ","").replace(":","");
//                  if(val1.length()==8)val1=val1+"000000";
//                  if(val2.length()==8)val2=val2+"235959";
//                  String[] strArr = new String[2];
//                  strArr[0]=val1;
//                  strArr[1]=val2;
//
//
//
//                  BetweenAnd bt = new BetweenAnd();
//                  bt.setProperty(map.get("field").toString());
//                  bt.setValue(strArr);
//                  sib.getCondition().add(bt);

                }
                else if(map.get("filter").toString().equals("NOTEQUAL")||map.get("filter").toString().equals("NOEQUAL")){
                    NotEqual ne = new NotEqual();
                    ne.setProperty(map.get("field").toString());
                    ne.setValue(map.get("value").toString());
                    sib.getCondition().add(ne);
                }
                else if(map.get("filter").toString().equals("GreatThan")){
                    GreatThan gt = new GreatThan();
                    gt.setProperty(map.get("field").toString());
                    gt.setValue(map.get("value").toString());
                    sib.getCondition().add(gt);
                }
                else if(map.get("filter").toString().equals("GreatEqual")){
                    GreatEqual ge = new GreatEqual();
                    ge.setProperty(map.get("field").toString());
                    ge.setValue(map.get("value").toString());
                    sib.getCondition().add(ge);
                }
                else if(map.get("filter").toString().equals("LessThan")){
                    LessThan lt = new LessThan();
                    lt.setProperty(map.get("field").toString());
                    lt.setValue(map.get("value").toString());
                    sib.getCondition().add(lt);
                }
                else if(map.get("filter").toString().equals("LessEqual")){
                    LessEqual le = new LessEqual();
                    le.setProperty(map.get("field").toString());
                    le.setValue(map.get("value").toString());
                    sib.getCondition().add(le);
                }
                else if(map.get("filter").toString().equals("EnumOr")){
                    EnumOr eo = new EnumOr();
                    eo.setProperty(map.get("field").toString());
                    eo.setValue((String[])(map.get("value")));
                    sib.getCondition().add(eo);
                }
                else if(map.get("filter").toString().equals("IS_NULL")){
                    IsNull isNull = new IsNull();
                    isNull.setProperty(map.get("field").toString());

                    sib.getCondition().add(isNull);
                }
                else if(map.get("filter").toString().equals("IS_NOT_NULL")){
                    IsNotNull isNotNull = new IsNotNull();
                    isNotNull.setProperty(map.get("field").toString());

                    sib.getCondition().add(isNotNull);
                }
                else if(map.get("filter").toString().equals("OR")){



                    if(map.get("value1")!=null&&map.get("value1").toString().length()>0) {

                        Or eo1 = new Or();
                        eo1.setProperty(map.get("field").toString());
                        String[] str_arr =  map.get("value").toString().split(",");
                        eo1.setValue(str_arr);

                        eo1.setProperty1(map.get("field1").toString());
                        String[] str_arr1 =  map.get("value1").toString().split(",");
                        eo1.setValue1(str_arr1);





                        sib.getCondition().add(eo1);
                    }else{


                        Or eo2 = new Or();
                        eo2.setProperty(map.get("field").toString());
                        String[] str_arr2 =  map.get("value").toString().split(",");
                        eo2.setValue(str_arr2);







                        sib.getCondition().add(eo2);
                    }
                }

            }
        }
        return sib;
    }
}
