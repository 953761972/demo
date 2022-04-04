package com.xzq.demo.vm;

public class SystemInfo {
    private String ip;
    private String hostName;
    private String jvmName;
    private String VmVersion;
    private String jvmJavaVer;
    private String jvmVendor;
    private String jvmUptime;
    private String JvmStartTime;
    private String JvmSystemProperties;
    private String processId;
    private String heapCurrentUsage;
    private String heapMax;
    private String heapCommitted;
    private String osName;
    private String osArch;
    private String osVersion;
    private String osAvailableProcessors;
    private String threadsLiveCount;
    private String threadsDaemonCount;
    private String threadsPeakCount;
    private String threadsTotalCount;
    private String AllIds;
    private String clsCurrLoaded;
    private String clsLoaded;
    private String clsUnloaded;

    @Override
    public String toString() {
        return "SystemInfo{" +
                "ip='" + ip + '\'' +
                ", hostName='" + hostName + '\'' +
                ", jvmName='" + jvmName + '\'' +
                ", VmVersion='" + VmVersion + '\'' +
                ", jvmJavaVer='" + jvmJavaVer + '\'' +
                ", jvmVendor='" + jvmVendor + '\'' +
                ", jvmUptime='" + jvmUptime + '\'' +
                ", JvmStartTime='" + JvmStartTime + '\'' +
                ", JvmSystemProperties='" + JvmSystemProperties + '\'' +
                ", processId='" + processId + '\'' +
                ", heapCurrentUsage='" + heapCurrentUsage + '\'' +
                ", heapMax='" + heapMax + '\'' +
                ", heapCommitted='" + heapCommitted + '\'' +
                ", osName='" + osName + '\'' +
                ", osArch='" + osArch + '\'' +
                ", osVersion='" + osVersion + '\'' +
                ", osAvailableProcessors='" + osAvailableProcessors + '\'' +
                ", threadsLiveCount='" + threadsLiveCount + '\'' +
                ", threadsDaemonCount='" + threadsDaemonCount + '\'' +
                ", threadsPeakCount='" + threadsPeakCount + '\'' +
                ", threadsTotalCount='" + threadsTotalCount + '\'' +
                ", AllIds='" + AllIds + '\'' +
                ", clsCurrLoaded='" + clsCurrLoaded + '\'' +
                ", clsLoaded='" + clsLoaded + '\'' +
                ", clsUnloaded='" + clsUnloaded + '\'' +
                '}';
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public String getHostName() {
        return hostName;
    }

    public void setHostName(String hostName) {
        this.hostName = hostName;
    }

    public String getJvmName() {
        return jvmName;
    }

    public void setJvmName(String jvmName) {
        this.jvmName = jvmName;
    }

    public String getVmVersion() {
        return VmVersion;
    }

    public void setVmVersion(String vmVersion) {
        VmVersion = vmVersion;
    }

    public String getJvmJavaVer() {
        return jvmJavaVer;
    }

    public void setJvmJavaVer(String jvmJavaVer) {
        this.jvmJavaVer = jvmJavaVer;
    }

    public String getJvmVendor() {
        return jvmVendor;
    }

    public void setJvmVendor(String jvmVendor) {
        this.jvmVendor = jvmVendor;
    }

    public String getJvmUptime() {
        return jvmUptime;
    }

    public void setJvmUptime(String jvmUptime) {
        this.jvmUptime = jvmUptime;
    }

    public String getJvmStartTime() {
        return JvmStartTime;
    }

    public void setJvmStartTime(String jvmStartTime) {
        JvmStartTime = jvmStartTime;
    }

    public String getJvmSystemProperties() {
        return JvmSystemProperties;
    }

    public void setJvmSystemProperties(String jvmSystemProperties) {
        JvmSystemProperties = jvmSystemProperties;
    }

    public String getProcessId() {
        return processId;
    }

    public void setProcessId(String processId) {
        this.processId = processId;
    }

    public String getHeapCurrentUsage() {
        return heapCurrentUsage;
    }

    public void setHeapCurrentUsage(String heapCurrentUsage) {
        this.heapCurrentUsage = heapCurrentUsage;
    }

    public String getHeapMax() {
        return heapMax;
    }

    public void setHeapMax(String heapMax) {
        this.heapMax = heapMax;
    }

    public String getHeapCommitted() {
        return heapCommitted;
    }

    public void setHeapCommitted(String heapCommitted) {
        this.heapCommitted = heapCommitted;
    }

    public String getOsName() {
        return osName;
    }

    public void setOsName(String osName) {
        this.osName = osName;
    }

    public String getOsArch() {
        return osArch;
    }

    public void setOsArch(String osArch) {
        this.osArch = osArch;
    }

    public String getOsVersion() {
        return osVersion;
    }

    public void setOsVersion(String osVersion) {
        this.osVersion = osVersion;
    }

    public String getOsAvailableProcessors() {
        return osAvailableProcessors;
    }

    public void setOsAvailableProcessors(String osAvailableProcessors) {
        this.osAvailableProcessors = osAvailableProcessors;
    }

    public String getThreadsLiveCount() {
        return threadsLiveCount;
    }

    public void setThreadsLiveCount(String threadsLiveCount) {
        this.threadsLiveCount = threadsLiveCount;
    }

    public String getThreadsDaemonCount() {
        return threadsDaemonCount;
    }

    public void setThreadsDaemonCount(String threadsDaemonCount) {
        this.threadsDaemonCount = threadsDaemonCount;
    }

    public String getThreadsPeakCount() {
        return threadsPeakCount;
    }

    public void setThreadsPeakCount(String threadsPeakCount) {
        this.threadsPeakCount = threadsPeakCount;
    }

    public String getThreadsTotalCount() {
        return threadsTotalCount;
    }

    public void setThreadsTotalCount(String threadsTotalCount) {
        this.threadsTotalCount = threadsTotalCount;
    }

    public String getAllIds() {
        return AllIds;
    }

    public void setAllIds(String allIds) {
        AllIds = allIds;
    }

    public String getClsCurrLoaded() {
        return clsCurrLoaded;
    }

    public void setClsCurrLoaded(String clsCurrLoaded) {
        this.clsCurrLoaded = clsCurrLoaded;
    }

    public String getClsLoaded() {
        return clsLoaded;
    }

    public void setClsLoaded(String clsLoaded) {
        this.clsLoaded = clsLoaded;
    }

    public String getClsUnloaded() {
        return clsUnloaded;
    }

    public void setClsUnloaded(String clsUnloaded) {
        this.clsUnloaded = clsUnloaded;
    }
}
