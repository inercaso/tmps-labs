package domain.factory.singleton;

import domain.models.Superhero;
import java.util.ArrayList;
import java.util.List;

/**
 * SINGLETON PATTERN - The Seven Team Manager
 * Manages The Seven superhero team (max 7 members)
 */
public class TheSevenManager {
    private static TheSevenManager instance;
    private final List<Superhero> members;
    private static final int MAX_MEMBERS = 7;
    
    // Private constructor prevents instantiation
    private TheSevenManager() {
        members = new ArrayList<>();
    }
    
    // Thread-safe singleton instance getter
    public static synchronized TheSevenManager getInstance() {
        if (instance == null) {
            instance = new TheSevenManager();
        }
        return instance;
    }
    
    /**
     * Add a member to The Seven
     */
    public boolean addMember(Superhero hero) {
        if (members.size() >= MAX_MEMBERS) {
            System.out.println("The Seven is full! Cannot add " + hero.getName());
            return false;
        }
        
        members.add(hero);
        hero.setTeam("The Seven");
        System.out.println("Added " + hero.getName() + " to The Seven");
        return true;
    }
    
    /**
     * Remove a member from The Seven
     */
    public boolean removeMember(Superhero hero) {
        boolean removed = members.remove(hero);
        if (removed) {
            System.out.println("Removed " + hero.getName() + " from The Seven");
        }
        return removed;
    }
    
    /**
     * Get all members
     */
    public List<Superhero> getMembers() {
        return new ArrayList<>(members);
    }
    
    /**
     * Get current team size
     */
    public int getTeamSize() {
        return members.size();
    }
    
    /**
     * Display team roster
     */
    public void displayRoster() {
        System.out.println("\nTHE SEVEN ROSTER");
        System.out.println("Members: " + members.size() + "/" + MAX_MEMBERS);
        
        if (members.isEmpty()) {
            System.out.println("No members currently registered");
        } else {
            for (int i = 0; i < members.size(); i++) {
                Superhero hero = members.get(i);
                System.out.println((i + 1) + ". " + hero.getName() + " (" + hero.getHeroType() + ")");
            }
        }
    }
    
    /**
     * Clear all members (for testing)
     */
    public void clearTeam() {
        members.clear();
        System.out.println("Cleared The Seven roster");
    }
}
