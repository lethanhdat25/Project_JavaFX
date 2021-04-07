package sach;

import java.util.ArrayList;

public interface DataAccessObject2<S> {
    ArrayList<S> getList();
    ArrayList<S> Search(String TenSach);
    boolean remove(S s);
    boolean save(String tenSach, String theLoai, String tinhTrang, Integer soLuong, String maNXB, Integer namNXB);
}
