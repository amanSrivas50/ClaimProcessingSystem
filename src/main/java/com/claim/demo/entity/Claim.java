package com.claim.demo.entity;


import jakarta.persistence.*;
import lombok.Data;

import java.util.Date;


@Entity
@Data
@Table(name = "claims")
public class Claim {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long claimId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "userId", referencedColumnName = "userId")
    private User user;

    @Column
    private Date claimDate;

    @Column
    private Double claimAmount;
    
    @Column
    private String emailId;

    @Column
    private String claimType;

    @Column
    private String claimStatus;

    @Column
    private Date lastUpdated;


}

