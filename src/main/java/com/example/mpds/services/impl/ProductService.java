package com.example.mpds.services.impl;

import com.example.mpds.dto.ProductDTO;
import com.example.mpds.entity.CategoryEntity;
import com.example.mpds.entity.ProductEntity;
import com.example.mpds.mapper.ProductMapper;
import com.example.mpds.repository.CategoryRepository;
import com.example.mpds.repository.ProductRepository;
import com.example.mpds.repository.ProductSpecifications;
import com.example.mpds.services.IProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ProductService implements IProductService {
    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private CategoryRepository categoryRepository;
    @Autowired
    private ProductMapper mapper;

    @Override
    public FilterProductResult findAll(List<Integer> categories, List<String> types, List<String> dialSizes, Pageable pageable, String searchText) {
//        List<ProductDTO> results=new ArrayList<>();
//        List<ProductEntity> productList=productRepository.findAll(pageable).getContent();
//        for(ProductEntity item:productList){
//            ProductDTO productDTO=mapper.toDTO(item);
//            results.add(productDTO);
//        }
//        return results;
//        if (categories == null || categories.isEmpty()) {
//            categories = List.of(""); // Assign default value for categories
//        }
//        if (types == null || types.isEmpty()) {
//            types = List.of(""); // Assign default value for types
//        }
//        if (dialSizes == null || dialSizes.isEmpty()) {
//            dialSizes = List.of(""); // Assign default value for dialSizes
//        }
//
//        if (categories.size() == 1 && categories.get(0).isEmpty() &&
//                types.size() == 1 && types.get(0).isEmpty() &&
//                dialSizes.size() == 1 && dialSizes.get(0).isEmpty()) {
//            // No filters provided, retrieve all products
//            productEntities = productRepository.findAll(pageable).getContent();
//        } else {
//            // Filters provided, apply them
//
//        }
//        String typesAsString = types != null ? String.join(",", types) : null;
//        String dialSizesAsString = dialSizes != null ? String.join(",", dialSizes) : null;

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
        List<ProductDTO> results=new ArrayList<>();
        List<ProductEntity> productList=productRepository.findAll();
        for(ProductEntity item:productList){
            ProductDTO productDTO=mapper.toDTO(item);
            System.out.println(productDTO.getCategoryName());
            results.add(productDTO);
        }
        return results;
    }
    public ProductDTO findOne(String name){
        ProductDTO productDTO=new ProductDTO();
        ProductEntity product=productRepository.findOneByName(name);
        productDTO= mapper.toDTO(product);
        return productDTO;
    }

    public ProductDTO findOne(int id){
        ProductDTO productDTO=new ProductDTO();
        ProductEntity product=productRepository.findOneById(id);
        productDTO= mapper.toDTO(product);
        return productDTO;
    }
    public ProductEntity findProduct(int id){

        ProductEntity product=productRepository.findOneById(id);

        return product;
    }
    public  void insertProduct(ProductDTO product){
        ProductEntity newProd = mapper.toEntity(product);
        CategoryEntity category = categoryRepository.findByName(product.getCategoryName());
        newProd.setCategory(category);
        productRepository.save(newProd);
    }
    public int totalProduct(){
        return (int) productRepository.count();
    }

    public void deleteById(Long id){
        productRepository.deleteById(id);
    }

    public void updateProduct(ProductDTO product)
    {

        ProductEntity existingProduct = productRepository.findById((long) product.getId())
                .orElseThrow(() -> new IllegalArgumentException("Product not found"));


        CategoryEntity category = categoryRepository.findByName(product.getCategoryName());
        existingProduct.setCategory(category);
        existingProduct.setName(product.getName());
        existingProduct.setPrice(product.getPrice());
        existingProduct.setImage1(product.getImage1());
        existingProduct.setImage2(product.getImage2());
        existingProduct.setImage3(product.getImage3());
        existingProduct.setDescription(product.getDescription());
        productRepository.save(existingProduct);
    }

//    public List<ProductDTO> filterProduct(List<String> categories, List<String> types, List<String> dialSizes, Pageable pageable) {
//        if (categories == null || categories.isEmpty()) {
//            categories = List.of(""); // Assign default value for categories
//        }
//        if (types == null || types.isEmpty()) {
//            types = List.of(""); // Assign default value for types
//        }
//        if (dialSizes == null || dialSizes.isEmpty()) {
//            dialSizes = List.of(""); // Assign default value for dialSizes
//        }
//        List<ProductEntity> productEntities = productRepository.findByCategoriesAndTypesAndDialSizes(categories, types, dialSizes, pageable);
//        List<ProductDTO> productDTOs = new ArrayList<>();
//        for (ProductEntity entity : productEntities) {
//            productDTOs.add(mapper.toDTO(entity));
//        }
//        return productDTOs;
//    }
}
