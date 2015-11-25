package main.sudoku;

public class stubGame implements Playable {
    int _gameID;
    int _difficulty;
    // aqui faltaría un board al que asociar el game, pero no el necesito per Stats.

    public stubGame(int gameID, int difficulty) {
        _gameID = gameID;
        _difficulty = difficulty;
    }

    public int getID() {
        return _gameID;
    }

    public int getDifficulty() {
        return _difficulty;
    }
}