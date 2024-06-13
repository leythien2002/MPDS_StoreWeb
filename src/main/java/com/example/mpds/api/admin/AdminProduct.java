package com.example.mpds.api.admin;

import com.example.mpds.dto.ProductDTO;
import com.example.mpds.entity.ProductEntity;
import com.example.mpds.entity.UserEntity;
import com.example.mpds.mapper.ProductMapper;
import com.example.mpds.services.impl.ProductService;
import com.example.mpds.services.impl.UserService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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

//        System.out.println("CO CHAY VAO GET LIST Product ");
//        for (ProductDTO prod: tmp) {
//            System.out.println(prod.getImage1());
//            System.out.println(prod.getName());
//
//        }
        return "adminproduct";
    }
//    @DeleteMapping(value = "/{id}")
//    @ResponseBody
//    public void deleteProductById(@PathVariable(value ="id") Long id) throws ExecutionException, InterruptedException {
//        System.out.println("CO CHAY VAO DELETE "+ id);
//        productService.deleteById(id);
//
//    }
    @DeleteMapping(value = "/{id}")
    @ResponseBody
    public ResponseEntity<String> deleteProductById(@PathVariable(value = "id") Long id) {
        String message = productService.deleteById(id);
        return ResponseEntity.ok(message);
    }
    @PostMapping
    @ResponseBody
    public ResponseEntity<String>  insertProduct(@RequestBody ProductDTO product){
        String message =   productService.insertProduct(product);
        return ResponseEntity.ok(message);
    }
    @PutMapping
    @ResponseBody
    public ResponseEntity<String> updateProduct(@RequestBody ProductDTO product){
        String message =  productService.updateProduct(product);
        return ResponseEntity.ok(message);
    }
}
