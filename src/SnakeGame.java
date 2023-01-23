import java.util.Scanner;

public class SnakeGame {

    FieldSnake gameField;
    Scanner scanner = new Scanner(System.in);
    char directionTurn;
    boolean gameOver = false;

    void setupNewGame() {
        System.out.println("Will play Snake game");
        this.gameField = new FieldSnake();
        this.gameField.initField();

    }

    void play() {
        this.setupNewGame();
        while (!gameOver) {
            turn();
            if(this.gameField.isGameOver()){
                this.gameOver=this.gameField.isGameOver();
                System.out.println("You lose!");
            } else if (this.gameField.isSnakeWin()){
                this.gameOver=this.gameField.isSnakeWin();
                System.out.println("You win!");
            }
        }
        System.out.println("Game Over");
    }

    void turn() {
        System.out.println("enter your direction: \nw-up, d-right, s-down, a-left");
        directionTurn = this.scanner.next().charAt(0);
        if (this.gameField.charIsDirection(directionTurn)) {
            this.gameField.changeOfLocationSnake(directionTurn);
            this.gameField.printField();
        } else {
            System.out.println("wrong, enter the direction again :");
            turn();
        }

    }
}
