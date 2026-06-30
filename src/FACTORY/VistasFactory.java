package FACTORY;
import VISTA.*;
import CONTROLADOR.*;
import javax.swing.JDesktopPane;
import javax.swing.JInternalFrame;
public class VistasFactory {
    private static void PresentarVista (JInternalFrame iframe, JDesktopPane contenedor) {
        contenedor.removeAll();
        contenedor.add(iframe);
        contenedor.repaint();
        int x = (contenedor.getWidth() - iframe.getWidth())/2;
        int y = (contenedor.getHeight() - iframe.getHeight())/2;
        iframe.setLocation(x, y);
        iframe.setVisible(true);
    }
    public static void CrearVista (String nomvista, String titulo, JDesktopPane contenedor) {
        if (nomvista.equals("RegistrarClie")){
            frmRegistrarClientes FRC = new frmRegistrarClientes();
            //ControlEstudiante ce = new ControlEstudiante(fes);
            FRC.setTitle(titulo);
            PresentarVista(FRC, contenedor);
        }
        if (nomvista.equals("CancelarCita")){}
    }   
}
