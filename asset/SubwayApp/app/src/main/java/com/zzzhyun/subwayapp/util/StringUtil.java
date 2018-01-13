package com.zzzhyun.subwayapp.util;

/**
 * Created by ZHYUN on 2017-10-20.
 */

public class StringUtil {
    /**
     * 초를 시간 출력형태로 변형시킴
     *
     * @param String second
     * @return "99:99"
     */
    public static String changeSecToTimeString(String second){
        int temp = Integer.parseInt(second);
        return temp/60 + ":" + temp%60;
    }
}