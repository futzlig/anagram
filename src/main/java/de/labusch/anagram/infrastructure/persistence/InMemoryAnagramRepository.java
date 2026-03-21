package de.labusch.anagram.infrastructure.persistence;

import de.labusch.anagram.domain.Anagram;
import de.labusch.anagram.domain.AnagramRepository;

import org.jspecify.annotations.NonNull;

import java.util.HashSet;
import java.util.Objects;
import java.util.Optional;
import java.util.Set;

import static java.util.Collections.unmodifiableSet;

/**
 * @author Fin Labusch
 * @since 19.03.2026.
 */
public class InMemoryAnagramRepository implements AnagramRepository {

    private final Set<Anagram> anagrams = new HashSet<>();

    @Override
    public Set<Anagram> allAnagrams() {
        return unmodifiableSet(anagrams);
    }

    @Override
    public Optional<Anagram> findAnagram(@NonNull String text) {
        Objects.requireNonNull(text);

        return anagrams.stream()
                .filter(a -> a.isAnagram(text))
                .findAny();
    }

    @Override
    public Anagram add(@NonNull Anagram anagram) {
        Objects.requireNonNull(anagram);

        anagrams.add(anagram);
        return anagram;
    }

}
