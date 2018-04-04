package seabattlegame.websocket.shared.dto;

import seabattlegui.ShotType;

public class OpponentFiresShot extends DataTransferObject{
    private int playerNr;
    private ShotType shotType;

    public int getPlayerNr() {
        return playerNr;
    }

    public ShotType getShotType() {
        return shotType;
    }

    public OpponentFiresShot(int playerNr, ShotType shotType) {
        super(DTOType.OPPONENTFIRESSHOT);
        this.playerNr = playerNr;
        this.shotType = shotType;
    }
}
