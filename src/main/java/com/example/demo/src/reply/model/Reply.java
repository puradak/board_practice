package com.example.demo.src.reply.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Reply {
    private int replyIdx;
    private int postIdx;
    private int userIdx;
    private int liked;
    private String replyContents;
    private String replyDate;
}
