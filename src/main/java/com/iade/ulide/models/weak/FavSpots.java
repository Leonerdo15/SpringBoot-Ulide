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

    @Column(name = "fs_us_id", nullable = false)
    private Integer fsUs;

    @Column(name = "fs_sp_id", nullable = false)
    private Integer fsSp;

    public Integer getFsSp() {
        return fsSp;
    }

    public void setFsSp(Integer fsSp) {
        this.fsSp = fsSp;
    }

    public Integer getFsUs() {
        return fsUs;
    }

    public void setFsUs(Integer fsUs) {
        this.fsUs = fsUs;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
}
