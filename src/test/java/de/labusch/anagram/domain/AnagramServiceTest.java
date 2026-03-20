package de.labusch.anagram.domain;

import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;
import java.util.Set;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.empty;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 * Unit-Test zur Klasse @{link AnagramServiceTest}.
 *
 * @since 20.03.2026.
 */
@ExtendWith(MockitoExtension.class)
class AnagramServiceTest {

    @InjectMocks
    private AnagramService service;

    @Mock
    private AnagramRepository repository;

    @Nested
    class AddAnagramTests {
        @Test
        void shouldAddFirstAnagramToRepository() {
            Set<String> anagrams = Set.of("foo");

            when(repository.allAnagrams())
                    .thenReturn(anagrams);

            Set<String> newAnagrams = service.addAnagram("foo");

            assertThat(newAnagrams, equalTo(anagrams));
            verify(repository).add("foo");
            verify(repository).allAnagrams();
        }

        @Test
        void shouldAddAnagramToRepository() {
            when(repository.anyAnagram())
                    .thenReturn(Optional.of("post"));

            service.addAnagram("stop");

            verify(repository).add("stop");
            verify(repository).allAnagrams();
        }

        @Test
        void shouldNotAddNonAnagramToRepository() {
            when(repository.anyAnagram())
                    .thenReturn(Optional.of("post"));

            service.addAnagram("pain");

            verify(repository, never()).add(any());
            verify(repository).allAnagrams();
        }
    }


    @Nested
    class FindAnagramTests {

        @Test
        void shouldFindAnagrams() {
            Set<String> anagrams = Set.of("stop", "post", "pots");
            when(repository.allAnagrams())
                    .thenReturn(anagrams);

            Set<String> foundAnagrams = service.findAnagrams("spot");

            assertThat(foundAnagrams, equalTo(anagrams));
            verify(repository).allAnagrams();
        }

        @Test
        void shouldNotFindAnyAnagramsIfTestFails() {
            Set<String> anagrams = Set.of("stop", "post", "pots");
            when(repository.allAnagrams())
                    .thenReturn(anagrams);

            Set<String> foundAnagrams = service.findAnagrams("pain");

            assertThat(foundAnagrams, is(empty()));
            verify(repository).allAnagrams();
        }

    }

}