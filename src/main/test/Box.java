package main.test;

import java.util.List;

class Box<T> {
    private T value;

    public void copyValueFrom(Box<? extends T> otherBox) {
        // 通配符捕获，捕获未知类型
        this.value = otherBox.getValue();
    }

    public T getValue() {
        return value;
    }
}

