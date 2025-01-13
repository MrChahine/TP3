package org.example.tp3.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class AddressFormController {

    @GetMapping("/address")
    public String showAdresseForm() {
        return "addressForm";
    }
}
