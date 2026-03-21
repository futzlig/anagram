package de.labusch.anagram.domain;

import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.containsInAnyOrder;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.notNullValue;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 * Unit-Test zur Klasse @{link AnagramServiceTest}.
 *
 * @author Fin Labusch
 * @since 20.03.2026.
 */
@ExtendWith(MockitoExtension.class)
class AnagramServiceTest {

    @InjectMocks
    private AnagramService service;

    @Mock
    private AnagramRepository repository;

    @Nested
    class AddAnagramsTests {

        @Test
        void shouldAddFirstAnagramToRepository() {
            when(repository.add(any(Anagram.class)))
                    .then(args -> args.getArgument(0));

            Anagram anagram = service.addAnagrams("post", "stop");

            assertThat(anagram, is(notNullValue()));
            assertThat(anagram.getAnagrams(), containsInAnyOrder("post", "stop"));
            verify(repository).add(any(Anagram.class));
        }

        @Test
        void shouldAddAnagramToRepository() {
            Anagram existingAnagram = Anagram.valueOf("post");
            when(repository.findAnagram("post"))
                    .thenReturn(Optional.of(existingAnagram));

            Anagram anagram = service.addAnagrams("post", "stop");

            assertThat(anagram, equalTo(existingAnagram));
            assertThat(anagram.getAnagrams(), containsInAnyOrder("post", "stop"));
            verify(repository, never()).add(any());
        }

    }


    @Nested
    class FindAnagramTests {

        @Test
        void shouldFindAnagrams() {
            Anagram existingAnagram = Anagram.valueOf("post");
            when(repository.findAnagram("post"))
                    .thenReturn(Optional.of(existingAnagram));

            Optional<Anagram> foundAnagram = service.findAnagrams("post");

            assertTrue(foundAnagram.isPresent());
            assertThat(foundAnagram.get(), equalTo(existingAnagram));
        }

        @Test
        void shouldNotFindAnyNonExistingAnagrams() {
            Optional<Anagram> foundAnagram = service.findAnagrams("pain");

            assertTrue(foundAnagram.isEmpty());
        }

    }

}