package com.cognizant.badgeorama.model;

import java.util.List;

public class Visitors {

    List<Visitor> visitors;
    String selected;

    public Visitors() {

    }

    public Visitors(List<Visitor> visitors, String selected) {
        this.visitors = visitors;
        this.selected = selected;
    }

    public List<Visitor> getVisitors() {
        return visitors;
    }

    public void setVisitors(List<Visitor> visitors) {
        this.visitors = visitors;
    }

    public String getSelected() {
        return selected;
    }

    public void setSelected(String selected) {
        this.selected = selected;
    }
}
