package de.labusch.anagram.domain;

import java.util.List;
import java.util.Map;
import java.util.Objects;

import static java.util.stream.Collectors.groupingBy;

/**
 * @since 19.03.2026.
 */
public class Anagram {
    public boolean isAnagram(String a1, String a2) {
        Objects.requireNonNull(a1);
        Objects.requireNonNull(a2);

        a1 = a1.trim().toLowerCase();
        a2 = a2.trim().toLowerCase();
        if (a1.equals(a2)) {
            return true;
        }

        Map<Integer, List<Integer>> letters1 = a1.codePoints()
                .boxed()
                .collect(groupingBy(x -> x));
        Map<Integer, List<Integer>> letters2 = a2.codePoints()
                .boxed()
                .collect(groupingBy(x -> x));

        return letters1.equals(letters2);
    }
}
