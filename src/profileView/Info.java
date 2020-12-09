package profileView;

import database.DBConnection;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * Created by TekSliveron 8/13/2018.
 */
public class Info {
    private String id,name,department,cgpa,vemail,dob,email,phone;
    private String gname,gemail,gphone,address;

    DBConnection database = new DBConnection();
    Connection connection;
    Statement statement;
    ResultSet resultSet;

    public Info() {
    }

    public Info(String id) {
        this.id = id;
        setOtherField();

    }


    private void setOtherField(){
        try {
            this.connection = this.database.getConnection();
            this.statement = this.connection.createStatement();
            this.resultSet = this.statement.executeQuery("select * from student where dbStudentID = " +this.id);
            if (resultSet.next()){
                this.name = this.resultSet.getString("dbStudentFname") + " " + this.resultSet.getString("dbStudentLname");
                this.department = this.resultSet.getString("dbStudentDepartment");
                this.cgpa = this.resultSet.getString("dbStudentCgpa");
                this.vemail = this.name.substring(0, 1) + this.resultSet.getString("dbStudentLname") + this.id.substring(3) + "@bs" + this.department.toLowerCase() + ".uiu.ac.bd";
                this.dob = this.resultSet.getString("dbStudentDOB");
                this.email = this.resultSet.getString("dbStudentEmail");
                this.phone = this.resultSet.getString("dbStudentPhone");
                this.address = this.resultSet.getString("dbStudentAddress");
                this.gname = this.resultSet.getString("dbGuardianFname") + " " + this.resultSet.getString("dbGuardianLname");
                this.gemail = this.resultSet.getString("dbGuardianEmail");
                this.gphone = this.resultSet.getString("dbGuardianPhone");
            }
       }catch (SQLException ex){
            System.out.println(ex.getMessage());
        }

    }




    public String getId() {
        return id.substring(0,3)+" "+id.substring(3,6)+" "+id.substring(6);
    }

    public String getfullId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public String getDepartment() {
        return department;
    }

    public String getCgpa() {
        return "("+cgpa+")";
    }

    public String getVemail() {
        return vemail;
    }

    public String getDob() {
        return dob;
    }

    public String getEmail() {
        return email;
    }

    public String getPhone() {
        return phone;
    }

    public String getGname() {
        return gname;
    }

    public String getGemail() {
        return gemail;
    }

    public String getGphone() {
        return gphone;
    }

    public String getAddress() {
        return address;
    }



}
