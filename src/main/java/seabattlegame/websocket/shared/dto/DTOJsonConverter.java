package seabattlegame.websocket.shared.dto;

import com.google.gson.Gson;
import seabattlegui.ShotType;

public class DTOJsonConverter {

    //Json string wordt geconverteerd naar een DTO om het type te kunnen herhalen, daarna wordt de json string
    //per type omgezet naar het juiste object

    public static DataTransferObject convertJsonToDTO(String json){
        return  new Gson().fromJson(json, DataTransferObject.class);
    }

    public static String convertTypeToJson(ShotType type){
        return new Gson().toJson(type);
    }

    public static String convertDTOToJson(DataTransferObject dto){
        return new Gson().toJson(dto);
    }

    public static ShowSquare convertJsonToShowSquare(String json){
        return  new Gson().fromJson(json, ShowSquare.class);
    }

    public static SetName convertJsonToSetName(String json){
        return  new Gson().fromJson(json, SetName.class);
    }

    public static OpponentFiresShot convertJsonToFireShot(String json){
        return new Gson().fromJson(json, OpponentFiresShot.class);
    }

    public static ShotType convertJsonToShotType(String json){
        if(json.contains("SUNK")){
            return ShotType.SUNK;
        }
        else if(json.contains("MISSED")){
            return ShotType.MISSED;
        }
        else if(json.contains("ALLSUNK")){
            return ShotType.ALLSUNK;
        }
        else
            return ShotType.HIT;
    }
}
