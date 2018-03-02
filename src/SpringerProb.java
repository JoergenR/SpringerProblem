import java.util.Scanner;

public class SpringerProb {

    static int n;
    public static int saveMoves[][];

    static boolean canMoveHere(int x, int y, int solution[][]) {
        return (x >= 0 && x < n && y >=0 && y < n && solution[x][y] == -1);
    }

    static void print() {

        for (int i =0; i < saveMoves.length -1; i++)
        {
            System.out.println("("+saveMoves[i][0] + ", " + saveMoves[i][1]+"),("+saveMoves[i+1][0] +", "+saveMoves[i+1][1]+")");
        }
    }
    public static boolean solveProblem(int i, int j)
    {
        int solution[][] = new int[n][n];
        saveMoves = new int[n*n][2];

        // Setter hele brettet til -1
        for (int x = 0; x < n; x++)
            for(int y = 0; y < n; y++)
                solution[x][y] = -1;

        // Lagrer trekk springeren kan gjøre i arrays
        int X[] = {2, 2, 1,-1,-2,-2,-1, 1};
        int Y[] = {1,-1,-2,-2,-1, 1, 2, 2};

        // Setter startrute til oppsøkt
        solution[i][j] = 0;
        saveMoves[0][0] = j;
        saveMoves[0][1] = i;

        if (!findSolution(i,j,1,solution, X,Y)) {
            System.out.println("Finnes ingen løsning");
            return false;
        }
        else {
            print();
        }
        return true;
    }

    public static boolean findSolution(int x, int y, int moves, int solution[][], int X[], int Y[]) {

        int x2, y2;
        if (moves == n*n)
            return true;

        // Prøver alle skritt fra nåværende rute
        for (int s = 0; s < 8; s++){
            x2 = x + X[s];
            y2 = y + Y[s];
            if (canMoveHere(x2, y2, solution)) {
                solution[x2][y2] = moves;
                if (findSolution(x2, y2, moves +1, solution, X,Y)) {
                    saveMoves[moves][0] = y2;
                    saveMoves[moves][1] = x2;
                    return true;
                }
                else
                    solution[x2][y2] = -1;

            }
        }
        return false;
    }

    public static void main(String[] args) {

        // Leser input fra user og starter solveProblem

        Scanner in = new Scanner(System.in);
        System.out.print("Size of the board: ");
        n = in.nextInt();
        System.out.print("Enter starting column: ");
        int col = in.nextInt();
        System.out.print("Enter starting row: ");
        int row = in.nextInt();

        solveProblem(row, col);
    }
}
