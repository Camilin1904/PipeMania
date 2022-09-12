package ui;

import model.Leaderboard;

import java.time.*;

public class InitLeaderBoard {

    private Clock startTime = Clock.systemDefaultZone();

    private Leaderboard leaderboard;

    public InitLeaderBoard(){

        leaderboard=new Leaderboard();

    }
    public static void main (String args[]){

        InitLeaderBoard init = new InitLeaderBoard();

        init.Starting();

        init.init();

    }

    public void Starting(){

        System.out.println("Iniciando el programa");

    }

    public void init(){

        leaderboard.add("kzapvtas", Duration.between(Instant.parse("2022-08-27T22:00:00Z"), startTime.instant()), 100, null);
        leaderboard.add("tumama", Duration.between(Instant.parse("2022-08-27T22:00:00Z"), startTime.instant()), 200, null);
        leaderboard.add("camilin", Duration.between(Instant.parse("2022-08-27T22:00:00Z"), startTime.instant()), 1000, null);
        leaderboard.add("pablo", Duration.between(Instant.parse("2022-08-27T22:00:00Z"), startTime.instant()), 50, null);
        leaderboard.add("sus", Duration.between(Instant.parse("2022-08-27T22:00:00Z"), startTime.instant()), 400, null);

        System.out.println(leaderboard.toString());

    }

}
