package cz.upce.eshop.controller;

import cz.upce.eshop.dto.CrudProductDto;
import cz.upce.eshop.entity.Produkt;
import cz.upce.eshop.repository.ProduktRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class ProduktController {

    @Autowired
    private ProduktRepository produktRepository;

    @ExceptionHandler(RuntimeException.class)
    public String handleException() {
        return "error";
    }

    @GetMapping("/")
    public String zobrazProdukty(Model model) {
        model.addAttribute("produkty", produktRepository.findAll());
        return "produkt-list";
    }

    @GetMapping("/produkt-detail/{id}")
    public String zobrazDetailProduktu(@PathVariable(required = false) Long id, Model model) {
        model.addAttribute("produkt", produktRepository.findById(id).get());
        return "produkt-detail";
    }

    @GetMapping(value = {"/produkt-form", "/produkt-form/{id}"})
    public String zobrazProduktForm(@PathVariable(required = false) Long id, Model model) {
        if (id != null) {
            Produkt zobrazenyProdukt = produktRepository.findById(id).orElse(new Produkt());
            CrudProductDto dto = new CrudProductDto();
            dto.setId(zobrazenyProdukt.getId());
            dto.setNazev(zobrazenyProdukt.getNazev());
            dto.setPopis(zobrazenyProdukt.getPopis());
            model.addAttribute("produkt", dto);
        } else {
            model.addAttribute("produkt", new CrudProductDto());
        }
        return "produkt-form";
    }

    @PostMapping("/produkt-form-zpracuj")
    public String productFormProcess(CrudProductDto addOrEditProductDto) {
        Produkt produkt = new Produkt();
        produkt.setId(addOrEditProductDto.getId());
        produkt.setNazev(addOrEditProductDto.getNazev());
        produkt.setPopis(addOrEditProductDto.getPopis());
        produktRepository.save(produkt);
        return "redirect:/";
    }
}
