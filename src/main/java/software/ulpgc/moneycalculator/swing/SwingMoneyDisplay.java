package software.ulpgc.moneycalculator.swing;

import software.ulpgc.moneycalculator.model.Money;
import software.ulpgc.moneycalculator.MoneyDisplay;

import javax.swing.*;
import java.awt.*;

public class SwingMoneyDisplay extends JLabel implements MoneyDisplay {
    public SwingMoneyDisplay() {
        this.setBackground(new Color(34, 34, 34)); // Fondo oscuro
        this.setForeground(Color.WHITE); // Texto blanco
        this.setFont(new Font("Arial", Font.BOLD, 24)); // Fuente grande y en negrita
        this.setHorizontalAlignment(SwingConstants.CENTER); // Centrado
    }

    @Override
    public void show(Money money) {
        this.setText(String.format("%.2f %s", money.amount(), money.currency().code()));
    }
}
