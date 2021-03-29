package cz.upce.eshop.dataFactory;

import cz.upce.eshop.entity.Produkt;
import cz.upce.eshop.repository.ProduktRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ProduktTestDataFactory {

    @Autowired
    private ProduktRepo produktRepo;

    public void saveProdukt(String nazevProduktu) {
        Produkt produkt = new Produkt();
        produkt.setNazev(nazevProduktu);
        produktRepo.save(produkt);
    }

    public void saveProdukt(Produkt produkt) {
        if (produkt.getNazev()==null) produkt.setNazev("Testovaci produkt");
        if (produkt.getPopis()==null) produkt.setPopis("Testovaci popis");
        produktRepo.save(produkt);
    }
}
