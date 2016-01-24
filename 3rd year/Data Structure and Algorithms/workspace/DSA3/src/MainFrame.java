import javax.swing.*;


public class MainFrame extends JFrame{
    private DrawingPanel drawPanel; // panel for drawing//without changes

    public MainFrame(){
        super("Week3");

        drawPanel = new DrawingPanel();
        add(drawPanel);
        
        setSize(drawPanel.width, drawPanel.height);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setVisible(true);
    }

    public static void main(String[] args){
        new MainFrame();
    }
}
