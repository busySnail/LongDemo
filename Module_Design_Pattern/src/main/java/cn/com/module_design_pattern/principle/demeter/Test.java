package cn.com.module_design_pattern.principle.demeter;

/**
 * Created by geely
 */
public class Test {
    public static void main(String[] args) {
        Boss boss = new Boss();
        TeamLeader teamLeader = new TeamLeader();
        boss.commandCheckNumber(teamLeader);

    }
}
