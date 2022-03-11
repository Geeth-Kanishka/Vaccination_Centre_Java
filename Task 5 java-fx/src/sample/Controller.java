package sample;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class Controller {
    @FXML
    private Button btnPrint;
    @FXML
    private TextField txtF;
    @FXML
    private TextField txtS;
    @FXML
    private TextField txtA;
    @FXML
    private TextField txtC;
    @FXML
    private TextField txtV;
    @FXML
    private TextField txtN;
    @FXML
    public void navigate(ActionEvent actionEvent) throws Exception{
        String Fname = txtF.getText();
        String Sname = txtS.getText();
        String Age = txtA.getText();
        String NIC = txtN.getText();
        String City = txtC.getText();
        String Vaccine = txtV.getText();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("reciept.fxml"));
        loader.load();
        Reciept display=loader.getController();
        display.setdata(Fname,Sname,Age,NIC,City,Vaccine);
        Stage newStage = new Stage();
        Parent root = loader.getRoot();
        newStage.setScene(new Scene(root));
        newStage.show();

        Stage previousStage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        previousStage.close();
    }
}
