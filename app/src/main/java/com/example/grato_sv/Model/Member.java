package com.example.grato_sv.Model;

public class Member {
    public String name;
    public String id;

    public Member(String name,String ID){
        this.name = name;
        this.id = ID;
    }

    public void setName(String _name){
        this.name = _name;
    }
    public String getName(){
        return this.name;
    }

    public void setID(String _ID){
        this.id = _ID;
    }
    public String getID(){
        return this.id;
    }

    @Override
    public String toString() {
        return "Member{" +
                "name='" + name + '\'' +
                ", ID='" + id + '\'' +
                '}';
    }
}
