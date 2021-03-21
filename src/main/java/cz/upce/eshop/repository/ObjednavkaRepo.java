package cz.upce.eshop.repository;

import cz.upce.eshop.entity.Objednavka;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ObjednavkaRepo extends JpaRepository<Objednavka,Long> {
}
