import org.apache.zookeeper.*;
import org.apache.zookeeper.client.ConnectStringParser;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.util.List;

public class ZookepperDemo {

    private String ConnectString = "hadoop102:2181,hadoop103:2181,hadoop104:2181";
    private int sessionTimeout =2000;
    private ZooKeeper zkClient;

    @Before
    public void init() throws IOException {


        zkClient = new ZooKeeper (ConnectString,sessionTimeout, new Watcher () {
              public void process(WatchedEvent watchedEvent) {
//     shou dao shi jian tong zhi hou de hui diao han shu  (yong hu de yewu luo ji )
                  System.out.println ("---------------------------");
                  List<String> children = null;
                  try {
                      children = zkClient.getChildren ("/", true);
                      for (String child:children)
                          System.out.println (child);
                      System.out.println ("--------------------------------");
                  } catch (KeeperException e) {
                      e.printStackTrace ();
                  } catch (InterruptedException e) {
                      e.printStackTrace ();
                  }

              }
          });
    }



@Test
//    chuang jian jie dian
    public void childNode() throws KeeperException, InterruptedException {
        String path = zkClient.create ("/Travis1", "ssssssss".getBytes (), ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.PERSISTENT);
        System.out.println (path);
    }
//    huo qu zi jie dian bing  jian kong shu ju de bian hua

    @Test
    public void getDataAndWatch() throws KeeperException, InterruptedException {


        List<String> children = zkClient.getChildren ("/", false);
        for (String child:children
             ) {
            System.out.println (child);
        }
        Thread.sleep (Long.MAX_VALUE);
    }
}
