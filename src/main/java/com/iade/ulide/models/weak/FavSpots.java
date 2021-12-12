package com.iade.ulide.models.weak;

import com.iade.ulide.models.Spot;
import com.iade.ulide.models.User;

import javax.persistence.*;

@Entity
@Table(name = "fav_spots")
public class FavSpots {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "fs_id", nullable = false)
    private Integer id;

    @ManyToOne(optional = false)
    @JoinColumn(name = "fs_us_id", nullable = false)
    private User fsUs;

    @ManyToOne(optional = false)
    @JoinColumn(name = "fs_sp_id", nullable = false)
    private Spot fsSp;

    public Spot getFsSp() {
        return fsSp;
    }

    public void setFsSp(Spot fsSp) {
        this.fsSp = fsSp;
    }

    public User getFsUs() {
        return fsUs;
    }

    public void setFsUs(User fsUs) {
        this.fsUs = fsUs;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
}
