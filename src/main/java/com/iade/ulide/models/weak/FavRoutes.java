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

    @ManyToOne(optional = false)
    @JoinColumn(name = "fr_us_id", nullable = false)
    private User frUs;

    @ManyToOne(optional = false)
    @JoinColumn(name = "fr_rt_id", nullable = false)
    private Route frRt;

    public Route getFrRt() {
        return frRt;
    }

    public void setFrRt(Route frRt) {
        this.frRt = frRt;
    }

    public User getFrUs() {
        return frUs;
    }

    public void setFrUs(User frUs) {
        this.frUs = frUs;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
}
