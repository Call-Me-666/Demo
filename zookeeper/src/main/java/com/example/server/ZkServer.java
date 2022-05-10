package com.example.server;

import org.apache.zookeeper.WatchedEvent;
import org.apache.zookeeper.Watcher;
import org.apache.zookeeper.ZooKeeper;

import java.io.IOException;

public class ZkServer {
    private static String connectStr;
    private static int sessionTimeOut;

    public static void main(String[] args) throws IOException {
        ZooKeeper zooKeeper = new ZooKeeper(connectStr, sessionTimeOut, new Watcher() {
            @Override
            public void process(WatchedEvent watchedEvent) {

            }
        });
    }
}
