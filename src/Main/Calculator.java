package Main;
import Main.Operations.Add;
import Main.Operations.Helpers.OperationType;
import Main.Operations.Subtract;
import Main.Operations.Multiply;
import Main.Operations.Divide;
import Main.Operations.Helpers.IOperation;

import java.util.Arrays;
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

    /*
        Vrací výsledek příkladu, ve kterém nejsou žádné závorky, podle priorit operací.
     */
    public double Calculate(String expression){
        String result = expression.replaceAll("\\s+", "").toLowerCase();

        for(Map.Entry<String, IOperation> entry : this.operations.entrySet()){
            String key = entry.getKey();
            IOperation value = entry.getValue();

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
