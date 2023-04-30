import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class CurrencyConverterGUI extends JFrame {
    private JLabel inputLabel, currencyTypeLabel, resultLabel;
    private JTextField inputField, resultField;
    private JComboBox<String> currencyComboBox;
    private JButton convertButton, clearButton;
    private JPanel panel;

    private String[] currencyList = {"United States (US)", "Canadian(CAN)", "European(Euro)"};
    private double[] conversionRates = {129.02,97.50, 164.33};

    public CurrencyConverterGUI() {
        // Set up the frame
        setTitle("Currency Converter");
        setSize(500, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Initialize components
        inputLabel = new JLabel("Input $:");
        currencyTypeLabel = new JLabel("Currency Type:");
        resultLabel = new JLabel("JMD Amount $:");

        inputField = new JTextField(10);
        resultField = new JTextField(10);
        resultField.setEditable(false);

        currencyComboBox = new JComboBox<>(currencyList);

        convertButton = new JButton("Convert");
        clearButton = new JButton("Clear");
        convertButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                convert();
            }
        });

        // Set up the panel
        panel = new JPanel(new GridBagLayout());
        clearButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                clearFields();
            }
        });

        GridBagConstraints c = new GridBagConstraints();
        c.fill = GridBagConstraints.HORIZONTAL;
        c.insets = new Insets(10, 10, 10, 10);

        c.gridx = 0;
        c.gridy = 0;
        panel.add(inputLabel, c);

        c.gridx = 1;
        c.gridy = 0;
        panel.add(inputField, c);

        c.gridx = 0;
        c.gridy = 1;
        panel.add(currencyTypeLabel, c);

        c.gridx = 1;
        c.gridy = 1;
        panel.add(currencyComboBox, c);

        c.gridx = 0;
        c.gridy = 3;
        panel.add(resultLabel, c);

        c.gridx = 1;
        c.gridy = 3;
        panel.add(resultField, c);

        c.gridwidth = 1;
        c.gridx = 0;
        c.gridy = 4;
        panel.add(convertButton, c);

        c.gridwidth = 0;
        c.gridx = 1;
        c.gridy = 4;
        panel.add(clearButton, c);

        // Add panel to the frame
        add(panel);
    }
    private void clearFields() {
        inputField.setText("");
        resultField.setText("");
        currencyComboBox.setSelectedIndex(0);
    }

    private void convert() {
        try {
            double amount = Double.parseDouble(inputField.getText());
            int fromIndex = currencyComboBox.getSelectedIndex();
            double result = amount * conversionRates[fromIndex];
            resultField.setText(String.format("%.2f", result));
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(panel, "Invalid input.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    public static void main(String[] args) {
        CurrencyConverterGUI converter = new CurrencyConverterGUI();
        converter.setVisible(true);
    }
}