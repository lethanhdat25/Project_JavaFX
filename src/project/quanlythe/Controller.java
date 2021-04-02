package project.quanlythe;

import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import project.Quanly.TheSV;
import project.sql.ModelTheSV;

import java.net.URL;
import java.time.LocalDate;
import java.util.ResourceBundle;

public class Controller implements Initializable {
    public static Integer indentity;
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
            ModelTheSV modelTheSV=new ModelTheSV();
            ds.addAll(modelTheSV.getList());
            indentity=ds.size();
            tbView.setItems(ds);
        }catch (Exception e){

        }
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
    ObservableList<TheSV> dsTim= FXCollections.observableArrayList();
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
        String n=TenSV.getText();
        String sdt=Sdt.getText();
        String dC=Diachi.getText();
        LocalDate nS=NgaySinh.getValue();
        LocalDate nT=NgayTao.getValue();
        LocalDate nHH=NgayHH.getValue();
        String sex=Sex.getText();
        if (n!=null && sdt!=null && dC!=null && nS!=null && nT!=null && nHH!=null && sex!=null){
            TheSV tSV=new TheSV(null,n,sdt,dC,nT.toString(),nHH.toString(),nS.toString(),sex);
            ds.add(tSV);
            tbView.setItems(ds);
        }
    }
    public void Xoa(ActionEvent actionEvent){
        TheSV layTen=tbView.getSelectionModel().getSelectedItem();
        TenSV.setText(layTen.getName());
        ModelTheSV modelTheSV=new ModelTheSV();
        if (modelTheSV.Xoa(layTen)){
            System.out.println("Da xoa thanh cong");
        }else{
            System.out.println("Xoa khong thanh cong");

        }
        tbView.getItems().remove(layTen);
    }
    public void Luu(){
        ModelTheSV modelTheSV=new ModelTheSV();
        for (TheSV sv:
                ds) {
            System.out.println(sv.getName());
            if(modelTheSV.Luu(sv.getMaThe(),sv.getName(),sv.getSdt(),sv.getDiachi(),sv.getNgayTao(),sv.getNgayHH(),sv.getNgaySinh(),sv.getSex())){
                System.out.println("them thanh cong");
            }else{
                System.out.println("Them that bai");
            }
        }
    }
    public void Tim(){
        dsTim.remove(0,dsTim.size());
        ModelTheSV modelTheSV=new ModelTheSV();
        dsTim.addAll(modelTheSV.Tim(Tim.getText()));
        tbView.setItems(dsTim);
    }
    public void Thoat(){
        Platform.exit();
    }
}
