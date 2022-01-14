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

    @Column(name = "rs_rt_id")
    private Integer rsRt;


    @Column(name = "rs_sp_id")
    private Integer rsSp;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getRsRt() {
        return rsRt;
    }

    public void setRsRt(Integer rsRt) {
        this.rsRt = rsRt;
    }

    public Integer getRsSp() {
        return rsSp;
    }

    public void setRsSp(Integer rsSp) {
        this.rsSp = rsSp;
    }
}
