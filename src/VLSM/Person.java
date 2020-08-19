package VLSM;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;

public class Person {

    private String  subnetName;
    private int needSize;
    private int allocatedSize;
    private String address;
    private String mask;
    private String assignableRange;
    private String broadcast;

    public Person(String subnetName, int needSize, int allocatedSize, String address, String mask, String assignableRange, String broadcast) {
        this.subnetName = subnetName;
        this.needSize = needSize;
        this.allocatedSize = allocatedSize;
        this.address = address;
        this.mask = mask;
        this.assignableRange = assignableRange;
        this.broadcast = broadcast;
    }

    public String getSubnetName() {
        return subnetName;
    }

    public void setSubnetName(String subnetName) {
        this.subnetName = subnetName;
    }

    public int getNeedSize() {
        return needSize;
    }

    public void setNeedSize(int needSize) {
        this.needSize = needSize;
    }

    public int getAllocatedSize() {
        return allocatedSize;
    }

    public void setAllocatedSize(int allocatedSize) {
        this.allocatedSize = allocatedSize;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getMask() {
        return mask;
    }

    public void setMask(String mask) {
        this.mask = mask;
    }

    public String getAssignableRange() {
        return assignableRange;
    }

    public void setAssignableRange(String assignableRange) {
        this.assignableRange = assignableRange;
    }

    public String getBroadcast() {
        return broadcast;
    }

    public void setBroadcast(String broadcast) {
        this.broadcast = broadcast;
    }
}
