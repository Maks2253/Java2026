import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class DistanceCalculatorGUI extends JFrame {

    // Поля введення
    private JTextField lat1Field;
    private JTextField lon1Field;
    private JTextField lat2Field;
    private JTextField lon2Field;
    private JTextField resultField;

    public DistanceCalculatorGUI() {
        setTitle("Distance Calculator (Haversine)");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        // Панель
        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(6, 2, 5, 5));

        // Компоненти
        panel.add(new JLabel("Latitude 1 (degrees):"));
        lat1Field = new JTextField();
        panel.add(lat1Field);

        panel.add(new JLabel("Longitude 1 (degrees):"));
        lon1Field = new JTextField();
        panel.add(lon1Field);

        panel.add(new JLabel("Latitude 2 (degrees):"));
        lat2Field = new JTextField();
        panel.add(lat2Field);

        panel.add(new JLabel("Longitude 2 (degrees):"));
        lon2Field = new JTextField();
        panel.add(lon2Field);

        panel.add(new JLabel("Distance D (meters):"));
        resultField = new JTextField();
        resultField.setEditable(false);
        panel.add(resultField);

        // Кнопки
        JButton solveButton = new JButton("Solve");
        JButton clearButton = new JButton("Clear");

        panel.add(solveButton);
        panel.add(clearButton);

        // Обробник кнопки Solve
        solveButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                calculateDistance();
            }
        });

        // Обробник кнопки Clear
        clearButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                lat1Field.setText("");
                lon1Field.setText("");
                lat2Field.setText("");
                lon2Field.setText("");
                resultField.setText("");
            }
        });

        add(panel);
    }

    // Метод обчислення відстані за формулою гаверсинуса
    private void calculateDistance() {
        try {
            double lat1 = Double.parseDouble(lat1Field.getText());
            double lon1 = Double.parseDouble(lon1Field.getText());
            double lat2 = Double.parseDouble(lat2Field.getText());
            double lon2 = Double.parseDouble(lon2Field.getText());

            // Радіус Землі (м)
            double R = 6371e3;

            // Переведення градусів у радіани
            double phi1 = Math.toRadians(lat1);
            double phi2 = Math.toRadians(lat2);
            double deltaPhi = Math.toRadians(lat2 - lat1);
            double deltaLambda = Math.toRadians(lon2 - lon1);

            // Формула гаверсинуса
            double a = Math.sin(deltaPhi / 2) * Math.sin(deltaPhi / 2)
                    + Math.cos(phi1) * Math.cos(phi2)
                    * Math.sin(deltaLambda / 2) * Math.sin(deltaLambda / 2);

            double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));
            double D = R * c;

            resultField.setText(String.format("%.2f", D));

        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this,
                    "Введіть коректні числові значення!",
                    "Помилка",
                    JOptionPane.ERROR_MESSAGE);
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new DistanceCalculatorGUI().setVisible(true);
        });
    }
}