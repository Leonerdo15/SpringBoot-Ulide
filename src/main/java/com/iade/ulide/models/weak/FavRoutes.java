package com.iade.ulide.models.weak;

import com.iade.ulide.models.Route;
import com.iade.ulide.models.User;

import javax.persistence.*;

@Entity
@Table(name = "fav_routes")
public class FavRoutes {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "fr_id", nullable = false)
    private Integer id;

    @Column(name = "fr_us_id", nullable = false)
    private Integer frUs;

    @Column(name = "fr_rt_id", nullable = false)
    private Integer frRt;

    public Integer getFrRt() {
        return frRt;
    }

    public void setFrRt(Integer frRt) {
        this.frRt = frRt;
    }

    public Integer getFrUs() {
        return frUs;
    }

    public void setFrUs(Integer frUs) {
        this.frUs = frUs;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
}
