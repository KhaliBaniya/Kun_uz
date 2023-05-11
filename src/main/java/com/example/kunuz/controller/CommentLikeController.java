package com.example.kunuz.controller;

import com.example.kunuz.enums.ProfileRole;
import com.example.kunuz.service.CommentLikeService;
import com.example.kunuz.util.HttpRequestUtil;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/comment_like")
public class CommentLikeController {
    private final CommentLikeService service;

    public CommentLikeController(CommentLikeService service) {
        this.service = service;
    }

    @PostMapping("/like/{c_id}")
    public ResponseEntity<?> like(@PathVariable("c_id") Integer id, HttpServletRequest request) {
        Integer profileId = HttpRequestUtil.getProfileId(request, ProfileRole.USER);
        Boolean result = service.like(id, profileId);
        return ResponseEntity.ok(result);
    }

    @PutMapping("/dislike/{c_id}")
    public ResponseEntity<?> dislike(@PathVariable("c_id") Integer id, HttpServletRequest request) {
        Integer profileId = HttpRequestUtil.getProfileId(request, ProfileRole.USER);
        boolean result = service.dislike(id, profileId);
        return ResponseEntity.ok(result);
    }

    @DeleteMapping("/remove/{c_id}")
    public ResponseEntity<?> remove(@PathVariable("c_id") Integer id, HttpServletRequest request) {
        Integer profileId = HttpRequestUtil.getProfileId(request, ProfileRole.USER);
        boolean result = service.remove(id, profileId);
        return ResponseEntity.ok(result);
    }

}
