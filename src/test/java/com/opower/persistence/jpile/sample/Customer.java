package com.opower.persistence.jpile.sample;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import java.util.Date;
import java.util.List;

/**
 * A sample pojo object for testing
 *
 * @author amir.raminfar
 */
@Entity
@Table
public class Customer {
    /**
     * The type of customer.
     */
    public enum Type {
        RESIDENTIAL,
        SMALL_BUSINESS
    }

    private Long id;
    private Contact contact;
    private List<Product> products;
    private Date lastSeenOn;
    private Type type;


    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @OneToMany(mappedBy = "customer", cascade = CascadeType.ALL)
    public List<Product> getProducts() {
        return products;
    }

    public void setProducts(List<Product> products) {
        for (Product product : products) {
            product.setCustomer(this);
        }
        this.products = products;
    }

    @OneToOne(mappedBy = "customer", cascade = CascadeType.ALL)
    @PrimaryKeyJoinColumn
    public Contact getContact() {
        return contact;
    }

    public void setContact(Contact contact) {
        contact.setCustomer(this);
        this.contact = contact;
    }

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "last_seen_on")
    public Date getLastSeenOn() {
        return lastSeenOn;
    }

    public void setLastSeenOn(Date lastSeenOn) {
        this.lastSeenOn = lastSeenOn;
    }

    @Column(name = "type")
    public Type getType() {
        return type;
    }

    public void setType(Type type) {
        this.type = type;
    }
}
