package com.example.mpds.api.admin;

import com.example.mpds.dto.CategoryDTO;
import com.example.mpds.dto.StrapDTO;
import com.example.mpds.mapper.CategoryMapper;
import com.example.mpds.mapper.StrapMapper;
import com.example.mpds.services.impl.CategoryService;
import com.example.mpds.services.impl.StrapService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.concurrent.ExecutionException;

@Controller
@RequestMapping("/strap")
@AllArgsConstructor
public class AdminStrap {
    @Autowired
    private StrapService strapService;


    @GetMapping
    public String getStrap(Model model) throws ExecutionException, InterruptedException {
        List<StrapDTO> tmp = strapService.getAll();
        model.addAttribute("listStrap", tmp);
        return "adminstrap";
    }
    
    @DeleteMapping(value = "/{id}")
    @ResponseBody
    public ResponseEntity<String> deleteStrapById(@PathVariable(value = "id") Long id) {
        String message = strapService.deleteById(id);
        return ResponseEntity.ok(message);
    }

    @PostMapping
    @ResponseBody
    public void insertCategory(@RequestBody StrapDTO strapDTO) {
        strapService.insertStrap(strapDTO);
    }

    @PutMapping
    @ResponseBody
    public void updateCategory(@RequestBody StrapDTO strapDTO) {
        strapService.updateStrap(strapDTO);
    }
}
