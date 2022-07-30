import java.util.Scanner;
 class CellIsNotEmptyException extends Exception
{
    public CellIsNotEmptyException(String str)
    {
        // Calling constructor of parent Exception
        super(str);
    }
}
public class Main {
    public static void main(String[] args) {
        char[][] grid = new char[3][3];

        int moveCount = 0;
        char curPlayer = 'X';
        System.out.println("---------");
        for(int i = 0; i < 3; i++)
        {
            System.out.print("| ");
            for (int j = 0; j < 3; j++)
            {
                grid[i][j] = '_';
                System.out.print(grid[i][j] + " ");
            }
            System.out.print("|\n");
        }
        System.out.println("---------");
        //System.out.println(checkWin(grid));
        while (moveCount <= 9) {
            playerMove(grid,curPlayer);

            curPlayer = (curPlayer == 'X') ? 'O': 'X';

            showGrid(grid);
            String currentResults = checkWin(grid);
            if(currentResults.equals("Game not finished"))
                moveCount++;
            else {
                System.out.println(currentResults);
                break;
            }
        }


    }

    private static void showGrid(char[][] grid) {
        System.out.println("---------");
        for(int i = 0; i < 3; i++)
        {
            System.out.print("| ");
            for (int j = 0; j < 3; j++)
            {
                char cell = grid[i][j];
                System.out.print(cell + " ");
            }
            System.out.print("|\n");
        }
        System.out.println("---------");
    }


    private static void playerMove(char[][] grid, char curPlayer) {
        int x = 0,y = 0;
        boolean flag;
        do {
            try {
                Scanner sc = new Scanner(System.in);
                x = sc.nextInt();
                y = sc.nextInt();
                if(grid[x - 1][y - 1] == '_'){
                    grid[x - 1][y - 1] = curPlayer;
                }
                else{
                    throw new CellIsNotEmptyException("Cell is occupied");
                }
                flag=false;
            }
            catch (ArrayIndexOutOfBoundsException e){
                System.out.println("Coordinates should be from 1 to 3!");
                flag = true;
            }
            catch (CellIsNotEmptyException cellIsNotEmptyException)
            {
                System.out.println("This cell is occupied! Choose another one!");
                flag = true;
            }
            catch(Exception e)
            {
                // accept integer only.
                System.out.println("You should enter numbers!");
                flag = true;
            }
        }
        while(flag);
    }

    private static String checkWin(char[][] grid){
        char winningChar = 'X';
        String winningX = checkForChar(grid, winningChar);
        winningChar = 'O';
        String winningO = checkForChar(grid, winningChar);
        if (winningX != null) return winningX;
        if (winningO != null) return winningO;
        String finish = checkForFinish(grid);
        if(finish != null) return finish;
        return "Draw";
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