package GUI.Panel;

import BUS.PhienBanSanPhamBUS;
import BUS.DungLuongRamBUS;
import BUS.DungLuongRomBUS;
import BUS.MauSacBUS;
import BUS.NhaCungCapBUS;
import BUS.SanPhamBUS;
import DAO.ChiTietSanPhamDAO;
import DTO.ChiTietSanPhamDTO;
import DTO.PhienBanSanPhamDTO;
import DTO.SanPhamDTO;
import GUI.Component.ButtonCustom;
import GUI.Component.InputForm;
import GUI.Main;
import java.awt.*;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import GUI.Component.PanelBorderRadius;
import GUI.Component.SelectForm;
import com.formdev.flatlaf.extras.FlatSVGIcon;
import com.formdev.flatlaf.fonts.roboto.FlatRobotoFont;
import helper.Formater;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import javax.swing.event.AncestorListener;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import org.jdesktop.swingx.autocomplete.AutoCompleteDecorator;

public class TaoPhieuXuat extends JPanel {
    PhienBanSanPhamBUS phienBanBus = new PhienBanSanPhamBUS();
    DungLuongRamBUS ramBus = new DungLuongRamBUS();
    DungLuongRomBUS romBus = new DungLuongRomBUS();
    MauSacBUS mausacBus = new MauSacBUS();
    PanelBorderRadius right, left;
    JPanel pnlBorder1, pnlBorder2, pnlBorder3, pnlBorder4, contentCenter, left_top, main;
    JTable tablePhieuNhap, tableSanPham;
    JScrollPane scrollTablePhieuNhap, scrollTableSanPham;
    DefaultTableModel tblModel, tblModelSP;
    NhapKho nhapKho;
    ButtonCustom btnAddSp, btnEditSP, btnDelete, btnImport, btnNhapHang;
    InputForm txtMaphieu, txtNhanVien, txtMaSp, txtTenSp;
    SelectForm cbxNhaCungCap, cbxTrangThai, cbxPhienBan;
    JTextField txtTimKiem;
    Color BackgroundColor = new Color(240, 247, 250);

    ArrayList<ChiTietSanPhamDTO> ctpb;
    SanPhamBUS spBUS = new SanPhamBUS();
    NhaCungCapBUS nccBus = new NhaCungCapBUS();
    ArrayList<DTO.SanPhamDTO> listSP = spBUS.getAll();
    private JTextArea textAreaImei;
    private JLabel labelImei;
    private JPanel content_right_bottom_top;
    private JPanel content_right_bottom_bottom;
    private JComboBox cbxImei;
    private ArrayList<PhienBanSanPhamDTO> ch;

    public TaoPhieuXuat() {
        initComponent();
        loadDataTalbeSanPham(listSP);
    }

    public void initPadding() {
        pnlBorder1 = new JPanel();
        pnlBorder1.setPreferredSize(new Dimension(0, 5));
        pnlBorder1.setBackground(BackgroundColor);
        this.add(pnlBorder1, BorderLayout.NORTH);

        pnlBorder2 = new JPanel();
        pnlBorder2.setPreferredSize(new Dimension(0, 5));
        pnlBorder2.setBackground(BackgroundColor);
        this.add(pnlBorder2, BorderLayout.SOUTH);

        pnlBorder3 = new JPanel();
        pnlBorder3.setPreferredSize(new Dimension(5, 0));
        pnlBorder3.setBackground(BackgroundColor);
        this.add(pnlBorder3, BorderLayout.EAST);

        pnlBorder4 = new JPanel();
        pnlBorder4.setPreferredSize(new Dimension(5, 0));
        pnlBorder4.setBackground(BackgroundColor);
        this.add(pnlBorder4, BorderLayout.WEST);
    }

    private void initComponent() {
        this.setBackground(BackgroundColor);
        this.setLayout(new BorderLayout(0, 0));
        this.setOpaque(true);

        // Phiếu nhập
        tablePhieuNhap = new JTable();
        scrollTablePhieuNhap = new JScrollPane();
        tblModel = new DefaultTableModel();
        String[] header = new String[]{"STT", "Mã sản phẩm", "Tên sản phẩm", "RAM", "ROM", "Màu sắc", "Đơn giá", "Số lượng"};
        tblModel.setColumnIdentifiers(header);
        tablePhieuNhap.setModel(tblModel);
        scrollTablePhieuNhap.setViewportView(tablePhieuNhap);
        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment(JLabel.CENTER);
        tablePhieuNhap.getColumnModel().getColumn(0).setCellRenderer(centerRenderer);
        tablePhieuNhap.getColumnModel().getColumn(1).setCellRenderer(centerRenderer);
        tablePhieuNhap.getColumnModel().getColumn(2).setCellRenderer(centerRenderer);
        scrollTablePhieuNhap.setViewportView(tablePhieuNhap);

        // Table sản phẩm
        tableSanPham = new JTable();
        scrollTableSanPham = new JScrollPane();
        tblModelSP = new DefaultTableModel();
        String[] headerSP = new String[]{"Mã SP", "Tên sản phẩm"};
        tblModelSP.setColumnIdentifiers(headerSP);
        tableSanPham.setModel(tblModelSP);
        scrollTableSanPham.setViewportView(tableSanPham);
        tableSanPham.getColumnModel().getColumn(0).setCellRenderer(centerRenderer);
        tableSanPham.getColumnModel().getColumn(1).setPreferredWidth(300);

        scrollTableSanPham.setViewportView(tableSanPham);

        initPadding();

        contentCenter = new JPanel();
        contentCenter.setPreferredSize(new Dimension(1100, 600));
        contentCenter.setBackground(BackgroundColor);
        contentCenter.setLayout(new BorderLayout(5, 5));
        this.add(contentCenter, BorderLayout.CENTER);

        left = new PanelBorderRadius();
        left.setLayout(new BorderLayout(0, 5));
        left.setBackground(Color.white);

        left_top = new JPanel(); // Chứa tất cả phần ở phía trái trên cùng
        left_top.setLayout(new BorderLayout());
        left_top.setBorder(new EmptyBorder(5, 5, 10, 10));
        left_top.setOpaque(false);

        JPanel content_top, content_btn, content_left, content_right, content_right_top, content_right_bottom;
        content_top = new JPanel(new GridLayout(1, 2, 5, 5));
        content_top.setOpaque(false);
        content_left = new JPanel(new BorderLayout(5, 5));
        content_left.setOpaque(false);
        content_left.setPreferredSize(new Dimension(0, 300));

        txtTimKiem = new JTextField();
        txtTimKiem.putClientProperty("JTextField.placeholderText", "Tên sản phẩm, mã sản phẩm...");
        txtTimKiem.putClientProperty("JTextField.showClearButton", true);
        txtTimKiem.putClientProperty("JTextField.leadingIcon", new FlatSVGIcon("./icon/search.svg"));

        txtTimKiem.setPreferredSize(new Dimension(100, 40));
        content_left.add(txtTimKiem, BorderLayout.NORTH);
        content_left.add(scrollTableSanPham, BorderLayout.CENTER);

        content_right = new JPanel(new BorderLayout(5, 5));
        content_right.setOpaque(false);
        content_right.setBackground(Color.WHITE);

        content_right_top = new JPanel(new BorderLayout());
        content_right_top.setPreferredSize(new Dimension(100, 165));
        txtMaSp = new InputForm("Mã sản phẩm");
        txtMaSp.setEditable(false);
        txtTenSp = new InputForm("Tên sản phẩm");
        txtTenSp.setEditable(false);
        String[] arrCauhinh = {"Chọn sản phẩm"};
        cbxPhienBan = new SelectForm("Cấu hình", arrCauhinh);
        cbxPhienBan.setPreferredSize(new Dimension(100, 90));
        content_right_top.add(txtMaSp, BorderLayout.WEST);
        content_right_top.add(txtTenSp, BorderLayout.CENTER);
        content_right_top.add(cbxPhienBan, BorderLayout.SOUTH);
        
        cbxPhienBan.getCbb().addItemListener(new ItemListener(){
            @Override
            public void itemStateChanged(ItemEvent e) {
                int pb = ch.get(cbxPhienBan.getSelectedIndex()).getMaphienbansp();
                setImeiByPb(pb);
            }
        });
        

        content_right_bottom = new JPanel(new BorderLayout());
        content_right_bottom.setBorder(new EmptyBorder(0, 10, 10, 10));
        content_right_bottom.setBackground(Color.WHITE);
        labelImei = new JLabel("Mã Imei");
        labelImei.setPreferredSize(new Dimension(0,30));
        textAreaImei = new JTextArea();
        textAreaImei.setBorder(BorderFactory.createLineBorder(new Color(204, 204, 204)));
        content_right_bottom_top = new JPanel(new BorderLayout());
        content_right_bottom_top.setSize(new Dimension(0,100));
        content_right_bottom_top.setBackground(Color.white);
        content_right_bottom_top.add(labelImei, BorderLayout.NORTH);
        content_right_bottom_top.add(textAreaImei,BorderLayout.CENTER);
        content_right_bottom_bottom = new JPanel(new BorderLayout());
        content_right_bottom_bottom.setSize(new Dimension(0,50));
        content_right_bottom_bottom.setBorder(new EmptyBorder(20, 0, 0, 0));
        JLabel labelImei = new JLabel("Chọn IMEI");
        labelImei.setPreferredSize(new Dimension(80,0));
        cbxImei = new JComboBox();
        String[] comString = {"Chọn sản phẩm"};
        cbxImei.setModel(new DefaultComboBoxModel(comString));
        AutoCompleteDecorator.decorate(cbxImei);
        content_right_bottom_bottom.setBackground(Color.white);
        content_right_bottom_bottom.add(labelImei,BorderLayout.WEST);
        content_right_bottom_bottom.add(cbxImei,BorderLayout.CENTER);
        content_right_bottom.add(content_right_bottom_top,BorderLayout.CENTER);
        content_right_bottom.add(content_right_bottom_bottom,BorderLayout.SOUTH);
        
        
        content_right.add(content_right_top, BorderLayout.NORTH);
        content_right.add(content_right_bottom, BorderLayout.CENTER);

        content_top.add(content_left);
        content_top.add(content_right);

        content_btn = new JPanel();
        content_btn.setPreferredSize(new Dimension(0, 47));
        content_btn.setLayout(new GridLayout(1, 4, 5, 5));
        content_btn.setBorder(new EmptyBorder(8, 5, 0, 10));
        content_btn.setOpaque(false);
        btnAddSp = new ButtonCustom("Thêm sản phẩm", "success", 14);
        btnEditSP = new ButtonCustom("Sửa sản phẩm", "warning", 14);
        btnDelete = new ButtonCustom("Xoá sản phẩm", "danger", 14);
        btnImport = new ButtonCustom("Nhập Excel", "excel", 14);
        content_btn.add(btnAddSp);
        content_btn.add(btnEditSP);
        content_btn.add(btnDelete);
        content_btn.add(btnImport);

        left_top.add(content_top, BorderLayout.CENTER);
        left_top.add(content_btn, BorderLayout.SOUTH);

        main = new JPanel();
        main.setOpaque(false);
        main.setPreferredSize(new Dimension(0, 280));
        main.setBorder(new EmptyBorder(0, 5, 10, 10));
        BoxLayout boxly = new BoxLayout(main, BoxLayout.Y_AXIS);
        main.setLayout(boxly);
        main.add(scrollTablePhieuNhap);
        left.add(left_top, BorderLayout.CENTER);
        left.add(main, BorderLayout.SOUTH);

        right = new PanelBorderRadius();
        right.setPreferredSize(new Dimension(320, 0));
        right.setBorder(new EmptyBorder(5, 5, 5, 5));
        right.setLayout(new BorderLayout());

        JPanel right_top, right_center, right_bottom, pn_tongtien;
        right_top = new JPanel(new GridLayout(4, 1, 0, 0));
        right_top.setPreferredSize(new Dimension(300, 360));
        txtMaphieu = new InputForm("Mã phiếu nhập");
        txtNhanVien = new InputForm("Nhân viên nhập");
        cbxNhaCungCap = new SelectForm("Nhà cung cấp", nccBus.getArrTenNhaCungCap());
        String[] arrTrangThai = {"Đang xử lý", "Đã nhập", "Huỷ"};
        cbxTrangThai = new SelectForm("Trạng thái", arrTrangThai);
        right_top.add(txtMaphieu);
        right_top.add(txtNhanVien);
        right_top.add(cbxNhaCungCap);
        right_top.add(cbxTrangThai);

        right_center = new JPanel();
        right_center.setPreferredSize(new Dimension(100, 100));
        right_center.setOpaque(false);

        right_bottom = new JPanel(new GridLayout(2, 1));
        right_bottom.setPreferredSize(new Dimension(300, 100));
        right_bottom.setBorder(new EmptyBorder(10, 10, 10, 10));
        right_bottom.setOpaque(false);

        pn_tongtien = new JPanel(new FlowLayout(1, 20, 0));
        pn_tongtien.setOpaque(false);
        JLabel lbltien = new JLabel("TỔNG TIỀN: ");
        lbltien.setFont(new Font(FlatRobotoFont.FAMILY, 1, 18));
        JLabel lbltongtien = new JLabel("10.000.000đ");
        lbltongtien.setFont(new Font(FlatRobotoFont.FAMILY, 1, 18));
        lbltien.setForeground(new Color(255, 51, 51));
        pn_tongtien.add(lbltien);
        pn_tongtien.add(lbltongtien);
        
        tableSanPham.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                int index = tableSanPham.getSelectedRow();
                if (index != -1) {
                    setInfoSanPham(listSP.get(index));
                }
            }
        });

        btnNhapHang = new ButtonCustom("Nhập hàng", "excel", 14);
        right_bottom.add(pn_tongtien);
        right_bottom.add(btnNhapHang);

        right.add(right_top, BorderLayout.NORTH);
        right.add(right_center, BorderLayout.CENTER);
        right.add(right_bottom, BorderLayout.SOUTH);

        contentCenter.add(left, BorderLayout.CENTER);
        contentCenter.add(right, BorderLayout.EAST);
    }

    public void loadDataTalbeSanPham(ArrayList<DTO.SanPhamDTO> result) {
        tblModelSP.setRowCount(0);
        for (DTO.SanPhamDTO sp : result) {
            tblModelSP.addRow(new Object[]{sp.getMasp(), sp.getTensp()});
        }
    }
    
    public void setInfoSanPham(SanPhamDTO sp) {
        this.txtMaSp.setText(Integer.toString(sp.getMasp()));
        this.txtTenSp.setText(sp.getTensp());
        ch = phienBanBus.getAll(sp.getMasp());
        int size = ch.size();
        String[] arr = new String[size];
        for (int i = 0; i < size; i++) {
            arr[i] = romBus.getKichThuocById(ch.get(i).getRom()) + "GB - "
                    + ramBus.getKichThuocById(ch.get(i).getRam()) + "GB - " + mausacBus.getTenMau(ch.get(i).getMausac())+" - "+Formater.FormatVND(ch.get(i).getGiaxuat());
        }
        this.cbxPhienBan.setArr(arr);
        
        int mapb = ch.get(0).getMaphienbansp();
        setImeiByPb(mapb);
    }
    
    public void setImeiByPb(int mapb){
        ctpb = ChiTietSanPhamDAO.getInstance().selectAllbyPb(mapb+"");
        String[] arrImei = new String[ctpb.size()];
        for (int i = 0; i< ctpb.size();i++){
            arrImei[i] = ctpb.get(i).getImei();
        }
        cbxImei.setModel(new DefaultComboBoxModel(arrImei));
    }
}
