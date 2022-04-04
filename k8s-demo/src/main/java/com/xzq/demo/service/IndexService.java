package com.xzq.demo.service;


import com.xzq.demo.vm.SystemInfo;

import java.net.UnknownHostException;

public interface IndexService {

     SystemInfo getSystemInfo() throws UnknownHostException;
}
