package learning.java;

import java.io.IOException;
import java.util.List;
import java.util.Scanner;

public class FilterApp {
    public static void main(String[] args) {
        FileHandler fileHandler = new FileHandler();
        StringFilter stringFilter = new StringFilter();

        Scanner scanner = new Scanner(System.in);
        System.out.print("Введіть шлях до вхідного файлу: ");
        String inputFile = scanner.nextLine();

        System.out.print("Введіть шлях до вихідного файлу: ");
        String outputFile = scanner.nextLine();

        System.out.print("Введіть ключове слово або регулярний вираз: ");
        String filterCriteria = scanner.nextLine();

        System.out.print("Фільтрувати за ключовим словом (1) чи регулярним виразом (2): ");
        int filterType = scanner.nextInt();

        try {
            List<String> lines = fileHandler.readFile(inputFile);
            List<String> filteredLines;

            if (filterType == 1) {
                filteredLines = stringFilter.filterByKeyword(lines, filterCriteria);
            } else if (filterType == 2) {
                filteredLines = stringFilter.filterByRegex(lines, filterCriteria);
            } else {
                System.out.println("Невідомий тип фільтрування.");
                return;
            }

            fileHandler.writeFile(outputFile, filteredLines);
            System.out.println("Рядки успішно відфільтровано та збережено у " + outputFile);

        } catch (IOException e) {
            System.err.println("Помилка роботи з файлами: " + e.getMessage());
        }
    }
}
