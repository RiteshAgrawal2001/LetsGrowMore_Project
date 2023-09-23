import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class TicTacToeGUI {
    private static final int BOARD_SIZE = 3;
    private static final char[] PLAYERS = {'X', 'O'};
    private char[][] board;
    private JButton[][] buttons;
    private char currentPlayer;
    private JLabel statusLabel;

    public TicTacToeGUI() {
        board = new char[BOARD_SIZE][BOARD_SIZE];
        buttons = new JButton[BOARD_SIZE][BOARD_SIZE];
        currentPlayer = PLAYERS[0];

        JFrame frame = new JFrame("Tic-Tac-Toe");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new BorderLayout());

        JPanel gamePanel = new JPanel(new GridLayout(BOARD_SIZE, BOARD_SIZE));
        frame.add(gamePanel, BorderLayout.CENTER);

        for (int row = 0; row < BOARD_SIZE; row++) {
            for (int col = 0; col < BOARD_SIZE; col++) {
                buttons[row][col] = new JButton("");
                buttons[row][col].setFont(new Font("Arial", Font.PLAIN, 60));
                int finalRow = row;
                int finalCol = col;
                buttons[row][col].addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        makeMove(finalRow, finalCol);
                    }
                });
                gamePanel.add(buttons[row][col]);
            }
        }

        statusLabel = new JLabel("Player " + currentPlayer + "'s turn");
        statusLabel.setFont(new Font("Arial", Font.PLAIN, 20));
        frame.add(statusLabel, BorderLayout.SOUTH);

        frame.setSize(300, 300);
        frame.setVisible(true);
    }

    private void makeMove(int row, int col) {
        if (board[row][col] == '\u0000') {
            board[row][col] = currentPlayer;
            buttons[row][col].setText(String.valueOf(currentPlayer));
            if (checkWin(row, col)) {
                statusLabel.setText("Player " + currentPlayer + " wins!");
                disableButtons();
            } else if (isBoardFull()) {
                statusLabel.setText("It's a draw!");
            } else {
                currentPlayer = (currentPlayer == PLAYERS[0]) ? PLAYERS[1] : PLAYERS[0];
                statusLabel.setText("Player " + currentPlayer + "'s turn");
            }
        }
    }

    private boolean checkWin(int row, int col) {
        char player = board[row][col];

        // Check row
        for (int i = 0; i < BOARD_SIZE; i++) {
            if (board[i][col] != player) {
                break;
            }
            if (i == BOARD_SIZE - 1) {
                return true;
            }
        }

        // Check column
        for (int i = 0; i < BOARD_SIZE; i++) {
            if (board[row][i] != player) {
                break;
            }
            if (i == BOARD_SIZE - 1) {
                return true;
            }
        }

        // Check diagonal
        if (row == col) {
            for (int i = 0; i < BOARD_SIZE; i++) {
                if (board[i][i] != player) {
                    break;
                }
                if (i == BOARD_SIZE - 1) {
                    return true;
                }
            }
        }

        // Check anti-diagonal
        if (row + col == BOARD_SIZE - 1) {
            for (int i = 0; i < BOARD_SIZE; i++) {
                if (board[i][BOARD_SIZE - 1 - i] != player) {
                    break;
                }
                if (i == BOARD_SIZE - 1) {
                    return true;
                }
            }
        }

        return false;
    }

    private boolean isBoardFull() {
        for (int i = 0; i < BOARD_SIZE; i++) {
            for (int j = 0; j < BOARD_SIZE; j++) {
                if (board[i][j] == '\u0000') {
                    return false;
                }
            }
        }
        return true;
    }

    private void disableButtons() {
        for (int row = 0; row < BOARD_SIZE; row++) {
            for (int col = 0; col < BOARD_SIZE; col++) {
                buttons[row][col].setEnabled(false);
            }
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new TicTacToeGUI());
    }
}
