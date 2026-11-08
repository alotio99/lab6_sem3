package app;

import annotations.Repeat;
import java.lang.reflect.Method;

public class Invoker {
    public static void main(String[] args) throws Exception {
        MyClass myClass = new MyClass();

        System.out.println("----- Вызов аннотированных методов -----");

        // получаем все методы класса MyClass
        Method[] methods = MyClass.class.getDeclaredMethods();

        for (Method method : methods) {
            // проверяем, есть ли над методом аннотация
            if (method.isAnnotationPresent(Repeat.class)) {

                // достаем параметр times() из аннотации
                Repeat repeat = method.getAnnotation(Repeat.class);
                int times = repeat.times();

                // берем только protected и private методы
                String modifiers = java.lang.reflect.Modifier.toString(method.getModifiers());
                if (modifiers.contains("public")) {
                    continue;
                }

                System.out.println("\nВызываем метод: " + method.getName() + " (раз: " + times + ")");

                // разрешаем вызов приватных методов
                method.setAccessible(true);

                // вызываем метод нужное количество раз
                for (int i = 0; i < times; i++) {
                    // передаем аргументы в зависимости от типа параметров метода
                    Class<?>[] paramTypes = method.getParameterTypes();
                // invoke() не знает заранее, какие параметры
                    // принимает вызываемый метод
                    if (paramTypes.length == 1) {
                        if (paramTypes[0] == int.class) {
                            method.invoke(myClass, 42);
                        } else if (paramTypes[0] == String.class) {
                            method.invoke(myClass, "Привет");
                        }
                    } else if (paramTypes.length == 2) {
                        method.invoke(myClass, "Иван", 25);
                    } else {
                        method.invoke(myClass); // если параметров нет
                    }
                }
            }
        }
    }
}