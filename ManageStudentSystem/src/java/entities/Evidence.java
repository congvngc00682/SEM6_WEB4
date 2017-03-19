/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

/**
 *
 * @author power
 */
public class Evidence {
    private int id;
    private String files;
    private String evidence_date;
    private int ecId;

    public int getEcId() {
        return ecId;
    }

    public void setEcId(int ecId) {
        this.ecId = ecId;
    }

    public Evidence(String files, String evidence_date, int account) {
        this.files = files;
        this.evidence_date = evidence_date;
        this.ecId = account;
    }

    public Evidence() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFiles() {
        return files;
    }

    public void setFiles(String files) {
        this.files = files;
    }

    public String getEvidence_date() {
        return evidence_date;
    }

    public void setEvidence_date(String evidence_date) {
        this.evidence_date = evidence_date;
    }

}
