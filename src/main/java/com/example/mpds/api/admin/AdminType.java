package com.example.mpds.api.admin;

import com.example.mpds.dto.StrapDTO;
import com.example.mpds.dto.TypeDTO;
import com.example.mpds.services.impl.StrapService;
import com.example.mpds.services.impl.TypeService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.concurrent.ExecutionException;

@Controller
@RequestMapping("/type")
@AllArgsConstructor
public class AdminType {
    @Autowired
    private TypeService typeService;


    @GetMapping
    public String get(Model model) throws ExecutionException, InterruptedException {
        List<TypeDTO> tmp = typeService.getAll();
        model.addAttribute("listType", tmp);
        return "admintype";
    }
    
    @DeleteMapping(value = "/{id}")
    @ResponseBody
    public ResponseEntity<String> deleteStrapById(@PathVariable(value = "id") Long id) {
        String message = typeService.deleteById(id);
        return ResponseEntity.ok(message);
    }

    @PostMapping
    @ResponseBody
    public void insert(@RequestBody TypeDTO typeDTO) {
        typeService.insertType(typeDTO);
    }

    @PutMapping
    @ResponseBody
    public void update(@RequestBody TypeDTO typeDTO) {
        typeService.updateType(typeDTO);
    }
}
