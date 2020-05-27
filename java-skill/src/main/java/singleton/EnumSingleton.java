package singleton;

import java.io.Serializable;

/**
 * Created by erik on 2015/9/15.
 */
public class EnumSingleton {

    enum Singleton implements Serializable{
        INSTANCE;

        public static Singleton getInstance(){
            return Singleton.INSTANCE;
        }
    }
}
