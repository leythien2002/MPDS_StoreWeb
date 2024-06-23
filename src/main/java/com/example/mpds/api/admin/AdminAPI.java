package com.example.mpds.api.admin;

import com.example.mpds.model.TotalProductByType;
import com.example.mpds.model.TotalStatusInvoice;
import com.example.mpds.services.impl.InvoiceService;
import com.example.mpds.services.impl.ProductService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.thymeleaf.util.StringUtils;

import java.text.ParseException;
import java.time.LocalDate;
import java.time.Month;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.List;
import java.util.Map;

@Controller
@AllArgsConstructor
public class AdminAPI {

    private final ProductService productService;
    private final InvoiceService invoiceService;
//    @GetMapping(value = "/admin")
//    public String adminPage(){
//        return "admin";
//    }

    @GetMapping("/admin")
    public String getStatistics(@RequestParam(required = false) String startDate, @RequestParam(required = false) String endDate, Model model) throws ParseException {
        // Mock data, replace with real data fetching logic
        Date start =null;
        Date end=null;
        if(! StringUtils.isEmpty(startDate)&& !StringUtils.isEmpty(endDate)) {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
            LocalDate startDateTemp = LocalDate.parse(startDate, formatter);

            // Cộng thêm 1 ngày cho startDate
            LocalDate endDateTemp = LocalDate.parse(endDate, formatter).plusDays(1);


             start = Date.from(startDateTemp.atStartOfDay(ZoneId.systemDefault()).toInstant());
             end = Date.from(endDateTemp.atStartOfDay(ZoneId.systemDefault()).toInstant());
        }
        model.addAttribute("totalProducts", productService.totalProduct());
        model.addAttribute("totalInvoices", invoiceService.totalInvoice(start,end));
        model.addAttribute("totalRevenue", invoiceService.totalRevenue(start,end));

        TotalStatusInvoice total=invoiceService.getTotalStatusInvoice(start,end);
        List<Map<String, Object>> invoiceStatusData = List.of(
                Map.of("status", "Paid", "count", total.getPaidStatus()),
                Map.of("status", "Pending", "count", total.getPendingStatus()),
                Map.of("status", "Canceled", "count", total.getCancelledStatus())
        );
        model.addAttribute("invoiceStatusData", invoiceStatusData);

        List<Map<String, Object>> revenueByDayData = List.of(
                Map.of("day", "January", "revenue", invoiceService.getInvoicesForJulyAndAugust(2024, Month.JANUARY)),
                Map.of("day", "February", "revenue", invoiceService.getInvoicesForJulyAndAugust(2024, Month.FEBRUARY)),
                Map.of("day", "March", "revenue", invoiceService.getInvoicesForJulyAndAugust(2024, Month.MARCH)),
                Map.of("day", "April", "revenue", invoiceService.getInvoicesForJulyAndAugust(2024, Month.APRIL)),
                Map.of("day", "May", "revenue", invoiceService.getInvoicesForJulyAndAugust(2024, Month.MAY)),
                Map.of("day", "June", "revenue", invoiceService.getInvoicesForJulyAndAugust(2024, Month.JUNE)),
                Map.of("day", "July", "revenue", invoiceService.getInvoicesForJulyAndAugust(2024, Month.JULY)),
                Map.of("day", "August", "revenue", invoiceService.getInvoicesForJulyAndAugust(2024, Month.AUGUST)),
                Map.of("day", "November", "revenue", invoiceService.getInvoicesForJulyAndAugust(2024, Month.NOVEMBER)),
                Map.of("day", "October", "revenue", invoiceService.getInvoicesForJulyAndAugust(2024, Month.OCTOBER)),
                Map.of("day", "December", "revenue", invoiceService.getInvoicesForJulyAndAugust(2024, Month.DECEMBER))
        );
        model.addAttribute("revenueByDayData", revenueByDayData);

        List<TotalProductByType> products = productService.getTotalProducType();
        model.addAttribute("products", products);

        return "adminStatistic";
    }

}
