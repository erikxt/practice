package cache;

import net.spy.memcached.ConnectionFactoryBuilder;
import net.spy.memcached.MemcachedClient;
import net.spy.memcached.auth.AuthDescriptor;
import net.spy.memcached.auth.PlainCallbackHandler;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.util.Collections;
import java.util.concurrent.ExecutionException;

public class MemcacheTest {

    static MemcachedClient mc;


    public static void main(String[] args) throws ExecutionException, InterruptedException {
        MemcacheTest spy = new MemcacheTest();
        try {
			mc = new MemcachedClient(new InetSocketAddress("192.168.1.93", 11211));
		} catch (IOException e) {
			e.printStackTrace();
		}
        spy.run();
    }

    public void run() throws ExecutionException, InterruptedException {
        for (int i = 0; i < 1000; i++) {
            String key = "key" + i;
            String value = "value" + i;
            if (mc.add(key, 0, value).get()) {
                System.out.printf("added %s -> %s\n", key, value);
            } else {
                System.out.printf("ERROR ADDING %s -> %s\n", key, value);
            }
        }
        for (int i = 0; i < 1000; i++) {
            String key = "key" + i;
            String value = "value" + (i * i);
            if (mc.set(key, 0, value).get()) {
                System.out.printf("set %s -> %s\n", key, value);
            } else {
                System.out.printf("ERROR SETTING %s -> %s\n", key, value);
            }
        }
        for (int i = 0; i < 1000; i++) {
            String key = "key" + i;
            String value = (String) mc.get(key);
            System.out.printf("GET %s -> %s\n", key, value);

        }
        for (int i = 0; i < 1000; i++) {
            String key = "key" + i;
            if (mc.delete(key).get()) {
                System.out.printf("deleted %s\n", key);
            } else {
                System.out.printf("ERROR DELETING %s\n", key);
            }
        }
        mc.shutdown();
    }

}