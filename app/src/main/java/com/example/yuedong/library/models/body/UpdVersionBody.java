package com.example.yuedong.library.models.body;

/**
 *
 */

public class UpdVersionBody {

    private String appName;
    private String version;
    private String machineId;
    private int phoneType;


    @Override
    public String toString() {
        return "UpdVersionBody{" +
                "appName='" + appName + '\'' +
                ", version='" + version + '\'' +
                ", machineId='" + machineId + '\'' +
                ", phoneType=" + phoneType +
                '}';
    }


    public String getAppName() {
        return appName;
    }

    public void setAppName(String appName) {
        this.appName = appName;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public String getMachineId() {
        return machineId;
    }

    public void setMachineId(String machineId) {
        this.machineId = machineId;
    }

    public int getPhoneType() {
        return phoneType;
    }

    public void setPhoneType(int phoneType) {
        this.phoneType = phoneType;
    }
}
