package com.wix.gym.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.util.Objects;

@Entity
public class Class {


    @Id
    @GeneratedValue
    private Long id;

    private String name;
    private String description;
    private double price;
    private int duration;
    private int maxParticNum;
    private String instructor;

    public Class() {
    }


    public Class(String name, String description, double price,int duration, int maxParticNum, String instructor) {
        this.name = name;
        this.description = description;
        this.price = price;
        this.duration = duration;
        this.maxParticNum = maxParticNum;
        this.instructor = instructor;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getMaxParticNum() {
        return maxParticNum;
    }

    public void setMaxParticNum(int maxParticNum) {
        this.maxParticNum = maxParticNum;
    }

    public String getInstructor() {
        return instructor;
    }

    public void setInstructor(String instructor) {
        this.instructor = instructor;
    }


    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Class aClass = (Class) o;
        return Double.compare(aClass.price, price) == 0 &&
                maxParticNum == aClass.maxParticNum &&
                Objects.equals(id, aClass.id) &&
                Objects.equals(name, aClass.name) &&
                Objects.equals(description, aClass.description);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, description, price, maxParticNum);
    }

    @Override
    public String toString() {
        return "Class{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", price=" + price +
                ", maxParticNum=" + maxParticNum +
                '}';
    }
}
