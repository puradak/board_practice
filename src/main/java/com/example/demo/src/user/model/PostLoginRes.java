package com.example.demo.src.user.model;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class PostLoginRes {

    private int userIdx;
    private String jwt;
}
