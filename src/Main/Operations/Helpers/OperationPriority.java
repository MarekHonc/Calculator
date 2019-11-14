package Main.Operations.Helpers;

/*
    Definice priority operací.
    Vyšší přiorita znamená že se operace provede dřív,
    tzn. pokud součet má prioritu 2 a násobení 100 násobení proběhne první.
 */
public class OperationPriority {
    public final static int ADDITION = 1;
    public final static int SUBTRACTION = 2;
    public final static int DIVISION = 100;
    public final static int MULTIPLICATION = 101;
}
