package java8test;

import sun.misc.Unsafe;

import java.lang.reflect.Field;

/**
 * Created by erik on 2015/9/12.
 */
public class TestUnsafe {

    private static final Unsafe U;

    static Field unsafeField = null;

    private static final long valueOffset;
    private volatile int value;

    static {
        try {
            unsafeField = Unsafe.class.getDeclaredField("theUnsafe");
            unsafeField.setAccessible(true);
            U = (Unsafe) unsafeField.get(null);
            valueOffset = U.objectFieldOffset
                    (TestUnsafe.class.getDeclaredField("value"));
        } catch (Exception ex) { throw new Error(ex); }
    }

    public int testIncrementAndGet(){
        int a = value;
        int b = value + 1;
        U.compareAndSwapInt(this, valueOffset, a, b);
        return value;
    }

    public void set(int value){
        U.getAndSetInt(this, valueOffset, value);
    }

    public int getValue(){
        return value;
    }

    public static void main(String[] args) {
        TestUnsafe t = new TestUnsafe();
        t.set(100);
        int result = t.testIncrementAndGet();
        System.out.println("result:" + result);
    }
}
