package com.example.demo.src.Reply.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class GetReplyOfReq {
    private int replyIdx;
    private int postIdx;
    private int userIdx;
}
