package cn.com.module_design_pattern.principle.compositionaggregation;

/**
 * Created by geely
 */
public class Test {
    public static void main(String[] args) {
        ProductDao productDao = new ProductDao();
        productDao.setDbConnection(new PostgreSQLConnection());
        productDao.addProduct();
    }
}
