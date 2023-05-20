import java.util.*;
public class TTT2 {
    static ArrayList<Integer> playerPos = new ArrayList<>();
    static ArrayList<Integer> cpuPos = new ArrayList<>();

    public static void main(String[] args) {

        char[][] Board = {{' ', '|', ' ', '|', ' '},
                {'-', '+', '-', '+', '-'},
                {' ', '|', ' ', '|', ' '},
                {'-', '+', '-', '+', '-'},
                {' ', '|', ' ', '|', ' '}};

        display(Board);

        while (true) {
            Scanner UserInput = new Scanner(System.in);
            System.out.println("Enter the number for your placement on the board(1-9):");
            int Player = UserInput.nextInt();

            while (playerPos.contains(Player) || cpuPos.contains(Player)) {
                System.out.println("Taken spot! Enter valid spot");
                Player = UserInput.nextInt();
            }

            UserPlace(Board, Player, "player");
            String dub = Winner();

            Random rand = new Random();
            int CPU = rand.nextInt((9) + 1);
            while (playerPos.contains(CPU) || cpuPos.contains(CPU)) {
                CPU = rand.nextInt((9) + 1);
            }
            UserPlace(Board, CPU, "computer");

            display(Board);

            dub = Winner();
            if(dub.contains("Congratulations you won!") || dub.contains("Computer wins! Sorry play again!")){
                System.out.println(dub);
                System.out.println("Thanks for playing my game, play again sometime!");
                break;
            }
            else if(dub.contains("CAT")){
                System.out.println(dub);
                System.out.println("Thanks for playing my game, play again sometime!");
                break;
            }
        }
    }

    public static void display(char[][] Board) {
        for (char[] rower : Board) {
            for (char symbol : rower) {
                System.out.print(symbol);
            }
            System.out.println();
        }
    }

    public static void UserPlace(char[][] Board, int spot, String user) {

        char symbol = ' ';

        if (user.equals("player")) {
            symbol = 'X';
            playerPos.add(spot);
        } else if (user.equals("computer")) {
            symbol = 'O';
            cpuPos.add(spot);
        }
        switch (spot) {
            case 1 -> Board[0][0] = symbol;
            case 2 -> Board[0][2] = symbol;
            case 3 -> Board[0][4] = symbol;
            case 4 -> Board[2][0] = symbol;
            case 5 -> Board[2][2] = symbol;
            case 6 -> Board[2][4] = symbol;
            case 7 -> Board[4][0] = symbol;
            case 8 -> Board[4][2] = symbol;
            case 9 -> Board[4][4] = symbol;
            default -> {
            }
        }
    }

    public static String Winner() {

        List<Integer> top = Arrays.asList(1, 2, 3);
        List<Integer> mid = Arrays.asList(4, 5, 6);
        List<Integer> bottom = Arrays.asList(7, 8, 9);
        List<Integer> leftCol = Arrays.asList(1, 4, 7);
        List<Integer> midRol = Arrays.asList(2, 5, 8);
        List<Integer> rightCol = Arrays.asList(3, 6, 9);
        List<Integer> Cross1 = Arrays.asList(1, 5, 9);
        List<Integer> Cross2 = Arrays.asList(7, 5, 3);

       List<List> winning = new ArrayList<>();
        winning.add(top);
        winning.add(mid);
        winning.add(bottom);
        winning.add(leftCol);
        winning.add(midRol);
        winning.add(rightCol);
        winning.add(Cross1);
        winning.add(Cross2);
        for (List l : winning) {
            if (playerPos.containsAll(l)) {
                return "Congratulations you won!";
            } else if (cpuPos.containsAll(l)) {
                return "Computer wins! Sorry play again!";
            } else if ((playerPos.size() + cpuPos.size()) >= 9 ){
                return "CAT!";
            }
        }System.out.println(playerPos.size());
        System.out.println(cpuPos.size());



        return " ";
    }
}

