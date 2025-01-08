package com.sullung2yo.usermanagementapplication.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class User {

    private Long id;
    private String name;
    private int age;

    public User(String name, int age) {
        this.name = name;
        this.age = age;
    }

}
