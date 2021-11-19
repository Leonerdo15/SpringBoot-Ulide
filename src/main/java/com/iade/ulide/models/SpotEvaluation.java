package com.iade.ulide.models;

import javax.persistence.*;

@Entity
@Table(name = "spot_evaluations")
public class SpotEvaluation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "se_id", nullable = false)
    private Integer id;

    @Column(name = "se_rate")
    private Integer seRate;

    @Column(name = "se_comment", length = 400)
    private String seComment;

    @ManyToOne
    @JoinColumn(name = "se_us_id", nullable = false)
    private User seUs;

    @ManyToOne
    @JoinColumn(name = "se_sp_id", nullable = false)
    private Spot seSp;

    public Spot getSeSp() {
        return seSp;
    }

    public void setSeSp(Spot seSp) {
        this.seSp = seSp;
    }

    public User getSeUs() {
        return seUs;
    }

    public void setSeUs(User seUs) {
        this.seUs = seUs;
    }

    public String getSeComment() {
        return seComment;
    }

    public void setSeComment(String seComment) {
        this.seComment = seComment;
    }

    public Integer getSeRate() {
        return seRate;
    }

    public void setSeRate(Integer seRate) {
        this.seRate = seRate;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
}
