package de.labusch.anagram.domain;

import org.jspecify.annotations.NonNull;

import java.util.Optional;
import java.util.Set;

/**
 * Repository for anagrams.
 *
 * @since 19.03.2026.
 * @author Fin Labusch
 */
public interface AnagramRepository {

    /**
     * Return all anagrams in this repository.
     */
    Set<Anagram> allAnagrams();

    /**
     * Returns any anagram - if one exists.
     */
    Optional<Anagram> findAnagram(@NonNull String text);

    /**
     * Adds an anagram to this repository.
     */
    Anagram add(@NonNull Anagram anagram);

}
