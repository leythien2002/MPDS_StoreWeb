package com.example.mpds.api.admin;

import com.example.mpds.dto.CategoryDTO;
import com.example.mpds.dto.ProductDTO;
import com.example.mpds.mapper.CategoryMapper;
import com.example.mpds.mapper.ProductMapper;
import com.example.mpds.services.impl.CategoryService;
import com.example.mpds.services.impl.ProductService;
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
@RequestMapping("/brand")
@AllArgsConstructor
public class AdminBrand {
    @Autowired
    private CategoryService categoryService;

    @Autowired
    private CategoryMapper categoryMapper;

    @GetMapping
    public String getCategory(Model model) throws ExecutionException, InterruptedException {
        List<CategoryDTO> tmp = categoryService.getAll();
        model.addAttribute("listCategory", tmp);
        return "adminbrand";
    }

    //    @DeleteMapping(value = "/{id}")
//    @ResponseBody
//    public void deleteCategoryById(@PathVariable(value ="id") Long id) throws ExecutionException, InterruptedException {
//        System.out.println("CO CHAY VAO DELETE "+ id);
//        categoryService.deleteById(id);
//
//    }
    @DeleteMapping(value = "/{id}")
    @ResponseBody
    public ResponseEntity<String> deleteCategoryById(@PathVariable(value = "id") Long id) {
        String message = categoryService.deleteById(id);
        return ResponseEntity.ok(message);
    }

    @PostMapping
    @ResponseBody
    public void insertCategory(@RequestBody CategoryDTO categoryDTO) {
        categoryService.insertCategory(categoryDTO);
    }

    @PutMapping
    @ResponseBody
    public void updateCategory(@RequestBody CategoryDTO categoryDTO) {
        categoryService.updateCategory(categoryDTO);
    }
}
