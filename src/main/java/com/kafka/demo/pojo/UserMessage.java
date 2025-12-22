package com.kafka.demo.pojo;

import lombok.*;

@Data
@Builder
public class UserMessage {

    private String userId;
    private String name;
    private String email;

    @Override
    public String toString() {
        return "UserMessage {" +
                "userId='" + userId + '\'' +
                ", name='" + name + '\'' +
                ", email='" + email + '\'' +
                '}';
    }
}
