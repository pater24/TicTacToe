import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import static javax.swing.JOptionPane.*;

public class ButtonGrid extends JPanel {

    private final int GRID_SIZE = 3;
    private static int numberOfClicks = 0;
    private GridButton[][] grid = new GridButton[3][3];
    private static boolean isGameOver = false;

    public ButtonGrid() {
        Border border = BorderFactory.createEmptyBorder(30, 30, 30, 30);
        this.setBorder(border);

        LayoutManager mgr = new GridLayout(this.GRID_SIZE, this.GRID_SIZE);
        this.setLayout(mgr);

        this.setupButtons();
    }

    private void setupButtons() {
        for (int x = 0; x < this.GRID_SIZE; x++) {
            for (int y = 0; y < this.GRID_SIZE; y++) {
                GridButton button = new GridButton(x, y);
                button.addActionListener(new ButtonListener());
                this.grid[x][y] = button;
                this.add(button);
            }
        }
    }

    private void checkWin(GridButton button) {
        Boolean isCenterButton = button.hasSameCoordinates(2, 2);

        if (checkVertical(button)) {
            showGameOver();
            return;
        }
        if (checkHorizontal(button)) {
            showGameOver();
            return;
        }
    }

    private void showGameOver() {
        isGameOver = true;

        int selectedOption = JOptionPane.showConfirmDialog(null, "Game Over.\n Play Again?", "Game Over", YES_NO_OPTION);

        switch (selectedOption) {
            case YES_OPTION:
                resetButtons();
                break;
            case NO_OPTION:
                System.exit(0);
                break;
        }
    }

    private void resetButtons() {
        numberOfClicks = 0;

        for (int i = 0; i < GRID_SIZE; i++) {
            for (int j = 0; j < GRID_SIZE; j++) {
                grid[i][j].setText("");
            }
        }
    }


    private Boolean checkVertical(GridButton button) {
        int verticalPosition = button.getyPosition();
        Boolean isWin = true;

        for(int i = 0; i < GRID_SIZE; i++) {
            GridButton otherButton = grid[i][verticalPosition];
            isWin = isWin && button.hasSameText(otherButton);
        }

        return isWin;
    }

    private Boolean checkHorizontal(GridButton button) {
        int horizontalPosition = button.getxPosition();
        Boolean isWin = true;

        for(int i = 0; i < GRID_SIZE; i++) {
            GridButton otherButton = grid[horizontalPosition][i];
            isWin = isWin && button.hasSameText(otherButton);
        }

        return isWin;
    }

    private class GridButton extends Button {

        public GridButton(int x, int y) {
            super(x, y);
        }

        public void click() {
            if (isGameOver || this.wasCLicked()) {
                return;
            }

            numberOfClicks++;

            String newText = ((numberOfClicks % 2) == 0) ? "O" : "X";

            this.setText(newText);
            checkWin(this);
        }
    }

    private class ButtonListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            GridButton buttonClicked = (GridButton) e.getSource();
            buttonClicked.click();
        }
    }

}
