package cz.upce.eshop.entity;

import javax.persistence.*;
import java.util.Set;

@Entity(name = "objednavka_form")
public class Objednavka {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Enumerated(EnumType.STRING)
    @Column(length = 20)
    private StavObjednavky stav;

    @OneToMany(mappedBy = "id")
    private Set<Kosik> kosik;

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public StavObjednavky getStav() {
        return stav;
    }

    public void setStav(StavObjednavky stav) {
        this.stav = stav;
    }

    public Set<Kosik> getKosik() {
        return kosik;
    }

    public void setKosik(Set<Kosik> kosik) { this.kosik = kosik; }

}
