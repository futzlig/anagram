package de.labusch.anagram;

import de.labusch.anagram.domain.Anagram;

import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.contains;
import static org.hamcrest.Matchers.containsInAnyOrder;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.params.provider.Arguments.arguments;

/**
 * Unit-Test zur Klasse @{link AnagramTester}.
 *
 * @author Fin Labusch
 * @since 19.03.2026.
 */
class AnagramTest {

    @Nested
    class ConstructionTests {

        @Test
        void shouldConstructEntity() {
            Anagram anagram = Anagram.valueOf("foo");

            assertThat(anagram.getAnagrams(), contains("foo"));
        }

    }

    @Nested
    class AddAnagramTests {

        @Test
        void shouldAddAnagram() {
            Anagram anagram = Anagram.valueOf("post");

            anagram.addAnagram("stop");

            assertThat(anagram.getAnagrams(), containsInAnyOrder("post", "stop"));
        }

        @Test
        void shouldThrowExecptionIfNotAnAnagram() {
            Anagram anagram = Anagram.valueOf("post");

            assertThrows(IllegalArgumentException.class, () -> anagram.addAnagram("bad!"));
        }

    }

    @Nested
    class IsAnagramTests {
        @ParameterizedTest
        @MethodSource
        void shouldTestAnagramsPositive(String t1, String t2) {
            Anagram a1 = Anagram.valueOf(t1);

            boolean test = a1.isAnagram(t2);
            assertTrue(test);
        }

        private static Stream<Arguments> shouldTestAnagramsPositive() {
            return Stream.of(
                    arguments("foo", "foo"),
                    arguments("Heart", "Earth"),
                    arguments("Opa mag Anna.", "Anna mag Opa."),
                    arguments("Eleven plus two", "Twelve plus one"),
                    arguments("William Shakespeare", "I'll make a wise phrase."),
                    arguments("Träume", "räumte")
            );
        }

        @ParameterizedTest
        @MethodSource
        void shouldTestAnagramsNegative(String t1, String t2) {
            Anagram a1 = Anagram.valueOf(t1);

            boolean test = a1.isAnagram(t2);

            assertFalse(test);
        }

        private static Stream<Arguments> shouldTestAnagramsNegative() {
            return Stream.of(
                    arguments("foo", "bar"),
                    arguments("Nee", "Neee"),
                    arguments("Träume", "rüumte")
            );
        }
    }

}