package com.example.yuedong.library.models;

/**
 * Created by EHN on 2017/12/28.
 */

public class UpdVersionModule {
    /**
     * upgrade : 1
     * latestVersion : 3.1.0
     * memo : 更新描述
     * downloadUrl : 版本下载链接
     * forceUpdate : 1
     */

    private int upgrade;
    private String latestVersion;
    private String memo;
    private String downloadUrl;
    private int forceUpdate;

    public int getUpgrade() {
        return upgrade;
    }

    public void setUpgrade(int upgrade) {
        this.upgrade = upgrade;
    }

    public String getLatestVersion() {
        return latestVersion;
    }

    public void setLatestVersion(String latestVersion) {
        this.latestVersion = latestVersion;
    }

    public String getMemo() {
        return memo;
    }

    public void setMemo(String memo) {
        this.memo = memo;
    }

    public String getDownloadUrl() {
        return downloadUrl;
    }

    public void setDownloadUrl(String downloadUrl) {
        this.downloadUrl = downloadUrl;
    }

    public int getForceUpdate() {
        return forceUpdate;
    }

    public void setForceUpdate(int forceUpdate) {
        this.forceUpdate = forceUpdate;
    }

    @Override
    public String toString() {
        return "UpdVersionModule{" +
                "upgrade=" + upgrade +
                ", latestVersion='" + latestVersion + '\'' +
                ", memo='" + memo + '\'' +
                ", downloadUrl='" + downloadUrl + '\'' +
                ", forceUpdate=" + forceUpdate +
                '}';
    }
}
