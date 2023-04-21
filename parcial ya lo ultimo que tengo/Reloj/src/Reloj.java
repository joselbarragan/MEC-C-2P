import javax.swing.*;
import java.awt.event.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

public class Reloj extends JFrame implements ActionListener {
    private JLabel labelHora;
    private Timer timer;
    private JButton botonIniciar, botonDetener;
    private JSlider sliderVelocidad;
    private int velocidad = 1000;

    public Reloj() {
        super("Reloj");
        setSize(300, 150);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        // Crear los componentes
        labelHora = new JLabel("", JLabel.CENTER);
        botonIniciar = new JButton("Iniciar");
        botonDetener = new JButton("Detener");
        sliderVelocidad = new JSlider(JSlider.HORIZONTAL, 0, 2000, 1000);

        // Agregar los componentes a la ventana
        JPanel panel = new JPanel();
        panel.add(labelHora);
        panel.add(botonIniciar);
        panel.add(botonDetener);
        panel.add(sliderVelocidad);
        add(panel);

        // Agregar los listeners a los botones y al slider
        botonIniciar.addActionListener(this);
        botonDetener.addActionListener(this);
        sliderVelocidad.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                velocidad = sliderVelocidad.getValue();
                if (timer != null) {
                    timer.setDelay(velocidad);
                }
            }
        });

        setVisible(true);
    }

    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == botonIniciar) {
            // Iniciar el Timer si no está corriendo
            if (timer == null) {
                timer = new Timer(velocidad, new ActionListener() {
                    public void actionPerformed(ActionEvent evt) {
                        labelHora.setText(obtenerHoraActual());
                    }
                });
                timer.start();
            }
        } else if (e.getSource() == botonDetener) {
            // Detener el Timer si está corriendo
            if (timer != null) {
                timer.stop();
                timer = null;
            }
        }
    }

    private String obtenerHoraActual() {
        // Obtener la hora actual y formatearla como cadena
        int horas = new java.util.Date().getHours();
        int minutos = new java.util.Date().getMinutes();
        int segundos = new java.util.Date().getSeconds();
        return String.format("%02d:%02d:%02d", horas, minutos, segundos);
    }

    public static void main(String[] args) {
        new Reloj();
    }
}