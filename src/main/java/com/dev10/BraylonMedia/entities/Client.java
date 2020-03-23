package com.dev10.BraylonMedia.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.validation.constraints.Size;
import lombok.Data;

@Entity(name = "crm_client")
@Data
public class Client {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int clientId;

    @Column(nullable = false)
    @Size(max = 256)
    private String contactFirstName;

    @Column(nullable = false)
    @Size(max = 256)
    private String contactLastName;

    @Column(nullable = false)
    @Size(max = 256)
    private String companyName;

    @Column(nullable = false)
    @Size(max = 256)
    private String streetAddress;

    @Column(nullable = false)
    @Size(max = 256)
    private String aptUnit;

    @Column(nullable = false)
    @Size(max = 256)
    private String city;

    @ManyToOne
    @JoinColumn(name = "state_id", nullable = false)
    private State state;

    @Column(nullable = false)
    @Size(max = 5)
    private int zip;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @Column(nullable = false)
    @Size(max = 256)
    private String emailAddress;

    @Column(nullable = false)
    @Size(max = 10)
    private String phoneNumber;
}
