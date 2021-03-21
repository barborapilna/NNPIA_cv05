package cz.upce.eshop.service;

import cz.upce.eshop.entity.Kosik;
import cz.upce.eshop.entity.Objednavka;
import cz.upce.eshop.entity.Produkt;
import cz.upce.eshop.entity.StavObjednavky;
import cz.upce.eshop.repository.KosikRepo;
import cz.upce.eshop.repository.ObjednavkaRepo;
import cz.upce.eshop.repository.ProduktRepo;
import org.springframework.stereotype.Service;
import org.springframework.web.context.annotation.SessionScope;

import java.util.HashMap;
import java.util.Map;
import java.util.NoSuchElementException;

@Service
@SessionScope
public class KosikServiceImpl implements KosikService {
    private Map<Produkt, Integer> kosik;
    private final ProduktRepo produktRepo;
    private final ObjednavkaRepo objednavkaRepo;
    private final KosikRepo kosikRepo;

    public KosikServiceImpl(ProduktRepo produktRepo, ObjednavkaRepo objednavkaRepo, KosikRepo kosikRepo) {
        this.produktRepo = produktRepo;
        this.objednavkaRepo = objednavkaRepo;
        this.kosikRepo = kosikRepo;
        kosik = new HashMap<>();
    }


    @Override
    public void pridej(Long id) {
        Produkt produkt = produktRepo.findById(id).orElseThrow(NoSuchElementException::new);
        if (kosik.containsKey(produkt)) {
            kosik.replace(produkt, kosik.get(produkt) + 1);
        } else {
            kosik.put(produkt, 1);
        }
    }

    @Override
    public void smaz(Long id) {
        Produkt produkt = produktRepo.findById(id).orElseThrow(NoSuchElementException::new);
        if (kosik.containsKey(produkt)) {
            if (kosik.get(produkt) > 1) {
                kosik.replace(produkt, kosik.get(produkt) - 1);
            } else {
                kosik.remove(produkt);
            }
        }
    }

    @Override
    public Map<Produkt, Integer> getKosik() {
        return kosik;
    }

    @Override
    public void objednat() {
        Objednavka objednavka = new Objednavka();
        objednavka.setStav(StavObjednavky.NOVA);
        objednavkaRepo.save(objednavka);
        for (Map.Entry<Produkt, Integer> jedenProdukt : kosik.entrySet()) {
            Kosik kosik = new Kosik();
            kosik.setNakoupenaPolozka(jedenProdukt.getKey());
            kosik.setObjednavka(objednavka);
            kosik.setPocetKusu(jedenProdukt.getValue());
            kosikRepo.save(kosik);
        }
        kosik.clear();
    }
}
