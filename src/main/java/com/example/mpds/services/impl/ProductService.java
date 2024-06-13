package com.example.mpds.services.impl;

import com.example.mpds.dto.ProductDTO;
import com.example.mpds.entity.CategoryEntity;
import com.example.mpds.entity.InvoiceInfoEntity;
import com.example.mpds.entity.ProductEntity;
import com.example.mpds.entity.ProductReviewEntity;
import com.example.mpds.mapper.ProductMapper;
import com.example.mpds.repository.*;
import com.example.mpds.services.IProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProductService implements IProductService {
    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private CategoryRepository categoryRepository;
    @Autowired
    private ProductMapper mapper;
    @Autowired
    private ProductReviewRepository productReviewRepository;

    @Autowired
    private InvoiceInfoRepository invoiceInfoRepository;

    @Override
    public FilterProductResult findAll(List<Integer> categories, List<String> types, List<String> dialSizes, Pageable pageable, String searchText) {


        Page<ProductEntity> productEntities;

//        productEntities = productRepository.findByCategoriesAndTypesAndDialSizes(categories, types, dialSizes, pageable);

        Specification<ProductEntity> spec = Specification.where(ProductSpecifications.hasCategories(categories))
                .and(ProductSpecifications.hasTypes(types))
                .and(ProductSpecifications.hasDialSizes(dialSizes))
                .and(ProductSpecifications.nameContains(searchText));


        productEntities = productRepository.findAll(spec, pageable);
        productEntities.getContent();
        List<ProductDTO> productDTOs = new ArrayList<>();
        for (ProductEntity entity : productEntities) {
            productDTOs.add(mapper.toDTO(entity));
        }

        return new FilterProductResult(productDTOs, productEntities.getTotalElements());
    }

    public List<ProductDTO> findAll() {
        List<ProductDTO> results = new ArrayList<>();
        List<ProductEntity> productList = productRepository.findAll();
        for (ProductEntity item : productList) {
            ProductDTO productDTO = mapper.toDTO(item);
            System.out.println(productDTO.getCategoryName());
            results.add(productDTO);
        }
        return results;
    }

    public ProductDTO findOne(String name) {
        ProductDTO productDTO = new ProductDTO();
        ProductEntity product = productRepository.findOneByName(name);
        productDTO = mapper.toDTO(product);
        return productDTO;
    }

    public ProductDTO findOne(int id) {
        ProductDTO productDTO = new ProductDTO();
        ProductEntity product = productRepository.findOneById(id);
        productDTO = mapper.toDTO(product);
        return productDTO;
    }

    public ProductEntity findProduct(int id) {

        ProductEntity product = productRepository.findOneById(id);

        return product;
    }

    public String insertProduct(ProductDTO product) {
        ProductEntity newProd = mapper.toEntity(product);
        CategoryEntity category = categoryRepository.findByName(product.getCategoryName());
        if (category != null) {
            newProd.setCategory(category);
            productRepository.save(newProd);
            return "Insert Product Suscessfully";
        } else return "Brand name: " + product.getCategoryName() + " does not exist!";
    }

    public int totalProduct() {
        return (int) productRepository.count();
    }

    public String deleteById(Long id) {
        List<InvoiceInfoEntity> lstInvoiceInfor = invoiceInfoRepository.findByProduct_Id(id.intValue());
        if (lstInvoiceInfor.isEmpty()) {
            productRepository.deleteById(id);
            return "Delete Suscessfully";
        }

        List<Integer> invoiceIds = lstInvoiceInfor.stream()
                .map(invoiceInfo -> invoiceInfo.getInvoice().getId())
                .collect(Collectors.toList());

        return "Delete Failed, Invoices associated with product ID " + id + ": " + invoiceIds;
    }

    public String updateProduct(ProductDTO product) {

        ProductEntity existingProduct = productRepository.findById((long) product.getId())
                .orElseThrow(() -> new IllegalArgumentException("Product not found"));

        CategoryEntity category = categoryRepository.findByName(product.getCategoryName());
        if (category == null){
            return "Update Failed! Brand "+product.getCategoryName()+" does not exist!";
        }

        existingProduct.setCategory(category);
        existingProduct.setName(product.getName());
        existingProduct.setPrice(product.getPrice());
        existingProduct.setImage1(product.getImage1());
        existingProduct.setImage2(product.getImage2());
        existingProduct.setImage3(product.getImage3());
        existingProduct.setDescription(product.getDescription());
        productRepository.save(existingProduct);
        return "Update Suscessfully";
    }

    public List<ProductReviewEntity> getReviewsByProductId(int productId) {
        return productReviewRepository.findByProductId(productId);
    }

}
