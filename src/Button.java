import javax.swing.*;

public class Button extends JButton implements IButton {

    private int xPosition;
    private int yPosition;

    public Button(int xPosition, int yPosition) {
        this.xPosition = xPosition;
        this.yPosition = yPosition;
    }

    public int getxPosition() {
        return this.xPosition;
    }
//
    public int getyPosition() {
        return this.yPosition;
    }

    public Boolean wasCLicked() {
        return this.getText() != "";
    }

    public void click() {

    }

    public Boolean hasSameText(Button otherButton) {
        return this.getText() == otherButton.getText();
    }

    public Boolean hasSameCoordinates(int x, int y) {
        return this.xPosition == x && this.yPosition == y;
    }
}
