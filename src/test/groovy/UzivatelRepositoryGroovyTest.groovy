package cz.upce.eshop

import cz.upce.eshop.dataFactory.Creator;
import cz.upce.eshop.entity.Uzivatel;
import cz.upce.eshop.repository.UzivatelRepository;
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
@Import([Creator.class])
class UzivatelRepositoryGroovyTest {

    @Autowired
    private UzivatelRepository uzivatelRepo;

    @Autowired
    private Creator creator;

    @Test
    void saveUzivatele() {
        Uzivatel testUzivatel = new Uzivatel(email: "tom.maly@seznam.cz")
        creator.save(testUzivatel);

        def nacteno = uzivatelRepo.findById(testUzivatel.getId()).get();
        Assertions.assertThat(nacteno.getJmeno()).isEqualTo("Test jmeno");
        Assertions.assertThat(nacteno.getPrijmeni()).isEqualTo("Test prijmeni");
        Assertions.assertThat(nacteno.getHeslo()).isEqualTo("Test heslo");
    }

    @Test
    void getUzivateleByEmail() {
        Uzivatel testUzivatel = new Uzivatel(email: "tom.maly@seznam.cz")
        creator.save(testUzivatel);

        Uzivatel nacteno = uzivatelRepo.getUzivatelByEmail("tom.maly@seznam.cz");
        Assertions.assertThat(nacteno.getJmeno()).isEqualTo("Test jmeno");
        Assertions.assertThat(nacteno.getPrijmeni()).isEqualTo("Test prijmeni");
        Assertions.assertThat(nacteno.getHeslo()).isEqualTo("Test heslo");
    }
}
