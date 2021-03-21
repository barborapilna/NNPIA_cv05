package cz.upce.eshop.entity;

import javax.persistence.*;
import java.util.Objects;

@Entity
public class Produkt {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column
    private String nazev;
    @Column(columnDefinition = "text")
    private String popis;

    public Long getId() { return id; }

    public void setId(Long id) { this.id = id; }

    public String getNazev() { return nazev; }

    public void setNazev(String nazev) { this.nazev = nazev; }

    public String getPopis() { return popis; }

    public void setPopis(String popis) { this.popis = popis; }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Produkt produkt = (Produkt) o;
        return Objects.equals(id, produkt.id) &&
                Objects.equals(nazev, produkt.nazev);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, nazev);
    }
}
