import static java.lang.Math.exp;
import static java.lang.Math.random;

public class FieldSnake {
    char[][] field;
    int sizeField = 6;
    int rowIndex = sizeField / 2;
    int colIndex = sizeField / 2;
    char snakeCh = '@';
    char apple = '*';
    char wall = '|';
    char freePlace = '.';
    int snakeSize = 1;
    boolean appleHave = false;
    int rowLastIndex = rowIndex;
    int colLastIndex = colIndex;

    void initField() {
        this.field = new char[sizeField][sizeField];
        for (int row = 0; row < sizeField; row++) {
            for (int col = 0; col < sizeField; col++) {
                this.field[row][col] = freePlace;
            }
        }
        this.startSnakeValue();
        this.printWall();
        this.printWall();
        this.printField();
    }

    void printField() {
        if (appleHave == false) {
            this.printApple();
            appleHave = true;
            for (int row = 0; row < sizeField; row++) {
                for (int col = 0; col < sizeField; col++) {
                    System.out.print(this.field[row][col] + "  ");
                }
                System.out.println();
            }
        } else {
            for (int row = 0; row < sizeField; row++) {
                for (int col = 0; col < sizeField; col++) {
                    System.out.print(this.field[row][col] + "  ");
                }
                System.out.println();
            }
        }
    }

    void printApple() {
        int row = (int) (Math.random() * sizeField);
        int col = (int) (Math.random() * sizeField);
        if (isPlaceFree(row, col)) {
            this.field[row][col] = apple;
        } else {
            printApple();
        }
    }

    void printWall() {
        int row = (int) (Math.random() * sizeField);
        int col = (int) (Math.random() * sizeField);
        if (isPlaceFree(row, col)) {
            this.field[row][col] = wall;
        } else {
            printWall();
        }
    }

    boolean isPlaceFree(int rowIndex, int colIndex) {
        if (rowIndex < 0 || rowIndex > sizeField || colIndex < 0 || colIndex > sizeField) {
            return false;
        } else if (this.field[rowIndex][colIndex] == this.freePlace) {
            return true;
        } else {
            return false;
        }
    }

    boolean isPlaceApple(int rowIndex, int colIndex) {
        if (rowIndex < 0 || rowIndex > sizeField || colIndex < 0 || colIndex > sizeField) {
            return false;
        } else if (this.field[rowIndex][colIndex] == this.apple) {
            return true;
        } else {
            return false;
        }
    }

    boolean isPlaceWall(int rowIndex, int colIndex) {
        if (rowIndex < 0 || rowIndex > sizeField || colIndex < 0 || colIndex > sizeField) {
            return false;
        } else if (this.field[rowIndex][colIndex] == this.wall) {
            return true;
        } else {
            return false;
        }
    }

    boolean isPlaceSnake(int rowIndex, int colIndex) {
        if (rowIndex < 0 || rowIndex > sizeField || colIndex < 0 || colIndex > sizeField) {
            return false;
        } else if (this.field[rowIndex][colIndex] == this.snakeCh) {
            return true;
        } else {
            return false;
        }
    }

    boolean charIsDirection(char dir) {
        if (dir == 'a' || dir == 's' || dir == 'd' || dir == 'w') {
            return true;
        }
        return false;
    }

    void startSnakeValue() {
        this.field[this.rowIndex][this.colIndex] = this.snakeCh;
    }

    boolean isGameOver() {
        if (colIndex < 0 || colIndex == sizeField || rowIndex < 0 || rowIndex == sizeField) {
            return true;
        } else {
            return false;
        }
    }

    boolean isSnakeWin() {
        if (snakeSize >= sizeField / 2) {
            return true;
        } else {
            return false;
        }
    }

    void changeOfLocationSnake(char turn) {
        if (turn == 'w') {
            if (isPlaceFree(rowIndex - 1, colIndex)) {
                if (snakeSize == 1) {
                    this.field[rowLastIndex][colLastIndex] = freePlace;
                    rowIndex--;
                    rowLastIndex=rowIndex;
                    this.field[rowIndex][colIndex] = snakeCh;
                } else {
                    this.field[rowLastIndex][colLastIndex] = freePlace;
                    rowLastIndex = rowIndex;
                    colLastIndex = colIndex;
                    rowIndex--;
                    this.field[rowIndex][colIndex] = snakeCh;
                }
            } else if (isPlaceApple(rowIndex - 1, colIndex)) {
                rowLastIndex = rowIndex;
                rowIndex--;
                this.field[rowIndex][colIndex] = snakeCh;
                snakeSize++;
                appleHave = false;
            } else if (isPlaceWall(rowIndex - 1, colIndex)) {
                rowIndex = sizeField;
            } else if (isPlaceSnake(rowIndex - 1, colIndex)) {
                rowIndex = sizeField;
            }

        } else if (turn == 'a') {
            if (isPlaceFree(rowIndex, colIndex - 1)) {
                if (snakeSize == 1) {
                    this.field[rowLastIndex][colLastIndex] = freePlace;
                    colIndex--;
                    colLastIndex=colIndex;
                    this.field[rowIndex][colIndex] = snakeCh;
                } else {
                    this.field[rowLastIndex][colLastIndex] = freePlace;
                    rowLastIndex = rowIndex;
                    colLastIndex = colIndex;
                    colIndex--;
                    this.field[rowIndex][colIndex] = snakeCh;
                }
            } else if (isPlaceApple(rowIndex, colIndex - 1)) {
                colLastIndex = colIndex;
                colIndex--;
                this.field[rowIndex][colIndex] = snakeCh;
                snakeSize++;
                appleHave = false;
            } else if (isPlaceWall(rowIndex, colIndex - 1)) {
                rowIndex = sizeField;
            } else if (isPlaceSnake(rowIndex, colIndex - 1)) {
                rowIndex = sizeField;
            }

        } else if (turn == 's') {
            if (isPlaceFree(rowIndex + 1, colIndex)) {
                if (snakeSize == 1) {
                    this.field[rowLastIndex][colLastIndex] = freePlace;
                    rowIndex++;
                    rowLastIndex = rowIndex;
                    this.field[rowIndex][colIndex] = snakeCh;
                } else {
                    this.field[rowLastIndex][colLastIndex] = freePlace;
                    rowLastIndex = rowIndex;
                    colLastIndex = colIndex;
                    rowIndex++;
                    this.field[rowIndex][colIndex] = snakeCh;
                }
            } else if (isPlaceApple(rowIndex + 1, colIndex)) {
                rowLastIndex = rowIndex;
                rowIndex++;
                this.field[rowIndex][colIndex] = snakeCh;
                snakeSize++;
                appleHave = false;
            } else if (isPlaceWall(rowIndex + 1, colIndex)) {
                rowIndex = sizeField;
            } else if (isPlaceSnake(rowIndex + 1, colIndex)) {
                rowIndex = sizeField;
            }

        } else if (turn == 'd') {

            if (isPlaceFree(rowIndex, colIndex + 1)) {
                if (snakeSize == 1) {
                    this.field[rowLastIndex][colLastIndex] = freePlace;
                    colIndex++;
                    colLastIndex = colIndex;
                    this.field[rowIndex][colIndex] = snakeCh;
                } else {
                    this.field[rowLastIndex][colLastIndex] = freePlace;
                    rowLastIndex = rowIndex;
                    colLastIndex = colIndex;
                    colIndex++;
                    this.field[rowIndex][colIndex] = snakeCh;
                }
            } else if (isPlaceApple(rowIndex, colIndex + 1)) {
                colLastIndex = colIndex;
                colIndex++;
                this.field[rowIndex][colIndex] = snakeCh;
                snakeSize++;
                appleHave = false;

            } else if (isPlaceWall(rowIndex, colIndex + 1)) {
                rowIndex = sizeField;
            } else if (isPlaceSnake(rowIndex, colIndex + 1)) {
                rowIndex = sizeField;
            }
        }
    }

}
