/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package GUI.Panel.ThongKe;

import DAO.ThongKeDAO;
import DTO.ThongKe.ThongKeKhachHangDTO;
import GUI.Component.ButtonCustom;
import GUI.Component.InputDate;
import GUI.Component.InputForm;
import GUI.Component.PanelBorderRadius;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.util.ArrayList;
import java.util.HashMap;
import javax.swing.BoxLayout;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import java.sql.Date;
import java.util.Map;

/**
 *
 * @author andin
 */
public class ThongKeKhachHang extends JPanel{
    PanelBorderRadius nhapxuat_left, nhapxuat_center;
    JTable tblKH;
    JScrollPane scrollTblTonKho;
    DefaultTableModel tblModel;
    InputForm tensanpham;
    InputDate start_date, end_date;
    ButtonCustom export;
    Date start = new Date(123, 4, 1);
    long mil = System.currentTimeMillis();
    Date end = new Date(mil);
    HashMap<Integer,ArrayList<ThongKeKhachHangDTO>>list = ThongKeDAO.getInstance().getThongKeKhachHang(start, end);

    public ThongKeKhachHang() {
        initComponent();
        loadDataTable(list);
    }

    public void initComponent() {
        this.setLayout(new BorderLayout(10, 10));
        this.setOpaque(false);
        this.setBorder(new EmptyBorder(10, 10, 10, 10));
        nhapxuat_left = new PanelBorderRadius();
        nhapxuat_left.setPreferredSize(new Dimension(300, 100));
        nhapxuat_left.setLayout(new BorderLayout());
        nhapxuat_left.setBorder(new EmptyBorder(0, 0, 0, 5));
        JPanel left_content = new JPanel(new GridLayout(4, 1));
        left_content.setPreferredSize(new Dimension(300, 360));
        nhapxuat_left.add(left_content, BorderLayout.NORTH);

        tensanpham = new InputForm("Tìm kiếm khách hàng");
        tensanpham.getTxtForm().putClientProperty("JTextField.showClearButton", true);
        start_date = new InputDate("Từ ngày");
        end_date = new InputDate("Đến ngày");
        JPanel btn_layout = new JPanel(new BorderLayout());
        btn_layout.setPreferredSize(new Dimension(30, 36));
        btn_layout.setBorder(new EmptyBorder(20, 10, 0, 10));
        btn_layout.setBackground(Color.white);
        export = new ButtonCustom("Xuất Excel", "excel", 14);
        btn_layout.add(export, BorderLayout.NORTH);

        left_content.add(tensanpham);
        left_content.add(start_date);
        left_content.add(end_date);
        left_content.add(btn_layout);

        nhapxuat_center = new PanelBorderRadius();
        BoxLayout boxly = new BoxLayout(nhapxuat_center, BoxLayout.Y_AXIS);
        nhapxuat_center.setLayout(boxly);

        tblKH = new JTable();
        scrollTblTonKho = new JScrollPane();
        tblModel = new DefaultTableModel();
        String[] header = new String[]{"STT", "Mã khách hàng", "Tên khách hàng", "Số lượng phiếu","Tổng số tiền"};
        tblModel.setColumnIdentifiers(header);
        tblKH.setModel(tblModel);
        tblKH.setAutoCreateRowSorter(true);
        tblKH.setDefaultEditor(Object.class, null);
        scrollTblTonKho.setViewportView(tblKH);
        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment(JLabel.CENTER);
        tblKH.setDefaultRenderer(Object.class, centerRenderer);
        tblKH.setFocusable(false);
        tblKH.getColumnModel().getColumn(0).setPreferredWidth(10);
        tblKH.getColumnModel().getColumn(1).setPreferredWidth(50);
        tblKH.getColumnModel().getColumn(2).setPreferredWidth(200);
        nhapxuat_center.add(scrollTblTonKho);

        this.add(nhapxuat_left, BorderLayout.WEST);
        this.add(nhapxuat_center, BorderLayout.CENTER);
    }
    
public void loadDataTable(HashMap<Integer,ArrayList<ThongKeKhachHangDTO>> result) {
        tblModel.setRowCount(0);
        int k=1;
        for ( Map.Entry<Integer, ArrayList<ThongKeKhachHangDTO>> i : result.entrySet()){
            for(int j=0;j<i.getValue().size();j++){
                tblModel.addRow(new Object[]{
                    k,i.getValue().get(j).getMakh(),i.getValue().get(j).getTenkh(),i.getValue().get(j).getSoluongphieu(),i.getValue().get(j).getTongtien()
                });
                k++;
            }
        }
    }
}