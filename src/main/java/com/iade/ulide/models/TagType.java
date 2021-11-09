package com.iade.ulide.models;

import javax.persistence.*;

@Entity
@Table(name = "tag_types")
public class TagType {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "tt_id", nullable = false)
    private Integer id;

    @Column(name = "tt_name", nullable = false, length = 30)
    private String ttName;

    public String getTtName() {
        return ttName;
    }

    public void setTtName(String ttName) {
        this.ttName = ttName;
    }

    public Integer getId() {
        return id;
    }
}
