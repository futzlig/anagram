package de.labusch.anagram.domain;

import org.jspecify.annotations.NonNull;

import java.util.HashSet;
import java.util.Map;
import java.util.Objects;
import java.util.Set;

import static java.util.function.Function.identity;
import static java.util.stream.Collectors.counting;
import static java.util.stream.Collectors.groupingBy;
import static java.util.stream.Collectors.joining;

/**
 * @since 21.03.2026.
 */
public class Anagram {

    private final AnagramKey key;
    private final Set<String> anagrams = new HashSet<>();

    private Anagram(String text) {
        this.key = AnagramKey.valueOf(text);
        this.anagrams.add(text);
    }

    public static Anagram valueOf(String text) {
        return new Anagram(text);
    }

    public Set<String> getAnagrams() {
        return anagrams;
    }

    public boolean isAnagram(String text) {
        if (text == null) {
            return false;
        }

        return this.key.equals(AnagramKey.valueOf(text));
    }

    public Anagram addAnagram(@NonNull String text) {
        if (!isAnagram(text)) {
            throw new IllegalArgumentException(text + " is not an anagram of " + this);
        }

        anagrams.add(text);
        return this;
    }

    private static class AnagramKey {

        private final Map<Integer, Long> key;

        private AnagramKey(Map<Integer, Long> key) {
            this.key = key;
        }

        private static AnagramKey valueOf(String text) {
            return new AnagramKey(computeKey(text));
        }

        private static Map<Integer, Long> computeKey(@NonNull String text) {
            text = prepareText(text);
            return text.codePoints()
                    .sorted()
                    .boxed()
                    .collect(groupingBy(identity(), counting()));
        }

        private static String prepareText(String s) {
            return s.toLowerCase().replaceAll("[^a-zäöüß0-9]", "");
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) {
                return true;
            }
            if (o == null || getClass() != o.getClass()) {
                return false;
            }
            AnagramKey that = (AnagramKey) o;
            return Objects.equals(key, that.key);
        }

        @Override
        public int hashCode() {
            return Objects.hash(key);
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Anagram anagram = (Anagram) o;
        return Objects.equals(key, anagram.key);
    }

    @Override
    public int hashCode() {
        return Objects.hash(key);
    }

    @Override
    public String toString() {
        return anagrams.stream().collect(joining(", ", "[", "]"));
    }

}
