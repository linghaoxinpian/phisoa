package com.shmilyou.entity;

/**
 * Created with 岂止是一丝涟漪
 * Date: 2018/8/14
 */

public class User extends BaseEntity {

    private String id;

    private String name;

    private String nickName;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }
}
