package com.example.demo.src.post.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class UpdatePostReq {
    private int boardIdx;
    private int userIdx;
    private int postIdx;
    private String postTitle;
    private String postDate;
    private String postContents;
}
