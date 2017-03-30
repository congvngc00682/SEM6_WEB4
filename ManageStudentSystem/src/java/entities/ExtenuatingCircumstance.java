/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

import java.util.List;
import org.apache.commons.lang3.StringUtils;

/**
 *
 * @author Cong
 */
public class ExtenuatingCircumstance {
    private int id;
    private String title;
    private String description;
    private String submitted_date;
    private String process_status;
    private boolean isActive;
    private int account;
    private String processedDate;
    private int assignedCoordinatorId;
    private String coordinatorName;
    private String studentName;
    private int noOfEvidence;

    public String getStudentName() {
        return studentName;
    }

    public void setStudentName(String studentName) {
        this.studentName = studentName;
    }
    private List<ExtenuatingCircumstance> extenuatingCircumstances; 

    
    public ExtenuatingCircumstance(String title, String description, String submitted_date, String process_status, int account) {
        this.title = title;
        this.description = description;
        this.submitted_date = submitted_date;
        this.process_status = process_status;
        this.account = account;
    }

    public ExtenuatingCircumstance() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getSubmitted_date() {
        if(StringUtils.isNotEmpty(submitted_date)){
            if(submitted_date.indexOf(" ") > 0) {
                return submitted_date.substring(0, submitted_date.indexOf(" "));
            }
        }
        return submitted_date;
    }

    public void setSubmitted_date(String submitted_date) {
        this.submitted_date = submitted_date;
    }

    public String getProcess_status() {
        return process_status;
    }

    public void setProcess_status(String process_status) {
        this.process_status = process_status;
    }

    public int getAccount() {
        return account;
    }

    public void setAccount(int account) {
        this.account = account;
    }
    
    public String getProcessedDate() {
        if(StringUtils.isNotEmpty(processedDate)){
            if(processedDate.indexOf(" ") > 0) {
                return processedDate.substring(0, processedDate.indexOf(" "));
            }
        }
        return processedDate;
    }

    public void setProcessedDate(String processedDate) {
        this.processedDate = processedDate;
    }

    public int getAssignedCoordinatorId() {
        return assignedCoordinatorId;
    }

    public void setAssignedCoordinatorId(int assignedCoordinatorid) {
        this.assignedCoordinatorId = assignedCoordinatorid;
    }
    public String getCoordinatorName() {
        return coordinatorName;
    }

    public void setCoordinatorName(String coordinatorName) {
        this.coordinatorName = coordinatorName;
    }

    public int getNoOfEvidence() {
        return noOfEvidence;
    }

    public void setNoOfEvidence(int noOfEvidence) {
        this.noOfEvidence = noOfEvidence;
    }

    public List<ExtenuatingCircumstance> getExtenuatingCircumstances() {
        return extenuatingCircumstances;
    }

    public void setExtenuatingCircumstances(List<ExtenuatingCircumstance> extenuatingCircumstances) {
        this.extenuatingCircumstances = extenuatingCircumstances;
    }
    
    public boolean isIsActive() {
        return isActive;
    }

    public void setIsActive(boolean isActive) {
        this.isActive = isActive;
    }
}
