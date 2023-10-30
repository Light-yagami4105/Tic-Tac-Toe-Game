import javax.swing.JButton;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class TicTacToeController {
    private TicTacToeModel model;
    private TicTacToeView view;

    public TicTacToeController(TicTacToeModel model, TicTacToeView view) {
        this.model = model;
        this.view = view;
        this.view.setButtonListener(new ButtonClickListener());
    }

    class ButtonClickListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            JButton clickedButton = (JButton) e.getSource();
            int row = -1, col = -1;

            // Find the clicked button's position
            outerloop:
            for (int i = 0; i < 3; i++) {
                for (int j = 0; j < 3; j++) {
                    if (clickedButton.equals(view.getButtons()[i][j])) {
                        row = i;
                        col = j;
                        break outerloop;
                    }
                }
            }

            // Make a move if the clicked position is valid
            if (row != -1 && col != -1) {
                if (model.makeMove(row, col)) {
                    view.updateButton(row, col, model.getCurrentPlayer());
                    if (model.checkForWinner()) {
                        view.showWinner(String.valueOf(model.getCurrentPlayer()));
                        view.resetBoard();
                        model.initializeBoard();
                    } else if (model.checkForTie()) {
                        view.showWinner("TIE");
                        view.resetBoard();
                        model.initializeBoard();
                    } else {
                        model.switchPlayer();
                    }
                }
            }
        }
    }

    public static void main(String[] args) {
        TicTacToeModel model = new TicTacToeModel();
        TicTacToeView view = new TicTacToeView();
        new TicTacToeController(model, view);
    }
}
