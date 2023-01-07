package com.hiqgroup.hiq.utils;

import java.lang.reflect.Method;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.List;

/**
 * Created by huangjunyi on 2016/12/28.
 */
public class ListSortUtil<T> {
    /**
     * @param targetList 目标排序List
     * @param sortField 排序字段(实体类属性名)
     * @param sortMode 排序方式（asc or  desc）
     */
    @SuppressWarnings({ "unchecked", "rawtypes" })
    public void sort(List<T> targetList, final String sortField, final String sortMode) {
        Collections.sort(targetList, new Comparator() {
            public int compare(Object obj1, Object obj2) {
                int retVal = 0;
                try {
                    //首字母转大写
                    String newStr=sortField.substring(0, 1).toUpperCase()+sortField.replaceFirst("\\w","");
                    String methodStr="get"+newStr;
                    Method method1 = ((T)obj1).getClass().getMethod(methodStr, null);
                    Method method2 = ((T)obj2).getClass().getMethod(methodStr, null);
                    Object value1=method2.invoke(((T) obj1), null);
                    Object value2=method2.invoke(((T) obj2), null);
                    if(value1 instanceof Date || value2 instanceof Date) {
                        Date date1=(Date) value1;
                        Date date2=(Date) value2;
                        if (sortMode != null && "desc".equals(sortMode)) {
                            retVal = date2.compareTo(date1); // 倒序
                        } else {
                            retVal = date1.compareTo(date2); // 正序
                        }
                    }else if(value1 instanceof Float || value2 instanceof Float){
                        Float date1=Float.parseFloat(value1.toString().replaceAll("[^0-9.]",""));
                        Float date2=Float.parseFloat(value2.toString().replaceAll("[^0-9.]",""));
                        if (sortMode != null && "desc".equals(sortMode)) {
                            retVal = date2.compareTo(date1); // 倒序
                        } else {
                            retVal = date1.compareTo(date2); // 正序
                        }
                    }else if(value1 instanceof Long||value2 instanceof Long){
                        Long date1=(Long) value1;
                        Long date2=(Long) value2;
                        if (sortMode != null && "desc".equals(sortMode)) {
                            retVal = date2.compareTo(date1); // 倒序
                        } else {
                            retVal = date1.compareTo(date2); // 正序
                        }
                    }
                    else{
                        if (sortMode != null && "desc".equals(sortMode)) {
                            retVal = method2.invoke(((T) obj2), null).toString().compareTo(method1.invoke(((T) obj1), null).toString()); // 倒序
                        } else {
                            retVal = method1.invoke(((T) obj1), null).toString().compareTo(method2.invoke(((T) obj2), null).toString()); // 正序
                        }
                    }
                } catch (Exception e) {
                    throw new RuntimeException();
                }
                return retVal;
            }
        });
    }
}
