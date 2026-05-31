package com.example.cixoil.service;

import com.example.cixoil.dto.productrecommendation.AiRecommendationResponseDTO;
import com.example.cixoil.dto.productrecommendation.ProductRecommendationDTO;
import com.example.cixoil.dto.productrecommendation.ProductRecommendationSaveDTO;
import com.example.cixoil.dto.productrecommendation.RecommendationRequestDTO;
import com.example.cixoil.enums.Priority;
import com.example.cixoil.enums.Status;
import com.example.cixoil.exception.ResourceNotFoundException;
import com.example.cixoil.mapper.ProductRecommendationMapper;
import com.example.cixoil.model.Product;
import com.example.cixoil.model.ProductRecommendation;
import com.example.cixoil.model.VehicleModel;
import com.example.cixoil.model.VehicleUseType;
import com.example.cixoil.repository.ProductRecommendationRepository;
import com.example.cixoil.repository.ProductRepository;
import com.example.cixoil.repository.VehicleModelRepository;
import com.example.cixoil.repository.VehicleUseTypeRepository;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductRecommendationService {

    private final ProductRecommendationRepository productRecommendationRepository;
    private final ProductRecommendationMapper productRecommendationMapper;
    private final VehicleModelRepository vehicleModelRepository;
    private final VehicleUseTypeRepository vehicleUseTypeRepository;
    private final ProductRepository productRepository;

    private final AiService aiService;
    private final ObjectMapper objectMapper;

    @Transactional(readOnly = true)
    public List<ProductRecommendationDTO> findAll() {
        return productRecommendationRepository.findAll()
                .stream().map(productRecommendationMapper::toDTO).toList();
    }

    @Transactional(readOnly = true)
    public ProductRecommendationDTO getById(Long id) {
        return productRecommendationRepository.findById(id).map(productRecommendationMapper::toDTO)
                .orElseThrow(() -> new ResourceNotFoundException("Recomendación de producto no encontrada"));
    }

    @Transactional
    public ProductRecommendationDTO create(RecommendationRequestDTO dto) {
        VehicleModel model = requireVehicleModelById(dto.idVehicleModel(),
                "No se encontró el modelo");
        VehicleUseType useType = requireUseTypeById(dto.idVehicleUseType(),
                "No se encontró el tipo de uso");
        List<Product> products = productRepository.findAllByStatusNot(Status.DELETED.getValue());

        String modelInput = model.toInput();
        String useTypeInput = useType.toInput();
        String productsInput = String.join("\n\n",
                products.stream().map(Product::toInput).toList());

        String response = aiService.recommend(modelInput, useTypeInput, productsInput);

        System.out.println("Respuesta de la IA: " + response);

        AiRecommendationResponseDTO recommendationDTO;
        try {
            recommendationDTO = objectMapper.readValue(
                    response,
                    AiRecommendationResponseDTO.class
            );
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }

        Product product = requireProductById(recommendationDTO.idProduct(),
                "Producto no encontrado");

        ProductRecommendation created = ProductRecommendation.builder()
                .vehicleModel(model)
                .vehicleUseType(useType)
                .product(product)
                .reason(recommendationDTO.reason())
                .priority(Priority.valueOf(recommendationDTO.priority()))
                .build();

        return productRecommendationMapper.toDTO(productRecommendationRepository.save(created));
    }

    @Transactional
    public ProductRecommendationDTO update(ProductRecommendationSaveDTO dto, Long id) {
        ProductRecommendation existent = requireRecommendationById(id,
                "No se encontró la recomendación");

        VehicleModel vehicleModel = requireVehicleModelById(dto.idVehicleModel(),
                "No se encontró el modelo de vehículo");
        VehicleUseType useType = requireUseTypeById(dto.idVehicleUseType(),
                "No se encontró el tipo de uso");
        Product product = requireProductById(dto.idProduct(),
                "No se encontró el producto");

        existent.setVehicleModel(vehicleModel);
        existent.setVehicleUseType(useType);
        existent.setProduct(product);
        existent.setReason(dto.reason());
        existent.setPriority(dto.priority());

        return productRecommendationMapper.toDTO(productRecommendationRepository.save(existent));
    }

    // Require

    private ProductRecommendation requireRecommendationById(Long id, String errorMessage) {
        return productRecommendationRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(errorMessage));
    }

    private VehicleModel requireVehicleModelById(Long id, String errorMessage) {
        return vehicleModelRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(errorMessage));
    }

    private VehicleUseType requireUseTypeById(Long id, String errorMessage) {
        return vehicleUseTypeRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(errorMessage));
    }

    private Product requireProductById(Long id, String errorMessage) {
        return productRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(errorMessage));
    }
}
