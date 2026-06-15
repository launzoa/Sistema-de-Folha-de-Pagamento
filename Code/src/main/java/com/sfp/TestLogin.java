package com.sfp;
import com.sfp.autenticacao.domain.CatalogoUsuario;
import com.sfp.core.domain.Usuario;

public class TestLogin {
    public static void main(String[] args) {
        CatalogoUsuario cat = new CatalogoUsuario();
        Usuario u = cat.buscarUsuario("admin", "admin");
        if (u != null) {
            System.out.println("Login SUCESSO! User: " + u.getNome() + " Status: " + u.isStatus());
        } else {
            System.out.println("Login FALHOU!");
            
            // let's check what's in the DB
            try {
                java.sql.Connection c = com.sfp.core.ConexaoBD.getConnection();
                java.sql.ResultSet rs = c.createStatement().executeQuery("SELECT * FROM usuario");
                while (rs.next()) {
                    System.out.println("Row: id=" + rs.getInt("id") + ", nome=" + rs.getString("nome") + 
                                       ", senha=" + rs.getString("senha") + ", status=" + rs.getBoolean("status"));
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
