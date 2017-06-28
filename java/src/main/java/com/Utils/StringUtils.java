package com.Utils;

import java.util.HashMap;
import java.util.Map;

/**
 * <p>Title:      string 工具类. </p>
 * <p>Description TODO </p>
 * <p>Company:    https://www.sogou.com/ </p>
 *
 * @Author <a href="litaoos2862@sogou-inc.com"/>李涛</a>
 * @CreateDate 2017/6/28 16:00
 */
public class StringUtils extends org.apache.commons.lang.StringUtils {
    public static Map<String, Integer> getMap(String str) {
        Map<String, Integer> map = new HashMap<>();
        if (org.apache.commons.lang.StringUtils.isNotBlank(str)) {
            String[] arr = org.apache.commons.lang.StringUtils.split(str, '|');
            for (int i = 0, size = arr.length; i < size; i++) {
                String[] items = org.apache.commons.lang.StringUtils.split(arr[i], ':');
                if (items.length == 2) {
                    map.put(items[0], Integer.parseInt(items[1], 10));
                }
            }
        }
        return map;
    }
}
