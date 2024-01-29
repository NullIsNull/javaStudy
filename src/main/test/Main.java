package main.test;

import java.util.*;
import java.util.function.Function;
import java.util.function.Predicate;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class Main<K, V> {

    public V computeIfAbsent1(K key, Function<? super K, ? extends V> mappingFunction) {
        Objects.<Function<?, ?>>requireNonNull(mappingFunction);
        V newValue = mappingFunction.apply(key);
        return newValue;
    }

    public static void main(String[] args) {
        TypeC typeC = new TypeC();
        Main<TypeC, String> main = new Main<>();
        Function<TypeB, String> function = new Function<>() {
            @Override
            public String apply(TypeB b) {
                System.out.println("ccc");
                b.f();
                return "Hello";
            }
        };
        main.computeIfAbsent1(typeC, function);

        List<Integer> list = new ArrayList<>();
        for (int i = 1; i <= 10; ++i) {
            list.add(i);
        }
        list.removeIf(new Predicate<Integer>() {
            @Override
            public boolean test(Integer filter) {
                return filter % 2 == 0;
            }
        });

        List<String> lst = new ArrayList<>();
        lst.add("hello");
        lst.add("world");
        lst.add("hello");
        for (String s : lst) {
            System.out.println(s);
        }
        System.out.println("-------");
        Set<String> sets = new HashSet<>(lst);
        for (String set : sets) {
            System.out.println(set);
        }
    }

    static class TypeB {
        void f() {
            System.out.println("B");
        }
    }

    static class TypeA {
        void f() {
            System.out.println("A");
        }
    }

    static class TypeC extends TypeB {
        void f() {
            System.out.println("C");
        }
    }

}