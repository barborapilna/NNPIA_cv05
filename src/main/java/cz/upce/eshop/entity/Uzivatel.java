package cz.upce.eshop.entity;

import javax.persistence.*;

@Entity
public class Uzivatel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column
    private String jmeno;
    @Column
    private String prijmeni;
//    @Column(unique = true)
    @Column
    private String email;
    @Column
    private String heslo;
//    @OneToMany(mappedBy = "id")
//    private Set<Kosik> kosik;

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public String getJmeno() {
        return jmeno;
    }

    public void setJmeno(String jmeno) {
        this.jmeno = jmeno;
    }

    public String getPrijmeni() {
        return prijmeni;
    }

    public void setPrijmeni(String prijmeni) {
        this.prijmeni = prijmeni;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getHeslo() {
        return heslo;
    }

    public void setHeslo(String heslo) {
        this.heslo = heslo;
    }

//    public void setKosik(Set<Kosik> kosik) { this.kosik = kosik; }

//    public Set<Kosik> getKosik() { return kosik; }
}
