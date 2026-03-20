package de.labusch.anagram.infrastructure.persistence;

import de.labusch.anagram.domain.AnagramRepository;

import org.jspecify.annotations.NonNull;

import java.util.Collections;
import java.util.HashSet;
import java.util.Objects;
import java.util.Optional;
import java.util.Set;

/**
 * @since 19.03.2026.
 * @author Fin Labusch
 */
public class InMemoryAnagramRepository implements AnagramRepository {

    private final Set<String> anagrams = new HashSet<>();

    @Override
    public Set<String> allAnagrams() {
        return Collections.unmodifiableSet(anagrams);
    }

    @Override
    public Optional<String> anyAnagram() {
        return allAnagrams().stream().findAny();
    }

    @Override
    public void add(@NonNull String anagram) {
        Objects.requireNonNull(anagram);
        anagrams.add(anagram);
    }

}
