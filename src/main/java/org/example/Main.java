package org.example;

// Press Shift twice to open the Search Everywhere dialog and type `show whitespaces`,
// then press Enter. You can now see whitespace characters in your code.
import javax.persistence.*;
import java.util.Scanner;

public class Main {
        private static EntityManagerFactory emf = Persistence.createEntityManagerFactory("BDJPA");
        private static EntityManager em = emf.createEntityManager();
        private static final EntityTransaction emt = em.getTransaction();
        private static void clearConsola() {
            for (int i = 0; i < 30; i++) {
                System.out.println();
            }
        }
        public static void menu(){
            boolean salir = false;
            Scanner sc = new Scanner(System.in);
            while (!salir) {
                System.out.println("""
                        -----------[Menu Principal]--------------
                        
                        Ingrese una opcion:
                        1- Alta de Tuplas.
                        2- Modificar Tuplas.
                        3- Deletear Tuplas.
                        Cualquier otra tecla para salir.
                        
                        Ingrese una opcion: """);
                String input = sc.nextLine().trim();
                switch (input) {
                    case "1":
                        altaTupla();
                    case "2":
                        modificarTupla();
                    case "3":
                        deleteTupla();
                    default:
                        clearConsola();
                        System.out.println("Nos vemos!");
                        salir = true;
                }
            }
            sc.close();
        }
    private static void deleteTupla() {
        String smsg = "persist()";
        Scanner sc = new Scanner(System.in);
        System.out.println("""
                Por favor Ingrese la tabla a la cual quiere realizar un alta:
                
                1. Cliente
                2. Factura
                """);
        String inp = sc.nextLine().trim();
        switch (inp){
            case "1": {
                em.getTransaction().begin();
                System.out.println("Ingrese el codigo: ");
                inp = sc.nextLine().trim();
                // Busca la entidad que deseas modificar
                Cliente c = em.find(Cliente.class, Integer.parseInt(inp));

                if (c == null) {
                    System.out.println("No existe dicho cliente");
                    break;
                }
                else em.remove(c);
            }
            case "2": {
                em.getTransaction().begin();
                System.out.println("Ingrese el numero: ");
                inp = sc.nextLine().trim();
                // Busca la entidad que deseas modificar
                Factura f = em.find(Factura.class, Integer.parseInt(inp));

                if (f == null) {
                    System.out.println("No existe dicha factura");
                    break;
                }
                else em.remove(f);
            }

            try {
                //Cliente f = em.find(Cliente.class,456); // acceso por PK
                //em.remove(f);
                System.out.println("Main:em.persist(c) hecho");
                smsg = "commit()";
                em.getTransaction().commit();
                //emt.commit();
                System.out.println("Main:emt.commit() hecho");
            } catch(IllegalArgumentException iae) {
                System.out.println("Main:Error en "+smsg+ " persistiendo, posiblemente sea null");
            } catch(EntityExistsException eee) {
                System.out.println("Main:Error en "+smsg+" persistiendo, esta entidad ya existe");
            } catch(TransactionRequiredException tre) {
                System.out.println("Main:Error en "+smsg+" persistiendo, se requiere de una transaccion");
            } catch(Exception e) {
                System.out.println("Main:Error en "+smsg+ " persistiendo, error: "+e.getMessage());
            }
        }
        System.out.println("Presione ENTER para continuar...");
        sc.nextLine();
        clearconsole();
    }

    private static void modificarTupla() {
        String smsg = "persist()";
        Scanner sc = new Scanner(System.in);
        System.out.println("Por favor Ingrese la tabla a la cual quiere realizar un alta:" +
                "1. Cliente." +
                "2. Factura");
        String inp = sc.nextLine().trim();
        switch (inp){
            case "1": {
                em.getTransaction().begin();
                System.out.println("Ingrese el codigo: ");
                inp = sc.nextLine().trim();
                // Busca la entidad que deseas modificar
                Cliente c = em.find(Cliente.class, Integer.parseInt(inp));

                if (c == null) {
                    System.out.println("No existe dicho cliente");
                    break;
                }

                // Modifica los atributos de la entidad
                System.out.println("Ingrese el nombre: ");
                inp = sc.nextLine().trim();
                c.setNombre(inp);

                System.out.println("Ingrese la direccion: ");
                inp = sc.nextLine().trim();
                c.setDirec(inp);

                System.out.println("Ingrese el codigo postal: ");
                inp = sc.nextLine().trim();
                c.setCodigo(Integer.parseInt(inp));

                System.out.println("Ingrese el telefono: ");
                inp = sc.nextLine().trim();
                c.setTel(inp);

                // Cierra el EntityManager
            }
            case "2": {
                em.getTransaction().begin();
                System.out.println("Ingrese el numero: ");
                inp = sc.nextLine().trim();
                // Busca la entidad que deseas modificar
                Factura f = em.find(Factura.class, Integer.parseInt(inp));

                if (f == null) {
                    System.out.println("No existe dicha factura");
                    break;
                }

                System.out.println("Ingrese el id: ");
                inp = sc.nextLine().trim();
                f.setCodigo(Integer.parseInt(inp));

                System.out.println("Ingrese la importe: ");
                inp = sc.nextLine().trim();
                f.setImporte(Double.parseDouble(inp));

                // Cierra el EntityManager
            }

            try {
                //Cliente f = em.find(Cliente.class,456); // acceso por PK
                //em.remove(f);
                System.out.println("Main:em.persist(c) hecho");
                smsg = "commit()";
                em.getTransaction().commit();
                //emt.commit();
                System.out.println("Main:emt.commit() hecho");
            } catch(IllegalArgumentException iae) {
                System.out.println("Main:Error en "+smsg+ " persistiendo, posiblemente sea null");
            } catch(EntityExistsException eee) {
                System.out.println("Main:Error en "+smsg+" persistiendo, esta entidad ya existe");
            } catch(TransactionRequiredException tre) {
                System.out.println("Main:Error en "+smsg+" persistiendo, se requiere de una transaccion");
            } catch(Exception e) {
                System.out.println("Main:Error en "+smsg+ " persistiendo, error: "+e.getMessage());
            }
        }
        System.out.println("Presione ENTER para continuar...");
        sc.nextLine();
        clearconsole();

    }

    private static void altaTupla() {

            String smsg = "persist()";
            Scanner sc = new Scanner(System.in);
            System.out.println("Por favor Ingrese la tabla a la cual quiere realizar un alta:" +
                    "1. Cliente." +
                    "2. Factura");
            String inp = sc.nextLine().trim();
            switch (inp){
                case "1":{
                    Cliente c = new Cliente();
                    System.out.println("Ingrese el codigo: ");
                    inp = sc.nextLine().trim();
                    c.setCodigo(Integer.parseInt(inp));

                    System.out.println("Ingrese el nombre: ");
                    inp = sc.nextLine().trim();
                    c.setNombre(inp);

                    System.out.println("Ingrese la direccion: ");
                    inp = sc.nextLine().trim();
                    c.setDirec(inp);

                    System.out.println("Ingrese el codigo postal: ");
                    inp = sc.nextLine().trim();
                    c.setCodigo(Integer.parseInt(inp));

                    System.out.println("Ingrese el telefono: ");
                    inp = sc.nextLine().trim();
                    c.setTel(inp);
                    emt.begin();
                    em.persist(c);
                    break;

                }
                case "2":{

                    Factura f = new Factura();
                    System.out.println("Ingrese el numero: ");
                    inp = sc.nextLine().trim();
                    f.setNumero(Integer.parseInt(inp));

                    System.out.println("Ingrese el id: ");
                    inp = sc.nextLine().trim();
                    f.setCodigo(Integer.parseInt(inp));

                    System.out.println("Ingrese la importe: ");
                    inp = sc.nextLine().trim();
                    f.setImporte(Double.parseDouble(inp));

                    emt.begin();
                    em.persist(f);
                    break;
                }
            }

            try {
                //Cliente f = em.find(Cliente.class,456); // acceso por PK
                //em.remove(f);
                System.out.println("Main:em.persist(c) hecho");
                smsg = "commit()";
                emt.commit();
                System.out.println("Main:emt.commit() hecho");
            } catch(IllegalArgumentException iae) {
                System.out.println("Main:Error en "+smsg+ " persistiendo, posiblemente sea null");
            } catch(EntityExistsException eee) {
                System.out.println("Main:Error en "+smsg+" persistiendo, esta entidad ya existe");
            } catch(TransactionRequiredException tre) {
                System.out.println("Main:Error en "+smsg+" persistiendo, se requiere de una transaccion");
            } catch(Exception e) {
                System.out.println("Main:Error en "+smsg+ " persistiendo, error: "+e.getMessage());
            }

        System.out.println("Presione ENTER para continuar...");
        sc.nextLine();
        clearconsole();
    }

    public static void clearconsole(){
        for (int i = 0; i < 30; i++) {
            System.out.println("");
        }
    }

    public static void main(String arg[]) {
            System.out.println("Main:inicio");
            menu();
            salir();
        }
        public static EntityManagerFactory getEntityManagerFactory() { return emf; }
        public static EntityManager getEntityManager() { return em; }
        public static void salir() {
            if ( em != null ) em.close();
            if ( emf != null ) emf.close();
        }
    }

