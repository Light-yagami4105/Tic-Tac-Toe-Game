import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.io.Serializable;

public class TicTacToeView extends JFrame implements Serializable {
    private static final long serialVersionUID = 1L; // Added serialVersionUID

    private JButton[][] buttons;

    public TicTacToeView() {
        setTitle("Tic-Tac-Toe");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new GridLayout(3, 3));
        buttons = new JButton[3][3];

        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                buttons[i][j] = new JButton();
                add(buttons[i][j]);
            }
        }

        pack();
        setLocationRelativeTo(null);
        setVisible(true);
    }

    public JButton[][] getButtons(){
    	return buttons;
    }
    public void resetBoard() {
        for (JButton[] buttonRow : buttons) {
            for (JButton button : buttonRow) {
                button.setText("");
            }
        }
    }

    public void setButtonListener(ActionListener listener) {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                buttons[i][j].addActionListener(listener);
            }
        }
    }

    public void updateButton(int row, int col, char playerSymbol) {
        buttons[row][col].setText(String.valueOf(playerSymbol));
    }

    public void showWinner(String winner) {
        if (winner.equals("TIE")) {
            JOptionPane.showMessageDialog(this, "TIE", "Game Over", JOptionPane.INFORMATION_MESSAGE);
        } else {
            JOptionPane.showMessageDialog(this, "Player " + winner + " wins!", "Game Over", JOptionPane.INFORMATION_MESSAGE);
        }
    }
}
