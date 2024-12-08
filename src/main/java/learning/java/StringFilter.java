package learning.java;

import java.util.List;
import java.util.stream.Collectors;

public class StringFilter {
    public List<String> filterByKeyword(List<String> lines, String keyword) {
        return lines.stream()
                .filter(line -> line.contains(keyword))
                .collect(Collectors.toList());
    }

    public List<String> filterByRegex(List<String> lines, String regex) {
        return lines.stream()
                .filter(line -> line.matches(regex))
                .collect(Collectors.toList());
    }
}
