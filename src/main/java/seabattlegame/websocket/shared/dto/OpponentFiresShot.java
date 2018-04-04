package seabattlegame.websocket.shared.dto;

import seabattlegui.ShotType;

public class OpponentFiresShot extends DataTransferObject{
    private ShotType shotType;

    public ShotType getShotType() {
        return shotType;
    }

    public OpponentFiresShot(int playerNr, ShotType shotType) {
        super(DTOType.OPPONENTFIRESSHOT, playerNr);
        this.shotType = shotType;
    }
}
