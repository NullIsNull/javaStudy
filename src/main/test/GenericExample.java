package main.test;

import java.util.Arrays;
import java.util.List;

public class GenericExample {

    public static void main(String[] args) {
        List<String> stringList = Arrays.asList("Java", "C++", "Python");
    }

    public void foo(List<String> stringList) {
        System.out.println("111");
    }

}
