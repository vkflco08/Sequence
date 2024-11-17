package com.example.notice_board.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ArticleRequestDto {
    private String title;
    private String content;
    private String writer; // 로그인 시 받은 username
}
