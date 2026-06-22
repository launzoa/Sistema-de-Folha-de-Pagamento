package com.sfp;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import java.net.URL;

public class TestFXMLLoad {
    public static void main(String[] args) {
        Platform.startup(() -> {
            try {
                URL fxmlLocation = TestFXMLLoad.class.getResource("/com/sfp/folha/ui/TelaFolhaMes.fxml");
                if (fxmlLocation == null) {
                    System.out.println("FXML NOT FOUND!");
                    System.exit(1);
                }
                System.out.println("Loading FXML: " + fxmlLocation);
                Parent root = FXMLLoader.load(fxmlLocation);
                System.out.println("SUCESSO: TelaFolhaMes.fxml loaded successfully!");
            } catch (Exception e) {
                System.err.println("FXMLLoader.load() threw an exception:");
                e.printStackTrace();
            } finally {
                Platform.exit();
            }
        });
    }
}
