package com.iade.ulide.models.weak;

import com.iade.ulide.models.Route;
import com.iade.ulide.models.Spot;

import javax.persistence.*;

@Entity
@Table(name = "route_spots")
public class RoutesSpots {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "rs_id", nullable = false)
    private Integer id;

    @ManyToOne(optional = false)
    @JoinColumn(name = "rs_rt_id", nullable = false)
    private Route rsRt;

    @ManyToOne(optional = false)
    @JoinColumn(name = "rs_sp_id", nullable = false)
    private Spot rsSp;

    public Spot getRsSp() {
        return rsSp;
    }

    public void setRsSp(Spot rsSp) {
        this.rsSp = rsSp;
    }

    public Route getRsRt() {
        return rsRt;
    }

    public void setRsRt(Route rsRt) {
        this.rsRt = rsRt;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
}
