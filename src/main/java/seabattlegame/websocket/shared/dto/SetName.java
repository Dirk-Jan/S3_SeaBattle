package seabattlegame.websocket.shared.dto;

public class SetName extends DataTransferObject {
    private String name;

    public String getName() {
        return name;
    }

    public SetName(int playerNr, String name, boolean player) {
        super(player ? DTOType.SETPLAYERNAME : DTOType.SETOPPONENTNAME, playerNr);
        this.name = name;
    }
}
