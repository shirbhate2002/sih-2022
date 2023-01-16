package com.vaidilya.sih;

public class attendance_data {

    private String name;
    private double attendace;

    public attendance_data(String name, double attendace) {
        this.name = name;
        this.attendace = attendace;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getAttendace() {
        return attendace;
    }

    public void setAttendace(int attendace) {
        this.attendace = attendace;
    }

    public void inc_attend(){
        attendace+=1;
    }
}
