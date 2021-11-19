package com.iade.ulide.models;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "user_achievements")
public class UserAchievement {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ua_id")
    private Integer id;

    @Column(name = "ua_date")
    private LocalDate uaDate;

    @ManyToOne
    @JoinColumn(name = "ua_us_id", nullable = false)
    private User uaUs;

    @ManyToOne
    @JoinColumn(name = "ua_ac_id", nullable = false)
    private Achievement uaAc;

    public Achievement getUaAc() {
        return uaAc;
    }

    public void setUaAc(Achievement uaAc) {
        this.uaAc = uaAc;
    }

    public User getUaUs() {
        return uaUs;
    }

    public void setUaUs(User uaUs) {
        this.uaUs = uaUs;
    }

    public LocalDate getUaDate() {
        return uaDate;
    }

    public void setUaDate(LocalDate uaDate) {
        this.uaDate = uaDate;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
}
