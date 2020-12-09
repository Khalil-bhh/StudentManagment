package profileView;

import database.DBConnection;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.XYChart;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.text.Text;
import javafx.scene.chart.XYChart.Data;

import java.lang.reflect.InvocationTargetException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * Created by TekSliveron 8/13/2018.
 */
public class ProfileView {

    @FXML
    private ImageView profilePic;

    @FXML
    Text tID;
    @FXML
    Text tName;
    @FXML
    Text tDepartment;
    @FXML
    Text tCGPA;
    @FXML
    Text tDOB;
    @FXML
    Text tEmail;
    @FXML
    Text tVEmail;
    @FXML
    Text tPhone;
    @FXML
    Text tAddress;
    @FXML
    Text tGName;
    @FXML
    Text tGEmail;
    @FXML
    Text tGPhone;

    DBConnection database = new DBConnection();
    Connection connection;
    Statement statement;
    ResultSet resultSet;

    @FXML
    private LineChart<String,Number> gpaVisualization;



    private Info currentInfo;

    public void setCurrentInfo(Info currentInfo) {
        this.tID.setText(currentInfo.getId());
        this.tName.setText(currentInfo.getName());
        this.tDepartment.setLayoutX(this.tName.getLayoutX() + 10.0D + this.tName.getBoundsInParent().getWidth());
        this.tDepartment.setText(currentInfo.getDepartment());
        this.tCGPA.setText(currentInfo.getCgpa());
        this.tVEmail.setText(currentInfo.getVemail());
        this.tDOB.setText(currentInfo.getDob());
        this.tEmail.setText(currentInfo.getEmail());
        this.tPhone.setText(currentInfo.getPhone());
        this.tAddress.setText(currentInfo.getAddress());
        this.tGName.setText(currentInfo.getGname());
        this.tGEmail.setText(currentInfo.getGemail());
        this.tGPhone.setText(currentInfo.getGphone());
        try {
            Image image = new Image("/image/"+currentInfo.getfullId()+".jpg");
            profilePic.setImage(image);
        }
        catch (Exception e){
            profilePic.setImage(new Image("/image/default-user-icon.png"));
        }
        this.currentInfo = currentInfo;
    }

    @FXML
    private void setLoadGpaButtonClcik(Event event){
        gpaVisualization.getData().clear();
        XYChart.Series<String,Number> gpaLineChart = new XYChart.Series<String,Number>();

        try{
            this.connection = this.database.getConnection();
            this.statement = this.connection.createStatement();
            System.out.println(this.currentInfo.getfullId());
            this.resultSet = this.statement.executeQuery("select * from student where dbStudentID = "+this.currentInfo.getfullId()+";");
            if (this.resultSet.next()){
                for (int i = 1; i <= 12 ; i++) {
                    if (this.resultSet.getDouble("dbStudent" + i + "thSemGpa") != 0.0D) {
                        gpaLineChart.getData().add(new Data(i + "th", this.resultSet.getDouble("dbStudent" + i + "thSemGpa")));
                    }
                }
                gpaLineChart.setName("All GPA");
                this.gpaVisualization.getData().add(gpaLineChart);
            }
        } catch ( SQLException ex){
            System.out.println(ex.getMessage());
        }


    }

}
