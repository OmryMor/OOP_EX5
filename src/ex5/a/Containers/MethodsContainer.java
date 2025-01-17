package ex5.a.Containers;

import java.util.HashMap;
import java.util.HashSet;

public class MethodsContainer {
   private static HashMap<String, MethodAttributes> methods;

   public static boolean AddMethod(MethodAttributes method){
        if(methods.containsKey(method.getMethodName())){
            return false;
        }
        methods.put(method.getMethodName(), method);
        return true;
   }

   public static MethodAttributes getMethod(String name){
       return methods.get(name);
   }
}
