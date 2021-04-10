package cz.upce.eshop.repository;

import cz.upce.eshop.entity.Uzivatel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UzivatelRepository extends JpaRepository<Uzivatel, Long> {

    Uzivatel getUzivatelByEmail(String email);
}
