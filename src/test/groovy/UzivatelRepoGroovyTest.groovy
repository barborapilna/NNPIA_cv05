package cz.upce.eshop;

import cz.upce.eshop.dataFactory.UzivatelTestDataFactory;
import cz.upce.eshop.entity.Uzivatel;
import cz.upce.eshop.repository.UzivatelRepo;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@Import(UzivatelTestDataFactory.class)
class UzivatelRepoGroovyTest {

    @Autowired
    private UzivatelRepo uzivatelRepo;

    @Autowired
    private UzivatelTestDataFactory uzivatelTestDataFactory;

    @Test
    void saveUzivatele() {
        Uzivatel testUzivatel = new Uzivatel(email: "tom.maly@seznam.cz")
        uzivatelTestDataFactory.saveUzivatel(testUzivatel);

        def nacteno = uzivatelRepo.findById(testUzivatel.getId()).get();
        Assertions.assertThat(nacteno.getJmeno()).isEqualTo("Tomas");
        Assertions.assertThat(nacteno.getPrijmeni()).isEqualTo("Maly");
        Assertions.assertThat(nacteno.getHeslo()).isEqualTo("heslo123");
    }

    @Test
    void getUzivateleByEmail() {
        Uzivatel testUzivatel = new Uzivatel(email: "tom.maly@seznam.cz")
        uzivatelTestDataFactory.saveUzivatel(testUzivatel);

        Uzivatel jeden = uzivatelRepo.getUzivatelByEmail("tom.maly@seznam.cz");
        Assertions.assertThat(jeden.getJmeno()).isEqualTo("Tomas");
        Assertions.assertThat(jeden.getPrijmeni()).isEqualTo("Maly");
        Assertions.assertThat(jeden.getHeslo()).isEqualTo("heslo123");
    }
}
