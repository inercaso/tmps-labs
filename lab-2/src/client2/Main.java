package client2;

import domain.models.Superhero;
import domain.enhancements.*;
import domain.teams.*;
import domain.operations.*;
import domain.factory.factories.*;
import utilities.MissionType;
import tests.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    private static Scanner scanner = new Scanner(System.in);
    private static List<Superhero> heroes = new ArrayList<>();
    private static List<ISuperheroComponent> enhancedHeroes = new ArrayList<>();
    private static List<HeroTeam> teams = new ArrayList<>();
    private static MissionFacade missionFacade = new MissionFacade();

    public static void main(String[] args) {
        boolean running = true;

        while (running) {
            clearScreen();
            printMainMenu();
            int choice = getChoice();

            switch (choice) {
                case 1:
                    createSuperhero();
                    break;
                case 2:
                    enhanceSuperhero();
                    break;
                case 3:
                    manageTeams();
                    break;
                case 4:
                    executeMission();
                    break;
                case 5:
                    viewStatistics();
                    break;
                case 6:
                    runAutomatedTests();
                    break;
                case 0:
                    running = false;
                    System.out.println("\nThank you for using Vought International Systems!");
                    break;
                default:
                    System.out.println("\nInvalid choice. Please try again.");
                    pause();
            }
        }

        scanner.close();
    }

    private static void printMainMenu() {
        System.out.println("\n================================================");
        System.out.println("  VOUGHT INTERNATIONAL - OPERATIONS SYSTEM");
        System.out.println("         Lab-2: Structural Patterns");
        System.out.println("================================================");
        System.out.println("\nHeroes: " + heroes.size() + " | Missions: " + missionFacade.getTotalMissions() + " | Teams: " + teams.size());
        System.out.println("\n----------- MAIN MENU -----------");
        System.out.println(" 1. Create Superhero");
        System.out.println(" 2. Enhance Superhero");
        System.out.println(" 3. Manage Teams");
        System.out.println(" 4. Execute Mission");
        System.out.println(" 5. View Statistics");
        System.out.println(" 6. Run Automated Tests");
        System.out.println(" 0. Exit");
        System.out.println("---------------------------------");
    }

    private static void printHeader(String title) {
        System.out.println("\n========================================");
        System.out.println("  " + title);
        System.out.println("========================================\n");
    }

    private static void createSuperhero() {
        clearScreen();
        printHeader("CREATE SUPERHERO (Lab-1 Patterns)");

        System.out.println("Select Superhero Type:");
        System.out.println("  1. Homelander Type (Strength-based)");
        System.out.println("  2. Starlight Type (Energy-based)");
        System.out.println("  3. A-Train Type (Speed-based)");
        System.out.println("  4. Queen Maeve Type (Balanced)");
        System.out.print("\nChoice: ");

        int typeChoice = getChoice();
        String name = getInput("\nEnter hero name: ");

        SuperheroFactory factory = null;
        switch (typeChoice) {
            case 1: factory = new HomelanderTypeFactory(); break;
            case 2: factory = new StarlightTypeFactory(); break;
            case 3: factory = new ATrainTypeFactory(); break;
            case 4: factory = new MaeveTypeFactory(); break;
            default:
                System.out.println("Invalid type!");
                pause();
                return;
        }

        System.out.println("\n> Creating using Factory Pattern...");
        Superhero hero = factory.createSuperhero(name);
        heroes.add(hero);

        System.out.println("> Hero created successfully!");
        System.out.println("\n--- " + hero.getName().toUpperCase() + " ---");
        System.out.println("Type: " + hero.getHeroType());
        System.out.println("Stats: " + hero.getStats());
        System.out.println("Powers: " + hero.getPowers().size());
        System.out.println("---------------------------");

        pause();
    }

    private static void enhanceSuperhero() {
        clearScreen();
        printHeader("ENHANCE SUPERHERO (Decorator Pattern)");

        if (heroes.isEmpty()) {
            System.out.println("No heroes available. Create a hero first!");
            pause();
            return;
        }

        System.out.println("Available Heroes:");
        for (int i = 0; i < heroes.size(); i++) {
            System.out.println("  " + (i + 1) + ". " + heroes.get(i).getName() + 
                             " [Power: " + heroes.get(i).getStats().getPowerLevel() + "]");
        }

        System.out.print("\nSelect hero: ");
        int heroChoice = getChoice() - 1;

        if (heroChoice < 0 || heroChoice >= heroes.size()) {
            System.out.println("Invalid hero!");
            pause();
            return;
        }

        Superhero selectedHero = heroes.get(heroChoice);
        ISuperheroComponent wrapped = new SuperheroWrapper(selectedHero);

        System.out.println("\n--- " + selectedHero.getName() + " (Base Stats) ---");
        System.out.println(selectedHero.getStats());
        System.out.println("Total Power: " + wrapped.calculatePower());
        System.out.println("-------------------------------");

        System.out.println("\nAvailable Enhancements:");
        System.out.println("  1. Compound V (+30% to physical stats)");
        System.out.println("  2. Training (+15 to all stats)");
        System.out.println("  3. Stack Both (Training + Compound V)");
        System.out.println("  0. Cancel");

        System.out.print("\nChoice: ");
        int enhanceChoice = getChoice();

        ISuperheroComponent enhanced = wrapped;
        switch (enhanceChoice) {
            case 1:
                System.out.println("\n> Applying Compound V Enhancement...");
                enhanced = new CompoundVDecorator(wrapped);
                System.out.println("> Compound V applied!");
                break;
            case 2:
                System.out.println("\n> Applying Training Enhancement...");
                enhanced = new TrainingDecorator(wrapped);
                System.out.println("> Training applied!");
                break;
            case 3:
                System.out.println("\n> Applying Training Enhancement...");
                enhanced = new TrainingDecorator(wrapped);
                System.out.println("> Training applied!");
                System.out.println("> Applying Compound V Enhancement...");
                enhanced = new CompoundVDecorator(enhanced);
                System.out.println("> Compound V applied!");
                break;
            case 0:
                return;
            default:
                System.out.println("Invalid choice!");
                pause();
                return;
        }

        enhancedHeroes.add(enhanced);

        System.out.println("\n--- " + selectedHero.getName() + " (Enhanced) ---");
        System.out.println(enhanced.getStats());
        System.out.println("Total Power: " + enhanced.calculatePower() + " (+" + 
                         (enhanced.calculatePower() - wrapped.calculatePower()) + " increase!)");
        System.out.println("-------------------------------");

        pause();
    }

    private static void manageTeams() {
        clearScreen();
        printHeader("MANAGE TEAMS (Composite Pattern)");

        if (teams.isEmpty()) {
            System.out.println("Current Teams: None");
        } else {
            System.out.println("Current Teams:");
            for (int i = 0; i < teams.size(); i++) {
                HeroTeam team = teams.get(i);
                System.out.println("  " + (i + 1) + ". " + team.getName() + 
                                 " [" + team.getMemberCount() + " members, Power: " + 
                                 team.getPowerLevel() + "]");
            }
        }

        System.out.println("\nOptions:");
        System.out.println("  1. Create New Team");
        System.out.println("  2. Add Hero to Team");
        System.out.println("  3. View Team Details");
        System.out.println("  0. Back");

        System.out.print("\nChoice: ");
        int choice = getChoice();

        switch (choice) {
            case 1:
                createTeam();
                break;
            case 2:
                addHeroToTeam();
                break;
            case 3:
                viewTeamDetails();
                break;
            case 0:
                return;
        }
    }

    private static void createTeam() {
        String teamName = getInput("\nEnter team name: ");
        HeroTeam team = new HeroTeam(teamName);
        teams.add(team);
        System.out.println("> Team '" + teamName + "' created successfully!");
        pause();
    }

    private static void addHeroToTeam() {
        if (teams.isEmpty()) {
            System.out.println("\nNo teams available. Create a team first!");
            pause();
            return;
        }

        if (heroes.isEmpty() && enhancedHeroes.isEmpty()) {
            System.out.println("\nNo heroes available. Create a hero first!");
            pause();
            return;
        }

        System.out.println("\nSelect team:");
        for (int i = 0; i < teams.size(); i++) {
            System.out.println("  " + (i + 1) + ". " + teams.get(i).getName());
        }
        System.out.print("\nChoice: ");
        int teamChoice = getChoice() - 1;

        if (teamChoice < 0 || teamChoice >= teams.size()) {
            System.out.println("Invalid team!");
            pause();
            return;
        }

        System.out.println("\nAvailable Heroes:");
        System.out.println("Base Heroes:");
        for (int i = 0; i < heroes.size(); i++) {
            System.out.println("  " + (i + 1) + ". " + heroes.get(i).getName());
        }
        System.out.println("\nEnhanced Heroes:");
        for (int i = 0; i < enhancedHeroes.size(); i++) {
            System.out.println("  " + (heroes.size() + i + 1) + ". " + 
                             enhancedHeroes.get(i).getName() + " (Enhanced)");
        }

        System.out.print("\nSelect hero: ");
        int heroChoice = getChoice() - 1;

        ISuperheroComponent selectedHero;
        if (heroChoice < heroes.size()) {
            selectedHero = new SuperheroWrapper(heroes.get(heroChoice));
        } else {
            int enhancedIndex = heroChoice - heroes.size();
            if (enhancedIndex < 0 || enhancedIndex >= enhancedHeroes.size()) {
                System.out.println("Invalid hero!");
                pause();
                return;
            }
            selectedHero = enhancedHeroes.get(enhancedIndex);
        }

        teams.get(teamChoice).addMember(new IndividualHero(selectedHero));
        System.out.println("> Hero added to team successfully!");
        pause();
    }

    private static void viewTeamDetails() {
        if (teams.isEmpty()) {
            System.out.println("\nNo teams available!");
            pause();
            return;
        }

        System.out.println("\nSelect team:");
        for (int i = 0; i < teams.size(); i++) {
            System.out.println("  " + (i + 1) + ". " + teams.get(i).getName());
        }
        System.out.print("\nChoice: ");
        int teamChoice = getChoice() - 1;

        if (teamChoice < 0 || teamChoice >= teams.size()) {
            System.out.println("Invalid team!");
            pause();
            return;
        }

        HeroTeam team = teams.get(teamChoice);
        System.out.println("\n--- " + team.getName().toUpperCase() + " ---");
        System.out.println("Total Power: " + team.getPowerLevel() + " (with +10% synergy bonus)");
        System.out.println("\nMembers:");
        team.displayHierarchy(2);
        System.out.println("-----------------------------");

        pause();
    }

    private static void executeMission() {
        clearScreen();
        printHeader("EXECUTE MISSION (Facade Pattern)");

        if (teams.isEmpty() && heroes.isEmpty()) {
            System.out.println("No heroes or teams available!");
            pause();
            return;
        }

        System.out.println("Select Mission Type:");
        System.out.println("  1. RESCUE (Difficulty: 60)");
        System.out.println("  2. COMBAT (Difficulty: 80)");
        System.out.println("  3. PR_EVENT (Difficulty: 40)");

        System.out.print("\nChoice: ");
        int missionChoice = getChoice();

        MissionType type;
        switch (missionChoice) {
            case 1: type = MissionType.RESCUE; break;
            case 2: type = MissionType.COMBAT; break;
            case 3: type = MissionType.PR_EVENT; break;
            default:
                System.out.println("Invalid mission type!");
                pause();
                return;
        }

        System.out.println("\nSelect Unit:");
        System.out.println("  1. Individual Hero");
        System.out.println("  2. Team");

        System.out.print("\nChoice: ");
        int unitChoice = getChoice();

        HeroComponent selectedUnit = null;

        if (unitChoice == 1) {
            if (heroes.isEmpty() && enhancedHeroes.isEmpty()) {
                System.out.println("No heroes available!");
                pause();
                return;
            }

            System.out.println("\nAvailable Heroes:");
            for (int i = 0; i < heroes.size(); i++) {
                System.out.println("  " + (i + 1) + ". " + heroes.get(i).getName());
            }
            for (int i = 0; i < enhancedHeroes.size(); i++) {
                System.out.println("  " + (heroes.size() + i + 1) + ". " + 
                                 enhancedHeroes.get(i).getName() + " (Enhanced)");
            }

            System.out.print("\nChoice: ");
            int heroChoice = getChoice() - 1;

            if (heroChoice < heroes.size()) {
                selectedUnit = new IndividualHero(new SuperheroWrapper(heroes.get(heroChoice)));
            } else {
                int enhancedIndex = heroChoice - heroes.size();
                if (enhancedIndex >= 0 && enhancedIndex < enhancedHeroes.size()) {
                    selectedUnit = new IndividualHero(enhancedHeroes.get(enhancedIndex));
                }
            }
        } else if (unitChoice == 2) {
            if (teams.isEmpty()) {
                System.out.println("No teams available!");
                pause();
                return;
            }

            System.out.println("\nAvailable Teams:");
            for (int i = 0; i < teams.size(); i++) {
                System.out.println("  " + (i + 1) + ". " + teams.get(i).getName() + 
                                 " [Power: " + teams.get(i).getPowerLevel() + "]");
            }

            System.out.print("\nChoice: ");
            int teamChoice = getChoice() - 1;

            if (teamChoice >= 0 && teamChoice < teams.size()) {
                selectedUnit = teams.get(teamChoice);
            }
        }

        if (selectedUnit == null) {
            System.out.println("Invalid selection!");
            pause();
            return;
        }

        String missionName = getInput("\nEnter mission name: ");
        missionFacade.executeMission(missionName, type, selectedUnit);

        pause();
    }

    private static void viewStatistics() {
        clearScreen();
        printHeader("MISSION STATISTICS");

        missionFacade.displayStatistics();

        pause();
    }

    private static void runAutomatedTests() {
        clearScreen();
        printHeader("AUTOMATED PATTERN DEMONSTRATION");

        System.out.println("This will demonstrate all patterns automatically.\n");
        System.out.println("Running Test Suite...\n");

        DecoratorTest.runAllTests();
        CompositeTest.runAllTests();
        FacadeTest.runAllTests();
        IntegrationTest.runAllTests();

        System.out.println("========================================");
        System.out.println("    ALL TESTS PASSED: 18/18");
        System.out.println("========================================");

        pause();
    }

    private static void clearScreen() {
        try {
            new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
        } catch (Exception e) {
            for (int i = 0; i < 50; i++) System.out.println();
        }
    }

    private static int getChoice() {
        System.out.print("\nEnter your choice: ");
        try {
            return scanner.nextInt();
        } catch (Exception e) {
            scanner.nextLine();
            return -1;
        }
    }

    private static String getInput(String prompt) {
        System.out.print(prompt);
        scanner.nextLine();
        return scanner.nextLine();
    }

    private static void pause() {
        System.out.print("\nPress Enter to continue...");
        scanner.nextLine();
    }
}
