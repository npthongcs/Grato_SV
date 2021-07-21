package com.example.grato_sv.Model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Group {

	@SerializedName("gname")
	@Expose
	private String gname;
	@SerializedName("no_student")
	@Expose
	private Integer noStudent;
	@SerializedName("max_student")
	@Expose
	private Integer maxStudent;

    public Group(String gname, Integer noStudent, Integer maxStudent) {
        this.gname = gname;
        this.noStudent = noStudent;
        this.maxStudent = maxStudent;
    }

    public String getGname() {
		return gname;
	}

	public void setGname(String gname) {
		this.gname = gname;
	}

	public Integer getNoStudent() {
		return noStudent;
	}

	public void setNoStudent(Integer noStudent) {
		this.noStudent = noStudent;
	}

	public Integer getMaxStudent() {
		return maxStudent;
	}

	public void setMaxStudent(Integer maxStudent) {
		this.maxStudent = maxStudent;
	}

    @Override
    public String toString() {
        return "Group{" +
                "gname='" + gname + '\'' +
                ", noStudent=" + noStudent +
                ", maxStudent=" + maxStudent +
                '}';
    }
}




//public class Group {
//    private String nameGroup;
//    private Integer maxMember;
//    private Integer wholesale;
//
//    public Group(String nameGroup, Integer maxMember, Integer wholesale){
//        this.nameGroup = nameGroup;
//        this.maxMember = maxMember;
//        this.wholesale = wholesale;
//    }
//
//    public String getNameGroup(){
//        return nameGroup;
//    }
//
//    public void setNameGroup(String nameGroup){
//        this.nameGroup = nameGroup;
//    }
//
//    public Integer getMaxMember(){
//        return maxMember;
//    }
//
//    public void setMaxMember(Integer maxMember){
//        this.maxMember = maxMember;
//    }
//
//    public Integer getWholesale(){
//        return wholesale;
//    }
//
//    public void setWholesale(Integer wholesale){
//        this.wholesale = wholesale;
//    }
//}
