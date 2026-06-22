import com.sfp.rubrica.domain.Rubrica;
import com.sfp.rubrica.infrastructure.persistence.MySQLRubricaRepository;
import java.util.List;

public class TesteRubricas {
    public static void main(String[] args) {
        MySQLRubricaRepository repo = new MySQLRubricaRepository();
        List<Rubrica> rubricas = repo.buscarTodas();
        for(Rubrica r : rubricas) {
            System.out.println(r.getCodigo() + " - " + r.getDescricao() + " (" + r.getNatureza() + ", " + r.getTipo() + ")");
        }
    }
}
