package com.example.client;

import org.apache.zookeeper.*;

import java.io.IOException;

public class Test {
    public static void main(String[] args) {
        //创建一个node节点
        createNode("sb",new byte[]{});
    }

    //创建节点的方法
    public static void createNode(String nodeName,byte[] bytes){
        ZooKeeper zooKeeper=null;
        //创建一个zk客户端连接对象
        try {
            zooKeeper = new ZooKeeper("47.108.216.244:2181", 10000, new Watcher() {
                //当客户端连接成功时需要做的事情，相当于一个回调
                @Override
                public void process(WatchedEvent watchedEvent) {
                    System.out.println("begin");
                }
            });
            //创建节点
            String result = zooKeeper.create("/"+nodeName, bytes, ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.PERSISTENT);
            System.out.println(result);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (KeeperException e) {
            e.printStackTrace();
        }
        finally {
            if (zooKeeper!=null){
                try {
                    zooKeeper.close();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
