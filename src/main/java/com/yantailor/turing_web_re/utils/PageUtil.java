package com.yantailor.turing_web_re.utils;

import java.util.List;

/**
 * Created by yantailor
 * on 2022/3/16 16:22 @Version 1.0
 */
public class PageUtil {
    public static <T> List<T> PageHandler(Integer page , Integer offset , List<T> dtoList){
        int fromIndex = (page-1)*offset;
        int toIndex = (page-1)*offset + offset;
        if(fromIndex > dtoList.size()){
            return null;
        }

        if(toIndex > dtoList.size()){
            return dtoList.subList(fromIndex, dtoList.size());
        }
        return dtoList.subList(fromIndex,toIndex);
    }
}
