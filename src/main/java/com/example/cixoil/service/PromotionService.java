package com.example.cixoil.service;

import com.example.cixoil.dto.promotion.PromotionDTO;
import com.example.cixoil.dto.promotion.PromotionSaveDTO;
import com.example.cixoil.enums.Status;
import com.example.cixoil.exception.BusinessException;
import com.example.cixoil.exception.ResourceNotFoundException;
import com.example.cixoil.mapper.PromotionMapper;
import com.example.cixoil.model.Product;
import com.example.cixoil.model.Promotion;
import com.example.cixoil.model.PromotionType;
import com.example.cixoil.repository.ProductRepository;
import com.example.cixoil.repository.PromotionRepository;
import com.example.cixoil.repository.PromotionTypeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PromotionService {

private final PromotionRepository promotionRepository;
private final PromotionTypeRepository promotionTypeRepository;
private final ProductRepository productRepository;
private final PromotionMapper promotionMapper;

@Transactional(readOnly = true)
public List<PromotionDTO> findNotDeleted() {
    return promotionRepository.findAllByStatusNot(Status.DELETED.getValue())
            .stream()
            .map(promotionMapper::toDTO)
            .toList();
}

@Transactional(readOnly = true)
public PromotionDTO getById(Long id) {
    return promotionRepository.findById(id)
            .map(promotionMapper::toDTO)
            .orElseThrow(() ->
                    new ResourceNotFoundException("Promoción no encontrada"));
}

@Transactional
public PromotionDTO create(PromotionSaveDTO dto) {

    validatePromotion(dto);

    Product triggerProduct = requireProductById(dto.idTriggerProduct());
    Product bonusProduct = requireProductById(dto.idBonusProduct());
    PromotionType promotionType =
            requirePromotionTypeById(dto.idPromotionType());

    Promotion created = Promotion.builder()
            .name(dto.name())
            .triggerProduct(triggerProduct)
            .triggerQuantity(dto.triggerQuantity())
            .bonusProduct(bonusProduct)
            .startMonth(dto.startMonth())
            .startDay(dto.startDay())
            .endMonth(dto.endMonth())
            .endDay(dto.endDay())
            .promotionType(promotionType)
            .autoActivate(dto.autoActivate())
            .build();

    return promotionMapper.toDTO(
            promotionRepository.save(created)
    );
}

@Transactional
public PromotionDTO update(PromotionSaveDTO dto, Long id) {

    validatePromotion(dto);

    Promotion existent = requirePromotionById(id);

    Product triggerProduct = requireProductById(dto.idTriggerProduct());
    Product bonusProduct = requireProductById(dto.idBonusProduct());
    PromotionType promotionType =
            requirePromotionTypeById(dto.idPromotionType());

    existent.setName(dto.name());
    existent.setTriggerProduct(triggerProduct);
    existent.setTriggerQuantity(dto.triggerQuantity());
    existent.setBonusProduct(bonusProduct);
    existent.setStartMonth(dto.startMonth());
    existent.setStartDay(dto.startDay());
    existent.setEndMonth(dto.endMonth());
    existent.setEndDay(dto.endDay());
    existent.setPromotionType(promotionType);
    existent.setAutoActivate(dto.autoActivate());

    return promotionMapper.toDTO(
            promotionRepository.save(existent)
    );
}

@Transactional
public PromotionDTO toggleStatus(Long id) {

    Promotion existent = requirePromotionById(id);

    existent.setStatus(
            existent.getStatus().equals(Status.ACTIVE.getValue())
                    ? Status.INACTIVE.getValue()
                    : Status.ACTIVE.getValue()
    );

    return promotionMapper.toDTO(
            promotionRepository.save(existent)
    );
}

@Transactional
public void delete(Long id) {

    Promotion existent = requirePromotionById(id);

    existent.setStatus(Status.DELETED.getValue());

    promotionRepository.save(existent);
}

private void validatePromotion(PromotionSaveDTO dto) {

    if (dto.triggerQuantity() == null || dto.triggerQuantity() <= 0) {
        throw new BusinessException(
                "La cantidad activadora debe ser mayor a cero"
        );
    }

    if (dto.idTriggerProduct().equals(dto.idBonusProduct())) {
        throw new BusinessException(
                "El producto activador y el producto bonus no pueden ser iguales"
        );
    }
}

private Promotion requirePromotionById(Long id) {
    return promotionRepository.findById(id)
            .orElseThrow(() ->
                    new ResourceNotFoundException("Promoción no encontrada"));
}

private Product requireProductById(Long id) {
    return productRepository.findById(id)
            .orElseThrow(() ->
                    new ResourceNotFoundException("Producto no encontrado"));
}

private PromotionType requirePromotionTypeById(Long id) {
    return promotionTypeRepository.findById(id)
            .orElseThrow(() ->
                    new ResourceNotFoundException(
                            "Tipo de promoción no encontrado"));
}

}