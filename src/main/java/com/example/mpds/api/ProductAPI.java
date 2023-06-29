package com.example.mpds.api;

import com.example.mpds.api.output.ProductOutput;
import com.example.mpds.dto.ProductDTO;
import com.example.mpds.services.impl.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;


import java.util.List;

@Controller
public class ProductAPI {
    @Autowired
    private ProductService productService;

    @GetMapping(value = "/shop")
    //limit se tu set, chu khong lay tu request
    public String shop(@RequestParam(value = "page",defaultValue = "1")int page,
                       @RequestParam(value = "sort",defaultValue ="default" ) String sort
            , Model model){
        Pageable pageable;
        switch (sort){
            case "asc":
                pageable= PageRequest.of(page-1,2, Sort.by("price").ascending());
                break;
            case "desc":
                pageable= PageRequest.of(page-1,2, Sort.by("price").descending());
                break;
            default:
                pageable = PageRequest.of(page-1,2);
                break;
        }
        ProductOutput result= new ProductOutput();
        result.setCurrentPage(page);
        result.setTotalPage((int) Math.ceil((double)productService.totalProduct()/2));
        result.setListResult(productService.findAll(pageable));
        //nen truyen them page hien tai vi co class active trong html
        model.addAttribute("listProduct",result);
        //active class in html
        String a="/shop";
        model.addAttribute("query",a);
        return "shop";
    }
    @GetMapping(value = "/detail")
    public String detailProduct(@RequestParam(value = "name")String name,Model model){
        ProductDTO productDTO=productService.findOne(name);
        model.addAttribute("product",productDTO);
        //active class in html
        String a="/detail";
        model.addAttribute("query",a);
        return "detail";
    }
}
