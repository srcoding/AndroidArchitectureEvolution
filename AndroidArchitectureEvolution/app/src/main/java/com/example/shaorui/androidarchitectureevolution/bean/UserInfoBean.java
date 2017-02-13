package com.example.shaorui.androidarchitectureevolution.bean;

/**
 * Created by shaorui on 17/2/12.
 */
public class UserInfoBean {
    private String name;
    private int age;
    private String desc;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public static final String MOCK_STR = "{\"name\":\"shaorui\"," +
            "\"age\":18," +
            "\"desc\":\"我喜欢打篮球\"}";

    public static final String MOCK_URL = "http://com.meizu.com/mock/getUserInfo";
}
