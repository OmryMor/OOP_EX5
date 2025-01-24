package ex5.Containers;

import java.util.HashMap;

/**
 * This class holds all data for all methods in the script.
 * @author Omry Mor, Ruth Schiller
 */
public class MethodsContainer {

   private static final HashMap<String, MethodAttributes> methods = new HashMap<>();

   /**
    * Add a method to the container
    * @param method the method to add
    * @return true if the method was added, false otherwise (if was already added)
    */
   public static boolean AddMethod(MethodAttributes method){
        if(methods.containsKey(method.getMethodName())){
            return false;
        }
        methods.put(method.getMethodName(), method);
        return true;
   }

    /**
     * Get a method by its name
     * @param name the name of the method
     * @return the method with the given name or null if it doesn't exist
     */
   public static MethodAttributes getMethod(String name){
       return methods.get(name);
   }
}
