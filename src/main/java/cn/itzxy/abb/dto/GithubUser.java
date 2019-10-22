package cn.itzxy.abb.dto;

import lombok.Data;

@Data
public class GithubUser {
    private String login;
    private String name;
    private Long id;
    private String avatar_url;
    private String bio;
    @Override
    public String toString() {
        return "GithubUser{" +
                "login='" + login + '\'' +
                ", name='" + name + '\'' +
                ", id=" + id +
                '}';
    }


}
