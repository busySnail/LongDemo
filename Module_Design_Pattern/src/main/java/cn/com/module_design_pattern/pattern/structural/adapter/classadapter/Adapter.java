package cn.com.module_design_pattern.pattern.structural.adapter.classadapter;

/**
 * Created by geely
 */
public class Adapter extends Adaptee implements Target{
    @Override
    public void request() {
        //...
        super.adapteeRequest();
        //...
    }
}
