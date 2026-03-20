package de.labusch.anagram.ui;

import de.labusch.anagram.domain.AnagramService;

import java.io.Console;
import java.util.Set;

import static java.util.stream.Collectors.joining;

/**
 * @since 20.03.2026.
 */
public class UIController {

    private static final Console console = System.console();

    public UIController(AnagramService service) {
        this.service = service;
    }

    enum Choice {
        ENTER,
        TEST,
        QUIT,
        MAIN_MENU
    }

    private final AnagramService service;

    public void start() {
        boolean quit = false;
        while (!quit) {
            switch (mainMenu()) {
                case ENTER:
                    enterAnagram();
                    break;
                case TEST:
                    testAnagram();
                    break;
                case QUIT:
                    quit = true;
                    break;
                default:
            }
        }

        console.printf("Goodbye\n");
    }

    private Choice mainMenu() {
        console.printf("Main Menu: 1 = Enter anagrams  2 = Test anagram  Q: Quit\n");
        console.printf("Your choice: ");
        String choice = System.console().readLine();
        return switch (choice) {
            case "1" -> Choice.ENTER;
            case "2" -> Choice.TEST;
            case "q", "Q", "x", "X" -> Choice.QUIT;
            default -> Choice.MAIN_MENU;
        };
    }

    public void enterAnagram() {
        console.printf("Enter anagrams. Press Return to leave.\n");
        while (true) {
            console.printf("Enter word or phrase: ");
            String text = console.readLine();
            if (text.isBlank()) {
                break;
            }

            Set<String> anagrams = service.addAnagram(text);
            console.printf(
                    anagrams.stream()
                            .collect(joining(", ", "Anagrams: [", "]\n"))
            );
        }
    }

    public void testAnagram() {
        console.printf("Test anagrams. Press Return to leave.\n");
        while (true) {
            console.printf("Enter word or phrase: ");
            String text = console.readLine();
            if (text.isBlank()) {
                break;
            }

            Set<String> anagrams = service.findAnagrams(text);
            console.printf(
                    anagrams.stream()
                            .collect(joining(", ", "Found anagrams: [", "]\n"))
            );
        }
    }
}
