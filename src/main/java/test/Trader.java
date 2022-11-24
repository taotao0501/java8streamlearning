package main.java.test;

/**
 * @Description: 交易员
 * @Author: Bentao She
 * @Email: harrypotterandsbt@gmail.com
 * @Date: 2022/11/22 14:58
 * @Version: V1.0
 **/

public class Trader {

    private final String name;
    private final String city;
    public Trader(String n, String c){
        this.name = n;
        this.city = c;
    }
    public String getName(){
        return this.name;
    }
    public String getCity(){
        return this.city;
    }

    @Override
    public String toString(){
        return "Trader:"+this.name + " in " + this.city;
    }
}
