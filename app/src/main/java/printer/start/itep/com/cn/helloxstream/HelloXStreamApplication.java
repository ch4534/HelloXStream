package printer.start.itep.com.cn.helloxstream;

import android.app.Application;
import android.content.Context;

/**
 * Created by admin on 2016/6/20.
 */
public class HelloXStreamApplication extends Application {

    private static HelloXStreamApplication sApplication;

    public static Context getContext(){
        return sApplication;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        sApplication = this;
    }
}
