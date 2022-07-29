import java.util.Scanner;
public class Main {
    public static void main(String[] args) {
        char[][] grid = new char[3][3];

        Scanner scanner = new Scanner(System.in);
        String stream = scanner.next();
        int streamI = 0;
        System.out.println("---------");
        for(int i = 0; i < 3; i++)
        {
            System.out.print("| ");
            for (int j = 0; j < 3; j++)
            {
                grid[i][j] = stream.charAt(streamI);
                char cell = grid[i][j];
                System.out.print(cell + " ");
                streamI++;
            }
            System.out.print("|\n");
        }
        System.out.println("---------");
        System.out.println(checkWin(grid));
    }

    private static String checkWin(char[][] grid){
        char winningChar = 'X';
        String winningX = checkForChar(grid, winningChar);
        //if (winningX != null) return winningX;
        winningChar = 'O';
        String winningO = checkForChar(grid, winningChar);
        //if (winningO != null) return winningO;
        String Impossible = checkForImpossible(grid, winningX, winningO);
        if (Impossible != null) return Impossible;
        if (winningX != null) return winningX;
        if (winningO != null) return winningO;
        String finish = checkForFinish(grid);
        if(finish != null) return finish;
        return "Draw";
    }

    private static String checkForImpossible(char[][] grid, String winningX, String winningO) {
        int xCounter = 0, oCounter = 0;
        for(int i = 0; i < 3; i++)
        {
            for(int j = 0; j < 3; j++)
            {
                if(grid[i][j] == 'X')
                    xCounter++;
                else if(grid[i][j] == 'O')
                    oCounter++;
            }
        }
        if((winningX != null && winningO != null) || Math.abs(xCounter - oCounter) > 1) {
            return "Impossible";
        }
        return null;
    }

    private static String checkForFinish(char[][] grid){
        for(int i = 0; i < 3; i++)
        {
            for(int j = 0; j < 3; j++)
            {
                if(grid[i][j] == '_'){
                    return "Game not finished";
                }
            }
        }
        return null;
    }

    private static String checkForChar(char[][] grid, char winningChar) {
        for(int i = 0; i < 3; i++)
        {
            boolean isEqual = true;
            for(int j = 0; j < 3; j++)
            {
                if(grid[i][j] != winningChar){
                    isEqual = false;
                    break;
                }
            }
            if(isEqual){
                return (String.format("%c wins", winningChar));
            }
        }
        for(int i = 0; i < 3; i++)
        {
            boolean isEqual = true;
            for(int j = 0; j < 3; j++)
            {
                if(grid[j][i] != winningChar){
                    isEqual = false;
                    break;
                }
            }
            if(isEqual){
                return (String.format("%c wins", winningChar));
            }
        }
        if((grid[0][0] == winningChar && grid[1][1] == winningChar && grid[2][2] == winningChar) ||
            (grid[0][2] == winningChar && grid[1][1] == winningChar && grid[2][0] == winningChar))
        {
            return (String.format("%c wins", winningChar));
        }
        return null;
    }

}