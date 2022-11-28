package main.java.test;

import jdk.nashorn.internal.ir.CallNode;

import java.util.Optional;

/**
 * @Description: Java8 新特性 Optional
 * @Author: Bentao She
 * @Email: harrypotterandsbt@gmail.com
 * @Date: 2022/11/28 16:52
 * @Version: V1.0
 **/

public class OptionalTest {
//    尽量避免使用的地方：
//    1、避免使用Optional.isPresent()来检查实例是否存在，因为这种方式和null != obj没有区别，这样用就没什么意义了。
//
//    2、避免使用Optional.get()方式来获取实例对象，因为使用前需要使用Optional.isPresent()来检查实例是否存在，否则会出现NPE问题。
//
//    3、避免使用Optional作为类或者实例的属性，而应该在返回值中用来包装返回实例对象。
//
//    4、避免使用Optional作为方法的参数，原因同3。
    public static void main(String[] args) {
        Dish dish1 = new Dish("dish1", false, 800, Dish.Type.MEAT);
        Dish dish2 = null;
        //创建的三种方式
        // 1.空Optional
        Optional<Dish> empty = Optional.empty();
        // 2. 非空值创建 of，如果dish1是null，会立刻报错
        Optional<Dish> dishes = Optional.of(dish1);
        // 3. 可接受null的Option
        Optional<Dish> optDish = Optional.ofNullable(dish2);
        //
    }
}
