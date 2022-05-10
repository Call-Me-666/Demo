package com.example.client;

import org.apache.zookeeper.*;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.IOException;

public class Tests {
    private static String connectStr = "myVm1:2181,myVm2:2181,myVm3:2181";
    private static int sessionTimeOut = 2000;
    private static ZooKeeper zooKeeper;

    @Test
    public void init() throws IOException {
        zooKeeper = new ZooKeeper(connectStr, sessionTimeOut, new Watcher() {
            @Override
            public void process(WatchedEvent watchedEvent) {

            }
        });
    }

    /**
     * 创建zk节点
     */
    public String createNode(String path, String content) throws InterruptedException, KeeperException {
        String val = zooKeeper.create(path,content.getBytes(), ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.PERSISTENT);
        return val;
    }
}
