package cz.upce.eshop;

import cz.upce.eshop.dataFactory.ProduktTestDataFactory;
import cz.upce.eshop.entity.Produkt;
import cz.upce.eshop.repository.ProduktRepo;
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
@Import(ProduktTestDataFactory.class)
class ProduktRepoGroovyTest {

    @Autowired
    private ProduktRepo produktRepo;
    
    @Autowired
    private ProduktTestDataFactory produktTestDataFactory;

    @Test
    void saveNakup() {
        Produkt testProdukt = new Produkt(nazev: "MujProdukt")
        produktTestDataFactory.saveProdukt(testProdukt);
//        List<Produkt> vsechnyProdukty = produktRepo.findAll();
//        Assertions.assertThat(vsechnyProdukty.size()).isEqualTo(1);

        def nacteno = produktRepo.findById(testProdukt.getId()).get();
        Assertions.assertThat(nacteno.getNazev()).isEqualTo("MujProdukt");
        Assertions.assertThat(nacteno.getPopis()).isEqualTo("Testovaci popis");
    }
}
