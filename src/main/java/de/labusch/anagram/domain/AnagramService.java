package de.labusch.anagram.domain;

import org.jspecify.annotations.NonNull;

import java.util.Set;

import static java.util.stream.Collectors.toUnmodifiableSet;

/**
 * @since 19.03.2026.
 * @author Fin Labusch
 */
public class AnagramService {

    private final AnagramRepository repository;
    private final Anagram anagram = new Anagram();

    public AnagramService(AnagramRepository repository) {
        this.repository = repository;
    }

    public Set<String> addAnagram(@NonNull String text) {
        String anyAnagram = repository.anyAnagram()
                .orElse(text);
        if (anagram.isAnagram(anyAnagram, text)) {
            repository.add(text);
        }

        return repository.allAnagrams();
    }

    public Set<String> findAnagrams(@NonNull String text) {
        Set<String> anagrams = repository.allAnagrams();
        return anagrams.stream()
                .filter(a -> anagram.isAnagram(text, a))
                .collect(toUnmodifiableSet());
    }

}
