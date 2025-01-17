package software.ulpgc.moneycalculator.swing;

import software.ulpgc.moneycalculator.model.Currency;
import software.ulpgc.moneycalculator.CurrencyDialog;

import javax.swing.*;
import java.awt.*;
import java.util.List;

public class SwingCurrencyDialog extends JPanel implements CurrencyDialog {

    private JComboBox<Currency> currencySelector;

    public SwingCurrencyDialog() {
        this.setBackground(new Color(34, 34, 34)); // Fondo oscuro
        this.setForeground(Color.WHITE); // Texto blanco
        this.setLayout(new FlowLayout(FlowLayout.CENTER, 10, 10)); // Layout centrado y espaciado
    }

    @Override
    public CurrencyDialog define(List<Currency> currencies) {
        add(createCurrencySelector(currencies));
        return this;
    }

    private Component createCurrencySelector(List<Currency> currencies) {
        JComboBox<Currency> selector = new JComboBox<>();
        selector.setPreferredSize(new Dimension(200, 30));
        selector.setMaximumSize(new Dimension(200, 30)); // Limitar tamaño
        selector.setBackground(new Color(50, 50, 50)); // Fondo oscuro
        selector.setForeground(Color.WHITE); // Texto blanco
        selector.setFont(new Font("Arial", Font.PLAIN, 16)); // Fuente
        selector.setFocusable(true); // Asegurar que se puede seleccionar
        for (Currency currency : currencies) selector.addItem(currency);
        this.currencySelector = selector;
        return selector;
    }

    @Override
    public Currency get() {
        return currencySelector.getItemAt(currencySelector.getSelectedIndex());
    }
}
