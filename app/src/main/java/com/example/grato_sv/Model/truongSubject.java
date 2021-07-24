
package com.example.grato_sv.Model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class truongSubject {

    @SerializedName("name")
    @Expose
    private String btnSubject;
    @SerializedName("class_id")
    @Expose
    private String txtClassName;
    @SerializedName("id")
    @Expose
    private String subjectId;

    public truongSubject(String btnSubject, String txtClassName, String classId) {
        this.btnSubject = btnSubject;
        this.txtClassName = txtClassName;
        this.subjectId = classId;
    }


    public String getTxtClassName() {
        return txtClassName;
    }

    public void setTxtClassName(String txtClassName) {
        this.txtClassName = txtClassName;
    }

    public String getBtnSubject() {
        return btnSubject;
    }

    public void setBtnSubject(String btnSubject) {
        this.btnSubject = btnSubject;
    }

    public String getSubjectId() {
        return subjectId;
    }

    public void setSubjectId(String classId) {
        this.subjectId = classId;
    }
}
