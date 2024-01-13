//package Modes;
//
//public class test {
//    import java.util.LinkedList;
//import java.util.List;
//import java.util.Scanner;
//
//    class Gomoku {
//        private char[][] grid;
//        private int[] topPieceIndex;
//        private int width;
//        private int height;
//        private int numOfPiecesToWin;
//        private int fills;
//        private int lastColumnPlayed = -1;
//
//        public Gomoku(int width, int height, int numOfPiecesToWin) {
//            fills = 0;
//            this.width = width;
//            this.height = height;
//            this.numOfPiecesToWin = numOfPiecesToWin;
//            grid = new char[height][width];
//            topPieceIndex = new int[width];
//            for (int i = 0; i < topPieceIndex.length; i++) {
//                topPieceIndex[i] = height;
//            }
//            for (char[] grid1 : grid) {
//                for (int j = 0; j < grid1.length; j++) {
//                    grid1[j] = ' ';
//                }
//            }
//        }
//
//        public Gomoku(Gomoku board) {
//            grid = new char[board.height][board.width];
//            topPieceIndex = new int[board.width];
//            System.arraycopy(board.topPieceIndex, 0, topPieceIndex, 0, this.topPieceIndex.length);
//            for (int i = 0; i < grid.length; i++) {
//                System.arraycopy(board.grid[i], 0, this.grid[i], 0, board.width);
//            }
//            this.fills = board.fills;
//            this.height = board.height;
//            this.width = board.width;
//            this.numOfPiecesToWin = board.numOfPiecesToWin;
//        }
//
//        public int getWidth() {
//            return width;
//        }
//
//        public int getHeight() {
//            return height;
//        }
//
//        public List<Gomoku> GenerateMoves(char nextPlayer) {
//            List<Gomoku> nextBoards = new LinkedList<>();
//            for (int i = 0; i < width; i++) {
//                if (topPieceIndex[i] != 0) {
//                    Gomoku nextBoard = new Gomoku(this);
//                    nextBoard.play(nextPlayer, i);
//                    nextBoards.add(nextBoard);
//                }
//            }
//            return nextBoards;
//        }
//
//        public boolean play(char player, int col) {
//            if (topPieceIndex[col] != 0) {
//                topPieceIndex[col] -= 1;
//                grid[topPieceIndex[col]][col] = player;
//                fills++;
//                lastColumnPlayed = col;
//                return true;
//            }
//            return false;
//        }
//
//        /*
//         *
//         * how good
//         * is the board for this player?**
//         *
//         * @param player
//         *
//         * @return
//         */
//        public int evaluate(char player) {
//            if (this.isWin(player))
//                return player == 'o' ? 1 : -1;
//
//            return 0;
//        }
//
//        /*
//         * checks if
//         *
//         * the game
//         * is withdraw**@return
//         */
//
//        public boolean isWithdraw() {
//            return (fills == width * height);
//        }/*
//         * checks if
//         *
//         * player putting
//         * last piece
//         * makes him
//         *
//         * win (connect four pieces)
//         *
//         * @param player
//         *
//         * @return true if win
//         */
//
//        public boolean isWinWithLastPiece(char player) {
//            int col = this.lastColumnPlayed;
//            return (isWinInColumn(player, col) || isWinInRow(player, col)
//                    || isWinInDiagonal_1(player, col) || isWinInDiagonal_2(player, col));
//        }
//
//        public boolean isWin(char player) {
//            for (int col = 0; col < width; col++) {
//                if (isWinInColumn(player, col)) {
//                    return true;
//                } else if (isWinInRow(player, col)) {
//                    return true;
//                } else if (isWinInDiagonal_1(player, col)) {
//                    return true;
//                } else if (isWinInDiagonal_2(player, col)) {
//                    return true;
//                }
//            }
//            return false;
//        }
//
//        public boolean isFinished() {
//            return (isWin('x') || isWin('o') || isWithdraw());
//        }
//
//        // row is empty
//        private boolean isWinInColumn(char player, int col) {
//            int row = topPieceIndex[col];
//            if (row == height) {
//                return false;
//            }
//            // the cell itself
//            if (grid[row][col] != player) {
//                return false;
//            }
//            int count = 1;
//            // check cells below
//            try {
//                for (int i = row + 1; i < height; i++) {
//                    if (grid[i][col] == player) {
//                        count++;
//                    } else {
//                        break;
//                    }
//                }
//            } catch (ArrayIndexOutOfBoundsException e) {
//            }
//            return (count >= numOfPiecesToWin);
//        }
//
//        private boolean isWinInRow(char player, int col) {
//            // collect row
//            int row = topPieceIndex[col];
//            // row is empty
//            if (row == height) {
//                return false;
//            }
//            // the cell itself
//            if (grid[row][col] != player) {
//                return false;
//            }
//            int count = 1;
//            // cells befor
//            try {
//                for (int i = col - 1; i >= 0; i--) {
//                    if (grid[row][i] == player) {
//                        count++;
//                    } else {
//                        break;
//                    }
//                }
//            } catch (ArrayIndexOutOfBoundsException e) {
//            }
//            // cells after
//            try {
//                for (int i = col + 1; i < width; i++) {
//                    if (grid[row][i] == player) {
//                        count++;
//                    } else {
//                        break;
//                    }
//                }
//            } catch (ArrayIndexOutOfBoundsException e) {
//            }
//            return (count >= numOfPiecesToWin);
//
//        }
//
//        private boolean isWinInDiagonal_1(char player, int col) {
//            // collect diagonal
//            int row = topPieceIndex[col];
//            // row is empty
//            if (row == height) {
//                return false;
//            }
//            // the cell itself
//            if (grid[row][col] != player) {
//                return false;
//            }
//            int count = 1;
//            // cells befor
//            try {
//                for (int i = 1;; i++) {
//                    if (grid[row - i][col - i] == player) {
//                        count++;
//                    } else {
//                        break;
//                    }
//                }
//            } catch (ArrayIndexOutOfBoundsException e) {
//            }
//            // cells after
//            try {
//                for (int i = 1;; i++) {
//                    if (grid[row + i][col + i] == player) {
//                        count++;
//                    } else {
//                        break;
//                    }
//                }
//            } catch (ArrayIndexOutOfBoundsException e) {
//            }
//            return (count >= numOfPiecesToWin);
//
//        }
//
//        private boolean isWinInDiagonal_2(char player, int col) {
//            // collect diagonal
//            int row = topPieceIndex[col];
//            // row is empty
//            if (row == height) {
//                return false;
//            }
//            // the cell itself
//            if (grid[row][col] != player) {
//                return false;
//            }
//            int count = 1;
//            // cells befor
//            try {
//                for (int i = 1;; i++) {
//                    if (grid[row - i][col + i] == player) {
//                        count++;
//                    } else {
//                        break;
//                    }
//                }
//            } catch (ArrayIndexOutOfBoundsException e) {
//            }
//            // cells after
//            try {
//                for (int i = 1;; i++) {
//                    if (grid[row + i][col - i] == player) {
//                        count++;
//                    } else {
//                        break;
//                    }
//                }
//            } catch (ArrayIndexOutOfBoundsException e) {
//            }
//            return (count >= numOfPiecesToWin);
//
//        }
//
//        private char otherPlayer(char player) {
//            if (player == 'x') {
//                return 'o';
//            }
//            return 'x';
//        }
//
//        @Override
//        public String toString() {
//            StringBuilder sb = new StringBuilder();
//            for (int i = 0; i < height; i++) {
//                sb.append(" | ");
//                for (int j = 0; j < width; j++) {
//                    sb.append(grid[i][j]);
//                    sb.append(" | ");
//                }
//                sb.append('\n');
//            }
//            sb.append(" ");
//            for (int i = 1; i < height; i++) {
//                sb.append("-----");
//            }
//            sb.append("\n | ");
//            for (int i = 1; i <= height; i++) {
//                sb.append(i);
//                sb.append(" | ");
//            }
//
//            return sb.toString();
//        }
//    }
//
//    public class Control {
//        char computer = 'o';
//        char human = 'x';
//        Gomoku board = new Gomoku(3, 3, 3);
//
//        public void play() {
//            System.out.println(board);
//            while (true) {
//                humanPlay();
//                System.out.println(board);
//
//                if (board.isWin(human)) {
//                    System.out.println("Human wins");
//                    break;
//                }
//                if (board.isWithdraw()) {
//                    System.out.println("Draw");
//                    break;
//                }
//                computerPlay();
//                System.out.println("_Computer Turn__");
//                System.out.println(board);
//                if (board.isWin(computer)) {
//                    System.out.println("Computer wins!");
//                    break;
//                }
//                if (board.isWithdraw()) {
//                    System.out.println("Draw");
//                    break;
//                }
//            }
//
//        }
//
//        // ***** YOUR CODE HERE ***** \\
//
//
//        /*
//         *
//         * Human plays**@return
//         * the column
//         * the human
//         * played in
//         */
//
//        private void humanPlay() {
//            Scanner s = new Scanner(System.in);
//            int col;
//            while (true) {
//                System.out.print("Enter column: ");
//                col = s.nextInt();
//                System.out.println();
//                if ((col > 0) && (col - 1 < board.getWidth())) {
//                    if (board.play(human, col - 1)) {
//                        return;
//                    }
//                    System.out.println("Invalid Column: Column " + col + " is full!, try agine");
//                }
//                System.out.println("Invalid Column: out of range " + board.getWidth() + ", try agine");
//            }
//        }
//
//        // ***** YOUR CODE HERE ***** \\
//        // function MaxMove
//        // function MaxMove
//        Gomoku maxMove(Gomoku g) {
//            Gomoku mahmoud = new Gomoku(g);
//            int value = Integer.MIN_VALUE;
//            List<Gomoku> movesList = g.GenerateMoves(computer);
//
//            if (movesList.isEmpty()) {
//                // Handle case where there are no available moves
//                return mahmoud;
//            }
//
//            for (Gomoku moves : movesList) {
//                int eval = minMove(moves).evaluate(computer);
//                if (eval > value) {
//                    mahmoud = moves;
//                    value = eval;
//                }
//            }
//            return mahmoud;
//        }
//
//        // function MinMove
//        Gomoku minMove(Gomoku g) {
//            Gomoku adnan = new Gomoku(g);
//            int value = Integer.MAX_VALUE;
//            List<Gomoku> movesList = g.GenerateMoves(human);
//
//            if (movesList.isEmpty()) {
//                // Handle case where there are no available moves
//                return adnan;
//            }
//
//            for (Gomoku moves : movesList) {
//                int eval = maxMove(moves).evaluate(human);
//                if (eval < value) {
//                    adnan = moves;
//                    value = eval;
//                }
//            }
//            return adnan;
//        }
//        private void computerPlay() {
//            this.board = alphaBetaMax(this.board, Integer.MIN_VALUE, Integer.MAX_VALUE);
//        }
//
//        private Gomoku alphaBetaMax(Gomoku g, int alpha, int beta) {
//            Gomoku bestMove = new Gomoku(g);
//            int value = Integer.MIN_VALUE;
//            List<Gomoku> movesList = g.GenerateMoves(computer);
//
//            if (movesList.isEmpty()) {
//                // Handle case where there are no available moves
//                return bestMove;
//            }
//
//            for (Gomoku moves : movesList) {
//                int eval = alphaBetaMin(moves, alpha, beta).evaluate(computer);
//                if (eval > value) {
//                    bestMove = moves;
//                    value = eval;
//                }
//                alpha = Math.max(alpha, value);
//                if (alpha >= beta) {
//                    break; // Beta cut-off
//                }
//            }
//            return bestMove;
//        }
//
//        private Gomoku alphaBetaMin(Gomoku g, int alpha, int beta) {
//            Gomoku bestMove = new Gomoku(g);
//            int value = Integer.MAX_VALUE;
//            List<Gomoku> movesList = g.GenerateMoves(human);
//
//            if (movesList.isEmpty()) {
//
//                return bestMove;
//            }
//
//            for (Gomoku moves : movesList) {
//                int eval = alphaBetaMax(moves, alpha, beta).evaluate(human);
//                if (eval < value) {
//                    bestMove = moves;
//                    value = eval;
//                }
//                beta = Math.min(beta, value);
//                if (beta <= alpha) {
//                    break; // Alpha cut-off
//                }
//            }
//            return bestMove;
//        }
//        public static void main(String[] args) {
//            Control g = new Control();
//            g.play();
//        }
//    }
//}
