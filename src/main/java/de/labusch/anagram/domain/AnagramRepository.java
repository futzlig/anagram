package de.labusch.anagram.domain;

import org.jspecify.annotations.NonNull;

import java.util.Optional;
import java.util.Set;

/**
 * Stores all variants of a single anagram.
 *
 * @since 19.03.2026.
 * @author Fin Labusch
 */
public interface AnagramRepository {

    /**
     * Returns all anagrams in this repository.
     */
    Set<String> allAnagrams();

    /**
     * Returns any anagram - if one exists.
     */
    Optional<String> anyAnagram();

    /**
     * Adds an anagram to this repository.
     */
    void add(@NonNull String anagram);

}
