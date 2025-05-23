package Vista;

import Controlador.VistaController;

import javax.swing.*;
import java.awt.event.*;
import java.util.List;

public class ModalEliminacionJugadresV2 extends JDialog {
    private JPanel pPrincipal;
    private JComboBox jugadores;
    private JButton eliminar;
    private JButton cancelar;

    public ModalEliminacionJugadresV2() {
        setContentPane(pPrincipal);
        setModal(true);
        getRootPane().setDefaultButton(eliminar);
        setSize(575,200);
        setLocationRelativeTo(null);
        setTitle("Eliminacion de Jugadres");

        ImageIcon imagen = new ImageIcon(getClass().getResource("/Vista/Fotos/FaviconEA.png"));
        setIconImage(imagen.getImage());

        List<String> listaJugadores = VistaController.listaJugadores();

        for (int i = 0; i < listaJugadores.size(); i++) {
            jugadores.insertItemAt(listaJugadores.get(i),i+1);
        }

        eliminar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                onOK();
            }
        });

        cancelar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                onCancel();
            }
        });

        // call onCancel() when cross is clicked
        setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                onCancel();
            }
        });

        // call onCancel() on ESCAPE
        pPrincipal.registerKeyboardAction(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                onCancel();
            }
        }, KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, 0), JComponent.WHEN_ANCESTOR_OF_FOCUSED_COMPONENT);
    }

    private void onOK() {
        String jugadorSeleccionado = (String) jugadores.getSelectedItem();

        if (jugadores.getSelectedIndex() != 0) {

            if (VistaController.ModalAdvertencia()) {
                boolean eliminado = VistaController.eliminarJugador(jugadorSeleccionado);

                if (eliminado) {
                    JOptionPane.showMessageDialog(pPrincipal, "Jugador eliminado con éxito.", "Éxito", JOptionPane.INFORMATION_MESSAGE);
                    dispose(); // Cierra la ventana
                } else {
                    JOptionPane.showMessageDialog(pPrincipal, "Error al eliminar el jugador.", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        } else {
            JOptionPane.showMessageDialog(pPrincipal, "Selecciona un jugador válido.", "Error", JOptionPane.ERROR_MESSAGE);
        }

    }

    private void onCancel() {
        // add your code here if necessary
        dispose();
    }

    public static void main(String[] args) {
        ModalEliminacionJugadresV2 dialog = new ModalEliminacionJugadresV2();
        dialog.pack();
        dialog.setVisible(true);
        System.exit(0);
    }
}
