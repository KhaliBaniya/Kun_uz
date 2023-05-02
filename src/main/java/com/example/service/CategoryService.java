package com.example.service;

import com.example.dto.CategoryDTO;
import com.example.entity.CategoryEntity;
import com.example.exps.ItemNotFoundException;
import com.example.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

@Service
public class CategoryService {
    @Autowired
    private CategoryRepository categoryRepository;

    public Integer create(CategoryDTO dto, Integer adminId) {
        CategoryEntity entity = new CategoryEntity();
        entity.setNameUz(dto.getNameUz());
        entity.setNameRu(dto.getNameRU());
        entity.setNameEng(dto.getNameEng());
        entity.setCreatedDate(LocalDateTime.now());
        entity.setVisible(true);
        entity.setPrtId(adminId);
        categoryRepository.save(entity); // save profile
        dto.setId(entity.getId());
        return entity.getId();
    }

    public Boolean update(Integer id, CategoryDTO categoryDto) {
        CategoryEntity entity = get(id);
        entity.setNameUz(categoryDto.getNameUz());
        entity.setNameRu(categoryDto.getNameRU());
        entity.setNameEng(categoryDto.getNameEng());
        categoryRepository.save(entity);
        return true;
    }

    public CategoryEntity get(Integer id) {
        Optional<CategoryEntity> optional = categoryRepository.findById(id);
        if (optional.isEmpty()) {
            throw new ItemNotFoundException("Item not found: " + id);
        }
        return optional.get();
    }

    public Boolean deleteById(Integer id, Integer prtId) {
        categoryRepository.updateVisible(id, prtId);
//        CategoryEntity entity = get(id);
//        entity.setVisible(false);
//        entity.setPrtId(4);
//        categoryRepository.save(entity);
        return true;
    }

    public Page<CategoryDTO> getAll(int page, int size) {
        Sort sort = Sort.by(Sort.Direction.DESC, "createdDate");
        Pageable paging = PageRequest.of(page - 1, size, sort);
        Page<CategoryEntity> pageObj = categoryRepository.findAll(paging);
        long totalCount = pageObj.getTotalElements();

        List<CategoryEntity> entityList = pageObj.getContent();
        List<CategoryDTO> dtoList = new LinkedList<>();

        for (CategoryEntity entity : entityList) {
            CategoryDTO dto = new CategoryDTO();
            dto.setId(entity.getId());
            dto.setNameUz(entity.getNameUz());
            dto.setNameRU(entity.getNameRu());
            dto.setNameEng(entity.getNameEng());
            dto.setCreatedDate(entity.getCreatedDate());
            dto.setVisible(entity.getVisible());
            dtoList.add(dto);
        }
        return new PageImpl<CategoryDTO>(dtoList, paging, totalCount);
    }
}
