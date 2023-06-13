package com.event.vue.domain;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BoardVO {
  private int docNo;
  private String title;
  private String content;
  private String writer;
  private String regDttm;
  private int view;
  private int reply;

  private int startNo;
  private int endNo;
  private String[] sort;

  private String schType;
  private String schVal;

  
  public String idx;
//  public String title;
  public String contents;
  public String author;
  public String createdAt;
  
  
}