package com.mylab.cromero.service;

import java.io.Serializable;

public class Course implements Serializable {

    private String title;
    private Integer hours;
    private Level level;
    private Boolean active;

    public Course(String title, Integer hours, Level level, Boolean active) {
        this.title = title;
        this.hours = hours;
        this.level = level;
        this.active = active;
    }

    public String getTitle() {
        return title;
    }

    public Integer getHours() {
        return hours;
    }

    public Level getLevel() {
        return level;
    }

    public Boolean getActive() {
        return active;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Course{");
        sb.append("active=").append(active);
        sb.append(", hours=").append(hours);
        sb.append(", level=").append(level);
        sb.append(", title='").append(title).append('\'');
        sb.append('}');
        return sb.toString();
    }
}
