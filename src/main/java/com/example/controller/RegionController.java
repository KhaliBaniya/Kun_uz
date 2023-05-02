package com.example.controller;

import com.example.dto.RegionDTO;
import com.example.dto.jwt.JwtDTO;
import com.example.enums.ProfileRole;
import com.example.service.RegionService;
import com.example.util.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/region")
public class RegionController {
    @Autowired
    private RegionService regionService;

    @PostMapping({"", "/"})
    public ResponseEntity<Integer> create(@RequestBody RegionDTO dto, @RequestHeader("Authorization") String authorization) {
        JwtDTO jwtDTO = JwtUtil.getJwtDTO(authorization, ProfileRole.ADMIN);
        return ResponseEntity.ok(regionService.create(dto, jwtDTO.getId()));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Boolean> update(@PathVariable("id") Integer id, @RequestBody RegionDTO regionDto, @RequestHeader("Authorization") String authorization) {
        JwtDTO jwtDTO = JwtUtil.getJwtDTO(authorization, ProfileRole.ADMIN);
        return ResponseEntity.ok(regionService.update(id, regionDto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Boolean> deleteById(@PathVariable("id") Integer id, @RequestHeader("Authorization") String authorization) {
        JwtDTO jwtDTO = JwtUtil.getJwtDTO(authorization, ProfileRole.ADMIN);
        return ResponseEntity.ok(regionService.deleteById(id, jwtDTO.getId()));
    }

    @GetMapping("/paging")
    public ResponseEntity<Page<RegionDTO>> getAll(@RequestParam(value = "page", defaultValue = "1") int page, @RequestParam(value = "size", defaultValue = "2") int size, @RequestHeader("Authorization") String authorization) {
        JwtDTO jwtDTO = JwtUtil.getJwtDTO(authorization, ProfileRole.ADMIN);
        return ResponseEntity.ok(regionService.getAll(page, size));
    }
}
