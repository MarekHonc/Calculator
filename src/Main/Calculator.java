package Main;
import Main.Operations.Add;
import Main.Operations.Helpers.OperationType;
import Main.Operations.Subtract;
import Main.Operations.Multiply;
import Main.Operations.Divide;
import Main.Operations.Helpers.IOperation;

import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.stream.Collectors;

public class Calculator {
    private Map<String, IOperation> operations = new HashMap<>();

    public Calculator(){
        this.initOperations();
    }

    private void initOperations(){
        this.operations.put("+", new Add());
        this.operations.put("-", new Subtract());
        this.operations.put("/", new Divide());
        this.operations.put("*", new Multiply());

        // Srovnám pomocí priority operací
        this.operations = this.operations.entrySet()
                .stream()
                .sorted((op1, op2) -> compareOperations(op1.getValue(), op2.getValue()))
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue, (e1, e2) -> e1, LinkedHashMap::new));
    }

    private int compareOperations(IOperation op1, IOperation op2){
        if(op1.getPriority() > op2.getPriority()){
            return -1;
        }
        else if(op1.getPriority() < op2.getPriority()){
            return 1;
        }
        else{
            return 0;
        }
    }

    /*
        Vrací výsledek příkladu, ve kterém nejsou žádné závorky, podle priorit operací.
     */
    public double Calculate(String expression){
        if(expression.contains("(") || expression.contains(")")){
            throw new java.lang.UnsupportedOperationException("This method doesnt support brackets");
        }

        String result = expression.replaceAll("\\s+", "").toLowerCase();

        for(Map.Entry<String, IOperation> entry : this.operations.entrySet()){
            String key = entry.getKey();
            IOperation value = entry.getValue();

            System.out.println(key);

            while(result.contains(key)){
                // Zjistím si index operátoru.
                int operationIndex = result.indexOf(key);

                // Získám číslo za operátorem.
                String[] allNumbersInSecondHalf = getAllNumbersInString(result.substring(operationIndex + 1));
                String secondNumberString = allNumbersInSecondHalf[0];
                double secondNumber = Double.parseDouble(secondNumberString);

                // Pokud je operace typu funkce, má pouze jeden argument.
                if(value.getType() == OperationType.Function){
                    // TODO: pokud má závorku () -> hledám druhou, pokud nemá beru prostě první číslo.
                    // TODO: + bude volat rekurzivně poté sama sebe aby bylo možná spočítat např. sin(1+2)
                    throw new java.lang.UnsupportedOperationException("Not implemented yet.");
                }

                // Získám číslo před operátorem.
                String[] allNumbersInFirstHalf = getAllNumbersInString(result.substring(0, operationIndex));
                String firstNumberString = allNumbersInFirstHalf[allNumbersInFirstHalf.length - 1];
                double firstNumber = Double.parseDouble(firstNumberString);

                // Nahradím operaci za výsledek.
                double partialResult = value.perform(firstNumber, secondNumber);
                result = result.replace(firstNumberString + key + secondNumberString, String.valueOf(partialResult));
            }
        }

        return Double.parseDouble(result);
    }

    private String[] getAllNumbersInString(String numbersInString){
        String[] numbers = numbersInString.split("[^0-9,.]");
        return Arrays.stream(numbers).filter(str -> !str.isEmpty()).toArray(String[]::new);
    }
}
