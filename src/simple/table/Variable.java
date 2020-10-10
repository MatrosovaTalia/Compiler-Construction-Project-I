package simple.table;

import simple.IExpression;

public class Variable {

    private String type;
    private IExpression value;
    private Integer index;
    private String methodName;
    private String variableName;

    public Variable(String variableName, String methodName, String type, IExpression value){
        this.index = 0;
        this.variableName = variableName;
        this.methodName = methodName;
        this.type = type;
        this.value = value;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof Variable) {
            Variable v = (Variable) obj;
            return v.variableName.equals(this.variableName);
        }
        else {
            return false;
        }
    }

    public void setIndex(Integer index){
        this.index = index;
    }

    public void setType(String type){
        this.type = type;
    }

    public void setValue(IExpression value){
        this.value = value;
    }

    public void setMethodName(String methodName){
        this.methodName = methodName;
    }

    public void setVariableName(String variableName){
        this.variableName = variableName;
    }

    public String getVariableName(){
        return this.variableName;
    }

    public String getVariableType(){
        return this.type;
    }

    @Override
    public String toString() {
        return String.format("Variable %s of type %s\n", variableName, type);
    }

    public static boolean validAssignment(String expectedType, String givenType) {
//        if (expectedType.equals("I") && givenType.equals("I")) {
//            desc = "I";
//        } else if (expectedType.equals("I") && givenType.equals("F")) {
//            desc = "I";
//            mv.visitInsn(F2I);
//        } else if (expectedType.equals("I") && givenType.equals("Z")) {
//            desc = "I";
//        } else if (expectedType.equals("F") && givenType.equals("F")) {
//            desc = "F";
//        } else if (expectedType.equals("Z") && givenType.equals("Z")) {
//            desc = "Z";
//        } else if (expectedType.equals("F") && givenType.equals("I")) {
//            mv.visitInsn(I2F);
//            desc = "F";
//        } else if (expectedType.equals("F") && givenType.equals("Z")) {
//            mv.visitInsn(I2F);
//            desc = "F";
//        } else if (expectedType.equals("Z") && givenType.equals("I")) {
//            throw new RuntimeException(String.format("Method %s: trying to assign integer value to a boolean variable %s",
//                    methodName, varName));
//
//        } else if (expectedType.equals("Z") && givenType.equals("F")) {
//            throw new RuntimeException(String.format("Method %s: trying to assign real value to a boolean variable %s",
//                    methodName, varName));
//        }
        return false;
    }
}
