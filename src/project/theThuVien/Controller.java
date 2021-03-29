package project.theThuVien;

import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import project.Quanly.TheSV;

import java.net.URL;
import java.util.ResourceBundle;

public class Controller implements Initializable {
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        tbTen.setCellValueFactory(new PropertyValueFactory<TheSV,String>("name"));
        tbSdt.setCellValueFactory(new PropertyValueFactory<TheSV,String>("sdt"));
        tbDiachi.setCellValueFactory(new PropertyValueFactory<TheSV,String>("Diachi"));
        tbNT.setCellValueFactory(new PropertyValueFactory<TheSV,String>("NgayTao"));
        tbNH.setCellValueFactory(new PropertyValueFactory<TheSV,String>("NgayHH"));
        tbNS.setCellValueFactory(new PropertyValueFactory<TheSV,String>("NgaySinh"));
        tbSex.setCellValueFactory(new PropertyValueFactory<TheSV,String>("Sex"));
    }

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
    public TableColumn<TheSV,String> MaThe;
    public TableColumn<TheSV,String> tbTen;
    public TableColumn<TheSV,String> tbSex;
    public TableColumn<TheSV,String> tbSdt;
    public TableColumn<TheSV,String> tbNS;
    public TableColumn<TheSV,String> tbNT;
    public TableColumn<TheSV,String> tbNH;
    public TableColumn<TheSV,String> tbDiachi;

    public void Them(){
        String n=TenSV.getText();
        String sdt=Sdt.getText();
        String dC=Diachi.getText();
        String nS=NgaySinh.getPromptText();
        String nT=NgayTao.getPromptText();
        String nHH=NgayHH.getPromptText();
        String sex=Sex.getText();
        if (n!=null && sdt!=null && dC!=null && nS!=null && nT!=null && nHH!=null && sex!=null){
            TheSV tSV=new TheSV(n,sdt,dC,nT,nHH,nS,sex);
            ds.add(tSV);
            tbView.setItems(ds);
        }
    }
    public void Thoat(){
        Platform.exit();
    }
}
