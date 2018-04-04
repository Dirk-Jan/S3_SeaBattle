package seabattlegame.websocket.shared.dto;

public class SetName extends DataTransferObject {
    private int playerNr;
    private String name;

    public int getPlayerNr() {
        return playerNr;
    }

    public String getName() {
        return name;
    }

    public SetName(int playerNr, String name) {
        super("setname");
        this.playerNr = playerNr;
        this.name = name;
    }
}
