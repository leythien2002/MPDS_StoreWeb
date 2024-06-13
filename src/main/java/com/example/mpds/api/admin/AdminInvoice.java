package com.example.mpds.api.admin;

import com.example.mpds.dto.InvoiceDTO;
import com.example.mpds.dto.ProductDTO;
import com.example.mpds.mapper.ProductMapper;
import com.example.mpds.services.impl.InvoiceService;
import com.example.mpds.services.impl.ProductService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.concurrent.ExecutionException;

@Controller
@RequestMapping("/invoice")
@AllArgsConstructor
public class AdminInvoice {
    @Autowired
    private InvoiceService invoiceService;

    @Autowired
    private ProductMapper productMapper;

    @GetMapping
    public String getInvoice(Model model) throws ExecutionException, InterruptedException {
        List<InvoiceDTO> tmp=invoiceService.findAll();
        model.addAttribute("listInvoice",tmp);
        return "admininvoice";
    }

    @PutMapping
    @ResponseBody
    public void updateProduct(@RequestBody InvoiceDTO invoiceDTO){
        invoiceService.updateInvoice(invoiceDTO);
    }
}
