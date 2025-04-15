public class Main {
    public static void main(String[] args) {
        Ayoayo game = new Ayoayo();
        Player player1 = game.createPlayer("Jensen");
        Player player2 = game.createPlayer("Brian");

        System.out.println(game.playGame(1, 3));
        game.playGame(1, 1);
        game.playGame(2, 3);
        game.playGame(2, 4);
        game.playGame(1, 2);
        game.playGame(2, 2);
        game.playGame(1, 1);
        game.printBoard();
        System.out.println(game.returnWinner());
    }
}
