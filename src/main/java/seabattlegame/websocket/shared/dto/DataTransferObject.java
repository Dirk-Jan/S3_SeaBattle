package seabattlegame.websocket.shared.dto;

public class DataTransferObject {
    private String function;

    public String getFunction() {
        return function;
    }

    public DataTransferObject(String function) {
        this.function = function;
    }
}
