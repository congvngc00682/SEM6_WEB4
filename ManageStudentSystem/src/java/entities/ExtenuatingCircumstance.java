/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

import java.util.List;

/**
 *
 * @author power
 */
public class ExtenuatingCircumstance {
    private int id;
    private String title;
    private String description;
    private String submitted_date;
    private String process_status;
    private String processed_date;
    private int account;
    private int assignedCoordinator;
    private List<ExtenuatingCircumstance> list;

    public ExtenuatingCircumstance(String title, String description, String submitted_date, String process_status, String processed_date, int account, int assignedCoordinator) {
        this.title = title;
        this.description = description;
        this.submitted_date = submitted_date;
        this.process_status = process_status;
        this.processed_date = processed_date;
        this.account = account;
        this.assignedCoordinator = assignedCoordinator;
    }

    public List<ExtenuatingCircumstance> getList() {
        return list;
    }

    public void setList(List<ExtenuatingCircumstance> list) {
        this.list = list;
    }

    

    public ExtenuatingCircumstance() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getProcessed_date() {
        return processed_date;
    }

    public void setProcessed_date(String processed_date) {
        this.processed_date = processed_date;
    }

    public int getAssignedCoordinator() {
        return assignedCoordinator;
    }

    public void setAssignedCoordinator(int assignedCoordinator) {
        this.assignedCoordinator = assignedCoordinator;
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
    
}
