import java.util.Random;
public class RockPaperScissors {
    public static String playRound(String playerMove, String computerMove) {
        if (playerMove.equalsIgnoreCase(computerMove)) {
            return "Draw";
        }        
        if ((playerMove.equalsIgnoreCase("Rock") && computerMove.equalsIgnoreCase("Scissors")) ||
            (playerMove.equalsIgnoreCase("Paper") && computerMove.equalsIgnoreCase("Rock")) ||
            (playerMove.equalsIgnoreCase("Scissors") && computerMove.equalsIgnoreCase("Paper"))) {
            return "Player Wins";
        } else {
            return "Computer Wins";
        }
    }
    public static void main(String[] args) {
        String[] moves = {"Rock", "Paper", "Scissors"};
        String[] predefinedPlayerMoves = {"Rock", "Paper", "Scissors", "Rock", "Paper"};       
        Random random = new Random();
        int nRounds = 5;        
        int wins = 0;
        int losses = 0;
        int draws = 0;
        String[][] summaryTable = new String[nRounds][4];

        for (int i = 0; i < nRounds; i++) {
            String playerMove = predefinedPlayerMoves[i];
            String computerMove = moves[random.nextInt(3)];
            String result = playRound(playerMove, computerMove);
            summaryTable[i][0] = String.valueOf(i + 1);
            summaryTable[i][1] = playerMove;
            summaryTable[i][2] = computerMove;
            summaryTable[i][3] = result;
            if (result.equals("Player Wins")) {
                wins++;
            } else if (result.equals("Computer Wins")) {
                losses++;
            } else {
                draws++;
            }
        }
        System.out.println("Round | Player Move | Computer Move | Result");
        System.out.println("---------------------------------------------");
        for (int i = 0; i < nRounds; i++) {
            System.out.printf("%-5s | %-11s | %-13s | %s\n", 
                summaryTable[i][0], summaryTable[i][1], summaryTable[i][2], summaryTable[i][3]);
        }
        double winPercentage = ((double) wins / nRounds) * 100;
        System.out.println("\nFinal Summary (after " + nRounds + " rounds)");
        System.out.printf("Wins: %d | Losses: %d | Draws: %d | Win %% = %.1f%%\n", wins, losses, draws, winPercentage);
    }
}