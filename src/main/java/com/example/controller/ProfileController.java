package com.example.controller;

import com.example.dto.jwt.JwtDTO;
import com.example.dto.profile.ProfileDTO;
import com.example.enums.ProfileRole;
import com.example.exps.MethodNotAllowedException;
import com.example.service.ProfileService;
import com.example.util.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/profile")
public class ProfileController {
    @Autowired
    private ProfileService profileService;

    @PostMapping({"", "/"})
    public ResponseEntity<ProfileDTO> create(@RequestBody ProfileDTO dto,
                                             @RequestHeader("Authorization") String authorization) {
        JwtDTO jwtDTO = JwtUtil.getJwtDTO(authorization, ProfileRole.ADMIN);
        return ResponseEntity.ok(profileService.create(dto, jwtDTO.getId()));
    }

    @GetMapping("")
    public ResponseEntity<List<ProfileDTO>> getAll() {
        return null;
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProfileDTO> getById(@PathVariable("id") Integer id) {
        return null;
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ProfileDTO> deleteById(@PathVariable("id") Integer id) {
        return null;
    }

    @GetMapping("/pagination")
    public ResponseEntity<List<ProfileDTO>> pagination(@RequestParam("page") int page,
                                                       @RequestParam("size") int size) {
        return null;
    }


}
