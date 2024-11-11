package bank;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class Main extends Application {
    private BankAccount currentAccount;

    @Override
    public void start(Stage primaryStage) {
        // Créer des comptes d'exemple
        SavingsAccount savings = new SavingsAccount("hadir", 1001, 1000.0);
        CheckingAccount checking = new CheckingAccount("asmaa", 1002, 500.0);
        BusinessAccount business = new BusinessAccount("salma", 1003, 2000.0);
        currentAccount = savings;

        // Étiquettes et champs de texte pour afficher les informations et les opérations
        Label balanceLabel = new Label("Solde actuel: " + currentAccount.getBalance());
        TextField amountField = new TextField();
        amountField.setPromptText("Entrez le montant");

        // Boutons d'interaction
        Button depositButton = new Button("Dépôt");
        Button withdrawButton = new Button("Retrait");
        Button checkBalanceButton = new Button("Vérifier le solde");
        Button switchAccountButton = new Button("Changer de type de compte");

        // Actions des boutons
        depositButton.setOnAction(e -> {
            try {
                double amount = Double.parseDouble(amountField.getText());
                currentAccount.deposit(amount);
                balanceLabel.setText("Solde actuel: " + currentAccount.getBalance());
            } catch (NumberFormatException ex) {
                showAlert("Erreur", "Veuillez entrer un montant valide.");
            }
        });

        withdrawButton.setOnAction(e -> {
            try {
                double amount = Double.parseDouble(amountField.getText());
                currentAccount.withdraw(amount);
                balanceLabel.setText("Solde actuel: " + currentAccount.getBalance());
            } catch (InsufficientFundsException ex) {
                showAlert("Erreur", ex.getMessage());
            } catch (NumberFormatException ex) {
                showAlert("Erreur", "Veuillez entrer un montant valide.");
            }
        });

        checkBalanceButton.setOnAction(e -> {
            balanceLabel.setText("Solde actuel: " + currentAccount.getBalance());
        });

        switchAccountButton.setOnAction(e -> {
            // Logique de changement de compte
            if (currentAccount instanceof SavingsAccount) {
                currentAccount = checking;
            } else if (currentAccount instanceof CheckingAccount) {
                currentAccount = business;
            } else {
                currentAccount = savings;
            }
            balanceLabel.setText("Type de compte : " + currentAccount.getClass().getSimpleName() + "\nSolde actuel: " + currentAccount.getBalance());
        });

        // Disposition des éléments dans l'interface
        VBox root = new VBox(10);
        root.getChildren().addAll(balanceLabel, amountField, depositButton, withdrawButton, checkBalanceButton, switchAccountButton);

        // Création de la scène et affichage
        Scene scene = new Scene(root, 300, 250);
        scene.getStylesheets().add(getClass().getResource("style.css").toExternalForm());
        primaryStage.setTitle("Banking Operations Simulation");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    // Méthode utilitaire pour afficher les alertes
    private void showAlert(String title, String message) {
        Alert alert = new Alert(AlertType.ERROR);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }

    public static void main(String[] args) {
        launch(args);
    }
}