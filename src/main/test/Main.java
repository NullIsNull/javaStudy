package main.test;

import java.util.function.Function;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class Main<K, V> {

    public V computeIfAbsent1(K key, Function<? super K, ? extends V> mappingFunction) {
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