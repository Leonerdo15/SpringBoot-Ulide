package com.iade.ulide.models;

import javax.persistence.*;

@Entity
@Table(name = "achievements")
public class Achievement {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ac_id", nullable = false)
    private Integer id;

    @Column(name = "ac_name", length = 30)
    private String acName;

    public String getAcName() {
        return acName;
    }

    public void setAcName(String acName) {
        this.acName = acName;
    }

    public Integer getId() {
        return id;
    }

}
