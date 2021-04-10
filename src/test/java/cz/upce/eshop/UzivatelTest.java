package cz.upce.eshop;

import cz.upce.eshop.entity.Uzivatel;
import cz.upce.eshop.repository.UzivatelRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@RunWith(SpringRunner.class)
@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class UzivatelTest {

    @Autowired
    private UzivatelRepository uzivatelRepository;

    @Test
    void saveUzivatel() {
        Uzivatel uzivatel = new Uzivatel();
        uzivatel.setJmeno("Karel");
        uzivatel.setPrijmeni("Novak");
        uzivatel.setEmail("karel.n@seznam.cz");
        uzivatel.setHeslo("heslo");
        uzivatelRepository.save(uzivatel);
        List<Uzivatel> all = uzivatelRepository.findAll();
        Assertions.assertTrue(all.size() == 1);
    }

    @Test
    void getUzivatelByEmail() {
        Uzivatel uzivatel1 = new Uzivatel();
        uzivatel1.setJmeno("Karel");
        uzivatel1.setPrijmeni("Novak");
        uzivatel1.setEmail("karel.n@seznam.cz");
        uzivatel1.setHeslo("heslo");
        uzivatelRepository.save(uzivatel1);

        Uzivatel uzivatel2 = new Uzivatel();
        uzivatel2.setJmeno("Petr");
        uzivatel2.setPrijmeni("Smith");
        uzivatel2.setEmail("petr.s@seznam.cz");
        uzivatel2.setHeslo("mojeHeslo");
        uzivatelRepository.save(uzivatel2);

        Uzivatel uzivatel3 = new Uzivatel();
        uzivatel3.setJmeno("Lidie");
        uzivatel3.setPrijmeni("Klidna");
        uzivatel3.setEmail("lidie.k@seznam.cz");
        uzivatel3.setHeslo("heslojeheslo");
        uzivatelRepository.save(uzivatel3);

        Uzivatel result = uzivatelRepository.getUzivatelByEmail("petr.s@seznam.cz");
        Assertions.assertTrue(result.getJmeno() == "Petr");
    }

//    @Test
//    void smazUzivatele() {
//        Uzivatel uzivatel1 = new Uzivatel();
//        uzivatel1.setJmeno("Karel");
//        uzivatel1.setPrijmeni("Novak");
//        uzivatel1.setEmail("karel.n@seznam.cz");
//        uzivatel1.setHeslo("heslo");
//        uzivatel1.setAdresa("Listopadu 123");
//        uzivatelRepository.save(uzivatel1);
//
//        Uzivatel uzivatel2 = new Uzivatel();
//        uzivatel2.setJmeno("Petr");
//        uzivatel2.setPrijmeni("Smith");
//        uzivatel2.setEmail("petr.s@seznam.cz");
//        uzivatel2.setHeslo("mojeHeslo");
//        uzivatel2.setAdresa("Brezova 13");
//        uzivatelRepository.save(uzivatel2);
//
//        Uzivatel uzivatel3 = new Uzivatel();
//        uzivatel3.setJmeno("Lidie");
//        uzivatel3.setPrijmeni("Klidna");
//        uzivatel3.setEmail("lidie.k@seznam.cz");
//        uzivatel3.setHeslo("heslojeheslo");
//        uzivatel3.setAdresa("Bytova 123");
//        uzivatelRepository.save(uzivatel3);
//
//        Uzivatel result = uzivatelRepository.getUzivatelByEmail("petr.s@seznam.cz");
//        uzivatelRepository.removeUzivatelById(result.getId());
//        Assertions.assertTrue(uzivatelRepository.findAll().size() == 2);
//    }
}
