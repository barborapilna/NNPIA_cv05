package cz.upce.eshop;

import cz.upce.eshop.entity.Kosik;
import cz.upce.eshop.entity.Produkt;
import cz.upce.eshop.entity.Uzivatel;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import cz.upce.eshop.repository.KosikRepo;
import cz.upce.eshop.repository.ProduktRepo;
import cz.upce.eshop.repository.UzivatelRepo;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@RunWith(SpringRunner.class)
@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class KosikTest {

    @Autowired
    private KosikRepo kosikRepo;
    @Autowired
    private ProduktRepo produktRepo;
    @Autowired
    private UzivatelRepo uzivatelRepo;

    @Test
    void saveNakup() {
        Uzivatel uzivatel = new Uzivatel();
        uzivatel.setJmeno("Karel");
        uzivatel.setPrijmeni("Novak");
        uzivatel.setEmail("karel.n@seznam.cz");
        uzivatel.setHeslo("heslo");
        uzivatel.setAdresa("Listopadu 123");
        uzivatelRepo.save(uzivatel);

        Produkt produkt = new Produkt();
        produkt.setMnozstvi(3);
        produkt.setPlatnost(true);
        produktRepo.save(produkt);

        Kosik kosik = new Kosik();
        kosik.setStav(true);
        kosik.setObjednavka(10);
        kosik.setNakoupenaPolozka(produkt);
        kosik.setUzivatel(uzivatel);
        kosikRepo.save(kosik);

        List<Kosik> all = kosikRepo.findAll();
        Assertions.assertTrue(all.size() == 1);
    }

    @Test
    void findNakupViaObjednavka() {
        Uzivatel uzivatel = new Uzivatel();
        uzivatel.setJmeno("Karel");
        uzivatel.setPrijmeni("Novak");
        uzivatel.setEmail("karel.n@seznam.cz");
        uzivatel.setHeslo("heslo");
        uzivatel.setAdresa("Listopadu 123");
        uzivatelRepo.save(uzivatel);

        Produkt produkt = new Produkt();
        produkt.setMnozstvi(3);
        produkt.setPlatnost(true);
        produktRepo.save(produkt);

        Kosik kosik1 = new Kosik();
        kosik1.setStav(true);
        kosik1.setObjednavka(10);
        kosik1.setNakoupenaPolozka(produkt);
        kosik1.setUzivatel(uzivatel);
        kosikRepo.save(kosik1);

        Kosik kosik2 = new Kosik();
        kosik2.setStav(true);
        kosik2.setObjednavka(20);
        kosik2.setNakoupenaPolozka(produkt);
        kosik2.setUzivatel(uzivatel);
        kosikRepo.save(kosik2);

        Kosik kosik3 = new Kosik();
        kosik3.setStav(true);
        kosik3.setObjednavka(30);
        kosik3.setNakoupenaPolozka(produkt);
        kosik3.setUzivatel(uzivatel);
        kosikRepo.save(kosik3);

        Kosik mujKosik = kosikRepo.getKosikByObjednavka(20);
        Assertions.assertTrue(mujKosik.getObjednavka() == 20);
    }

    @Test
    void vyprazdniKosik() {
        Uzivatel uzivatel = new Uzivatel();
        uzivatel.setJmeno("Karel");
        uzivatel.setPrijmeni("Novak");
        uzivatel.setEmail("karel.n@seznam.cz");
        uzivatel.setHeslo("heslo");
        uzivatel.setAdresa("Listopadu 123");
        uzivatelRepo.save(uzivatel);

        Produkt produkt = new Produkt();
        produkt.setMnozstvi(3);
        produkt.setPlatnost(true);
        produktRepo.save(produkt);

        Kosik kosik1 = new Kosik();
        kosik1.setStav(true);
        kosik1.setObjednavka(10);
        kosik1.setNakoupenaPolozka(produkt);
        kosik1.setUzivatel(uzivatel);
        kosikRepo.save(kosik1);

        Kosik kosik2 = new Kosik();
        kosik2.setStav(true);
        kosik2.setObjednavka(20);
        kosik2.setNakoupenaPolozka(produkt);
        kosik2.setUzivatel(uzivatel);
        kosikRepo.save(kosik2);

        Kosik kosik3 = new Kosik();
        kosik3.setStav(true);
        kosik3.setObjednavka(30);
        kosik3.setNakoupenaPolozka(produkt);
        kosik3.setUzivatel(uzivatel);
        kosikRepo.save(kosik3);

        Kosik removed = kosikRepo.getKosikByObjednavka(20);
        kosikRepo.removeNakupById(removed.getId());
        List<Kosik> all = kosikRepo.findAll();
        Assertions.assertTrue(all.size() == 2);
    }
}
