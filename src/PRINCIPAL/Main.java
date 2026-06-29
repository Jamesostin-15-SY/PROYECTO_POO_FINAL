package PRINCIPAL;
import VISTA.*;
import CONTROLADOR.*;
public class Main {
    public static void main(String[] args) {
        frmLogin fl = new frmLogin();
        
        // Inicializamos el controlador pasándole la vista
        ControlLogin control = new ControlLogin(fl);
        
        fl.setTitle("VETCARE S.A.C");
        fl.setLocationRelativeTo(null); // Centra el JFrame en la pantalla
        fl.setVisible(true);
    }
}
