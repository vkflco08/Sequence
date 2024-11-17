package com.example.notice_board.controller;
import com.example.notice_board.dto.ArticleRequestDto;
import com.example.notice_board.dto.ArticleResponseDto;
import com.example.notice_board.service.ArticleService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/article")
public class ArticleController {
    private ArticleService articleService;

    public ArticleController(ArticleService articleService) {
        this.articleService = articleService;
    }

    // 게시글 작성
    @PostMapping
    public ResponseEntity<ArticleResponseDto> createArticle(@RequestBody ArticleRequestDto requestDto) {
        ArticleResponseDto responseDto = articleService.createArticle(requestDto);
        return ResponseEntity.ok(responseDto);
    }

    // 게시글 목록 조회
    @GetMapping
    public ResponseEntity<List<ArticleResponseDto>> getAllArticles() {
        List<ArticleResponseDto> articles = articleService.getAllArticles();
        return ResponseEntity.ok(articles);
    }
}
