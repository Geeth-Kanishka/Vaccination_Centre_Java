package sample;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
public class Reciept  {

    @FXML
    private Label lblP;
    @FXML
    private Label lblF;
    @FXML
    private Label lblS;
    @FXML
    private Label lblA;
    @FXML
    private Label lblN;
    @FXML
    private Label lblC;
    @FXML
    private Label lblV;
    @FXML
    private Button btnBack;
    @FXML
    private Label lblD;
    @FXML
    private Label lblT;

    @FXML
    public void navigate(ActionEvent actionEvent)throws Exception{
        Stage newStage=new Stage();
        Parent root= FXMLLoader.load(getClass().getResource("sample.fxml"));
        newStage.setScene(new Scene(root));
        newStage.show();

        Stage previousStage=(Stage) ((Node)actionEvent.getSource()).getScene().getWindow();
        previousStage.close();
    }
    public void setdata(String a,String b,String c,String d,String e,String f){
        this.lblF.setText(a);
        this.lblS.setText(b);
        this.lblA.setText(c);
        this.lblN.setText(d);
        this.lblC.setText(e);
        this.lblV.setText(f);
        LocalDateTime current = LocalDateTime.now();
        DateTimeFormatter DateFormat = DateTimeFormatter.ofPattern("dd/MM/yy");
        DateTimeFormatter TimeFormat = DateTimeFormatter.ofPattern("HH:mm");
        lblD.setText(DateFormat.format(current));
        lblT.setText(TimeFormat.format(current));
    }




}


