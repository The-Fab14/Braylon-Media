package com.dev10.BraylonMedia.entities;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.validation.constraints.Size;
import lombok.Data;

@Entity(name = "crm_order")
@Data
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int orderId;

    @Column(nullable = false)
    private LocalDate dateSubmitted;

    @Column
    private LocalDate dateInstalled;

    @Column
    private LocalDate dateCompleted;

    @Column(nullable = false)
    private BigDecimal orderTotal;

    @Column(nullable = false)
    @Size(max = 256)
    private String orderStatus;

    @Column
    @Size(max = 5000)
    private String orderComments;

    @ManyToOne
    @JoinColumn(name = "client_id", nullable = false)
    private Client client;

    @ManyToMany
    @JoinTable(name = "crm_order_product",
            joinColumns = {
                @JoinColumn(name = "order_id")},
            inverseJoinColumns = {
                @JoinColumn(name = "product_id")})
    private List<Product> products;
}
