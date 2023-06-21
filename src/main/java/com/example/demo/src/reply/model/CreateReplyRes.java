package com.example.demo.src.reply.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class CreateReplyRes {
    private int replyIdx;
    private int postIdx;
    private int userIdx;
    private String replyContents;
    private int liked;
}
