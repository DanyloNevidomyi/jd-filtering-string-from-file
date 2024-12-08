package learning.java;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.io.File;
import java.io.IOException;
import java.util.List;

class FilterAppTest {
    // Тест для фільтрації за ключовим словом
    @Test
    void testFilterByKeyword() {
        StringFilter filter = new StringFilter();
        List<String> inputLines = List.of(
                "Помилка в коді",
                "Успішно виконано",
                "Помилка підключення",
                "Успішний запуск"
        );

        List<String> expected = List.of(
                "Помилка в коді",
                "Помилка підключення"
        );

        List<String> actual = filter.filterByKeyword(inputLines, "Помилка");
        assertEquals(expected, actual);
    }

    // Тест для фільтрації за регулярним виразом
    @Test
    void testFilterByRegex() {
        StringFilter filter = new StringFilter();
        List<String> inputLines = List.of(
                "Помилка 123",
                "Помилка 456",
                "Успішно виконано",
                "Помилка 789"
        );

        List<String> expected = List.of(
                "Помилка 123",
                "Помилка 456",
                "Помилка 789"
        );

        List<String> actual = filter.filterByRegex(inputLines, "Помилка \\d+");
        assertEquals(expected, actual);
    }

    // Тест для читання файлу
    @Test
    void testReadFile() throws IOException {
        FileHandler handler = new FileHandler();
        String testInputFilePath = "src/test/resources/testInput.txt";

        new File("src/test/resources").mkdirs();
        new File(testInputFilePath).createNewFile();
        try (java.io.BufferedWriter writer = new java.io.BufferedWriter(new java.io.FileWriter(testInputFilePath))) {
            writer.write("Тест 1\nТест 2\nТест 3");
        }

        List<String> lines = handler.readFile(testInputFilePath);
        assertEquals(3, lines.size());
        assertEquals("Тест 1", lines.get(0));
        assertEquals("Тест 2", lines.get(1));
        assertEquals("Тест 3", lines.get(2));

        new File(testInputFilePath).delete();
    }

    // Тест для запису до файлу
    @Test
    void testWriteFile() throws IOException {
        FileHandler handler = new FileHandler();
        String testOutputFilePath = "src/test/resources/testOutput.txt";

        List<String> testLines = List.of("Рядок 1", "Рядок 2", "Рядок 3");

        handler.writeFile(testOutputFilePath, testLines);

        List<String> readLines = handler.readFile(testOutputFilePath);
        assertEquals(3, readLines.size());
        assertEquals("Рядок 1", readLines.get(0));
        assertEquals("Рядок 2", readLines.get(1));
        assertEquals("Рядок 3", readLines.get(2));

        new File(testOutputFilePath).delete();
    }

}