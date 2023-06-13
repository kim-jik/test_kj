package com.event.vue.domain;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Getter
@Setter
public class ReplyVO {
  private int replyNo;
  private int docNo;
  private String writer;
  private String content;
  private String regDttm;

}