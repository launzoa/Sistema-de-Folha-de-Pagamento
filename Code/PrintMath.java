import java.math.BigDecimal;
import java.math.RoundingMode;
public class PrintMath {
    public static void main(String[] args) {
        // TesteFaltasDias
        BigDecimal salarioBase = new BigDecimal("3000.0");
        int horasMes = 220;
        int diasUteis = 22;
        BigDecimal valorDiario = salarioBase.divide(new BigDecimal(diasUteis), 2, RoundingMode.HALF_UP);
        BigDecimal descontoFaltas = valorDiario.multiply(new BigDecimal("2.0")).setScale(2, RoundingMode.HALF_UP);
        System.out.println("Desconto faltas = " + descontoFaltas);
    }
}
