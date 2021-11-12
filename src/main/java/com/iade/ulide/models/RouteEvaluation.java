package com.iade.ulide.models;

import javax.persistence.*;

@Entity
@Table(name = "route_evaluations")
public class RouteEvaluation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "re_id", nullable = false)
    private Integer id;

    @Column(name = "re_rate")
    private Integer reRate;

    @Column(name = "re_comment", length = 400)
    private String reComment;

    @ManyToOne(optional = false)
    @JoinColumn(name = "re_us_id", nullable = false)
    private User reUs;

    @ManyToOne(optional = false)
    @JoinColumn(name = "re_rt_id", nullable = false)
    private Route reRt;

    public Route getReRt() {
        return reRt;
    }

    public void setReRt(Route reRt) {
        this.reRt = reRt;
    }

    public User getReUs() {
        return reUs;
    }

    public void setReUs(User reUs) {
        this.reUs = reUs;
    }

    public String getReComment() {
        return reComment;
    }

    public void setReComment(String reComment) {
        this.reComment = reComment;
    }

    public Integer getReRate() {
        return reRate;
    }

    public void setReRate(Integer reRate) {
        this.reRate = reRate;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
}
