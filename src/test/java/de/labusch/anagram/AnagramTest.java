package de.labusch.anagram;

import de.labusch.anagram.domain.Anagram;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.params.provider.Arguments.arguments;

/**
 * Unit-Test zur Klasse @{link Anagram}.
 *
 * @since 19.03.2026.
 * @author Fin Labusch
 */
class AnagramTest {

    private final Anagram anagram = new Anagram();

    @ParameterizedTest
    @MethodSource
    void shouldTestAnagramsPositive(String a1, String a2) {
        boolean test = anagram.isAnagram(a1, a2);
        assertTrue(test);
    }

    private static Stream<Arguments> shouldTestAnagramsPositive() {
        return Stream.of(
                arguments("foo", "foo"),
                arguments("Heart", "Earth"),
                arguments("Opa mag Anna.", "Anna mag Opa."),
                arguments("Eleven plus two", "Twelve plus one")
        );
    }

    @ParameterizedTest
    @MethodSource
    void shouldTestAnagramsNegative(String a1, String a2) {
        boolean test = anagram.isAnagram(a1, a2);
        assertFalse(test);
    }

    private static Stream<Arguments> shouldTestAnagramsNegative() {
        return Stream.of(
                arguments("foo", "bar"),
                arguments("Nee", "Neee")
        );
    }

}