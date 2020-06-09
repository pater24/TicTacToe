import javax.swing.*;
import java.awt.*;

public class TicTacToe {

    private int buttonClicks = 0;
    private JButton buttons[] = new JButton[9];

    public TicTacToe() {
        JFrame frame = new JFrame();
        JPanel panel = new ButtonGrid();

        frame.add(panel, BorderLayout.CENTER);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setTitle("Tic Tac Toe");
        frame.pack();
        frame.setVisible(true);
    }

}
