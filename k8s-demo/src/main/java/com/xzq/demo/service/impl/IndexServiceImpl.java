package com.xzq.demo.service.impl;

import com.xzq.demo.service.IndexService;
import com.xzq.demo.vm.SystemInfo;
import org.springframework.stereotype.Service;

import java.lang.management.*;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.text.NumberFormat;
import java.util.Locale;

@Service
public class IndexServiceImpl implements IndexService {

    private NumberFormat fmtI = new DecimalFormat("###,###", new DecimalFormatSymbols(Locale.ENGLISH));
    private NumberFormat fmtD = new DecimalFormat("###,##0.000", new DecimalFormatSymbols(Locale.ENGLISH));
    private final int Kb = 1024;


    @Override
    public SystemInfo getSystemInfo() throws UnknownHostException {
        return printSummary();
    }

    public static void main(String[] args) throws UnknownHostException {
        IndexServiceImpl jvmResource = new IndexServiceImpl();
        jvmResource.printSummary();
    }

    public SystemInfo printSummary() throws UnknownHostException {
        SystemInfo systemInfo=new SystemInfo();
        //ip hostname
        InetAddress addr = InetAddress.getLocalHost();
        System.out.println("Local HostAddress: "+addr.getHostAddress());
        systemInfo.setIp(addr.getHostAddress());
        String hostname = addr.getHostName();
        System.out.println("Local host name: "+hostname);
        systemInfo.setHostName(hostname);

        RuntimeMXBean runtime = ManagementFactory.getRuntimeMXBean();
        //获取JVM的启动时间，版本及名称，当前进程ID,环境变量等
        System.out.printf("jvmName:%s %s %s%n", runtime.getVmName(), "version", runtime.getVmVersion());
        systemInfo.setJvmName(runtime.getVmName());
        systemInfo.setVmVersion(runtime.getVmVersion());
        System.out.printf("jvmJavaVer:%s%n", System.getProperty("java.version"));
        systemInfo.setJvmJavaVer(System.getProperty("java.version"));
        System.out.printf("jvmVendor:%s%n", runtime.getVmVendor());
        systemInfo.setJvmVendor(runtime.getVmVendor());
        System.out.printf("jvmUptime:%s%n", toDuration(runtime.getUptime()));
        systemInfo.setJvmUptime(toDuration(runtime.getUptime()));
        System.out.printf("JvmStartTime:%s%n",runtime.getStartTime());
        systemInfo.setJvmStartTime(String.valueOf(runtime.getStartTime()));
        System.out.printf("JvmSystemProperties:%s%n",runtime.getSystemProperties());
        systemInfo.setJvmSystemProperties(String.valueOf(runtime.getSystemProperties()));
        System.out.printf("processId:%S%n", runtime.getName().split("@")[0]);
        systemInfo.setProcessId(runtime.getName().split("@")[0]);

        System.out.println("---------------------------------------------");
        //获取JVM内存使用状况，包括堆内存和非堆内存
        MemoryMXBean mem = ManagementFactory.getMemoryMXBean();
        System.out.printf("heapCurrentUsage:%d kb %n", mem.getHeapMemoryUsage().getUsed() / Kb);
        systemInfo.setHeapCurrentUsage(String.valueOf(mem.getHeapMemoryUsage().getUsed() / Kb));
        System.out.printf("heapMax:%d kb %n", mem.getHeapMemoryUsage().getMax() / Kb);
        systemInfo.setHeapMax(String.valueOf(mem.getHeapMemoryUsage().getMax() / Kb));
        System.out.printf("heapCommitted:%d kb%n", mem.getHeapMemoryUsage().getCommitted() / Kb);
        systemInfo.setHeapCommitted(String.valueOf(mem.getHeapMemoryUsage().getCommitted() / Kb));

        System.out.println("---------------------------------------------");
        //操作系统及硬件信息：系统名称、版本，CPU内核，机器总内存、可用内存、可用内存占比
        OperatingSystemMXBean os = ManagementFactory.getOperatingSystemMXBean();
        System.out.printf("osName:%s%n", os.getName());
        systemInfo.setOsName(os.getName());
        System.out.printf("osArch:%s%n", os.getArch());
        systemInfo.setOsArch(os.getArch());
        System.out.printf("osVersion: %s%n", os.getVersion());
        systemInfo.setOsVersion(os.getVersion());
        System.out.printf("osAvailableProcessors:%s%n", os.getAvailableProcessors());
        systemInfo.setOsAvailableProcessors(String.valueOf(os.getAvailableProcessors()));

        System.out.println("---------------------------------------------");
        //虚拟机线程系统的管理,可以获取某个线程信息(阻塞时间，次数，堆栈信息等)
        ThreadMXBean threads = ManagementFactory.getThreadMXBean();
        System.out.printf("threadsLiveCount:%d%n", threads.getThreadCount());
        systemInfo.setThreadsLiveCount(String.valueOf(threads.getThreadCount()));
        System.out.printf("threadsDaemonCount:%d%n", threads.getDaemonThreadCount());
        systemInfo.setThreadsDaemonCount(String.valueOf(threads.getDaemonThreadCount()));
        System.out.printf("threadsPeakCount:%d%n", threads.getPeakThreadCount());
        systemInfo.setThreadsPeakCount(String.valueOf(threads.getPeakThreadCount()));
        System.out.printf("threadsTotalCount:%d%n", threads.getTotalStartedThreadCount());
        systemInfo.setThreadsTotalCount(String.valueOf(threads.getTotalStartedThreadCount()));
        System.out.printf("AllIds:%s%n", toString(threads.getAllThreadIds()));
        systemInfo.setAllIds(String.valueOf(threads.getAllThreadIds()));

        System.out.println("---------------------------------------------");
        //Java虚拟机类加载系统的管理接口。
        ClassLoadingMXBean cl = ManagementFactory.getClassLoadingMXBean();
        System.out.printf("clsCurrLoaded:%s%n", cl.getLoadedClassCount());
        systemInfo.setClsCurrLoaded(String.valueOf(cl.getLoadedClassCount()));
        System.out.printf("clsLoaded:%s%n", cl.getTotalLoadedClassCount());
        systemInfo.setClsLoaded(String.valueOf(cl.getTotalLoadedClassCount()));
        System.out.printf("clsUnloaded:%s%n", cl.getUnloadedClassCount());
        systemInfo.setClsUnloaded(String.valueOf(cl.getUnloadedClassCount()));
        return systemInfo;
    }

    protected String printSizeInKb(double size) {
        return fmtI.format((long) (size / 1024)) + " kbytes";
    }

    protected String toDuration(double uptime) {
        uptime /= 1000;
        if (uptime < 60) {
            return fmtD.format(uptime) + " seconds";
        }
        uptime /= 60;
        if (uptime < 60) {
            long minutes = (long) uptime;
            String s = fmtI.format(minutes) + (minutes > 1 ? " minutes" : " minute");
            return s;
        }
        uptime /= 60;
        if (uptime < 24) {
            long hours = (long) uptime;
            long minutes = (long) ((uptime - hours) * 60);
            String s = fmtI.format(hours) + (hours > 1 ? " hours" : " hour");
            if (minutes != 0) {
                s += " " + fmtI.format(minutes) + (minutes > 1 ? " minutes" : " minute");
            }
            return s;
        }
        uptime /= 24;
        long days = (long) uptime;
        long hours = (long) ((uptime - days) * 24);
        String s = fmtI.format(days) + (days > 1 ? " days" : " day");
        if (hours != 0) {
            s += " " + fmtI.format(hours) + (hours > 1 ? " hours" : " hour");
        }
        return s;
    }

    protected String toString(long[] collection){
        if(collection.length > 0) {
            StringBuilder stringBuilder = new StringBuilder();
            for(long id : collection) {
                stringBuilder.append(id).append(",");
            }
            return stringBuilder.substring(0, stringBuilder.length() -1);
        }
        return null;
    }
}
