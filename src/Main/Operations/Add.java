package Main.Operations;

import Main.Operations.Helpers.IOperation;
import Main.Operations.Helpers.OperationPriority;
import Main.Operations.Helpers.OperationType;

public class Add implements IOperation {
    @Override
    public double perform(double firstOperand, double secondOperand) {
        return firstOperand + secondOperand;
    }

    @Override
    public int getPriority() {
        return OperationPriority.ADDITION;
    }

    @Override
    public OperationType getType() {
        return OperationType.ArithmeticAction;
    }
}
