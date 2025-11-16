package domain.teams;

import java.util.ArrayList;
import java.util.List;

// composite pattern: composite - represents team that can contain heroes or sub-teams
public class HeroTeam implements HeroComponent {
    private String teamName;
    private List<HeroComponent> members;
    private static final double SYNERGY_BONUS = 1.1;

    public HeroTeam(String teamName) {
        this.teamName = teamName;
        this.members = new ArrayList<>();
    }

    public void addMember(HeroComponent member) {
        members.add(member);
    }

    public void removeMember(HeroComponent member) {
        members.remove(member);
    }

    public List<HeroComponent> getMembers() {
        return new ArrayList<>(members);
    }

    @Override
    public String getName() {
        return teamName;
    }

    @Override
    public int getPowerLevel() {
        int totalPower = 0;
        for (HeroComponent member : members) {
            totalPower += member.getPowerLevel();
        }
        return (int)(totalPower * SYNERGY_BONUS);
    }

    @Override
    public void displayInfo() {
        System.out.println("Team: " + teamName);
        System.out.println("Members: " + members.size());
        System.out.println("Total Power: " + getPowerLevel() + " (with synergy bonus)");
    }

    @Override
    public void displayHierarchy(int indent) {
        StringBuilder spacing = new StringBuilder();
        for (int i = 0; i < indent; i++) {
            spacing.append(" ");
        }
        System.out.println(spacing.toString() + teamName + " [Power: " + getPowerLevel() + "]");
        for (HeroComponent member : members) {
            member.displayHierarchy(indent + 2);
        }
    }

    @Override
    public boolean isAvailable() {
        for (HeroComponent member : members) {
            if (!member.isAvailable()) {
                return false;
            }
        }
        return true;
    }

    public int getMemberCount() {
        return members.size();
    }
}
