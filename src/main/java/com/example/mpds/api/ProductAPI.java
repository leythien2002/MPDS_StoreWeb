package com.example.mpds.api;

import com.example.mpds.api.output.ProductOutput;
import com.example.mpds.dto.CategoryDTO;
import com.example.mpds.dto.ProductDTO;
import com.example.mpds.entity.ProductReviewEntity;
import com.example.mpds.repository.ProductReviewRepository;
import com.example.mpds.services.impl.CategoryService;
import com.example.mpds.services.impl.ProductService;
import com.example.mpds.services.impl.FilterProductResult;
import com.example.mpds.dto.*;
import com.example.mpds.entity.ProductReviewEntity;
import com.example.mpds.repository.ProductReviewRepository;
import com.example.mpds.services.impl.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;


import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Controller
public class ProductAPI {
    @Autowired
    private ProductService productService;

    @Autowired
    private CategoryService categoryService;

    @Autowired
    private TypeService typeService;
    @Autowired
    private StrapService strapService;
    @Autowired
    private DialSizeService dialSizeService;

//    @GetMapping(value = "/shop")
//    //limit se tu set, chu khong lay tu request
//    public String shop(@RequestParam(value = "page",defaultValue = "1")int page,
//                       @RequestParam(value = "sort",defaultValue ="default" ) String sort
//            , Model model){
//        Pageable pageable;
//        int pageSize = 9;
//        switch (sort){
//            case "asc":
//                pageable= PageRequest.of(page-1,pageSize, Sort.by("price").ascending());
//                break;
//            case "desc":
//        pageable= PageRequest.of(page-1,pageSize, Sort.by("price").descending());
//        break;
//        default:
//        pageable = PageRequest.of(page-1,pageSize);
//        break;
//    }
//    ProductOutput result= new ProductOutput();
//        result.setCurrentPage(page);
//        result.setTotalPage((int) Math.ceil((double)productService.totalProduct()/pageSize));
////        System.out.println("TONG SO TRANG: "+ result.getTotalPage());
//        result.setListResult(productService.findAll(pageable));
//    //nen truyen them page hien tai vi co class active trong html
//        model.addAttribute("listProduct",result);
//        //active class in html
//        String a="/shop";
//        model.addAttribute("query",a);
//        return "shop";
//    }
@GetMapping(value = "/shop")
public String shop(@RequestParam(value = "page", defaultValue = "1") int page,
                   @RequestParam(value = "sort", defaultValue = "default") String sort,
                   @RequestParam(value = "category", required = false) List<String> categories,
                   @RequestParam(value = "type", required = false) List<String> types,
                   @RequestParam(value = "dialSize", required = false) List<String> dialSizes,
                   @RequestParam(value = "strap", required = false) List<String> straps,
                   @RequestParam(value = "gender", required = false) List<String> genders,
                   @RequestParam(value = "searchText", required = false) String searchText,

                   Model model) {
    int pageSize = 9;
    Pageable pageable;
    switch (sort) {
        case "asc":
            pageable = PageRequest.of(page - 1, pageSize, Sort.by("price").ascending());
            break;
        case "desc":
            pageable = PageRequest.of(page - 1, pageSize, Sort.by("price").descending());
            break;
        default:
            pageable = PageRequest.of(page - 1, pageSize);
            break;
    }
    if (searchText==null) searchText="";

    FilterProductResult filterProductResult = productService.findAll(categories, types, dialSizes,straps, genders, pageable,searchText);

    List<ProductDTO> productList= filterProductResult.getListProductDTO();

    ProductOutput result = new ProductOutput();
    result.setCurrentPage(page);
    result.setTotalPage((int) Math.ceil((double) filterProductResult.getTotalElements() / pageSize));
    result.setListResult(productList);

    System.out.println("TOTAL PAGE: "+result.getTotalPage());
    System.out.println("TOTAL PRODUCT WITH FILTER: "+ filterProductResult.getTotalElements());

    //Get All Filter
    List<CategoryDTO> lstCategoryDTO = categoryService.getAll();
    List<TypeDTO> lstTypeDTO = typeService.getAll();
    List<DialSizeDTO> lstDialSizeDTO = dialSizeService.getAll();
    List<StrapDTO> lstStrapDTO = strapService.getAll();

    model.addAttribute("listCategory", lstCategoryDTO);
    model.addAttribute("listType", lstTypeDTO);
    model.addAttribute("listDialSize", lstDialSizeDTO);
    model.addAttribute("listStrap", lstStrapDTO);

    model.addAttribute("listProduct", result);
    model.addAttribute("currentCategories", categories != null ? categories : new ArrayList<>());
    model.addAttribute("currentTypes", types != null ? types : new ArrayList<>());
    model.addAttribute("currentDialSizes", dialSizes != null ? dialSizes : new ArrayList<>());
    model.addAttribute("currentStraps", straps != null ? straps : new ArrayList<>());
    model.addAttribute("currentGenders", genders != null ? genders : new ArrayList<>());

    model.addAttribute("currentSort", sort);
    model.addAttribute("currentSearch", searchText);

    model.addAttribute("query", "/shop");

    return "shop";
}
    @GetMapping(value = "/detail")
    public String detailProduct(@RequestParam(value = "name")String name,Model model){
        ProductDTO productDTO=productService.findOne(name);

        System.out.println("ID CUA PRODUCT DA CHON: "+productDTO.getId());

        List<ProductReviewEntity> lstReview =productService.getReviewsByProductId(productDTO.getId());

        System.out.println(productDTO.getDialSize());
        model.addAttribute("product",productDTO);
        model.addAttribute("lstReviews",lstReview);


        List<ProductDTO> allproduct = productService.findAll();
        List<ProductDTO> suggestedList = new ArrayList<>();
        Collections.shuffle(allproduct);


        int count = 0;

        for (ProductDTO prod : allproduct) {
            if (prod.getId() != productDTO.getId() && count < 4 &&prod.getStatus()==1) {
                suggestedList.add(prod);
                count++;
            }
        }
        //active class in html
        String a="/detail";
        model.addAttribute("query",a);
        model.addAttribute("suggestedList",suggestedList);

        return "detail";
    }

//    @GetMapping("/filter")
//    public String getProducts(@RequestParam(value = "category", required = false) List<String> categories,
//                              @RequestParam(value = "type", required = false) List<String> types,
//                              @RequestParam(value = "dialSize", required = false) List<String> dialSizes,
//                              Model model) {
//        List<ProductDTO> products = productService.getProductsByCategoriesTypesAndDialSizes(categories, types, dialSizes);
//        model.addAttribute("products", products);
//        return "products";
//    }
}
