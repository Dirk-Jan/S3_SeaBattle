package seabattlegame.websocket.shared.dto;

public class DataTransferObject {
    private DTOType type;
    private int playerNr;

    public DataTransferObject(DTOType type, int playerNr) {
        this.type = type;
        this.playerNr = playerNr;
    }

    public int getPlayerNr() {
        return playerNr;
    }
    public DTOType getType(){ return type; }
}
