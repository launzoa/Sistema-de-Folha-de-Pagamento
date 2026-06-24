import com.sfp.core.application.ServicoDashboard;

public class TesteDashboard {
    public static void main(String[] args) {
        try {
            ServicoDashboard sd = new ServicoDashboard();
            sd.processarDadosDashboard();
            System.out.println("SUCESSO");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
