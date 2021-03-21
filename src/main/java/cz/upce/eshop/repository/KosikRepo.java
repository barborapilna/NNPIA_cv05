package cz.upce.eshop.repository;

import cz.upce.eshop.entity.Kosik;
import org.springframework.data.jpa.repository.JpaRepository;

public interface KosikRepo extends JpaRepository<Kosik, Long> {

//    Kosik getKosikByObjednavka(Integer objednavka);

}
