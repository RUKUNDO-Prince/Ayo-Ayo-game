import java.util.Scanner;

public class Main {
    static Scanner scanner = new Scanner(System.in);
    static String player1Name;
    static String player2Name;
    static int[] board = new int[14]; // [0-5]: P1 pits, 6: P1 store, [7-12]: P2 pits, 13: P2 store
    static boolean isPlayer1Turn = true;

    public static void main(String[] args) {
        printRules();
        initializeGame();
        playGame();
        endGame();
    }

    static void printRules() {
        System.out.println("🎮 Welcome to Ayo-Ayo Game!");
        System.out.println("🌰 Rules:");
        System.out.println("1. Each player has 6 pits with 4 seeds each.");
        System.out.println("2. Players take turns picking a pit on their side (1-6) and sowing seeds counter-clockwise.");
        System.out.println("3. If your last seed lands in your store, you get another turn.");
        System.out.println("4. The game ends when all 6 pits on one side are empty.");
        System.out.println("5. The player with the most seeds in their store wins.");
        System.out.println("--------------------------------------------------\n");
    }

    static void initializeGame() {
        System.out.print("Enter name for Player 1: ");
        player1Name = scanner.nextLine();

        System.out.print("Enter name for Player 2: ");
        player2Name = scanner.nextLine();

        for (int i = 0; i < 14; i++) {
            if (i != 6 && i != 13) {
                board[i] = 4;
            }
        }
    }

    static void playGame() {
        while (!isGameOver()) {
            displayBoard();
            int pitIndex = getPlayerMove();
            makeMove(pitIndex);
        }
    }

    static int getPlayerMove() {
        int pit = -1;
        while (true) {
            System.out.printf("%s, choose a pit to play (1-6): %n", isPlayer1Turn ? player1Name : player2Name);
            if (!scanner.hasNextInt()) {
                System.out.println("❌ Invalid input. Please enter a number from 1 to 6.");
                scanner.next(); // consume invalid input
                continue;
            }

            pit = scanner.nextInt();
            if (pit < 1 || pit > 6) {
                System.out.println("❌ Invalid number. Please choose a pit from 1 to 6.");
                continue;
            }

            int actualIndex = getPitIndex(pit);
            if (board[actualIndex] == 0) {
                System.out.println("❌ Pit is empty. Choose a different pit.");
                continue;
            }

            return actualIndex;
        }
    }

    static int getPitIndex(int pitNumber) {
        return isPlayer1Turn ? pitNumber - 1 : pitNumber + 6;
    }

    static void makeMove(int pitIndex) {
        int seeds = board[pitIndex];
        board[pitIndex] = 0;
        int index = pitIndex;

        while (seeds > 0) {
            index = (index + 1) % 14;

            // Skip opponent's store
            if ((isPlayer1Turn && index == 13) || (!isPlayer1Turn && index == 6)) {
                continue;
            }

            board[index]++;
            seeds--;
        }

        // Check if last seed landed in player's store
        if ((isPlayer1Turn && index == 6) || (!isPlayer1Turn && index == 13)) {
            System.out.println("🔁 " + (isPlayer1Turn ? player1Name : player2Name) + " take another turn");
        } else {
            isPlayer1Turn = !isPlayer1Turn; // Switch turn
        }
    }

    static boolean isGameOver() {
        boolean player1Empty = true;
        boolean player2Empty = true;

        for (int i = 0; i < 6; i++) {
            if (board[i] != 0) player1Empty = false;
        }

        for (int i = 7; i < 13; i++) {
            if (board[i] != 0) player2Empty = false;
        }

        return player1Empty || player2Empty;
    }

    static void endGame() {
        // Move remaining seeds to their respective stores
        for (int i = 0; i < 6; i++) {
            board[6] += board[i];
            board[i] = 0;
        }

        for (int i = 7; i < 13; i++) {
            board[13] += board[i];
            board[i] = 0;
        }

        displayBoard();

        System.out.println("🏁 Game Over!");
        System.out.printf("%s's store: %d%n", player1Name, board[6]);
        System.out.printf("%s's store: %d%n", player2Name, board[13]);

        if (board[6] > board[13]) {
            System.out.println("🏆 Winner: " + player1Name);
        } else if (board[13] > board[6]) {
            System.out.println("🏆 Winner: " + player2Name);
        } else {
            System.out.println("🤝 It's a tie!");
        }
    }

    static void displayBoard() {
        System.out.println("\nCurrent Board:");
        System.out.println(player1Name + ":");
        System.out.println("store: " + board[6]);
        System.out.print("[");
        for (int i = 0; i < 6; i++) {
            System.out.print(board[i] + (i < 5 ? ", " : ""));
        }
        System.out.println("]");

        System.out.println(player2Name + ":");
        System.out.println("store: " + board[13]);
        System.out.print("[");
        for (int i = 7; i < 13; i++) {
            System.out.print(board[i] + (i < 12 ? ", " : ""));
        }
        System.out.println("]\n");
    }
}
