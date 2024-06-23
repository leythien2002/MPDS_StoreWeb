package com.example.mpds.api.admin;

import com.example.mpds.dto.DialSizeDTO;
import com.example.mpds.dto.TypeDTO;
import com.example.mpds.services.impl.DialSizeService;
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
@RequestMapping("/dialsize")
@AllArgsConstructor
public class AdminDialSize {
    @Autowired
    private DialSizeService dialSizeService;


    @GetMapping
    public String get(Model model) throws ExecutionException, InterruptedException {
        List<DialSizeDTO> tmp = dialSizeService.getAll();
        model.addAttribute("listDialSize", tmp);
        return "admindialsize";
    }
    
    @DeleteMapping(value = "/{id}")
    @ResponseBody
    public ResponseEntity<String> deleteById(@PathVariable(value = "id") Long id) {
        String message = dialSizeService.deleteById(id);
        return ResponseEntity.ok(message);
    }

    @PostMapping
    @ResponseBody
    public void insert(@RequestBody DialSizeDTO dialSizeDTO) {
        dialSizeService.insertDialSize(dialSizeDTO);
    }

    @PutMapping
    @ResponseBody
    public void update(@RequestBody DialSizeDTO dialSizeDTO) {
        dialSizeService.updateDialSize(dialSizeDTO);
    }
}
