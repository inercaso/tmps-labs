import core.FilmArchive;
import iterator.ArchiveIterator;
import memento.FilmSnapshot;
import model.Condition;
import model.FilmReel;
import strategy.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * Demonstration of the Vintage Film Reel Archive system.
 * Showcases all three behavioral patterns: Memento, Iterator, Strategy.
 */
public class Main {
    
    private static final int WIDTH = 62;
    private static FilmArchive archive;
    private static Scanner scanner;
    private static List<FilmReel> lastBrowseResults = new ArrayList<>();

    private static String repeat(String str, int count) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < count; i++) {
            sb.append(str);
        }
        return sb.toString();
    }

    public static void main(String[] args) {
        scanner = new Scanner(System.in);
        archive = new FilmArchive("The Obsidian Vault");
        populateArchive(archive);
        
        clearScreen();
        printWelcome();
        pause(1500);
        
        boolean running = true;
        while (running) {
            clearScreen();
            printHeader();
            printMainMenu();
            
            String choice = prompt("Your choice");
            
            switch (choice.toLowerCase()) {
                case "1": case "b": case "browse":
                    browseFlow();
                    break;
                case "2": case "r": case "restore":
                    restoreFlow(null);
                    break;
                case "3": case "h": case "history":
                    historyFlow(null);
                    break;
                case "4": case "d": case "demo":
                    runAutomatedDemo();
                    break;
                case "0": case "q": case "quit": case "exit":
                    running = false;
                    break;
                default:
                    showMessage("Invalid option. Try again.");
            }
        }
        
        clearScreen();
        printGoodbye();
        scanner.close();
    }

    // ==================== BROWSE FLOW ====================

    private static void browseFlow() {
        boolean inBrowse = true;
        while (inBrowse) {
            clearScreen();
            printHeader();
            printBox("BROWSE ARCHIVE");
            System.out.println();
            System.out.println("  How would you like to explore the collection?");
            System.out.println();
            System.out.println("  [1] All Films          - Sequential order");
            System.out.println("  [2] By Decade          - Filter by era (1920s, 1930s...)");
            System.out.println("  [3] By Genre           - Horror, Drama, Fantasy...");
            System.out.println("  [4] By Condition       - Worst first (restoration priority)");
            System.out.println();
            System.out.println("  [0] Back to main menu");
            System.out.println();
            
            String choice = prompt("Select view");
            
            switch (choice) {
                case "1":
                    lastBrowseResults = showFilmsWithIterator(
                        archive.createSequentialIterator(), 
                        "COMPLETE COLLECTION"
                    );
                    afterBrowseActions();
                    break;
                case "2":
                    browseByDecade();
                    break;
                case "3":
                    browseByGenre();
                    break;
                case "4":
                    lastBrowseResults = showFilmsWithIterator(
                        archive.createConditionIterator(), 
                        "RESTORATION PRIORITY (worst first)"
                    );
                    afterBrowseActions();
                    break;
                case "0":
                    inBrowse = false;
                    break;
                default:
                    showMessage("Invalid option.");
            }
        }
    }

    private static void browseByDecade() {
        clearScreen();
        printHeader();
        printBox("BROWSE BY DECADE");
        System.out.println();
        System.out.println("  Available decades in collection:");
        System.out.println("  [1] 1920s    [2] 1930s");
        System.out.println();
        
        String choice = prompt("Select decade (or enter year like 1920)");
        
        int decade;
        switch (choice) {
            case "1": decade = 1920; break;
            case "2": decade = 1930; break;
            default:
                try {
                    decade = Integer.parseInt(choice);
                    decade = (decade / 10) * 10; // normalize to decade
                } catch (NumberFormatException e) {
                    showMessage("Invalid decade.");
                    return;
                }
        }
        
        lastBrowseResults = showFilmsWithIterator(
            archive.createDecadeIterator(decade), 
            "FILMS FROM THE " + decade + "s"
        );
        
        if (!lastBrowseResults.isEmpty()) {
            afterBrowseActions();
        } else {
            showMessage("No films found from the " + decade + "s.");
        }
    }

    private static void browseByGenre() {
        clearScreen();
        printHeader();
        printBox("BROWSE BY GENRE");
        System.out.println();
        System.out.println("  Available genres:");
        System.out.println("  [1] Horror       [2] Drama       [3] Fantasy");
        System.out.println("  [4] Documentary  [5] Surrealist");
        System.out.println();
        
        String choice = prompt("Select genre");
        
        String genre;
        switch (choice) {
            case "1": genre = "Horror"; break;
            case "2": genre = "Drama"; break;
            case "3": genre = "Fantasy"; break;
            case "4": genre = "Documentary"; break;
            case "5": genre = "Surrealist"; break;
            default: genre = choice; break;
        }
        
        lastBrowseResults = showFilmsWithIterator(
            archive.createGenreIterator(genre), 
            genre.toUpperCase() + " FILMS"
        );
        
        if (!lastBrowseResults.isEmpty()) {
            afterBrowseActions();
        } else {
            showMessage("No films found in genre: " + genre);
        }
    }

    private static List<FilmReel> showFilmsWithIterator(ArchiveIterator iterator, String title) {
        List<FilmReel> results = new ArrayList<>();
        
        clearScreen();
        printHeader();
        printBox(title);
        System.out.println();
        
        int count = 1;
        while (iterator.hasNext()) {
            FilmReel film = iterator.next();
            results.add(film);
            printFilmCard(count++, film);
        }
        
        if (results.isEmpty()) {
            System.out.println("  No films found.");
        }
        
        System.out.println("  " + repeat("-", 54));
        System.out.printf("  Showing %d film(s)%n", results.size());
        
        return results;
    }

    private static void afterBrowseActions() {
        System.out.println();
        System.out.println("  What would you like to do?");
        System.out.println();
        System.out.println("  [S] Select a film to restore");
        System.out.println("  [H] View a film's history");
        System.out.println("  [B] Browse differently");
        System.out.println("  [M] Main menu");
        System.out.println();
        
        String choice = prompt("Action").toLowerCase();
        
        switch (choice) {
            case "s": case "select": case "restore":
                selectFilmFromResults("restore");
                break;
            case "h": case "history":
                selectFilmFromResults("history");
                break;
            case "b": case "browse":
                // Returns to browse menu naturally
                break;
            case "m": case "menu": case "0":
                lastBrowseResults.clear();
                break;
        }
    }

    private static void selectFilmFromResults(String action) {
        if (lastBrowseResults.isEmpty()) {
            showMessage("No films to select from.");
            return;
        }
        
        String input = prompt("Enter film number (1-" + lastBrowseResults.size() + ")");
        
        try {
            int index = Integer.parseInt(input) - 1;
            if (index >= 0 && index < lastBrowseResults.size()) {
                FilmReel selected = lastBrowseResults.get(index);
                if (action.equals("restore")) {
                    restoreFlow(selected);
                } else {
                    historyFlow(selected);
                }
            } else {
                showMessage("Invalid selection.");
            }
        } catch (NumberFormatException e) {
            showMessage("Please enter a number.");
        }
    }

    // ==================== RESTORE FLOW ====================

    private static void restoreFlow(FilmReel preselected) {
        FilmReel film = preselected;
        
        // If no film preselected, let user choose
        if (film == null) {
            clearScreen();
            printHeader();
            printBox("SELECT FILM TO RESTORE");
            System.out.println();
            
            List<FilmReel> films = archive.getAllFilms();
            for (int i = 0; i < films.size(); i++) {
                FilmReel f = films.get(i);
                String status = getConditionStatus(f.getCondition().getOverallScore());
                System.out.printf("  [%d] %-32s %s %s%n", 
                    i + 1, f.getTitle(), 
                    f.getCondition().getProgressBar(),
                    status);
            }
            System.out.println();
            System.out.println("  [0] Back");
            System.out.println();
            
            String input = prompt("Select film");
            if (input.equals("0")) return;
            
            try {
                int index = Integer.parseInt(input) - 1;
                if (index >= 0 && index < films.size()) {
                    film = films.get(index);
                } else {
                    showMessage("Invalid selection.");
                    return;
                }
            } catch (NumberFormatException e) {
                showMessage("Please enter a number.");
                return;
            }
        }
        
        // Show film details and restoration options
        clearScreen();
        printHeader();
        printBox("RESTORE: " + film.getTitle());
        System.out.println();
        System.out.println("  Current State:");
        System.out.println("  " + repeat("-", 50));
        System.out.printf("  Director:    %s%n", film.getDirector());
        System.out.printf("  Year:        %d%n", film.getYear());
        System.out.printf("  Condition:   %s%n", film.getCondition().getProgressBar());
        System.out.printf("  Status:      %s%n", film.getCondition().getPhysicalState());
        System.out.println();
        System.out.println("  " + film.getCondition().toString());
        System.out.println();
        System.out.println("  " + repeat("-", 50));
        System.out.println("  Choose Restoration Strategy:");
        System.out.println();
        
        RestorationStrategy[] strategies = {
            new DigitalRemasterStrategy(),
            new ChemicalTreatmentStrategy(),
            new FrameByFrameStrategy()
        };
        
        for (int i = 0; i < strategies.length; i++) {
            System.out.printf("  [%d] %s%n", i + 1, strategies[i].getName());
            System.out.printf("      Cost: $%,.0f  |  Time: %s%n", 
                strategies[i].estimateCost(), strategies[i].estimateTime());
            System.out.printf("      %s%n", strategies[i].getDescription());
            System.out.println();
        }
        
        System.out.println("  [H] View restoration history first");
        System.out.println("  [0] Cancel");
        System.out.println();
        
        String choice = prompt("Select strategy");
        
        if (choice.equalsIgnoreCase("h")) {
            historyFlow(film);
            return;
        }
        if (choice.equals("0")) return;
        
        try {
            int strategyIndex = Integer.parseInt(choice) - 1;
            if (strategyIndex >= 0 && strategyIndex < strategies.length) {
                String archivist = prompt("Archivist name (Enter for anonymous)");
                if (archivist.isEmpty()) archivist = "Anonymous Archivist";
                
                executeRestoration(film, strategies[strategyIndex], archivist);
            } else {
                showMessage("Invalid selection.");
            }
        } catch (NumberFormatException e) {
            showMessage("Please enter a number.");
        }
    }

    private static void executeRestoration(FilmReel film, RestorationStrategy strategy, String archivist) {
        clearScreen();
        printHeader();
        printBox("RESTORATION IN PROGRESS");
        System.out.println();
        System.out.printf("  Film:      %s%n", film.getTitle());
        System.out.printf("  Strategy:  %s%n", strategy.getName());
        System.out.printf("  Archivist: %s%n", archivist);
        System.out.println();
        System.out.println("  " + repeat("-", 50));
        System.out.println();
        
        // Show "progress"
        System.out.print("  Processing");
        for (int i = 0; i < 5; i++) {
            pause(300);
            System.out.print(".");
        }
        System.out.println(" Done!");
        System.out.println();
        
        RestorationResult result = archive.restore(film, strategy, archivist);
        
        System.out.println("  " + repeat("=", 50));
        System.out.println("  RESTORATION COMPLETE");
        System.out.println("  " + repeat("=", 50));
        System.out.println();
        System.out.printf("  Before:      %s%n", result.getConditionBefore().getProgressBar());
        System.out.printf("  After:       %s%n", result.getConditionAfter().getProgressBar());
        System.out.printf("  Improvement: +%d%%%n", result.getImprovementPercentage());
        System.out.println();
        System.out.println("  Notes: " + result.getNotes());
        System.out.println();
        System.out.println("  " + repeat("-", 50));
        System.out.println();
        System.out.println("  [A] Apply another restoration to this film");
        System.out.println("  [H] View full restoration history");
        System.out.println("  [M] Return to main menu");
        System.out.println();
        
        String next = prompt("What next").toLowerCase();
        
        switch (next) {
            case "a": case "another":
                restoreFlow(film);
                break;
            case "h": case "history":
                historyFlow(film);
                break;
            default:
                // Return to main
                break;
        }
    }

    // ==================== HISTORY FLOW ====================

    private static void historyFlow(FilmReel preselected) {
        FilmReel film = preselected;
        
        // If no film preselected, let user choose
        if (film == null) {
            clearScreen();
            printHeader();
            printBox("VIEW RESTORATION HISTORY");
            System.out.println();
            
            List<FilmReel> films = archive.getAllFilms();
            for (int i = 0; i < films.size(); i++) {
                FilmReel f = films.get(i);
                int snapshots = archive.getFilmHistory(f).size();
                String historyNote = snapshots > 1 ? "(" + snapshots + " records)" : "(no restorations)";
                System.out.printf("  [%d] %-35s %s%n", i + 1, f.getTitle(), historyNote);
            }
            System.out.println();
            System.out.println("  [0] Back");
            System.out.println();
            
            String input = prompt("Select film");
            if (input.equals("0")) return;
            
            try {
                int index = Integer.parseInt(input) - 1;
                if (index >= 0 && index < films.size()) {
                    film = films.get(index);
                } else {
                    showMessage("Invalid selection.");
                    return;
                }
            } catch (NumberFormatException e) {
                showMessage("Please enter a number.");
                return;
            }
        }
        
        showFilmHistory(film);
    }

    private static void showFilmHistory(FilmReel film) {
        List<FilmSnapshot> snapshots = archive.getFilmHistory(film);
        
        clearScreen();
        printHeader();
        printBox("HISTORY: " + film.getTitle());
        System.out.println();
        System.out.printf("  Current Condition: %s%n", film.getCondition().getProgressBar());
        System.out.println();
        System.out.println("  " + repeat("-", 50));
        System.out.println("  RESTORATION TIMELINE");
        System.out.println("  " + repeat("-", 50));
        System.out.println();
        
        if (snapshots.isEmpty()) {
            System.out.println("  No restoration history available.");
        } else {
            for (int i = 0; i < snapshots.size(); i++) {
                FilmSnapshot snapshot = snapshots.get(i);
                String marker = (i == snapshots.size() - 1) ? " >> " : "    ";
                String num = "[" + (i + 1) + "]";
                System.out.printf("  %s %s %s%n", marker, num, snapshot.getRestorationStage());
                System.out.printf("        %s%n", snapshot.getCondition().toString());
                System.out.printf("        By: %s%n", snapshot.getArchivist());
                System.out.println();
            }
            
            if (snapshots.size() >= 2) {
                FilmSnapshot first = snapshots.get(0);
                FilmSnapshot last = snapshots.get(snapshots.size() - 1);
                int improvement = archive.getHistory().compareSnapshots(first, last);
                System.out.println("  " + repeat("-", 50));
                System.out.printf("  Total improvement: +%d%% across %d stages%n", 
                    improvement, snapshots.size());
            }
        }
        
        System.out.println();
        System.out.println("  " + repeat("-", 50));
        System.out.println();
        
        if (snapshots.size() > 1) {
            System.out.println("  [R] Rollback to a previous state");
        }
        System.out.println("  [S] Start new restoration");
        System.out.println("  [M] Return to main menu");
        System.out.println();
        
        String choice = prompt("Action").toLowerCase();
        
        switch (choice) {
            case "r": case "rollback":
                if (snapshots.size() > 1) {
                    rollbackFilm(film, snapshots);
                }
                break;
            case "s": case "restore":
                restoreFlow(film);
                break;
            default:
                break;
        }
    }

    private static void rollbackFilm(FilmReel film, List<FilmSnapshot> snapshots) {
        String input = prompt("Rollback to which state? (1-" + snapshots.size() + ")");
        
        try {
            int index = Integer.parseInt(input) - 1;
            if (index >= 0 && index < snapshots.size()) {
                archive.restoreFilmToSnapshot(film, index);
                
                System.out.println();
                System.out.println("  " + repeat("=", 50));
                System.out.println("  ROLLBACK COMPLETE");
                System.out.println("  " + repeat("=", 50));
                System.out.printf("  Restored to: %s%n", snapshots.get(index).getRestorationStage());
                System.out.printf("  Condition:   %s%n", film.getCondition().getProgressBar());
                
                waitForEnter();
            } else {
                showMessage("Invalid state number.");
            }
        } catch (NumberFormatException e) {
            showMessage("Please enter a number.");
        }
    }

    // ==================== AUTOMATED DEMO ====================

    private static void runAutomatedDemo() {
        archive = new FilmArchive("The Obsidian Vault");
        populateArchive(archive);
        
        clearScreen();
        printHeader();
        printBox("AUTOMATED DEMONSTRATION");
        System.out.println();
        System.out.println("  This demo showcases all three behavioral patterns:");
        System.out.println("    - Iterator: Multiple ways to browse the collection");
        System.out.println("    - Strategy: Interchangeable restoration techniques");
        System.out.println("    - Memento:  Save/restore film condition states");
        System.out.println();
        waitForEnter();
        
        // Iterator Demo
        clearScreen();
        printBox("PATTERN 1: ITERATOR");
        System.out.println();
        System.out.println("  Demonstrating different ways to traverse the collection...");
        System.out.println();
        
        System.out.println("  >> Decade Iterator (1920s):");
        System.out.println("  " + repeat("-", 50));
        ArchiveIterator decadeIt = archive.createDecadeIterator(1920);
        int count = 1;
        while (decadeIt.hasNext() && count <= 3) {
            FilmReel f = decadeIt.next();
            System.out.printf("     %d. %s (%d)%n", count++, f.getTitle(), f.getYear());
        }
        System.out.println("     ... and more");
        System.out.println();
        
        System.out.println("  >> Condition Iterator (worst first):");
        System.out.println("  " + repeat("-", 50));
        ArchiveIterator condIt = archive.createConditionIterator();
        count = 1;
        while (condIt.hasNext() && count <= 3) {
            FilmReel f = condIt.next();
            System.out.printf("     %d. %-25s %d/10 %s%n", 
                count++, f.getTitle(), 
                f.getCondition().getOverallScore(),
                f.getCondition().getOverallScore() <= 3 ? "[CRITICAL]" : "");
        }
        System.out.println();
        waitForEnter();
        
        // Strategy Demo
        clearScreen();
        printBox("PATTERN 2: STRATEGY");
        System.out.println();
        
        FilmReel haxan = archive.getFilmById("HAX1922");
        System.out.printf("  Selected film: %s%n", haxan.getTitle());
        System.out.printf("  Condition:     %s (Critical!)%n", haxan.getCondition().getProgressBar());
        System.out.println();
        System.out.println("  Applying two different restoration strategies...");
        System.out.println();
        
        System.out.println("  >> Strategy 1: Chemical Treatment");
        RestorationResult r1 = archive.restore(haxan, new ChemicalTreatmentStrategy(), "Dr. Helena Vance");
        System.out.printf("     Result: %s -> %s (+%d%%)%n", 
            r1.getConditionBefore().getProgressBar(),
            r1.getConditionAfter().getProgressBar(),
            r1.getImprovementPercentage());
        System.out.println();
        
        System.out.println("  >> Strategy 2: Digital Remaster");
        RestorationResult r2 = archive.restore(haxan, new DigitalRemasterStrategy(), "Dr. Helena Vance");
        System.out.printf("     Result: %s -> %s (+%d%%)%n", 
            r2.getConditionBefore().getProgressBar(),
            r2.getConditionAfter().getProgressBar(),
            r2.getImprovementPercentage());
        System.out.println();
        waitForEnter();
        
        // Memento Demo
        clearScreen();
        printBox("PATTERN 3: MEMENTO");
        System.out.println();
        System.out.printf("  Restoration history for: %s%n", haxan.getTitle());
        System.out.println("  " + repeat("-", 50));
        System.out.println();
        
        List<FilmSnapshot> history = archive.getFilmHistory(haxan);
        for (int i = 0; i < history.size(); i++) {
            FilmSnapshot s = history.get(i);
            String marker = (i == history.size() - 1) ? ">>" : "  ";
            System.out.printf("  %s [%d] %s%n", marker, i + 1, s.getRestorationStage());
            System.out.printf("        %s%n", s.getCondition().toString());
        }
        
        System.out.println();
        System.out.println("  >> Demonstrating rollback...");
        System.out.printf("     Current:  %s%n", haxan.getCondition().getProgressBar());
        
        archive.restoreFilmToSnapshot(haxan, 0);
        System.out.printf("     Rollback: %s (original state)%n", haxan.getCondition().getProgressBar());
        
        archive.restoreFilmToSnapshot(haxan, history.size() - 1);
        System.out.printf("     Restore:  %s (latest state)%n", haxan.getCondition().getProgressBar());
        System.out.println();
        waitForEnter();
        
        // Summary
        clearScreen();
        printFinalSummary();
        waitForEnter();
    }

    // ==================== DATA ====================

    private static void populateArchive(FilmArchive archive) {
        archive.addFilm(new FilmReel(
            "NOS1922", "Nosferatu", "F.W. Murnau", 1922, "Horror", "Germany",
            new Condition(3, 4, 5, "Brittle edges", "Acquired from Prague estate sale")
        ));
        archive.addFilm(new FilmReel(
            "CAB1920", "The Cabinet of Dr. Caligari", "Robert Wiene", 1920, "Horror", "Germany",
            new Condition(4, 3, 4, "Warped sections", "Expressionist masterpiece, needs care")
        ));
        archive.addFilm(new FilmReel(
            "VAM1932", "Vampyr", "Carl Theodor Dreyer", 1932, "Horror", "France",
            new Condition(5, 5, 6, "Stable", "Dreamlike quality preserved")
        ));
        archive.addFilm(new FilmReel(
            "HAX1922", "Haxan", "Benjamin Christensen", 1922, "Documentary", "Sweden",
            new Condition(2, 2, 3, "Severe decay", "Witchcraft documentary, urgent restoration needed")
        ));
        archive.addFilm(new FilmReel(
            "PHC1921", "The Phantom Carriage", "Victor Sjostrom", 1921, "Drama", "Sweden",
            new Condition(6, 5, 5, "Good", "Double exposure effects intact")
        ));
        archive.addFilm(new FilmReel(
            "FAU1926", "Faust", "F.W. Murnau", 1926, "Fantasy", "Germany",
            new Condition(4, 4, 3, "Color tinting faded", "Special effects groundbreaking for era")
        ));
        archive.addFilm(new FilmReel(
            "USH1928", "The Fall of the House of Usher", "Jean Epstein", 1928, "Horror", "France",
            new Condition(3, 3, 4, "Fragile", "Poe adaptation, surrealist style")
        ));
        archive.addFilm(new FilmReel(
            "UCA1929", "Un Chien Andalou", "Luis Bunuel", 1929, "Surrealist", "France",
            new Condition(7, 6, 7, "Excellent", "Dali collaboration, well preserved")
        ));

        for (FilmReel film : archive.getAllFilms()) {
            archive.saveSnapshot(film, "Acquired", "Head Archivist");
        }
    }

    // ==================== UI HELPERS ====================

    private static void printWelcome() {
        System.out.println();
        System.out.println();
        System.out.println("         " + repeat(".", 44));
        System.out.println();
        System.out.println("              Welcome to the archives...");
        System.out.println();
        System.out.println("         " + repeat(".", 44));
        System.out.println();
    }

    private static void printHeader() {
        System.out.println();
        System.out.println("  +" + repeat("-", WIDTH - 4) + "+");
        System.out.println("  |" + centerText("VINTAGE FILM REEL ARCHIVE", WIDTH - 4) + "|");
        System.out.println("  |" + centerText("~ " + archive.getArchiveName() + " ~", WIDTH - 4) + "|");
        System.out.println("  +" + repeat("-", WIDTH - 4) + "+");
        System.out.printf("  |  Films: %-3d   |   Restored: %-3d   |   Critical: %-3d  |%n",
            archive.getFilmCount(), archive.getRestoredCount(), archive.getCriticalCount());
        System.out.println("  +" + repeat("-", WIDTH - 4) + "+");
    }

    private static void printMainMenu() {
        System.out.println();
        System.out.println("  What would you like to do?");
        System.out.println();
        System.out.println("    [1]  Browse the collection");
        System.out.println("    [2]  Restore a film");
        System.out.println("    [3]  View restoration history");
        System.out.println("    [4]  Run automated demo");
        System.out.println();
        System.out.println("    [0]  Exit");
        System.out.println();
    }

    private static void printBox(String title) {
        System.out.println();
        System.out.println("  +" + repeat("-", 54) + "+");
        System.out.println("  |" + centerText(title, 54) + "|");
        System.out.println("  +" + repeat("-", 54) + "+");
    }

    private static void printFilmCard(int num, FilmReel film) {
        String status = getConditionStatus(film.getCondition().getOverallScore());
        System.out.printf("  [%d] %s (%d)%n", num, film.getTitle(), film.getYear());
        System.out.printf("      Director: %s  |  %s  |  %s%n", 
            film.getDirector(), film.getGenre(), film.getCountry());
        System.out.printf("      Condition: %s %s%n", 
            film.getCondition().getProgressBar(), status);
        System.out.println();
    }

    private static String getConditionStatus(int score) {
        if (score <= 3) return "[CRITICAL]";
        if (score <= 5) return "[NEEDS WORK]";
        if (score <= 7) return "[STABLE]";
        return "[EXCELLENT]";
    }

    private static void printFinalSummary() {
        printHeader();
        System.out.println();
        printBox("DEMONSTRATION COMPLETE");
        System.out.println();
        System.out.println("  Patterns demonstrated:");
        System.out.println();
        System.out.println("    * Iterator  - Multiple traversal strategies for the collection");
        System.out.println("    * Strategy  - Interchangeable restoration algorithms");
        System.out.println("    * Memento   - Capture and restore film condition states");
        System.out.println();
        System.out.println("  " + repeat("-", 54));
        System.out.printf("  Archive Status: %d films, %d restored, %d critical%n",
            archive.getFilmCount(), archive.getRestoredCount(), archive.getCriticalCount());
        System.out.println();
    }

    private static void printGoodbye() {
        System.out.println();
        System.out.println("  +" + repeat("-", 44) + "+");
        System.out.println("  |" + centerText("Thank you for visiting", 44) + "|");
        System.out.println("  |" + centerText("~ The Obsidian Vault ~", 44) + "|");
        System.out.println("  |" + centerText("Until we meet again...", 44) + "|");
        System.out.println("  +" + repeat("-", 44) + "+");
        System.out.println();
    }

    private static String centerText(String text, int width) {
        if (text.length() >= width) return text.substring(0, width);
        int padding = (width - text.length()) / 2;
        int extra = (width - text.length()) % 2;
        return repeat(" ", padding) + text + repeat(" ", padding + extra);
    }

    private static String prompt(String message) {
        System.out.print("  " + message + " > ");
        return scanner.nextLine().trim();
    }

    private static void showMessage(String message) {
        System.out.println();
        System.out.println("  ! " + message);
        pause(1000);
    }

    private static void waitForEnter() {
        System.out.print("  Press Enter to continue...");
        scanner.nextLine();
    }

    private static void pause(int ms) {
        try {
            Thread.sleep(ms);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }

    private static void clearScreen() {
        // Works on most terminals; fallback is just printing newlines
        System.out.print("\033[H\033[2J");
        System.out.flush();
        // Fallback for terminals that don't support ANSI
        for (int i = 0; i < 2; i++) System.out.println();
    }
}
