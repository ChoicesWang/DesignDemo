package com.support.design.bean;


public class Photo {
    public String url;
    public String name;

    @Override
    public String toString() {
        return "Photo{" +
                "url='" + url + '\'' +
                ", name='" + name + '\'' +
                '}';
    }
}
