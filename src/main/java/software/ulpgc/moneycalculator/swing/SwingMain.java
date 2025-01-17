package software.ulpgc.moneycalculator.swing;

import software.ulpgc.moneycalculator.*;
import software.ulpgc.moneycalculator.control.Command;
import software.ulpgc.moneycalculator.control.ExchangeMoneyCommand;
import software.ulpgc.moneycalculator.fixerws.*;
import software.ulpgc.moneycalculator.model.Currency;

import javax.swing.*;
import java.awt.*;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SwingMain extends JFrame {
    private final Map<String, Command> commands = new HashMap<>();
    private MoneyDisplay moneyDisplay;
    private MoneyDialog moneyDialog;
    private CurrencyDialog currencyDialog;

    public static void main(String[] args) {
        SwingMain main = new SwingMain();
        List<Currency> currencies = new FixerCurrencyLoader().load();
        Command command = new ExchangeMoneyCommand(
                main.moneyDialog().define(currencies),
                main.currencyDialog().define(currencies),
                new FixerExchangeRateLoader(),
                main.moneyDisplay());
        main.add("exchange money", command);
        main.setVisible(true);
    }

    public SwingMain() {
        // Configuración de la ventana principal
        this.setTitle("Money Calculator");
        this.setSize(800, 600);
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setLayout(new FlowLayout());

        // Configurar fondo oscuro y colores
        this.getContentPane().setBackground(new Color(34, 34, 34)); // Fondo oscuro
        this.setForeground(Color.WHITE); // Texto blanco

        // Añadir componentes
        this.add(createMoneyDialog());
        this.add(createCurrencyDialog());
        this.add(createMoneyDisplay());
        this.add(toolbar());

        // Estilizar la ventana
        UIManager.put("Button.select", new Color(75, 110, 175)); // Color al seleccionar
        UIManager.put("Button.focus", new Color(75, 110, 175));  // Color al hacer foco
    }

    private Component toolbar() {
        JButton button = new JButton("Calculate");
        button.setPreferredSize(new Dimension(150, 40));
        button.setBackground(new Color(75, 110, 175)); // Azul claro
        button.setForeground(Color.WHITE); // Texto blanco
        button.setFont(new Font("Arial", Font.BOLD, 16)); // Fuente
        button.setFocusPainted(false); // Sin borde al hacer clic
        button.addActionListener(e -> commands.get("exchange money").execute());
        button.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR)); // Cursor en mano
        return button;
    }

    private Component createMoneyDisplay() {
        SwingMoneyDisplay display = new SwingMoneyDisplay();
        this.moneyDisplay = display;
        return display;
    }

    private Component createCurrencyDialog() {
        SwingCurrencyDialog dialog = new SwingCurrencyDialog();
        this.currencyDialog = dialog;
        return dialog;
    }

    private Component createMoneyDialog() {
        SwingMoneyDialog dialog = new SwingMoneyDialog();
        this.moneyDialog = dialog;
        return dialog;
    }

    private void add(String name, Command command) {
        commands.put(name, command);
    }

    private MoneyDisplay moneyDisplay() {
        return moneyDisplay;
    }

    private CurrencyDialog currencyDialog() {
        return currencyDialog;
    }

    private MoneyDialog moneyDialog() {
        return moneyDialog;
    }
}
