public class SquareModel {
    private int value;

    SquareModel() { value = 0; }

    public void reset() { value = 0; }

    public void setValue(int v){
	value = v;
    }
    
    public int square() {
	return value * value;
    }

}
