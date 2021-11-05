package com.example.demo.Zookeeper;

import com.example.demo.utils.CustomWatcher;
import com.example.demo.utils.ZkUtil;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @Author XZQ
 * @Date 2021/11/1 23:28
 **/
@RunWith(SpringRunner.class)
@SpringBootTest
public class TestZK {

    //https://www.jianshu.com/p/67c7d331462f

    @Autowired
    private ZkUtil zkUtil;

    /**
     * 新增节点
     */
    @Test
    public void testCreateNode() {
        zkUtil.createPerNode("/demo", "auskat");
    }
    /**
     * 新增节点
     */
    @Test
    public void testCreateNode1() {
        zkUtil.createPerNode("/demo/demo2", "auskatww");
    }
    /**
     * 修改节点
     */
    @Test
    public void testUpdateNode() {
        zkUtil.updateNode("/demo", "auskat-2");
    }

    /**
     * 获取节点是否存在
     * 自定义监听
     */
    @Test
    public void exists() {
        zkUtil.exists("/demo", new CustomWatcher());
    }

    /**
     * 获取节点数据
     * 自定义监听
     */
    @Test
    public void getData() throws InterruptedException {
        String data = zkUtil.getData("/demo", new CustomWatcher());
        System.out.println(data);
        zkUtil.updateNode("/demo", "auskat-3");
        Thread.sleep(Long.MAX_VALUE);
    }

    /**
     * 删除节点
     */
    @Test
    public void testDeleteNode() {
        zkUtil.deleteNode("/demo");
    }

}
