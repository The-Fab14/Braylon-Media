package com.dev10.BraylonMedia.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import lombok.Data;

@Entity(name = "crm_user")
@Data
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int userId;

    @Column(nullable = false)
    @NotBlank(message = "First name must not be empty.")
    @Size(max = 256)
    private String firstName;

    @Column(nullable = false)
    @NotBlank(message = "Last name must not be empty.")
    @Size(max = 256)
    private String lastName;

    @Column(nullable = false)
    @NotBlank(message = "Email Address must not be empty.")
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