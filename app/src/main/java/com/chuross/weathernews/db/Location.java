package com.chuross.weathernews.db;

import com.activeandroid.Model;
import com.activeandroid.annotation.Column;
import com.activeandroid.annotation.Table;
import com.chuross.weathernews.util.DateProvider;

import java.util.Date;

@Table(name = "locations")
public class Location extends Model {

    @Column(name = "name", unique = true)
    private String name;
    @Column(name = "latitude")
    private String latitude;
    @Column(name = "longtude")
    private String longitude;
    @Column(name = "createdAt")
    private Date createdAt;
    @Column(name = "updatedAt")
    private Date updatedAt;

    public Location() {
        super();
    }

    public Location(String name, String latitude, String longitude, Date createdAt, Date updatedAt) {
        this.name = name;
        this.latitude = latitude;
        this.longitude = longitude;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    public String getName() {
        return name;
    }

    public void setName(final String name) {
        this.name = name;
    }

    public String getLatitude() {
        return latitude;
    }

    public void setLatitude(final String latitude) {
        this.latitude = latitude;
    }

    public String getLongitude() {
        return longitude;
    }

    public void setLongitude(final String longitude) {
        this.longitude = longitude;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(final Date createdAt) {
        this.createdAt = createdAt;
    }

    public Date getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(final Date updatedAt) {
        this.updatedAt = updatedAt;
    }

    public static boolean save(Location location, DateProvider dateProvider) {
        if(location == null) {
            return false;
        }
        if(location.getId() > 0) {
            location.setUpdatedAt(dateProvider.now());
        } else {
            Date now = dateProvider.now();
            location.setCreatedAt(now);
            location.setUpdatedAt(now);
        }
        return location.save() > 0;
    }
}
