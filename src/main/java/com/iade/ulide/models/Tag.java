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

    @ManyToOne(optional = false)
    @JoinColumn(name = "tg_tt_id", nullable = false)
    private TagType tgTt;

    public TagType getTgTt() {
        return tgTt;
    }

    public void setTgTt(TagType tgTt) {
        this.tgTt = tgTt;
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
