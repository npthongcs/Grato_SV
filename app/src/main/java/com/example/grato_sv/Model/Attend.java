package com.example.grato_sv.Model;

import java.util.Date;

public class Attend {
    java.util.Date start_time;
    java.util.Date end_time;
    java.util.Date lasttook;

    public Attend(Date start_time, Date end_time, Date lasttook) {
        this.start_time = start_time;
        this.end_time = end_time;
        this.lasttook = lasttook;
    }

    public Date getStart_time() {
        return start_time;
    }

    public void setStart_time(Date start_time) {
        this.start_time = start_time;
    }

    public Date getEnd_time() {
        return end_time;
    }

    public void setEnd_time(Date end_time) {
        this.end_time = end_time;
    }

    public Date getLasttook() {
        return lasttook;
    }

    public void setLasttook(Date lasttook) {
        this.lasttook = lasttook;
    }
}
