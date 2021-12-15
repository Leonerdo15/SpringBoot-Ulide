package com.iade.ulide.models.weak;

import com.iade.ulide.models.Spot;
import com.iade.ulide.models.User;

import javax.persistence.*;

@Entity
@Table(name = "done_spots")
public class DoneSpots {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ds_id", nullable = false)
    private Integer id;

    @ManyToOne(optional = false)
    @JoinColumn(name = "ds_us_id", nullable = false)
    private User dsUs;

    @ManyToOne(optional = false)
    @JoinColumn(name = "ds_sp_id", nullable = false)
    private Spot dsSp;

    public Spot getDsSp() {
        return dsSp;
    }

    public void setDsSp(Spot dsSp) {
        this.dsSp = dsSp;
    }

    public User getDsUs() {
        return dsUs;
    }

    public void setDsUs(User dsUs) {
        this.dsUs = dsUs;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
}
