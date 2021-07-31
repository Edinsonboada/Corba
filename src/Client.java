import java.util.Scanner;
import org.omg.CORBA.*;
import org.omg.CosNaming.*;
import sumapp.*;


/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Paolo
 */
public class Client {
    static suma sumaImpl;
    
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        
        try {
            ORB orb = ORB.init(args, null);
            
            org.omg.CORBA.Object objRef = orb.resolve_initial_references("NameService");
            NamingContextExt ncRef = NamingContextExtHelper.narrow(objRef);
            
            String name = "Suma";
            
            sumaImpl = sumaHelper.narrow(ncRef.resolve_str(name));
            
            System.out.println("Ingrese el primer numero: ");
            int firstNumber = sc.nextInt();
            
            System.out.println("Ingrese el segundo numero: ");
            int secondNumber = sc.nextInt();
            
            System.out.println("La suma es: "+ Integer.toString(sumaImpl.add(firstNumber, secondNumber)));
            
            sumaImpl.shutdown();           
        } catch (Exception e) {
            System.err.println("Error: "+e);
            e.printStackTrace(System.out);
        }
    }   
}