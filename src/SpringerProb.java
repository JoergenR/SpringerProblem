import java.util.Scanner;

public class SpringerProb {

    static int n;

    static boolean isSafe(int x, int y, int solution[][]) {
        return (x >= 0 && x < n && y >=0 && y < n && solution[x][y] == -1);
    }

    static void print(int solution[][]) {
        for(int x = 0; x < n; x++){
            for(int y = 0; y < n; y++)
                System.out.print(solution[x][y] + " ");
            System.out.println();

        }
    }
    public static boolean solveProblem(int i, int j)
    {
        int solution[][] = new int[n][n];

        // Setter hele brettet til -1
        for (int x = 0; x < n; x++)
            for(int y = 0; y < n; y++)
                solution[x][y] = -1;

        // Lagrer trekk springeren kan gjøre i arrays
        int X[] = {2, 2, 1,-1,-2,-2,-1, 1};
        int Y[] = {1,-1,-2,-2,-1, 1, 2, 2};

        // Setter startrute til oppsøkt
        solution[i][j] = 0;

        if (!solver(i,j,1,solution, X,Y)) {
            System.out.println("Finnes ingen løsning");
            return false;
        }
        else {
            print(solution);
        }
        return true;
    }

    public static boolean solver(int x, int y, int moves, int solution[][], int X[], int Y[]) {

        int x2, y2;

        if (moves == n*n)
            return true;

        // Prøver alle skritt fra nåværende rute
        for (int s = 0; s < n; s++){
            x2 = x + X[s];
            y2 = y + Y[s];
            if (isSafe(x2, y2, solution)) {
                solution[x2][y2] = moves;
                if (solver(x2, y2, moves +1, solution, X,Y))
                    return true;
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
        System.out.println("Enter starting row: ");
        int row = in.nextInt();
        System.out.println("Enter starting column: ");
        int col = in.nextInt();

        solveProblem(row, col);
    }
}
