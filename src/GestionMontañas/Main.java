
package GestionMontañas;

import java.sql.*;
import java.util.Scanner;


/**
 *
 * @author sergiogutierrez
 */
public class Main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        
        
        //Conexion con MySQl
        
        String url = "jdbc:mysql://localhost:3306/Geografia";
        String usuario = "root";
        String contraseña = "";
        
        try (Connection conexion = DriverManager.getConnection(url, usuario, contraseña);
                Scanner sc = new Scanner(System.in)){
            System.out.println("Conexion establecida con MySQL");
            
            int opcion;
            do{
                mostrarMenu();
                opcion = sc.nextInt();
                sc.nextLine();
                
                switch (opcion){
                    case 1: crearTabla(conexion);
                            break;
                    case 2: insertarMontaña(conexion, sc);
                            break;
                    case 3: mostrarTodas (conexion);
                            break;
                    case 4: consultarMontaña (conexion, sc);
                            break;
                    case 5: editarMontaña (conexion, sc);
                            break;
                    case 6: eliminarMontaña (conexion, sc);
                            break;
                    case 7: eliminarTabla (conexion, sc);
                            break;
                    case 0: 
                        System.out.println("Gracias por utilizarme, saliendo del programa.....");
                            break;
                    default: 
                        System.out.println("Opcion no valida");
                            break;
                }
            }while (opcion != 0);
            
            
            
        }catch (SQLException e){
           System.out.println("Error al conectar:" + e.getMessage());
        }
        
        
        
        
    }
    
    
    
    private static void mostrarMenu(){
        
        System.out.println("***MENÚ DE MONTAÑAS***");
        System.out.println("1º Crear tabla Montañas");
        System.out.println("2º Añadir una Montaña");
        System.out.println("3º Consultar todas las Montañas");
        System.out.println("4º Consultar una Montaña por nombre");
        System.out.println("5º Editar una Montaña");
        System.out.println("6º Borrar una Montaña");
        System.out.println("7º Borrar tabla Montañas");
        System.out.println("0 - Salir");
        System.out.print("Indica una opción: ");
    }
    
    
    
    //Metodo para crear la tabla
    private static void crearTabla (Connection conexion) throws SQLException {
        String sql = """
             CREATE TABLE IF NOT EXISTS Montañas (
                     nombre VARCHAR (50) PRIMARY KEY,
                     altura INT,
                     primera_ascension INT,
                     region VARCHAR(50),
                     pais VARCHAR(50)
                     
                    )
                """;
        try(Statement stmt = conexion.createStatement()){
            stmt.executeUpdate(sql);
            System.out.println("Tabla creada correctamente");
        }
                     
     
        
    }
    
    // Metodo para insertar los datos de una montaña
    private static void insertarMontaña(Connection conexion, Scanner sc) throws SQLException{
        
        System.out.println("Nombre: ");
        String nombre = sc.nextLine();
        System.out.println("Altura: ");
        int altura = sc.nextInt();
        System.out.println("Año de la primera ascension: ");
        int año = sc.nextInt();
        sc.nextLine();
        System.out.println("Region: ");
        String region = sc.nextLine();
        System.out.println("Pais");
        String pais = sc.nextLine();
        
        String sql = "INSERT INTO Montañas VALUES (?, ?, ?, ?, ?)";
        try (PreparedStatement ps = conexion.prepareStatement(sql)){
            ps.setString(1, nombre);
            ps.setInt(2, altura);
            ps.setInt(3, año);
            ps.setString(4, region);
            ps.setString(5, pais);
            ps.executeUpdate();
            
            System.out.println("Montaña añadida correctamente");
        }
        
        
    }
    
    
    // Metodo para consultar todas las montañas
    private static void mostrarTodas(Connection conexion) throws SQLException {
        
        String sql = "SELECT * FROM Montañas";
        try (Statement stmt = conexion.createStatement();
                ResultSet rs = stmt.executeQuery (sql)){
            
            System.out.println("--- LISTADO DE MONTAÑAS ---");
            while (rs.next()){
               System.out.printf("%s | %d m | %d | %s | %s%n",
                rs.getString("nombre"),
                rs.getInt("altura"),
                rs.getInt("primera_ascension"),
                rs.getString("region"),
                rs.getString("pais"));
            }
        }
    }
    
    
    // Metodo para consultar una montaña por nomnbre
   private static void consultarMontaña(Connection conexion, Scanner sc) throws SQLException{
       
       System.out.println("introduce el nombre de la montaña: ");
       String nombre = sc.nextLine();
       
       String sql = "SELECT * FROM Montañas WHERE nombre = ?";
       try (PreparedStatement ps = conexion.prepareStatement (sql)){
           ps.setString(1, nombre);
           ResultSet rs = ps.executeQuery();
           
           if(rs.next()){
               System.out.printf("Nombre: %s | Altura : %d | Primera ascension: %d | Region: %s | Pais: %s%n",
                       rs.getString("nombre"),
                       rs.getInt("altura"),
                       rs.getInt("primera_ascension"),
                       rs.getString("region"),
                       rs.getString("pais"));
           }else {
               System.out.println("No se encontró la montaña indicada");
           }
       }
   }
   
   
   //Metodo para editar una montaña
   
   private static void editarMontaña(Connection conexion, Scanner sc) throws SQLException {
    
    System.out.print("Introduce el nombre de la montaña a editar: ");
    String nombre = sc.nextLine();
    
    System.out.println("indica nueva altura");
    int altura = sc.nextInt();
    System.out.println("indica nuevo año de ascension");
    int año = sc.nextInt();
    sc.nextLine();
    System.out.println("Indica nueva region");
    String region = sc.nextLine();
    System.out.println("Nuevo pais");
    String pais = sc.nextLine();
    
    String sql = "UPDATE Montañas SET altura=?, primera_ascension=?, region=?, pais=? WHERE nombre=?";
    try (PreparedStatement ps = conexion.prepareStatement (sql)){
        ps.setInt(1, altura);
        ps.setInt(2, año);
        ps.setString(3, region);
        ps.setString(4, pais);
        ps.setString(5, nombre);
        
        int filas = ps.executeUpdate();
        if (filas > 0)
            System.out.println("Montañan actualizada");
        else
            System.out.println("No se encontró la montaña");
    }
       
   }
   //metodo para eliminar una montaña indicando el nombre
   private static void eliminarMontaña (Connection conexion, Scanner sc) throws SQLException{
       
       System.out.println("introduce el nombre de la montala que quieres eliminar");
       String nombre = sc.nextLine();
       
       String sql = "DELETE FROM Montañas WHERE nombre = ?";
       try(PreparedStatement ps = conexion.prepareStatement (sql)) {
           ps.setString(1, nombre);
           int filas = ps.executeUpdate();
           if (filas > 0)
               System.out.println("Montaña eliminada");
           else
               System.out.println("No se encontró la montaña");
       }
   }
   
   //metodo para eliminar la tabla 
   private static void eliminarTabla (Connection conexion, Scanner sc) throws SQLException {
       
       String sql = "DROP TABLE IF EXISTS Montañas";
       
       try(Statement stmt = conexion.createStatement()){
           stmt.executeUpdate(sql);
           System.out.println("Tabla eliminada correctamente");
       }
   }
    
    
    
}
