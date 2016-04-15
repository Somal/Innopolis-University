import javax.swing.*;
import java.awt.event.*;

public class SquareView extends JFrame {
    private JTextField input = new JTextField(5);
    private JTextField output = new JTextField(10);
    private JButton sqrBtn = new JButton("Square");
    //
    private SquareModel M;

    public SquareView(SquareModel m) {
        M = m;

        // Layout
        JPanel content = new JPanel();
        content.add(input);
        content.add(sqrBtn);
        content.add(output);
        //
        addSquareListener(new MyListener());
        addInputListener(new InputListener());
        //

        this.setContentPane(content);
        this.pack();
        //
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    public String getNumber() {
        return input.getText();
    }

    public void setNumber(String v) {
        output.setText(v);
    }

    public void addSquareListener(ActionListener a) {
        sqrBtn.addActionListener(a);
    }

    public void addInputListener(KeyListener al) {
        this.input.addKeyListener(al);
    }


    class MyListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            String userinput = getNumber();
            M.setValue(Integer.parseInt(userinput));
            setNumber(Integer.toString(M.square()));
        }
    }

    class InputListener implements KeyListener {
        public void keyTyped(KeyEvent e) {
            setNumber("");
        }

        public void keyReleased(KeyEvent e) {
        }

        public void keyPressed(KeyEvent e) {
        }
    }

}
