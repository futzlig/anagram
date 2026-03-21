package de.labusch.anagram;

import de.labusch.anagram.domain.AnagramRepository;
import de.labusch.anagram.application.AnagramService;
import de.labusch.anagram.infrastructure.persistence.InMemoryAnagramRepository;
import de.labusch.anagram.ui.UIController;

/**
 * @since 19.03.2026.
 */
public class Main {

    public static void main(String[] args) {
        AnagramRepository repository = new InMemoryAnagramRepository();
        AnagramService service = new AnagramService(repository);

        new UIController(service).start();
    }

}
