package com.iade.ulide.models;

import javax.persistence.*;

@Entity
@Table(name = "routes")
public class Route {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "rt_id")
    private Integer id;

    @Column(name = "rt_name")
    private String rtName;

    @Column(name = "rt_bio")
    private String rtBio;

    @Column(name = "rt_dist")
    private Double rtDist;

    public Double getRtDist() {
        return rtDist;
    }

    public void setRtDist(Double rtDist) {
        this.rtDist = rtDist;
    }

    public String getRtBio() {
        return rtBio;
    }

    public void setRtBio(String rtBio) {
        this.rtBio = rtBio;
    }

    public String getRtName() {
        return rtName;
    }

    public void setRtName(String rtName) {
        this.rtName = rtName;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
}
