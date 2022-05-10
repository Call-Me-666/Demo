package com.example.client;

import org.apache.zookeeper.*;

import java.io.IOException;

public class ZkClient {

    private static String connectStr = "myVm1:2181,myVm2:2181,myVm3:2181";
    private static int sessionTimeOut = 2000;
    private static ZooKeeper zooKeeper;

    public static void main(String[] args) throws IOException, InterruptedException, KeeperException {
        zooKeeper = new ZooKeeper(connectStr, sessionTimeOut, new Watcher() {
            @Override
            public void process(WatchedEvent watchedEvent) {

            }
        });
        String result = createNode("/xxx","mapi");

    }

    /**
     * 创建zk节点
     */
    public static String createNode(String path, String content) throws InterruptedException, KeeperException {
        String val = zooKeeper.create(path,content.getBytes(), ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.PERSISTENT);
        return val;
    }
}
