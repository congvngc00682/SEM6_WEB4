/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

/**
 *
 * @author CuongDH
 */
public class AssginedCoordinator {
    private int id;
    
    private int ecId;
    private int coordinatorId;
    
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getEcId() {
        return ecId;
    }

    public void setEcId(int ecId) {
        this.ecId = ecId;
    }

    public int getCoordinatorId() {
        return coordinatorId;
    }

    public void setCoordinatorId(int coordinatorId) {
        this.coordinatorId = coordinatorId;
    }
}
