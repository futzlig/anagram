package de.labusch.anagram.domain;

import org.jspecify.annotations.NonNull;

import java.util.List;
import java.util.Map;
import java.util.Objects;

import static java.util.function.Function.identity;
import static java.util.stream.Collectors.groupingBy;

/**
 * @author Fin Labusch
 * @since 19.03.2026.
 */
public class Anagram {

    /**
     * Returns true, if a1 is an anagram of a2.
     *
     * @param a1 any text.
     * @param a2 any text.
     * @return true if a1 is an anagram of a2, false otherwise.
     */
    public boolean isAnagram(@NonNull String a1, @NonNull String a2) {
        Objects.requireNonNull(a1);
        Objects.requireNonNull(a2);

        a1 = a1.trim().toLowerCase();
        a2 = a2.trim().toLowerCase();
        if (a1.equals(a2)) {
            return true;
        }

        Map<Integer, List<Integer>> letters1 = a1.codePoints()
                .boxed()
                .collect(groupingBy(identity()));
        Map<Integer, List<Integer>> letters2 = a2.codePoints()
                .boxed()
                .collect(groupingBy(identity()));

        return letters1.equals(letters2);
    }
}
