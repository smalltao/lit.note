package com.Utils;

import com.alibaba.fastjson.JSON;
import com.bean.Group;
import com.bean.User;
import org.junit.Test;

/**
 * <p>Title:      阿里巴巴fastjson工具. </p>
 * <p>Description TODO </p>
 * <p>Company:    https://www.sogou.com/ </p>
 *
 * @Author <a href="litaoos2862@sogou-inc.com"/>李涛</a>
 * @CreateDate 2017/7/24 16:47
 */
public class FastjsonUtils {

    @Test
    public void beanToJson() {

        Group group = new Group();
        group.setId(0L);
        group.setName("admin");

        User guestUser = new User();
        guestUser.setId(2L);
        guestUser.setName("guest");

        User rootUser = new User();
        rootUser.setId(3L);
        rootUser.setName("root");

        group.addUser(guestUser);
        group.addUser(rootUser);

        String jsonString = JSON.toJSONString(group);
        System.out.println("beana to object: " + jsonString);
    }


}
