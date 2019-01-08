package com.codegym.model;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "provinces")
public class Province {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String name;
    private Long zipcode;
    @OneToMany(mappedBy = "province")
    private Set <Customer> customers;

    public Province() {
    }

    public Province(String name) {
        this.name = name;
    }

    public Long getZipcode() {
        return zipcode;
    }

    public void setZipcode(Long zipcode) {
        this.zipcode = zipcode;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Set <Customer> getCustomers() {
        return customers;
    }

    public void setCustomers(Set <Customer> customers) {
        this.customers = customers;
    }
}
