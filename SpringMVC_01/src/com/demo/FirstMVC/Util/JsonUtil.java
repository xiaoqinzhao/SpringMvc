package com.demo.FirstMVC.Util;

import com.demo.FirstMVC.Entity.User;
import net.sf.json.JSONObject;

public class JsonUtil {
    public static void main(String []args)
    {
        User user=new User();
        user.setId(123);
        user.setName("zhaoxiaoqin");
        user.setPassword("031001");
        user.setSalt("sd122");
        JSONObject isJson = JSONObject.fromObject(user);
        System.out.println(isJson);
    }
}