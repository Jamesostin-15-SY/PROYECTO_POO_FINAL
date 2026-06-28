package MODELO;
import java.time.LocalDateTime;
public class Citas {
    int id_cita;
    int fk_id_mascota;
    String dni_empleado;
    LocalDateTime fecha_hora;
    int fk_id_servicio;
    int fk_id_estado;
    String motivo_cita;
    
}
