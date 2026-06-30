package CONTROLADOR;
import javax.swing.JFrame;
import DAO.ProcesosPermisos;
import FACTORY.VistasFactory;
import MODELO.UsuariosCredenciales;
import VISTA.frmMenusVeterinaria;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
public class ControladorMenu implements ActionListener {
    
    
    private final frmMenusVeterinaria vistaMenu;
    private final UsuariosCredenciales usuarioLogueado;

    // Recibe la ventana del menú y el objeto del usuario que inició sesión
    public ControladorMenu(frmMenusVeterinaria vistaMenu, UsuariosCredenciales usuarioLogueado) {
        this.vistaMenu = vistaMenu;
        this.usuarioLogueado = usuarioLogueado;
        vistaMenu.itemRegistrarCli.addActionListener(this);
    }

    // Muestra el panel principal configurando títulos y permisos de rol
    public void iniciar() {
        vistaMenu.setTitle("Sistema de Gestión Veterinaria - Panel Principal");
        vistaMenu.setLocationRelativeTo(null);
        vistaMenu.setExtendedState(JFrame.MAXIMIZED_BOTH);
        
        // Llama a tu clase ProcesosPermisos para habilitar/deshabilitar los componentes correspondientes
        ProcesosPermisos.AplicarRestricciones(vistaMenu, usuarioLogueado.getFk_id_role());
        
        vistaMenu.setVisible(true);
        
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == vistaMenu.itemRegistrarCli){
            VistasFactory.CrearVista("RegistrarClie", "RegistrarClientes", vistaMenu.spnContenedor);
        }
        if (e.getSource() == vistaMenu.itemCancelar){
            
        }
    }
}