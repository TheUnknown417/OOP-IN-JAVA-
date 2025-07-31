package com.example.myfinalpractice;
import javafx.beans.property.SimpleStringProperty;
import java.time.LocalDate;

public  class Member
{
    private final SimpleStringProperty name ;
    private final SimpleStringProperty gender ;
    private final SimpleStringProperty mem_type ;
    private final SimpleStringProperty dob ;





    public Member(String name, String gender, String memType, String dob) {
        this.name = new SimpleStringProperty(name);
        this.gender = new SimpleStringProperty(gender);
        this.mem_type = new SimpleStringProperty(memType);
        this.dob = new SimpleStringProperty (dob);
    }


    public String getName() {
        return name.get();
    }

    public String getMem_type() {
        return mem_type.get();
    }

    public String getGender() {
        return gender.get();
    }
    public String getDob() {
        return dob.get();
    }

    @Override
    public String toString() {
        return "Member{" +
                "name=" + name +
                ", gender=" + gender +
                ", mem_type=" + mem_type +
                ", dob=" + dob +
                '}';
    }



}






























