package forkjoin;

import java.util.Arrays;
import java.util.concurrent.RecursiveTask;
import java.util.concurrent.atomic.AtomicInteger;

@SuppressWarnings("serial")
public class SplitTask extends RecursiveTask<Long> {
    private int[] list;
    public long result;
    public static int i = 0;
    public static AtomicInteger j = new AtomicInteger(0);
    public SplitTask(int[] array) {
        this.list = array;
    }
    @Override
    protected Long compute() {
        if (list.length == 1) {
            result = list[0];
        } else {
            int midpoint = list.length / 2;
            int[] l1 = Arrays.copyOfRange(list, 0, midpoint);
            int[] l2 = Arrays.copyOfRange(list, midpoint, list.length);
            SplitTask s1 = new SplitTask(l1);
            SplitTask s2 = new SplitTask(l2);
            s1.fork();
            s2.fork();
            result = s1.join() + s2.join();//合并结果          
        }
        synchronized (SplitTask.class) {
        	i++;
		}    
//        j.incrementAndGet();
		return result;
    }
}

