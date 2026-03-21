package de.labusch.anagram.domain;

import org.jspecify.annotations.NonNull;

import java.util.Objects;
import java.util.Optional;

/**
 * @author Fin Labusch
 * @since 19.03.2026.
 */
public class AnagramService {

    private final AnagramRepository repository;

    public AnagramService(AnagramRepository repository) {
        this.repository = repository;
    }

    public boolean areAnagrams(@NonNull String text1, @NonNull String text2) {
        Objects.requireNonNull(text1);
        Objects.requireNonNull(text2);

        return Anagram.valueOf(text1).isAnagram(text2);
    }

    public Anagram addAnagrams(@NonNull String text1, @NonNull String text2) {
        Objects.requireNonNull(text1);
        Objects.requireNonNull(text2);

        Anagram anagram = repository.findAnagram(text1)
                .orElseGet(() -> repository.add(Anagram.valueOf(text1)));
        anagram.addAnagram(text2);
        return anagram;
    }

    public Optional<Anagram> findAnagrams(@NonNull String text) {
        Objects.requireNonNull(text);

        return repository.findAnagram(text);
    }

}
