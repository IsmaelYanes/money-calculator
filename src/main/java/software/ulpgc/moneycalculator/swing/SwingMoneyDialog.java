package software.ulpgc.moneycalculator.swing;

import software.ulpgc.moneycalculator.model.Currency;
import software.ulpgc.moneycalculator.CurrencyDialog;
import software.ulpgc.moneycalculator.model.Money;
import software.ulpgc.moneycalculator.MoneyDialog;

import javax.swing.*;
import java.awt.*;
import java.util.List;

public class SwingMoneyDialog extends JPanel implements MoneyDialog {
    private JTextField amountField;
    private CurrencyDialog currencyDialog;

    public SwingMoneyDialog() {
        this.setLayout(new FlowLayout());
        this.setBackground(new Color(34, 34, 34)); // Fondo oscuro
        this.setForeground(Color.WHITE); // Texto blanco
    }

    @Override
    public MoneyDialog define(List<Currency> currencies) {
        add(createAmountField());
        add(createCurrencyDialog(currencies));
        return this;
    }

    private Component createCurrencyDialog(List<Currency> currencies) {
        SwingCurrencyDialog dialog = new SwingCurrencyDialog();
        dialog.define(currencies);
        this.currencyDialog = dialog;
        return dialog;
    }

    private Component createAmountField() {
        JTextField textField = new JTextField();
        textField.setPreferredSize(new Dimension(200, 30));
        textField.setBackground(new Color(50, 50, 50)); // Fondo oscuro
        textField.setForeground(Color.WHITE); // Texto blanco
        textField.setFont(new Font("Arial", Font.PLAIN, 16)); // Fuente
        textField.setCaretColor(Color.WHITE); // Color del cursor
        this.amountField = textField;
        return textField;
    }

    @Override
    public Money get() {
        return new Money(toLong(amountField.getText()), currencyDialog.get());
    }

    private long toLong(String text) {
        try {
            return Long.parseLong(text);
        } catch (NumberFormatException e) {
            return 0;
        }
    }
}
