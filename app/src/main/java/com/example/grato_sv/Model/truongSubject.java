
package com.example.grato_sv.Model;

public class truongSubject {
    private String btnSubject;
    private String txtClassName;

    public truongSubject(String btnSubject, String txtClassName) {
        this.btnSubject = btnSubject;
        this.txtClassName = txtClassName;
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
}
