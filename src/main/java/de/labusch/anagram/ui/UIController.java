package de.labusch.anagram.ui;

import de.labusch.anagram.application.AnagramService;

import java.io.Console;
import java.util.Optional;

/**
 * @author Fin Labusch
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
        console.printf("\nMain Menu: 1 = Enter anagrams  2 = Test anagram  Q: Quit\n");
        console.printf("Your choice: ");
        String choice = console.readLine();
        return switch (choice) {
            case "1" -> Choice.ENTER;
            case "2" -> Choice.TEST;
            case "q", "Q", "x", "X" -> Choice.QUIT;
            default -> Choice.MAIN_MENU;
        };
    }

    public void enterAnagram() {
        prompt("Enter word or phrase: ")
                .ifPresent(text1 ->
                        prompt("Enter second word or phrase: ")
                                .ifPresent(text2 -> {
                                    boolean test = service.areAnagrams(text1, text2);
                                    if (test) {
                                        service.addAnagrams(text1, text2);
                                        console.printf("'%s' is an anagram of '%s'.\n", text1, text2);
                                    } else {
                                        console.printf("'%s' and '%s' are not anagrams.\n", text1, text2);
                                    }
                                })
                );
    }


    public void testAnagram() {
        console.printf("Find anagrams.\n");
        prompt("Enter word or phrase: ")
                .ifPresent(text ->
                        service.findAnagrams(text)
                                .ifPresentOrElse(
                                        a -> console.printf("%s\n", a),
                                        () -> console.printf("[]\n")
                                )
                );
    }

    private Optional<String> prompt(String message) {
        console.printf(message);
        String text = console.readLine().trim();
        if (text.isBlank()) {
            console.printf("No text entered.");
            return Optional.empty();
        }

        return Optional.of(text);
    }

}

