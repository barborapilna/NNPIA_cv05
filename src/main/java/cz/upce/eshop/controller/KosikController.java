package cz.upce.eshop.controller;

import cz.upce.eshop.service.KosikService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class KosikController {

    private final KosikService kosikService;

    public KosikController(KosikService kosikService) {
        this.kosikService = kosikService;
    }

    @GetMapping("/kosik-add/{id}")
    public String pridejDoKosiku(@PathVariable Long id, Model model) {
        kosikService.pridej(id);
        return "redirect:/kosik";
    }

    @GetMapping("/kosik-remove/{id}")
    public String smazZKosiku(@PathVariable Long id, Model model) {
        kosikService.smaz(id);
        return "redirect:/kosik";
    }

    @GetMapping("/kosik")
    public String zobrazKosik(Model model) {
        model.addAttribute("kosik", kosikService.getKosik());
        return "kosik";
    }
}
