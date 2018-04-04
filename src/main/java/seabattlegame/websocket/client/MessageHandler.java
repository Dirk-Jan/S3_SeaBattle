package seabattlegame.websocket.client;

import seabattlegame.websocket.shared.dto.*;
import seabattlegui.ISeaBattleGUI;

import java.util.Observable;
import java.util.Observer;

public class MessageHandler implements Observer{
    private ISeaBattleGUI gui;

    public MessageHandler(ISeaBattleGUI gui) {
        this.gui = gui;
    }

    //Klassen worden meerdere keren geconverteerd uit gemak
    //We kunnen geen DataTransferObject naar een child klasse casten vanuit JSON
    @Override
    public void update(Observable o, Object arg) {
        String jsonObject = (String)arg;
        DataTransferObject dto = DTOJsonConverter.convertJsonToDTO(jsonObject);

        switch(dto.getType()){
            case OPPONENTFIRESSHOT:
                OpponentFiresShot firesShot = DTOJsonConverter.convertJsonToFireShot(jsonObject);
                gui.opponentFiresShot(firesShot.getPlayerNr(), firesShot.getShotType());
                break;
            case SETOPPONENTNAME:
                SetName setNameP = DTOJsonConverter.convertJsonToSetName(jsonObject);
                gui.setPlayerName(setNameP.getPlayerNr(), setNameP.getName());
                break;
            case SETPLAYERNAME:
                SetName setNameO = DTOJsonConverter.convertJsonToSetName(jsonObject);
                gui.setPlayerName(setNameO.getPlayerNr(), setNameO.getName());
                break;
            case SHOWSQUAREOPPONENT:
                ShowSquare showSquareO = DTOJsonConverter.convertJsonToShowSquare(jsonObject);
                gui.showSquarePlayer(showSquareO.getPlayerNr(), showSquareO.getX(), showSquareO.getY(), showSquareO.getSquareState());
                break;
            case SHOWSQUAREPLAYER:
                ShowSquare showSquareP = DTOJsonConverter.convertJsonToShowSquare(jsonObject);
                gui.showSquarePlayer(showSquareP.getPlayerNr(), showSquareP.getX(), showSquareP.getY(), showSquareP.getSquareState());
                break;

        }

    }


}
