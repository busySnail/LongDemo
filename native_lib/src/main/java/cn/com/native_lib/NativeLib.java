package cn.com.native_lib;

public class NativeLib {

    // Used to load the 'native_lib' library on application startup.
    static {
        System.loadLibrary("native_lib");
    }

    /**
     * 从Java调用Native
     */
    public native String stringFromJNI();

    public native String testCallMethod();  //非静态

    public static native String testStaticCallMethod();//静态

    /**
     * Native回调Java Callback
     */
    public native void startDownload(OnFinishListener listener);

    public interface OnFinishListener {
        void onFinish(String msg);
    }

    /**
     * 从Native调用Java
     */
    public String describe() {
        return "非静态方法";
    }


    public static String staticDescribe() {
        return "静态方法";
    }


    ;
}