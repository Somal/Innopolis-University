/* Answer on question 1:
*   During rendering we give new data from application every frame. And every frame we have following action chain.
*   Firstly Controller gives data from Model, handlers it and send to View, which show new data on Frame.
*
*	This chain done in countless loop.
* */


public class Main {
    public static void main(String[] args) {
	SquareModel M = new SquareModel();
	SquareView V = new SquareView(M);
	V.setVisible(true);
    }
}
