package seabattlegame;

import seabattlegame.websocket.shared.dto.DTOJsonConverter;
import seabattlegame.websocket.shared.dto.DataTransferObject;
import seabattlegame.websocket.shared.dto.ShowSquare;
import seabattlegui.SquareState;

public class TestJsonDTO {
    static DataTransferObject testSquare = new ShowSquare(7,2,3, SquareState.WATER, false);
    static String json;
//TODO: even kijken waarom playernr 0 wordt
    public static void main(String[] args) {

        json = DTOJsonConverter.convertDTOToJson(testSquare);
        System.out.println(json);

        DataTransferObject squareTest = DTOJsonConverter.convertJsonToDTO(json);
        System.out.println(squareTest.getType());

        ShowSquare square = DTOJsonConverter.convertJsonToShowSquare(json);
        System.out.println(square.getType());
    }
}
