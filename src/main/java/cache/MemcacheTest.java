package cache;

import net.spy.memcached.MemcachedClient;
import java.io.IOException;
import java.net.InetSocketAddress;
import java.util.concurrent.ExecutionException;

public class MemcacheTest {

    static MemcachedClient mc;


    public static void main(String[] args) throws ExecutionException, InterruptedException {
        MemcacheTest spy = new MemcacheTest();
        try {
			mc = new MemcachedClient(new InetSocketAddress("192.168.1.100", 11211));
		} catch (IOException e) {
			e.printStackTrace();
		}
        spy.run();
    }

    public void run() throws ExecutionException, InterruptedException {
    	int s = 0, f = 0;
        for (int i = 0; i < 1000; i++) {
            String key = "key" + i;
            String value = "value" + i;
            if (mc.add(key, 0, value).get()) {
//                System.out.printf("added %s -> %s\n", key, value);
                s++;
            } else {
//                System.out.printf("ERROR ADDING %s -> %s\n", key, value);
                f++;
            }
        }
        System.out.println("s="+s+"   f="+f);
        s = 0;
        f = 0;
        for (int i = 0; i < 1000; i++) {
            String key = "key" + i;
            String value = "value" + (i * i);
            if (mc.set(key, 0, value).get()) {
//                System.out.printf("set %s -> %s\n", key, value);
            	s++;
            } else {
//                System.out.printf("ERROR SETTING %s -> %s\n", key, value);
            	f++;
            }
        }
        System.out.println("s="+s+"   f="+f);
        s = 0;
        f = 0;
        for (int i = 0; i < 1000; i++) {
            String key = "key" + i;
            String value = (String) mc.get(key);
//            System.out.printf("GET %s -> %s\n", key, value);
            s++;

        }
        System.out.println("s="+s+"   f="+f);
        s = 0;
        f = 0;
        for (int i = 0; i < 1000; i++) {
            String key = "key" + i;
            if (mc.delete(key).get()) {
//                System.out.printf("deleted %s\n", key);
            	s++;
            } else {
//                System.out.printf("ERROR DELETING %s\n", key);
            	f++;
            }
        }
        System.out.println("s="+s+"   f="+f);
        mc.shutdown();
    }

}