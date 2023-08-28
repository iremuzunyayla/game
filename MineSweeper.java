import button.button;

import java.awt.GridLayout;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JOptionPane;


public class MineSweeper implements MouseListener {
    JFrame frame;
    button[][] board = new button[10][10];
    int openButton;

    public MineSweeper() {
        openButton = 0;
        frame = new JFrame("MayÃ½n TarlasÃ½");
        frame.setSize(800, 800);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new GridLayout(10, 10));

        for (int row = 0; row < board.length; row++) {
            for (int col = 0; col < board[0].length; col++) {
                button b = new button(row, col);
                frame.add(b);
                b.addMouseListener(this);
                board[row][col] = b;
            }
        }

        generateMine();
        updateCount();
        //printMine();

        frame.setVisible(true);
    }

    public void generateMine() {
        int i = 0;
        while (i < 10) {
            int randRow = (int) (Math.random() * board.length);
            int randCol = (int) (Math.random() * board[0].length);

            while (board[randRow][randCol].isMine()) {
                randRow = (int) (Math.random() * board.length);
                randCol = (int) (Math.random() * board[0].length);
            }
            board[randRow][randCol].setMine(true);
            i++;
        }
    }

    public void print() {
        for (int row = 0; row < board.length; row++) {
            for (int col = 0; col < board[0].length; col++) {
                if (board[row][col].isMine()) {
                    board[row][col].setIcon(new ImageIcon("mine.png"));
                } else {
                    board[row][col].setText(board[row][col].getCount() + "");
                    board[row][col].setEnabled(false);
                }
            }
        }
    }

    public void printMine() {
        for (int row = 0; row < board.length; row++) {
            for (int col = 0; col < board[0].length; col++) {
                if (board[row][col].isMine()) {
                    board[row][col].setIcon(new ImageIcon("mine.png"));
                }
            }
        }
    }

    public void updateCount() {
        for (int row = 0; row < board.length; row++) {
            for (int col = 0; col < board[0].length; col++) {
                if (board[row][col].isMine()) {
                    counting(row, col);
                }
            }
        }
    }

    public void counting(int row, int col) {
        for (int i = row - 1; i <= row + 1; i++) {
            for (int k = col - 1; k <= col + 1; k++) {
                try {
                    int value = board[i][k].getCount();
                    board[i][k].setCount(++value);
                } catch (Exception e) {

                }
            }
        }
    }

    public void open(int r, int c) {
        if (r < 0 || r >= board.length || c < 0 || c >= board[0].length || board[r][c].getText().length() > 0
                || board[r][c].isEnabled() == false) {
            return;
        } else if (board[r][c].getCount() != 0) {
            board[r][c].setText(board[r][c].getCount() + "");
            board[r][c].setEnabled(false);
            openButton++;
        } else {
            openButton++;
            board[r][c].setEnabled(false);
            open(r - 1, c);
            open(r + 1, c);
            open(r, c - 1);
            open(r, c + 1);
        }
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        button b = (button) e.getComponent();
        if (e.getButton() == 1) {
            System.out.println("sol tÃ½k");
            if (b.isMine()) {
                JOptionPane.showMessageDialog(frame, "Game OVER !");
                print();
            } else {
                open(b.getRow(), b.getCol());
                if (openButton == (board.length * board[0].length) - 10) {
                    JOptionPane.showMessageDialog(frame, "Congratulations you have won the game !");
                    print();
                }
            }
        } else if (e.getButton() == 3) {
            System.out.println("saÃ° tÃ½k");
            if (!b.isFlag()) {
                b.setIcon(new ImageIcon("flag.png"));
                b.setFlag(true);
            } else {
                b.setIcon(null);
                b.setFlag(false);
            }

        }

    }

    @Override
    public void mouseEntered(MouseEvent arg0) {
        // TODO Auto-generated method stub

    }

    @Override
    public void mouseExited(MouseEvent arg0) {
        // TODO Auto-generated method stub

    }

    @Override
    public void mousePressed(MouseEvent arg0) {
        // TODO Auto-generated method stub

    }

    @Override
    public void mouseReleased(MouseEvent arg0) {
        // TODO Auto-generated method stub

    }

}



