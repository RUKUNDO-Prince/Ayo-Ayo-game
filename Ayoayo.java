public class Ayoayo {
    private Player player1;
    private Player player2;
    private boolean gameEnded = false;

    public Player createPlayer(String name) {
        if (player1 == null) {
            player1 = new Player(name);
            return player1;
        } else if (player2 == null) {
            player2 = new Player(name);
            return player2;
        }
        return null;
    }

    public void printBoard() {
        System.out.println("player1:");
        System.out.println("store: " + player1.getStore());
        System.out.print("[");
        for (int i = 0; i < 6; i++) {
            System.out.print(player1.getPit(i));
            if (i != 5) System.out.print(", ");
        }
        System.out.println("]");

        System.out.println("player2:");
        System.out.println("store: " + player2.getStore());
        System.out.print("[");
        for (int i = 0; i < 6; i++) {
            System.out.print(player2.getPit(i));
            if (i != 5) System.out.print(", ");
        }
        System.out.println("]");
    }

    public String returnWinner() {
        if (!gameEnded) return "Game has not ended";

        int store1 = player1.getStore();
        int store2 = player2.getStore();

        if (store1 > store2) return "Winner is player 1: " + player1.getName();
        else if (store2 > store1) return "Winner is player 2: " + player2.getName();
        else return "It's a tie";
    }

    public String playGame(int playerIndex, int pitIndex) {
        if (pitIndex < 1 || pitIndex > 6) return "Invalid number for pit index";
        if (gameEnded) return "Game is ended";

        Player current = playerIndex == 1 ? player1 : player2;
        Player opponent = playerIndex == 1 ? player2 : player1;

        int index = pitIndex - 1;
        if (index < 0 || index >= 6) return "Invalid pit index";

        int seeds = current.getPit(index);
        if (seeds == 0) return "Pit is empty";

        current.setPit(index, 0);

        int pos = index + 1;
        boolean onCurrent = true;

        while (seeds > 0) {
            // Sow on current player's side
            while (pos < 6 && seeds > 0) {
                current.incrementPit(pos++);
                seeds--;
            }

            // Sow into current player's store
            if (seeds > 0) {
                current.addToStore(1);
                seeds--;
                if (seeds == 0) {
                    return "player " + playerIndex + " take another turn\n" + getBoardState();
                }
            }

            // Switch to opponent's side
            pos = 0;
            onCurrent = false;
            while (pos < 6 && seeds > 0) {
                opponent.incrementPit(pos++);
                seeds--;
            }

            // Prepare for next round
            onCurrent = true;
            pos = 0;
        }

        // Capture logic
        int lastPit = pos - 1;
        if (onCurrent && lastPit >= 0 && lastPit < 6 && current.getPit(lastPit) == 1) {
            int oppositeIndex = 5 - lastPit;
            if (oppositeIndex >= 0 && oppositeIndex < 6) {
                int captured = opponent.getPit(oppositeIndex);
                if (captured > 0) {
                    current.addToStore(captured + 1);
                    current.setPit(lastPit, 0);
                    opponent.setPit(oppositeIndex, 0);
                }
            }
        }

        // Check for end of game
        if (player1.isEmpty() || player2.isEmpty()) {
            player1.collectRemainingSeeds();
            player2.collectRemainingSeeds();
            gameEnded = true;
        }

        return getBoardState();
    }

    private String getBoardState() {
        StringBuilder sb = new StringBuilder("[");
        for (int i = 0; i < 6; i++) sb.append(player1.getPit(i)).append(", ");
        sb.append(player1.getStore()).append(", ");
        for (int i = 0; i < 6; i++) sb.append(player2.getPit(i)).append(", ");
        sb.append(player2.getStore()).append("]");
        return sb.toString();
    }
}
