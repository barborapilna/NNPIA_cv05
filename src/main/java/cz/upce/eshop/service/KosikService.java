package cz.upce.eshop.service;

import cz.upce.eshop.entity.Produkt;

import java.util.Map;

public interface KosikService {

    void pridej(Long id);

    void smaz(Long id);

    Map<Produkt,Integer> getKosik();

    void objednat();
}
