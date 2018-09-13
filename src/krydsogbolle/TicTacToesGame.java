package krydsogbolle;

public class TicTacToesGame {

    //commit:3 start
    //here i make an array that handle userside information about current board
    //right now this array wont work bacause the Playboard class has not been made yet
    //as default i make all the values nothing
    private Client[] gameBoard = {
        null, null, null,
        null, null, null,
        null, null, null};
    //commit:3 end


    //commit: 9 start
    //i will just create the first client as the server
    Client playerHost;
    //this will do here for now just to keep track of what needs to get done


    //i will create the statement for how to win,
    //i easily found this logic online but read trough it
    //and you'll find that its quite simple slavic walktrought of all possible correct cominations
    public boolean winnerFound(){
        return (
                gameBoard[0] != null && gameBoard[0] == gameBoard[1] && gameBoard[0] == gameBoard[2])
                ||(gameBoard[3] != null && gameBoard[3] == gameBoard[4] && gameBoard[3] == gameBoard[5])
                ||(gameBoard[6] != null && gameBoard[6] == gameBoard[7] && gameBoard[6] == gameBoard[8])
                ||(gameBoard[0] != null && gameBoard[0] == gameBoard[3] && gameBoard[0] == gameBoard[6])
                ||(gameBoard[1] != null && gameBoard[1] == gameBoard[4] && gameBoard[1] == gameBoard[7])
                ||(gameBoard[2] != null && gameBoard[2] == gameBoard[5] && gameBoard[2] == gameBoard[8])
                ||(gameBoard[0] != null && gameBoard[0] == gameBoard[4] && gameBoard[0] == gameBoard[8])
                ||(gameBoard[2] != null && gameBoard[2] == gameBoard[4] && gameBoard[2] == gameBoard[6]);

    }
    //this should do it, unfortunately as of the time of this commit i have no way to test if it works so ill just have to trust in my planning
    //commit: 9 end

    //commit: 10 start
    //i will here make the program have the ability to figure out if there are no more empty tiles in on the board
    public boolean fullBoard(){
        for (int i = 0; i < gameBoard.length; i++){
            if (gameBoard[i] == null ){
                return false;
            }
        }
    return true;
    }
    //i think this should be able to be called to check that the board is full or not
    //commit: 10 end



}
