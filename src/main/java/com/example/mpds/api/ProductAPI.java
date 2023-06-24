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
    public String shop(@RequestParam(value = "page",defaultValue = "1") int page, Model model){
        Pageable pageable;
//        if(sort==1){
//             pageable= PageRequest.of(page-1,2, Sort.Direction.DESC);
//        }
//        if(sort==0){
//             pageable= PageRequest.of(page-1,2, Sort.Direction.ASC);
//        }
//        else{}
            pageable = PageRequest.of(page-1,2);

        ProductOutput result= new ProductOutput();
        result.setTotalPage((int) Math.ceil((double)productService.totalProduct()/2));
        result.setListResult(productService.findAll(pageable));
        //nen truyen them page hien tai vi co class active trong html
        model.addAttribute("listProduct",result);
        return "shop";
    }
}
