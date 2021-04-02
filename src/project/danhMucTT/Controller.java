package project.danhMucTT;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import project.Quanly.TheSV;
import project.Quanly.thongTinSach;

import java.net.URL;
import java.util.ResourceBundle;

public class Controller implements Initializable {

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        MS.setCellValueFactory(new PropertyValueFactory<thongTinSach,Integer>("MaSach"));
        TS.setCellValueFactory(new PropertyValueFactory<thongTinSach,String>("Tensach"));
        TL.setCellValueFactory(new PropertyValueFactory<thongTinSach,String>("Theloai"));
        TT.setCellValueFactory(new PropertyValueFactory<thongTinSach,String>("Tinhtrang"));
        SL.setCellValueFactory(new PropertyValueFactory<thongTinSach,Integer>("Soluong"));
        MNXB.setCellValueFactory(new PropertyValueFactory<thongTinSach,String>("MaNXB"));
        NNXB.setCellValueFactory(new PropertyValueFactory<thongTinSach,String>("NamNXB"));
    }
    public static Integer IdSach;
    public TextField Masach;
    public TextField Tensach;
    public TextField Theloai;
    public TextField Tinhtrang;
    public TextField Soluong;
    public TextField MaNXB;
    public TextField NamNXB;
    public TextField Search;
    ObservableList<thongTinSach> ds = FXCollections.observableArrayList();
    public TableView<thongTinSach> tbView;
    public TableColumn<thongTinSach,Integer> MS;
    public TableColumn<thongTinSach,String> TS;
    public TableColumn<thongTinSach,String> TL;
    public TableColumn<thongTinSach,String> TT;
    public TableColumn<thongTinSach,Integer> SL;
    public TableColumn<thongTinSach,String> MNXB;
    public TableColumn<thongTinSach,String> NNXB;

    public void add(){
        String ts = Tensach.getText();
        String tl = Theloai.getText();
        String tt = Tinhtrang.getText();
        Integer sl = Integer.parseInt(Soluong.getText()) ;
        String mnxb = MaNXB.getText();
        String nnxb = NNXB.getText();
        if (ts!=null && tl!=null && tt!=null && sl!=null && mnxb!=null && nnxb!=null ){
            thongTinSach tts = new thongTinSach(1,ts,tl,tt,sl,mnxb,nnxb);
            ds.add(tts);
            tbView.setItems(ds);
        }
    }
    public void remove(ActionEvent actionEvent){
        thongTinSach getName = tbView.getSelectionModel().getSelectedItem();
        ObservableList<thongTinSach> allThe;
        ObservableList<thongTinSach> SingleThe;
        allThe=tbView.getItems();
        SingleThe=tbView.getSelectionModel().getSelectedItems();
        SingleThe.forEach(allThe::remove);
    }
    public void save(){

    }
}

