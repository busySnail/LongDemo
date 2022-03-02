package cn.com.module_design_pattern.pattern.creational.singleton;

/**
 * Created by geely
 */
public class ThreadLocalInstance {
    private static final ThreadLocal<ThreadLocalInstance> threadLocalInstanceThreadLocal
            = ThreadLocal.withInitial(ThreadLocalInstance::new);

    private ThreadLocalInstance() {

    }

    public static ThreadLocalInstance getInstance() {
        return threadLocalInstanceThreadLocal.get();
    }

}
