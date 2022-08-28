package ui;

import model.Leaderboard;

public class InitLeaderBoard {

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

        leaderboard.add(100, "kzapvtas", null);
        leaderboard.add(200, "tumama", null);
        leaderboard.add(1000, "camilin", null);
        leaderboard.add(50, "pablo", null);
        leaderboard.add(400, "sus", null);

        System.out.println(leaderboard.toString());

    }

}
