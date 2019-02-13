package seabattlegame.rest.client;

public class MyCalculator implements ICalculator {

    @Override
    public int abs(int value) {
        return (value < 0)? -value : value;
    }

    @Override
    public int naa(int value) {
        return 0;
    }
}
