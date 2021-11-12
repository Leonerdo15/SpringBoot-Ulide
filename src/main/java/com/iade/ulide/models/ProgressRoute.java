package com.iade.ulide.models;

import javax.persistence.*;

@Entity
@Table(name = "progress_routes")
public class ProgressRoute {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "pr_id", nullable = false)
    private Integer id;

    @ManyToOne(optional = false)
    @JoinColumn(name = "pr_us_id", nullable = false)
    private User prUs;

    @ManyToOne(optional = false)
    @JoinColumn(name = "pr_rt_id", nullable = false)
    private Route prRt;

    @ManyToOne(optional = false)
    @JoinColumn(name = "pr_sp_id", nullable = false)
    private Spot prSp;

    public Spot getPrSp() {
        return prSp;
    }

    public void setPrSp(Spot prSp) {
        this.prSp = prSp;
    }

    public Route getPrRt() {
        return prRt;
    }

    public void setPrRt(Route prRt) {
        this.prRt = prRt;
    }

    public User getPrUs() {
        return prUs;
    }

    public void setPrUs(User prUs) {
        this.prUs = prUs;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
}
