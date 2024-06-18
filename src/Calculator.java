import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Calculator extends JFrame implements ActionListener {
    // 계산기 구성요소
    private JTextField display;
    private JPanel panel;
    private String[] buttons = {
            "7", "8", "9", "+", 
            "4", "5", "6", "-", 
            "1", "2", "3", "*", 
            "0", "AC", "=", "/"
    };
    private JButton[] button = new JButton[buttons.length];
    
    // 계산을 위한 변수
    private double firstNum = 0, secondNum = 0, result = 0;
    private char operator;

    public Calculator() {
        // 프레임 속성
        setTitle("Calculator");
        setSize(400, 500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());
        
        // Menu bar
        JMenuBar menuBar = new JMenuBar();
        
        JMenu fileMenu = new JMenu("File");
        JMenu editMenu = new JMenu("Edit");
        JMenu helpMenu = new JMenu("Help");
        
        // 메뉴항목
        JMenuItem newMenuItem = new JMenuItem("New");
        JMenuItem openMenuItem = new JMenuItem("Open");
        fileMenu.add(newMenuItem);
        fileMenu.add(openMenuItem);
        
        menuBar.add(fileMenu);
        menuBar.add(editMenu);
        menuBar.add(helpMenu);
        
        setJMenuBar(menuBar);

        // 화면구성
        display = new JTextField();
        display.setEditable(false);
        display.setFont(new Font("Arial", Font.PLAIN, 24));  // Set font size to make the display larger
        display.setHorizontalAlignment(SwingConstants.RIGHT); // Align text to the right
        add(display, BorderLayout.NORTH);

        // 버튼 패널
        panel = new JPanel();
        panel.setLayout(new GridLayout(4, 4));
        for (int i = 0; i < buttons.length; i++) {
            button[i] = new JButton(buttons[i]);
            button[i].addActionListener(this);
            button[i].setFont(new Font("Arial", Font.PLAIN, 20));
            panel.add(button[i]);
        }
        add(panel, BorderLayout.CENTER);
    }

    public void actionPerformed(ActionEvent e) {
        String command = e.getActionCommand();
        
        if ((command.charAt(0) >= '0' && command.charAt(0) <= '9')) {
            display.setText(display.getText() + command);
        } else if (command.equals("AC")) {
            display.setText("");
            firstNum = 0;
            secondNum = 0;
            result = 0;
            operator = '\0';
        } else if (command.charAt(0) == '=') {
            secondNum = Double.parseDouble(display.getText());
            switch (operator) {
                case '+':
                    result = firstNum + secondNum;
                    break;
                case '-':
                    result = firstNum - secondNum;
                    break;
                case '*':
                    result = firstNum * secondNum;
                    break;
                case '/':
                    result = firstNum / secondNum;
                    break;
            }
            display.setText(String.valueOf(result));
            firstNum = result;
        } else {
            if (!display.getText().isEmpty()) {
                firstNum = Double.parseDouble(display.getText());
                display.setText("");
                operator = command.charAt(0);
            }
        }
    }

    public static void main(String[] args) {
        Calculator calc = new Calculator();
        calc.setVisible(true);
    }
}