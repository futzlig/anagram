package de.labusch.anagram.domain;

import java.util.Optional;
import java.util.Set;

/**
 * @since 19.03.2026.
 */
public interface AnagramRepository {

    Set<String> allAnagrams();

    Optional<String> anyAnagram();

    void add(String anagram);

}
