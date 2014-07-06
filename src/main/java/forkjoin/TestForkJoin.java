package forkjoin;

import java.util.concurrent.ForkJoinPool;

public class TestForkJoin {
    public static void main(String[] args) {
        LargeInteger test = new LargeInteger();
        // Check the number of available processors
        int nThreads = Runtime.getRuntime().availableProcessors();
        System.out.println(nThreads);
        SplitTask mfj = new SplitTask(test.getList());
        ForkJoinPool pool = new ForkJoinPool(nThreads);       
        long result = pool.invoke(mfj);
        System.out.println(SplitTask.i);
        System.out.println("Done. Result: " + result);     
    }
}
