import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Main extends JFrame implements ActionListener{

    private String operator = "";
    private double firstNumber = 0;
    private boolean startNewNumber = true;
    JTextField display = new JTextField("0");

    public Main() {

        // Configuración de la ventana principal
        setTitle("Calculadora");
        setResizable(false);
        setSize(300, 400);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Campo de texto
        display.setFont(new Font("Arial", Font.BOLD,24));
        display.setHorizontalAlignment(JTextField.RIGHT);
        display.setEditable(false);
        display.setCaretColor(new Color(0,0,0,0));
        add(display, BorderLayout.NORTH);

        // Crear los botones
        JPanel jPanel = new JPanel();
        jPanel.setLayout(new GridLayout(4, 4, 5, 5));

        String[] buttons = {
                "7", "8", "9", "/",
                "4", "5", "6", "*",
                "1", "2", "3", "-",
                "0", "C", "=", "+"
        };

        for (String text : buttons) {
            JButton button = new JButton(text);
            button.setFont(new Font("arial", Font.BOLD, 18));
            button.addActionListener(this);
            jPanel.add(button);
        }

        add(jPanel, BorderLayout.CENTER);
        setVisible(true);
    }

    public static void main(String[] args) {
        new Main();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String command = e.getActionCommand(); // Devuelve el texto asociado al botón presionado (Ejem: "1", "+", "=")

        if ("0123456789".contains(command)){ // Verifica si el número obtenido es del 0-9
            if (startNewNumber){
                display.setText(command); // Añade a display el texto del botón presionado
                startNewNumber = false;
            } else { // Si startNewNumber es false solo concatena el numero anterior con el nuevo
                display.setText(display.getText() + command);
            }
        } else if ("+-*/".contains(command)) {
            firstNumber = Double.parseDouble(display.getText());
            operator = command;
            startNewNumber = true;
        } else if ("=".contains(command)) {
            double secondNumber = Double.parseDouble(display.getText()); // Convierte a dato tipo double
            double result = calculate(firstNumber, secondNumber, operator);
            display.setText(String.valueOf(result));
            startNewNumber = true;
        } else if ("C".contains(command)){
            display.setText("0");
            firstNumber = 0;
            operator = "";
            startNewNumber = true;
        }
    }

    private double calculate(double nmr1, double nmr2, String operator) {
        return switch (operator){
            case "+" -> nmr1 + nmr2;
            case "-" -> nmr1 - nmr2;
            case "*" -> nmr1 * nmr2;
            case "/" -> nmr1 != 0? nmr1 / nmr2 : 0; // No sé que mdr hace, pero lo encóntre en internet y arreglo mi problema
            default -> 0;
        };
    }
}