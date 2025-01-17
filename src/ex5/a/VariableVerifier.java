//package ex5.a;
//
//import ex5.utils.LineNumberTuple;
//import ex5.utils.RegexConstants;
//
//import java.util.HashMap;
//import java.util.regex.Matcher;
//import java.util.regex.Pattern;
//
//public class VariableVerifier {
//
//    public final String VAR_NAME_REGEX = "([A-Za-z][A-Za-z0-9_]*|_[A-Za-z0-9][A-Za-z0-9_]*)";
////TODO MAKE NAME EVERYTHING\/
//    public final String VAR_DECLARATION_REGEX =
//            VAR_NAME_REGEX + "((\\s*[=]\\s*)([-+]?[\\d]*[.]?[\\d]*|[\"].*[\"]|['].[']|true|false))?(\\s*[,;]\\s*)";
//    public final String FINAL_VAR_DECLARATION_REGEX =
//            VAR_NAME_REGEX + "((\\s*[=]\\s*)([-+]?[\\d]*[.]?[\\d]*|[\"].*[\"]|['].[']|true|false))(\\s*[,;]\\s*)";
//
//
//
//    public enum VariableScope {
//        GLOBAL, LOCAL
//    }
//
//    public final HashMap<String, Variable> globalVariables = new HashMap<>();
//
//    public boolean isVariable(LineNumberTuple line){
//        Pattern pattern = Pattern.compile(RegexConstants.VAR_TYPE_REGEX);
//        Matcher matcher = pattern.matcher(line.line);
//        if (matcher.find()) {
//            return true;
//        } else if (globalVariables.get(line.line.split("\\s+")[0]) != null){
//            return true;
//        }
//        return false;
//    }
//
//    //TODO RAISE EXCEPTION
//    public boolean handleVariable(LineNumberTuple line) {
//        // First, check if we have a valid variable type
//        Pattern typePattern = Pattern.compile(RegexConstants.VAR_TYPE_REGEX);
//        Matcher typeMatcher = typePattern.matcher(line.line);
//
//        // If no type is found, no variable declaration is handled here
//        if (!typeMatcher.find()) {
//            return true;
//            // TODO: Add variable assignment checks only in method scope if needed
//        }
//
//        // Extract and map the type
//        VariableType type = getType(typeMatcher.group(2));
//        boolean isFinal = typeMatcher.group(1) != null;
//        Pattern declarationPattern = Pattern.compile(isFinal ? FINAL_VAR_DECLARATION_REGEX : VAR_DECLARATION_REGEX);
//        Matcher declarationMatcher = declarationPattern.matcher(line.line);
//
//        boolean encounteredSemicolon = false;
//
//        // Process each matching declaration (e.g., "a = 5", "b", etc.)
//        while (declarationMatcher.find()) {
//            // Group(1) is the variable name
//            String name = declarationMatcher.group(1);
//
//
//            // If we already encountered a semicolon in a previous match,
//            // but still found another declaration, it's invalid
//            if (encounteredSemicolon) {
//                // TODO: There was a semicolon before last declaration -> syntax error
//                return false;
//            }
//
//            // Check if the last token is a semicolon
//            // group(5) typically holds the delimiter (comma or semicolon)
//            if (declarationMatcher.group(5).trim().equals(";")) {
//                encounteredSemicolon = true;
//            }
//            if (!verifyName(name)) {
//                // TODO: Print error at line number - "wrong name syntax error"
//                return false;
//            }
//
//            // Check for double declaration
//            if (globalVariables.get(name) != null) {
//                // TODO: "double declaration" error
//                return false;
//            }
//
//            // Create the variable (handle optional assignment)
//            String assignedValue = declarationMatcher.group(4);
//            boolean success = createVariable(type, name, VariableScope.GLOBAL, assignedValue, isFinal);
//            if (!success) {
//                // TODO: "wrong type assignment" error
//                return false;
//            }
//        }
//
//        return true;
//    }
//
//    private boolean createVariable(VariableType type, String name, VariableScope scope, String value, boolean isFinal) {
//
//        if (value != null && !verifyValue(type, value)) {
//            return false;
//        }
//        if (globalVariables.containsKey(name)) {
//            return false;
//        }
//        boolean hasValue = (value != null);
//        globalVariables.put(name, new Variable(type, scope, hasValue, isFinal));
//        return true;
//    }
//
//    private boolean verifyValue(VariableType type, String value){
//        switch (type){
//            case INT:
//                try {
//                    Integer.parseInt(value);
//                } catch (NumberFormatException e){
//                    return false;
//                }
//                break;
//            case DOUBLE:
//                try {
//                    Double.parseDouble(value);
//                } catch (NumberFormatException e){
//                    return false;
//                }
//                break;
//            case STRING:
//                if (!value.startsWith("\"") || !value.endsWith("\"")){
//                    return false;
//                }
//                break;
//            case BOOLEAN:
//                if (!value.equals("true") && !value.equals("false")){
//                    try {
//                        Double.parseDouble(value);
//                    } catch (NumberFormatException e){
//                    return false;
//                    }
//                }
//                break;
//            case CHAR:
//                if (!value.startsWith("'") || !value.endsWith("'") || value.length() != 3){
//                    return false;
//                }
//                break;
//        }
//        return true;
//    }
//
//
//
//    private boolean verifyName(String name){
//        Pattern pattern = Pattern.compile(VAR_NAME_REGEX);
//        Matcher matcher = pattern.matcher(name);
//        return matcher.find();
//    }
//
//    public class Variable {
//        public final VariableType type;
//        public final VariableScope scope;
//        public boolean hasValue;
//        public boolean isFinal;
//
//        public Variable(VariableType type, VariableScope scope, boolean hasValue, boolean isFinal){
//            this.hasValue = hasValue;
//            this.type = type;
//            this.scope = scope;
//            this.isFinal = isFinal;
//        }
//    }
//}
