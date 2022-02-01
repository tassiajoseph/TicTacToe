import java.util.*;

public class TicTacToe {

    // Global
    // Used to compare the winning List<List> to *.Position ArrayList
    static ArrayList<Integer> playerPositions = new ArrayList<Integer>(); // empty player list
    static ArrayList<Integer> cpuPositions = new ArrayList<Integer>(); // empty cpu list

    // start main method
    public static void main(String[] args) {

        /* ========================================================
            Game Board Setup
            3 rows and 3 columns w/ 2 extra for symbols in between
        =========================================================== */
        char[][] gameBoard = {
            {' ', '|', ' ', '|', ' '},
            {'-', '+', '-', '+', '-'},
            {' ', '|', ' ', '|', ' '},
            {'-', '+', '-', '+', '-'},
            {' ', '|', ' ', '|', ' '}
        };

        printGameBoard(gameBoard); // call the game board
        System.out.println(); // new line

        // run this code until someone wins or all picks have been chosen
        while(true) {
            Scanner scan = new Scanner(System.in); // initiate the scanner
            System.out.println("Enter your placement (1-9): "); // user input

            /* ===================================================
                Get user's input then make sure it's only used once
            ====================================================== */
            int playerPos = scan.nextInt(); // save user input in variable

            while(playerPositions.contains(playerPos) || cpuPositions.contains(playerPos)) {
                System.out.println("Position taken! Enter a correct position: ");
                playerPos = scan.nextInt();
            }

            // User's pick
            placePiece(gameBoard, playerPos, "player"); // call place piece for user

            /* ==================================
                Check winner and result for user
            ===================================== */
            String result = checkWinner(); // call check winner
            if(result.length() > 0) {
                printGameBoard(gameBoard); // call the game board
                System.out.println();
                System.out.println(result); // print winning String
                break;
            }

            /* ===================================================
                Get CPU's input then make sure it's only used once
            ====================================================== */

            // Randomise CPU's pick
            Random rand = new Random(); // initialize random nums
            int cpuPos = rand.nextInt(9) + 1; // change from 0-8 to 1-9

            while(playerPositions.contains(cpuPos) || cpuPositions.contains(cpuPos)) {
                cpuPos = rand.nextInt(9) + 1;
            }

            // CPU's pick
            placePiece(gameBoard, cpuPos, "cpu"); // call place piece for cpu

            /* ==========================
                Show updated game board
            ============================= */
            printGameBoard(gameBoard); // call the game board
            System.out.println();

            /* =================================
                Check winner and result for cpu
            ==================================== */
            result = checkWinner(); // call check winner
            if(result.length() > 0) {
                printGameBoard(gameBoard); // call the game board
                System.out.println();
                System.out.println(result); // print winning String
                break;
            }
        }

    } // end main method


    /* ===========================
        Print Game Board Method
    ============================== */

    public static void printGameBoard(char[][] gameBoard) { // 2D char array parameter
        // Print out the game board w/ foreach
        for (char[] row : gameBoard) { // for each row
            for (char c : row) { // for each char in each row
                System.out.print(c); // print the char
            }
            System.out.println(); // add new line
        }
    } // end printGameBoard


    /* ================================================================================
        Place Piece Method
        parameters =
        get the game board + get the pos num + get if it's the user or cpu's input
    ==================================================================================== */

    public static void placePiece(char[][] gameBoard, int pos, String user) {

        char symbol = ' '; // default empty char

        // .equals() compares Strings
        if(user.equals("player")) { // parameter user = player
            symbol = 'X';
            playerPositions.add(pos); // add to playerPositions ArrayList
        } else if(user.equals("cpu")) {
            symbol = 'O';
            cpuPositions.add(pos); // add to cpuPositions ArrayList
        }


        /* ==========================================================
            Switch statement - switch from the pos that user enters
            - change this element of the game board into X or O
            - skipping row 1 & 3 because they are symbols
        ============================================================= */

        switch(pos) {
            case 1:
                gameBoard[0][0] = symbol; // [row] [column]
                break;
            case 2:
                gameBoard[0][2] = symbol;
                break;
            case 3:
                gameBoard[0][4] = symbol;
                break;
            case 4:
                gameBoard[2][0] = symbol;
                break;
            case 5:
                gameBoard[2][2] = symbol;
                break;
            case 6:
                gameBoard[2][4] = symbol;
                break;
            case 7:
                gameBoard[4][0] = symbol;
                break;
            case 8:
                gameBoard[4][2] = symbol;
                break;
            case 9:
                gameBoard[4][4] = symbol;
                break;
            default:
                break;
        }
    } // end Switch


    /* ==================================
        Check Winner Method
        Possible outcomes:
        - if one of three rows match
        - if one of three columns match
        - if one of two diagonals match
    ===================================== */

    public static String checkWinner() {

        // Rows
        List topRow = Arrays.asList(1, 2, 3);
        List midRow = Arrays.asList(4, 5, 6);
        List botRow = Arrays.asList(7, 8, 9);

        // Columns
        List leftCol = Arrays.asList(1, 4, 7);
        List midCol = Arrays.asList(2, 5, 8);
        List rightCol = Arrays.asList(3, 6, 9);

        // Diagonals
        List cross1 = Arrays.asList(1, 5, 9);
        List cross2 = Arrays.asList(7, 5, 3);


        // add possible outcomes into list
        List<List> winning = new ArrayList<List>();
        winning.add(topRow);
        winning.add(midRow);
        winning.add(botRow);
        winning.add(leftCol);
        winning.add(midCol);
        winning.add(rightCol);
        winning.add(cross1);
        winning.add(cross2);

        // for each list inside of winning list
        for(List l : winning) {
            if(playerPositions.containsAll(l)) {
                return "*************************\nCongratulations! You won!\n*************************\n";
            } else if(cpuPositions.containsAll(l)) { // contains all the winning conditions
                return "******************\nCPU wins! Sorry :(\n******************\n";
            } else if(playerPositions.size() + cpuPositions.size() == 10) {
                return "****\nCAT!\n****\n";
            }
        }
        return "";
    }

} // end Tic Tac Toe class
