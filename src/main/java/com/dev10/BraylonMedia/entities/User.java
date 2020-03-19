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

@Entity(name = "crm_user")
@Data
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int userId;

    @Column(nullable = false)
    @Size(max = 256)
    private String firstName;

    @Column(nullable = false)
    @Size(max = 256)
    private String lastName;

    @Column(nullable = false)
    @Size(max = 256)
    private String emailAddress;

    @Column(nullable = false)
    private String userRole;

    @Column(nullable = false)
    private String userPassword;

    @Column(nullable = false)
    private boolean didPasswordChange;

    @ManyToOne
    @JoinColumn(name = "state_id", nullable = false)
    private State state;

}
