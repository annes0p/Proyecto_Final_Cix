package com.example.cixoil.service;

import com.example.cixoil.dto.SelectDTO;
import com.example.cixoil.dto.category.CategoryDTO;
import com.example.cixoil.dto.category.CategorySaveDTO;
import com.example.cixoil.enums.Status;
import com.example.cixoil.exception.ResourceNotFoundException;
import com.example.cixoil.mapper.CategoryMapper;
import com.example.cixoil.model.Category;
import com.example.cixoil.repository.CategoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CategoryService {

    private final CategoryRepository categoryRepository;
    private final CategoryMapper categoryMapper;

    @Transactional(readOnly = true)
    public List<CategoryDTO> findNotDeleted() {
        return categoryRepository.findAllByStatusNot(Status.DELETED.getValue())
                .stream().map(categoryMapper::toDTO).toList();
    }

    @Transactional(readOnly = true)
    public CategoryDTO getById(Long id) {
        return categoryRepository.findById(id).map(categoryMapper::toDTO)
                .orElseThrow(() -> new ResourceNotFoundException("Categoría no encontrada"));
    }

    // TODO: Cambiar a mappers
    @Transactional(readOnly = true)
    public List<SelectDTO<Long>> listForSelect() {
        return categoryRepository.findAllByStatusNot(Status.DELETED.getValue())
                .stream()
                .map(e -> new SelectDTO<>(e.getId(), e.getName()))
                .toList();
    }

    @Transactional
    public CategoryDTO create(CategorySaveDTO dto) {
        Category created = Category.builder()
                .name(dto.name())
                .description(dto.description())
                .build();

        return categoryMapper.toDTO(categoryRepository.save(created));
    }

    @Transactional
    public CategoryDTO update(CategorySaveDTO dto, Long id) {
        Category existent = requireCategoryById(id);

        existent.setName(dto.name());
        existent.setDescription(dto.description());

        return categoryMapper.toDTO(categoryRepository.save(existent));
    }

    @Transactional
    public CategoryDTO toggleStatus(Long id) {
        Category existent = requireCategoryById(id);

        existent.setStatus(
                existent.getStatus().equals(Status.ACTIVE.getValue()) ?
                        Status.INACTIVE.getValue() : Status.ACTIVE.getValue()
        );

        return categoryMapper.toDTO(categoryRepository.save(existent));
    }

    @Transactional
    public void delete(Long id) {
        Category existent = requireCategoryById(id);
        existent.setStatus(Status.DELETED.getValue());
        categoryRepository.save(existent);
    }

    // Require

    private Category requireCategoryById(Long id) {
        return categoryRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Categoría no encontrada"));
    }
}
