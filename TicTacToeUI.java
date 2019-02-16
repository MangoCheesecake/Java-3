package lab.pkg3;
 
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import static javax.swing.JFrame.EXIT_ON_CLOSE;
import javax.swing.JTextField;

public class TicTacToeUI extends JPanel
    implements ActionListener
{
    private TicTacToeBoard board;
    
    private JButton[][] buttons = new JButton[3][3];
    private JTextField gameText;
    private JButton restart = new JButton("Restart");
            
    private JPanel panel = new JPanel();
    private JFrame frame = new JFrame();
    
    public TicTacToeUI(TicTacToeBoard board)
    {
        this.board = board;
        
        panel.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        
        setUpButtons(gbc);
        setUpGameText(gbc);
        setUpRestart(gbc);

        JFrame frame = new JFrame("Tic Tac Toe");
        frame.add(panel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
        frame.setBounds(100, 100, 400, 400);    
    }
    
    private void setUpButtons(GridBagConstraints gbc)
    {
        //Sets up tic tac toe buttons
        gbc.gridwidth = gbc.gridheight = 1;
        gbc.fill = GridBagConstraints.BOTH;
        gbc.weightx = 20;
        gbc.weighty = 20;
        
        for (int row = 0; row < 3; row++) 
        {
            for (int col = 0; col < 3; col++) 
            {
                buttons[row][col] = new JButton();
                gbc.gridx = col;
                gbc.gridy = row;
                buttons[row][col].setPreferredSize(new Dimension(50, 50));

                panel.add(buttons[row][col], gbc);
                buttons[row][col].addActionListener(this);
            }
        }
    }
    
    private void setUpGameText(GridBagConstraints gbc)
    {
        //Sets up game state text
        gbc.weightx = 1;
        gbc.weighty = 5;
        gbc.gridx = 0;
        gbc.gridy = 4;
        gbc.gridwidth = 3;
        
        gameText = new JTextField();
        gameText.setEditable(false);
        gameText.setBackground(Color.WHITE);
        
        panel.add(gameText,gbc);
    }
             
    private void setUpRestart(GridBagConstraints gbc)
    {
        //Sets up restart button
        gbc.weightx = 1;
        gbc.weighty = 5;
        gbc.gridx = 1;
        gbc.gridy = 5;
        gbc.gridwidth = 1;
        
        panel.add(restart,gbc);
        restart.addActionListener(this);
    }
            
    public void disableButtons()
    {
        for (int row = 0; row < 3; row++) 
        {
            for (int col = 0; col < 3; col++) 
            {
                buttons[row][col].setEnabled(false);
            }
        }
    }
    
    public void enableButtons()
    {
        for (int row = 0; row < 3; row++) 
        {
            for (int col = 0; col < 3; col++) 
            {
                buttons[row][col].setEnabled(true);
            }
        }
    }

    public void clearButtons()
    {
        for (int row = 0; row < 3; row++) 
        {
            for (int col = 0; col < 3; col++) 
            {
                buttons[row][col].setText("");
            }
        }
    }
    
    public void restart()
    {
        gameText.setText("");
        clearButtons();
        enableButtons();
    }
    
    public void handleCheckWin(int state)
    {

        if(state == 1)
        {
            gameText.setText("Player 1 has won!");
        }
        if(state == 2)
        {
            gameText.setText("Player 2 has won!");
        }
        if(state == 3)
        {
            gameText.setText("Draw");
        }
        
        disableButtons();
    }
    
    @Override
    public void actionPerformed(ActionEvent e) 
    {
        
        for(int row = 0; row <3; row++)
        {
            for(int col =0; col <3; col++)
            {
                if(e.getSource() == buttons[row][col])                
                {
                    if(board.getTurn() == 1)
                    {
                        buttons[row][col].setText("X");
                    }
                    else
                    {
                        buttons[row][col].setText("O");
                    }

                    buttons[row][col].setEnabled(false);
                    board.squarePressed(row,col,this);  
                }
            }
        }

        if(e.getSource() == restart)                
        {
              restart();
              board.restartGame();
        } 
      
    }
}
