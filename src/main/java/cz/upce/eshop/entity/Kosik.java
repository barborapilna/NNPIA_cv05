package cz.upce.eshop.entity;

import javax.persistence.*;

@Entity
public class Kosik {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    private Objednavka objednavka;
    @ManyToOne
    private Uzivatel uzivatel;
    @ManyToOne
    private Produkt produkt;
    private Integer pocetKusu;

    public Objednavka getObjednavka() { return objednavka; }

    public void setObjednavka(Objednavka objednavka) { this.objednavka = objednavka; }

    public Produkt getProdukt() { return produkt; }

    public void setProdukt(Produkt produkt) { this.produkt = produkt; }

    public Integer getPocetKusu() { return pocetKusu; }

    public void setPocetKusu(Integer pocetKusu) { this.pocetKusu = pocetKusu; }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public Uzivatel getUzivatel() {
        return uzivatel;
    }

    public void setUzivatel(Uzivatel uzivatel) {
        this.uzivatel = uzivatel;
    }

    public Produkt getNakoupenaPolozka() {
        return produkt;
    }

    public void setNakoupenaPolozka(Produkt nakoupenaPolozka) {
        this.produkt = nakoupenaPolozka;
    }

}
