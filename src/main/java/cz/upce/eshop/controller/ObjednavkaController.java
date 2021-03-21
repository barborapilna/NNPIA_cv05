package cz.upce.eshop.controller;

import cz.upce.eshop.service.KosikService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ObjednavkaController {
    private final KosikService kosikService;

    public ObjednavkaController(KosikService kosikService) {
        this.kosikService = kosikService;
    }

    @GetMapping("/objednat")
    public String checkout(Model model) {
        kosikService.objednat();
        return "objednat";
    }
}
