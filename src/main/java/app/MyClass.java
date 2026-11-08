package app;

import annotations.Repeat;

public class MyClass {

    // публичные методы
    public void publicMethodNoAnnotation() {
        System.out.println("Публичный метод без аннотации");
    }

    @Repeat(times = 2)
    public void publicMethodWithAnnotation(String text) {
        System.out.println("Публичный метод с аннотацией: " + text);
    }

    // защищенные методы
    protected void protectedMethodNoAnnotation() {
        System.out.println("Защищенный метод без аннотации");
    }

    @Repeat(times = 3)
    protected void protectedMethodWithAnnotation(int number) {
        System.out.println("Защищенный метод с аннотацией: число " + number);
    }

    // приватные методы
    private void privateMethodNoAnnotation() {
        System.out.println("Приватный метод без аннотации");
    }

    @Repeat(times = 4)
    private void privateMethodWithAnnotation(String name, int age) {
        System.out.println("Приватный метод с аннотацией: " + name + ", возраст " + age);
    }
}
