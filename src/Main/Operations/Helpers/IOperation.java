package Main.Operations.Helpers;

/*
    Rozhraní pro definovaní operace.
 */
public interface IOperation {
    /*
        Provede libovolnou operaci a vrací její výsledek.
     */
    double perform(double firstOperand, double secondOperand);

    /*
        Vrací prioritu dané operace, čím je vyšší tím dřiv se operace provede.
     */
    int getPriority();

    /*
        Vrací typ operace, buď se jedná o aritmetickou, nebo o funkci.
     */
    OperationType getType();
}
