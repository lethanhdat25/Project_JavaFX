package project.quanLyThe;

import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import project.Quanly.TheSV;

import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ResourceBundle;

public class Controller implements Initializable {
    public final static String connectString="jdbc:mysql://localhost:3306/T2008M";
    public final static String user="root";
    public final static String password="root";
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        MaThe.setCellValueFactory(new PropertyValueFactory<TheSV,Integer>("maThe"));
        tbTen.setCellValueFactory(new PropertyValueFactory<TheSV,String>("name"));
        tbSdt.setCellValueFactory(new PropertyValueFactory<TheSV,String>("sdt"));
        tbDiachi.setCellValueFactory(new PropertyValueFactory<TheSV,String>("Diachi"));
        tbNT.setCellValueFactory(new PropertyValueFactory<TheSV,String>("NgayTao"));
        tbNH.setCellValueFactory(new PropertyValueFactory<TheSV,String>("NgayHH"));
        tbNS.setCellValueFactory(new PropertyValueFactory<TheSV,String>("NgaySinh"));
        tbSex.setCellValueFactory(new PropertyValueFactory<TheSV,String>("Sex"));
        try{
            Class.forName("com.mysql.jdbc.Driver");
            Connection conn= DriverManager.getConnection(connectString,user,password);
            Statement stt=conn.createStatement();
            String txt_sql="select * from ThuVien_TheSV";
            ResultSet rs= stt.executeQuery(txt_sql);
            while (rs.next()){
                int  maThe=rs.getInt("id");
                String n=rs.getString("Ten");
                String sdt=rs.getString("SDT");
                String dC=rs.getString("Diachi");
                String nS=rs.getString("ngaytao");
                String nT=rs.getString("ngayhethan");
                String nHH=rs.getString("ngaysinh");
                String sex=rs.getString("gioitinh");
                System.out.println(maThe);
                TheSV tSV=new TheSV(maThe,n,sdt,dC,nT,nHH,nS,sex);
                ds.add(tSV);
                tbView.setItems(ds);
                indentity=maThe;
            }
        }catch (Exception e){

        }
    }
    public static Integer indentity;
    public TextField TenSV;
    public TextField Sdt;
    public TextField Diachi;
    public TextField Tim;
    public DatePicker NgaySinh;
    public DatePicker NgayTao;
    public DatePicker NgayHH;
    public MenuButton Sex;
    ObservableList<TheSV> ds= FXCollections.observableArrayList();
    public TableView<TheSV> tbView;
    public TableColumn<TheSV, Integer> MaThe;
    public TableColumn<TheSV,String> tbTen;
    public TableColumn<TheSV,String> tbSex;
    public TableColumn<TheSV,String> tbSdt;
    public TableColumn<TheSV,String> tbNS;
    public TableColumn<TheSV,String> tbNT;
    public TableColumn<TheSV,String> tbNH;
    public TableColumn<TheSV,String> tbDiachi;

    public void Them(){
        indentity++;
        String n=TenSV.getText();
        String sdt=Sdt.getText();
        String dC=Diachi.getText();
        String nS=NgaySinh.getPromptText();
        String nT=NgayTao.getPromptText();
        String nHH=NgayHH.getPromptText();
        String sex=Sex.getText();
        if (n!=null && sdt!=null && dC!=null && nS!=null && nT!=null && nHH!=null && sex!=null){
            TheSV tSV=new TheSV(indentity,n,sdt,dC,nT,nHH,nS,sex);
            ds.add(tSV);
            tbView.setItems(ds);
        }
        for (TheSV sv:
                ds) {
            System.out.println(sv.getName());
        }
    }
    public void Xoa(ActionEvent actionEvent){
        TheSV layTen=tbView.getSelectionModel().getSelectedItem();
        TenSV.setText(layTen.getName());
        try {
            String deleteN=layTen.getName();
            Class.forName("com.mysql.jdbc.Driver");
            Connection conn= DriverManager.getConnection(connectString,user,password);
            Statement stt=conn.createStatement();
            String delete = "DELETE FROM SinhVien where Name like '%"+deleteN+"%'";
            stt.executeUpdate(delete);

        }catch (Exception e){

        }

        ObservableList<TheSV> allThe,SingleThe;
        allThe=tbView.getItems();
        SingleThe=tbView.getSelectionModel().getSelectedItems();
        SingleThe.forEach(allThe::remove);
    }
    public void Luu(){

    }
    public void Tim(){
        try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection conn= DriverManager.getConnection(connectString,user,password);
            Statement stt=conn.createStatement();
            String search="seclect * from SinhVien_TheSV where Name like'%"+Tim.getText()+"'";

            ResultSet rs= stt.executeQuery(search);
            int  id=rs.getInt("id");
            String n=rs.getString("Ten");
            String sdt=rs.getString("SDT");
            String dC=rs.getString("Diachi");
            String nS=rs.getString("ngaytao");
            String nT=rs.getString("ngayhethan");
            String nHH=rs.getString("ngaysinh");
            String sex=rs.getString("gioitinh");
            TheSV tSV=new TheSV(id,n,sdt,dC,nT,nHH,nS,sex);
            TenSV.setText(n);
            ds.add(tSV);
            tbView.setItems(ds);
            for (TheSV sv:
                    ds) {
                System.out.println(sv.getName());
            }
        }catch (Exception e){

        }
    }
    public void Thoat(){
        Platform.exit();
    }
}
