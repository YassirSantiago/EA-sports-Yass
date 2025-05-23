package Controlador;

import Modelo.EquipoRolesDAO;
import Modelo.JugadorDAO;

import java.time.LocalDate;
import java.util.List;
/**
 * Controlador para gestionar las operaciones relacionadas con los jugadores.
 */
public class JugadorController {

    /**
     * Inscribe un jugador en la base de datos con los datos proporcionados.
     *
     * @param nombre Nombre del jugador.
     * @param apellido Apellido del jugador.
     * @param nacionalidad Nacionalidad del jugador.
     * @param fechaParseada Fecha de nacimiento del jugador.
     * @param nickname Apodo del jugador.
     * @param sueldoFloat Sueldo del jugador.
     * @param rol Rol del jugador en el equipo.
     * @param equipo Nombre del equipo al que pertenece el jugador.
     * @return true si el jugador fue inscrito correctamente, false en caso contrario.
     */
    public static boolean inscribirJugador(String nombre, String apellido, String nacionalidad, LocalDate fechaParseada, String nickname, float sueldoFloat, String rol, String equipo) {
        boolean insertado =  JugadorDAO.inscribirJugador(nombre,apellido,nacionalidad,fechaParseada,nickname,sueldoFloat, rol, equipo);

        if (insertado){
            EquipoRolesDAO.eliminarRolEquipo(rol,equipo);
        }

        return insertado;
    }
    /**
     * Busca un jugador en la base de datos por su nombre.
     *
     * @param nombre Nombre del jugador a buscar.
     * @return true si el jugador existe, false en caso contrario.
     */
    public static boolean buscarJugador(String nombre){
        return JugadorDAO.buscarJugador(nombre);
    }
    /**
     * Obtiene una lista de nombres de todos los jugadores registrados.
     *
     * @return Una lista de cadenas con los nombres de los jugadores.
     */
    public static List<String> listaJugadores(){
        return JugadorDAO.listaJugadores();
    }
    /**
     * Elimina un jugador de la base de datos y actualiza los roles disponibles.
     *
     * @param jugadorSeleccionado Nombre del jugador a eliminar.
     * @return true si el jugador fue eliminado correctamente, false en caso contrario.
     */
    public static boolean eliminarJugador(String jugadorSeleccionado) {
        boolean eliminado = false;

        if (EquipoRolesController.insertarRolJugadorEliminado(jugadorSeleccionado)){
            eliminado = JugadorDAO.eliminarJugador(jugadorSeleccionado);
        }

        return eliminado;
    }

    /**
     * Busca un jugador en la base de datos por su nickname.
     *
     * @param nickname Nickname del jugador a buscar.
     * @return true si el nickname existe, false en caso contrario.
     */
    public static boolean buscarNickname(String nickname) {
        return JugadorDAO.buscarNickname(nickname);
    }



    /**
     * Obtiene una lista de jugadores con sus detalles almacenados en la base de datos.
     *
     * @return Una lista de arreglos de cadenas, donde cada arreglo contiene los detalles de un jugador.
     */
    public static List<String[]> obtenerJugadores() {
        return JugadorDAO.obtenerJugadores();

    }
    //Comprobacion para cerrar Competicion(Jugadores)

    /**
     * Verifica si los equipos tienen una cantidad válida de jugadores para cerrar la competición.
     *
     * @return true si todos los equipos tienen una cantidad válida de jugadores, false en caso contrario.
     */
    public static boolean equiposConCantidadValidaDeJugadores() {
        return JugadorDAO.equiposConCantidadValidaDeJugadores();
    }

    /**
     * Obtiene la clave primaria (PK) de un jugador específico desde la base de datos.
     *
     * @param jugador Nombre del jugador del cual se desea obtener la clave primaria.
     * @return La clave primaria del jugador si se encuentra, 0 en caso contrario.
     */
    public static int obtenerPKjugadorNick(String jugador){
        return JugadorDAO.obtenerPKjugadorNick(jugador);
    }

    /**
     * Obtiene el rol de un jugador específico desde la base de datos.
     *
     * @param jugador Nombre del jugador del cual se desea obtener el rol.
     * @return El rol del jugador si se encuentra, null en caso contrario.
     */
    public static String obtenerRolJugador(String jugador){
        return JugadorDAO.obtenerRolJugador(jugador);
    }

    /**
     * Obtiene el identificador del equipo al que pertenece un jugador.
     *
     * @param idJugador Identificador del jugador.
     * @return El identificador del equipo si se encuentra, 0 en caso contrario.
     */
    public static int obtenerEquipoJugador(int idJugador){
        return JugadorDAO.obtenerEquipoJugador(idJugador);
    }

    public static List<String> listaNicknames(){
        return JugadorDAO.listaNicknames();
    }

    public static boolean modificarJugador(String nombre, String apellido, String nacionalidad, LocalDate fecha, String nickname, Float sueldoFloat, String rol, String equipo,Boolean duplicado,String nickname_viejo,Boolean cambiarRoles) {
        if (cambiarRoles){
            EquipoRolesDAO.eliminarRolEquipo(rol,equipo);
            EquipoRolesDAO.insertarRolJugadorEliminado(nickname_viejo);
        }
        return JugadorDAO.modificarJugador(nombre,apellido,nacionalidad,fecha,nickname,sueldoFloat,rol,equipo,duplicado,nickname_viejo);
    }
    public static int obtenerCantidadJugadoreEquipo(String equipo){
        return JugadorDAO.obtenerCantidadJugadoreEquipo(equipo);
    }
    public static String obtenerRolJugadorNick(String nickname){
        return JugadorDAO.obtenerRolJugadorNick(nickname);
    }
}
