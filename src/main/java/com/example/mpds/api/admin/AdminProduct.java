package com.example.mpds.api.admin;

import com.example.mpds.dto.ProductDTO;
import com.example.mpds.entity.ProductEntity;
import com.example.mpds.entity.UserEntity;
import com.example.mpds.mapper.ProductMapper;
import com.example.mpds.services.impl.ProductService;
import com.example.mpds.services.impl.UserService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.concurrent.ExecutionException;

@Controller
@RequestMapping("/product")
@AllArgsConstructor
public class AdminProduct {
    @Autowired
    private ProductService productService;

    @Autowired
    private ProductMapper productMapper;

    @GetMapping
    public String getUser(Model model) throws ExecutionException, InterruptedException {
        List<ProductDTO> tmp=productService.findAll();

        model.addAttribute("listProduct",tmp);

        System.out.println("CO CHAY VAO GET LIST Product ");
        for (ProductDTO prod: tmp) {
            System.out.println(prod.getImage1());
            System.out.println(prod.getName());

        }
        return "admin";
    }
    @DeleteMapping(value = "/{id}")
    @ResponseBody
    public void deleteProductById(@PathVariable(value ="id") Long id) throws ExecutionException, InterruptedException {
        System.out.println("CO CHAY VAO DELETE "+ id);
        productService.deleteById(id);
    }
    @PostMapping
    @ResponseBody
    public void  insertProduct(@RequestBody ProductDTO product){
        productService.insertProduct(product);
    }
    @PutMapping
    @ResponseBody
    public void updateProduct(@RequestBody ProductDTO product){
       productService.updateProduct(product);
    }
}
