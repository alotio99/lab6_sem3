package app;

import java.io.IOException;
import java.nio.file.*;
import java.util.Comparator;
import java.util.stream.Stream;

public class FileManager {
    public static void main(String[] args) {
        String surname = "Dudoshnikov";
        String name = "Ilya";

        try {
            System.out.println("----- Задание №2. Работа с файлами -----");

            // создаем директорию surname
            Path rootDir = Paths.get(surname);
            Files.createDirectories(rootDir);
            System.out.println("Создана директория: " + rootDir.toAbsolutePath());

            // создаем файл name внутри surname
            Path mainFile = rootDir.resolve(name);
            if (Files.exists(mainFile)) Files.delete(mainFile); // удаляем старый, если остался с прошлого запуска
            Files.createFile(mainFile);
            System.out.println("Создан файл: " + mainFile.getFileName());

            // создаем вложенные директории dir1/dir2/dir3
            Path dir3 = rootDir.resolve("dir1/dir2/dir3");
            Files.createDirectories(dir3);

            // копируем файл name в dir3
            Path copiedFile = dir3.resolve(name);
            Files.copy(mainFile, copiedFile, StandardCopyOption.REPLACE_EXISTING);
            System.out.println("Файл скопирован в: " + dir3);

            // создаем файл file1 в dir1
            Path file1 = rootDir.resolve("dir1/file1");
            if (!Files.exists(file1)) Files.createFile(file1);

            // создаем файл file2 в dir2
            Path file2 = rootDir.resolve("dir1/dir2/file2");
            if (!Files.exists(file2)) Files.createFile(file2);

            System.out.println("\n----- Рекурсивный обход -----");
            // рекурсивный обход через walk()
            try (Stream<Path> paths = Files.walk(rootDir)) {
                paths.filter(p -> !p.equals(rootDir)) // пропускаем саму корневую папку
                        .forEach(p -> {
                            // проверяем, папка это или файл, и ставим метку
                            if (Files.isDirectory(p)) {
                                System.out.println("D: " + p.getFileName());
                            } else {
                                System.out.println("F: " + p.getFileName());
                            }
                        });
            }

            // удаление директории dir1 со всем содержимым
            System.out.println("\n----- Удаление dir1 -----");
            Path dir1 = rootDir.resolve("dir1");

            // сортируем в обратном порядке (reversed), чтобы
            // удалить файлы внутри, а потом снаружи
            try (Stream<Path> paths = Files.walk(dir1)) {
                paths.sorted(Comparator.reverseOrder())
                        .forEach(p -> {
                            try {
                                Files.delete(p);
                            } catch (IOException e) {
                                System.out.println("Не удалось удалить: " + p);
                            }
                        });
            }
            System.out.println("Директория dir1 успешно удалена!");

        } catch (IOException e) {
            System.err.println("Ошибка ввода-вывода: " + e.getMessage());
        }
    }
}
