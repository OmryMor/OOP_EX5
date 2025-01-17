package ex5.utils;

public class ScopeManager {

    private static int scope = 0;

    public static int getScope() {
        return scope;
    }

    public static void incrementScope() {
        scope++;
    }

    public static void decrementScope() {
        scope--;
        if(scope<0){
            //TODO RAISE ERROR - INVALID SCOPE due to brackets mismatch
        }
    }
}
