package org.example.controllers;

import org.example.JsonToFile;
import org.example.entities.Invoice;
import org.example.services.InvoiceService;
import org.example.services.UserService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class InvoiceController {

    InvoiceService invoiceService;
    JsonToFile jsonToFile;

    UserService userService;
    public InvoiceController(InvoiceService invoiceService, UserService userService){
        this.invoiceService = invoiceService;
        this.jsonToFile = new JsonToFile();
        this.userService = userService;
    }

    @PostMapping("/billing")
    public void createInvoices(){
        invoiceService.createInvoices();
        jsonToFile.writeToJson(invoiceService.findAll());
    }

    @GetMapping("/users/{id}/invoices")
    public void getInvoicesForUser(@PathVariable Long id){
       List<Invoice> invoices =  invoiceService.getInvoicesOfUser(id);
       jsonToFile.writeToJson(invoices);
    }
}
