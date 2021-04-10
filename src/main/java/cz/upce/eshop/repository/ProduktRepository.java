package cz.upce.eshop.repository;

import cz.upce.eshop.entity.Produkt;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProduktRepository extends JpaRepository<Produkt,Long> {

}
