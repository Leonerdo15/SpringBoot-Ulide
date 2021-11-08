package com.iade.ulide.models;

import javax.persistence.*;

@Entity
@Table(name = "tags")
public class Tag {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "tg_id", nullable = false)
    private Integer id;

    @Column(name = "tg_name", nullable = false, length = 30)
    private String tgName;

    @Column(name = "tg_tt_id", nullable = false)
    private Integer tgTtId;

    public Integer getTgTtId() {
        return tgTtId;
    }

    public void setTgTtId(Integer tgTtId) {
        this.tgTtId = tgTtId;
    }

    public String getTgName() {
        return tgName;
    }

    public void setTgName(String tgName) {
        this.tgName = tgName;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
}
