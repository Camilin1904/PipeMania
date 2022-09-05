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

        leaderboard.add(100, "kzapvtas", Duration.between(Instant.parse("2022-08-27T22:00:00Z"), startTime.instant()), null);
        leaderboard.add(200, "tumama", Duration.between(Instant.parse("2022-08-27T22:00:00Z"), startTime.instant()), null);
        leaderboard.add(1000, "camilin", Duration.between(Instant.parse("2022-08-27T22:00:00Z"), startTime.instant()), null);
        leaderboard.add(50, "pablo", Duration.between(Instant.parse("2022-08-27T22:00:00Z"), startTime.instant()), null);
        leaderboard.add(400, "sus", Duration.between(Instant.parse("2022-08-27T22:00:00Z"), startTime.instant()), null);

        System.out.println(leaderboard.toString());

    }

}
