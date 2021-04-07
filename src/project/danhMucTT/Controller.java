package project.danhMucTT;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import project.Main;
import project.Quanly.ThongTinSach;
import sach.ModelSach;
import java.net.URL;
import java.util.ResourceBundle;

public class Controller implements Initializable {
    public TextField Tensach;
    public TextField Theloai;
    public TextField Tinhtrang;
    public TextField Soluong;
    public TextField MaNXB;
    public TextField NamNXB;
    public TextField Search;
    public static ThongTinSach fix;
    public TableView<ThongTinSach> tbView;
    ObservableList<ThongTinSach> ds = FXCollections.observableArrayList();
    ObservableList<ThongTinSach> dsSearch = FXCollections.observableArrayList();
    public TableColumn<ThongTinSach,String> tbTS;
    public TableColumn<ThongTinSach,String> tbTL;
    public TableColumn<ThongTinSach,String> tbTT;
    public TableColumn<ThongTinSach,Integer> tbSL;
    public TableColumn<ThongTinSach,String> tbMNXB;
    public TableColumn<ThongTinSach,Integer> tbNNXB;


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        try {
            ModelSach modelSach = new ModelSach();
            ds.addAll(modelSach.getList());
            tbView.setItems(ds);
        }catch (Exception e){}
        tbTS.setCellValueFactory(new PropertyValueFactory<ThongTinSach,String>("Tensach"));
        tbTL.setCellValueFactory(new PropertyValueFactory<ThongTinSach,String>("Theloai"));
        tbTT.setCellValueFactory(new PropertyValueFactory<ThongTinSach,String>("Tinhtrang"));
        tbSL.setCellValueFactory(new PropertyValueFactory<ThongTinSach,Integer>("Soluong"));
        tbMNXB.setCellValueFactory(new PropertyValueFactory<ThongTinSach,String>("MaNXB"));
        tbNNXB.setCellValueFactory(new PropertyValueFactory<ThongTinSach,Integer>("NamNXB"));
    }
    public void add(){
        String ts = Tensach.getText();
        String tl = Theloai.getText();
        String tt = Tinhtrang.getText();
        Integer sl = Integer.parseInt(Soluong.getText());
        String mnxb = MaNXB.getText();
        Integer nnxb = Integer.parseInt(NamNXB.getText());

        if(!ts.isEmpty()){
            if(fix != null){
                fix.setTensach(ts);
                fix.setTheloai(tl);
                fix.setTinhtrang(tt);
                fix.setSoluong(sl);
                fix.setMaNXB(mnxb);
                fix.setNamNXB(nnxb);
                for(ThongTinSach ttts:ds){
                    if(ttts.getTensach()== fix.getTensach()){
                        ttts = fix;
                        break;
                    }
                }
                tbView.refresh();
            }else {
                ThongTinSach tts = new ThongTinSach(ts,tl,tt,sl,mnxb,nnxb);
                ds.add(tts);
                tbView.setItems(ds);
            }
        }
        fix = null;
        Tensach.setText("");
        Theloai.setText("");
        Tinhtrang.setText("");
        Soluong.setText("");
        MaNXB.setText("");
        NamNXB.setText("");
    }
    public void remove(){
        ThongTinSach layTen = tbView.getSelectionModel().getSelectedItem();
        Tensach.setText(layTen.getTensach());
        ModelSach modelSach = new ModelSach();
        if (modelSach.remove(layTen)){
            System.out.println("Đã xóa thành công");
        }
        tbView.getItems().remove(layTen);
    }
    public void save(){
        ModelSach modelSach = new ModelSach();
        for (ThongTinSach tts: ds) {
            System.out.println(tts.getTensach());
            if(modelSach.save(tts.getTensach(),tts.getTheloai(),tts.getTinhtrang(),tts.getSoluong(),tts.getMaNXB(),tts.getNamNXB())){
                System.out.println("Thêm thành công");
            }
        }
    }
    public void search(){
        dsSearch.remove(0,dsSearch.size());
        ModelSach modelSach = new ModelSach();
        dsSearch.addAll(modelSach.Search(Search.getText()));
        tbView.setItems(dsSearch);
    }
    public void fix(){
        ThongTinSach tts = tbView.getSelectionModel().getSelectedItem();
        Tensach.setText(tts.getTensach());
        Theloai.setText(tts.getTheloai());
        Tinhtrang.setText(tts.getTinhtrang());
        Soluong.setText(tts.getSoluong().toString());
        MaNXB.setText(tts.getMaNXB());
        NamNXB.setText(tts.getNamNXB().toString());
        fix = tts;
    }
    public void home()throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("../quanlythuvien/project.fxml"));
        Main.menuStage.setTitle("Hello World");
        Main.menuStage.setScene(new Scene(root, 1283, 813));
        Main.menuStage.show();
    }
}

