package com.iade.ulide.models.weak;

import com.iade.ulide.models.Route;
import com.iade.ulide.models.User;

import javax.persistence.*;

@Entity
@Table(name = "done_routes")
public class DoneRoutes {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "dr_id", nullable = false)
    private Integer id;

    @ManyToOne(optional = false)
    @JoinColumn(name = "dr_us_id", nullable = false)
    private User drUs;

    @ManyToOne(optional = false)
    @JoinColumn(name = "dr_rt_id", nullable = false)
    private Route drRt;

    public Route getDrRt() {
        return drRt;
    }

    public void setDrRt(Route drRt) {
        this.drRt = drRt;
    }

    public User getDrUs() {
        return drUs;
    }

    public void setDrUs(User drUs) {
        this.drUs = drUs;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
}
