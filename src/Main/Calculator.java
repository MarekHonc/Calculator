package Main;
import Main.Operations.Add;
import Main.Operations.Subtract;
import Main.Operations.Multiply;
import Main.Operations.Divide;
import Main.Operations.Helpers.IOperation;

import java.util.HashMap;
import java.util.Map;

public class Calculator {
    private Map<String, IOperation> operations = new HashMap<>();

    public Calculator(){
        this.initOperations();
    }

    private void initOperations(){
        operations.put("+", new Add());
        operations.put("-", new Subtract());
        operations.put("/", new Divide());
        operations.put("*", new Multiply());
    }

    public double Calculate(String expression){
        String defaultExpression = expression;

        //TODO: Parse string and calculate based on priority
        //STEP 1 = replace highest priority operations with result
        //STEP 2 = repeat until there are none of this operation
        //STEP 3 = repeat for all operations

        return 0;
    }
}
