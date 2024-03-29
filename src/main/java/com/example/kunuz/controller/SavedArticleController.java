package com.example.kunuz.controller;

import com.example.kunuz.dto.article.SavedArticleResponseDTO;
import com.example.kunuz.enums.Language;
import com.example.kunuz.enums.ProfileRole;
import com.example.kunuz.service.SavedArticleService;
import com.example.kunuz.util.HttpRequestUtil;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/saved_article")
public class SavedArticleController {

    private final SavedArticleService service;

    public SavedArticleController(SavedArticleService service) {
        this.service = service;
    }

    @PostMapping("/create/{article_id}")
    public ResponseEntity<?> create(@PathVariable("article_id") String articleId, HttpServletRequest request) {
        Integer profileId = HttpRequestUtil.getProfileId(request, ProfileRole.USER);
        Boolean result = service.create(articleId, profileId);
        return ResponseEntity.ok(result);
    }

    @DeleteMapping("/delete/{article_id}")
    public ResponseEntity<?> delete(@PathVariable("article_id") String articleId, HttpServletRequest request) {
        Integer profileId = HttpRequestUtil.getProfileId(request, ProfileRole.USER);
        Boolean result = service.delete(articleId, profileId);
        return ResponseEntity.ok(result);
    }

    @GetMapping("/get_list")
    public ResponseEntity<?> getList(HttpServletRequest request,@RequestHeader(value = "Accept-Language",defaultValue = "RU") Language language) {
        Integer profileId = HttpRequestUtil.getProfileId(request, ProfileRole.USER);
        List<SavedArticleResponseDTO> result = service.getList(profileId,language);
        return ResponseEntity.ok(result);
    }

}
