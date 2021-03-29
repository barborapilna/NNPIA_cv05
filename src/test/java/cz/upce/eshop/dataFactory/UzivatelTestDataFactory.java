package cz.upce.eshop.dataFactory;

import cz.upce.eshop.entity.Uzivatel;
import cz.upce.eshop.repository.UzivatelRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class UzivatelTestDataFactory {

    @Autowired
    private UzivatelRepo uzivatelRepo;

    public void saveUzivatel(String email) {
        Uzivatel uzivatel = new Uzivatel();
        uzivatel.setEmail(email);
        uzivatel.setHeslo("heslo123");
        uzivatelRepo.save(uzivatel);
    }

    public void saveUzivatel(Uzivatel uzivatel) {
        if (uzivatel.getJmeno() == null) uzivatel.setJmeno("Tomas");
        if (uzivatel.getPrijmeni() == null) uzivatel.setPrijmeni("Maly");
        if (uzivatel.getHeslo() == null) uzivatel.setHeslo("heslo123");
        uzivatelRepo.save(uzivatel);
    }
}
