package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import config.JDBCUtil;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import DTO.DanhMucSanPhamDTO;

public class DanhMucSanPhamDAO implements DAOinterface<DanhMucSanPhamDTO> {

    public static DanhMucSanPhamDAO getInstance() {
        return new DanhMucSanPhamDAO();
    }

    @Override
    public int insert(DanhMucSanPhamDTO t) {
        int result = 0;
        try {
            Connection con = (Connection) JDBCUtil.getConnection();
            String sql = "INSERT INTO `danhmucsanpham`(`madmcsp`, `tendmsp`, `hinhanh`, `xuatxu`, `chipxuly`, `dungluongpin`, `kichthuocman`, `hedieuhanh`, `phienbanhdh`, `camerasau`, `cameratruoc`, `thoigianbaohanh`, `thuonghieu`, `khuvuckho`) VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
            PreparedStatement pst = (PreparedStatement) con.prepareStatement(sql);
            pst.setInt(1, t.getMadanhmuc());
            pst.setString(2, t.getTendanhmuc());
            pst.setString(3, t.getHinhanh());
            pst.setString(4, t.getXuatxu());
            pst.setString(5, t.getChipxuly());
            pst.setInt(6, t.getDungluongpin());
            pst.setDouble(7, t.getKichthuocman());
            pst.setString(8, t.getHedieuhanh());
            pst.setInt(9, t.getPhienbanhdh());
            pst.setString(10, t.getCamerasau());
            pst.setString(11, t.getCameratruoc());
            pst.setInt(12, t.getThoigianbaohanh());
            pst.setInt(13, t.getThuonghieu());
            pst.setInt(14, t.getKhuvuckho());
            result = pst.executeUpdate();
            JDBCUtil.closeConnection(con);
        } catch (SQLException ex) {
            Logger.getLogger(DanhMucSanPhamDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return result;
    }

    @Override
    public int update(DanhMucSanPhamDTO t) {
        int result = 0;
        try {
            Connection con = (Connection) JDBCUtil.getConnection();
            String sql = "UPDATE `danhmucsanpham` SET `tendmsp`=?,`hinhanh`=?,`xuatxu`=?,`chipxuly`=?,`dungluongpin`=?,`kichthuocman`=?,`hedieuhanh`=?,`phienbanhdh`=?,`camerasau`=?,`cameratruoc`=?,`thoigianbaohanh`=?,`thuonghieu`=?,`khuvuckho`=? WHERE `madmcsp`=?";
            PreparedStatement pst = (PreparedStatement) con.prepareStatement(sql);
            pst.setString(1, t.getTendanhmuc());
            pst.setString(2, t.getHinhanh());
            pst.setString(3, t.getXuatxu());
            pst.setString(4, t.getChipxuly());
            pst.setInt(5, t.getDungluongpin());
            pst.setDouble(6, t.getKichthuocman());
            pst.setString(7, t.getHedieuhanh());
            pst.setInt(8, t.getPhienbanhdh());
            pst.setString(9, t.getCamerasau());
            pst.setString(100, t.getCameratruoc());
            pst.setInt(11, t.getThoigianbaohanh());
            pst.setInt(12, t.getThuonghieu());
            pst.setInt(13, t.getKhuvuckho());
            pst.setInt(14, t.getMadanhmuc());
            result = pst.executeUpdate();
            JDBCUtil.closeConnection(con);
        } catch (SQLException ex) {
            Logger.getLogger(DanhMucSanPhamDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return result;
    }

    @Override
    public int delete(String t) {
        int result = 0;
        try {
            Connection con = (Connection) JDBCUtil.getConnection();
            String sql = "UPDATE `danhmucsanpham` SET `trangthai`=0 WHERE madmcsp = ?";
            PreparedStatement pst = (PreparedStatement) con.prepareStatement(sql);
            pst.setString(1, t);
            result = pst.executeUpdate();
            JDBCUtil.closeConnection(con);
        } catch (SQLException ex) {
            Logger.getLogger(DanhMucSanPhamDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return result;
    }

    @Override
    public ArrayList<DanhMucSanPhamDTO> selectAll() {
        ArrayList<DanhMucSanPhamDTO> result = new ArrayList<DanhMucSanPhamDTO>();
        try {
            Connection con = (Connection) JDBCUtil.getConnection();
            String sql = "SELECT * FROM danhmucsanpham WHERE `trangthai`= 1";
            PreparedStatement pst = (PreparedStatement) con.prepareStatement(sql);
            ResultSet rs = (ResultSet) pst.executeQuery();
            while (rs.next()) {
                int madm = rs.getInt("madmsp");
                String tendm = rs.getString("tendmsp");
                String hinhanh = rs.getString("hinhanh");
                String xuatxu = rs.getString("xuatxu");
                String chipxuly = rs.getString("chipxuly");
                int dungluongpin = rs.getInt("dungluongpin");
                double kichthuocman = rs.getDouble("kichthuocman");
                String hedieuhanh = rs.getString("hedieuhanh");
                int phienbanhdh = rs.getInt("phienbanhdh");
                String camerasau = rs.getString("camerasau");
                String cameratruoc = rs.getString("cameratruoc");
                int thoigianbaohanh = rs.getInt("thoigianbaohanh");
                int thuonghieu = rs.getInt("thuonghieu");
                int khuvuckho = rs.getInt("khuvuckho");
                int soluongton = rs.getInt("soluongton");
                DanhMucSanPhamDTO sp = new DanhMucSanPhamDTO(madm, tendm, hinhanh, xuatxu, chipxuly, dungluongpin, kichthuocman, hedieuhanh, phienbanhdh, camerasau, cameratruoc, thoigianbaohanh, thuonghieu, khuvuckho, soluongton);
                result.add(sp);
            }
            JDBCUtil.closeConnection(con);
        } catch (Exception e) {
        }
        return result;
    }

    @Override
    public DanhMucSanPhamDTO selectById(String t) {
        DanhMucSanPhamDTO result = null;
        try {
            Connection con = (Connection) JDBCUtil.getConnection();
            String sql = "SELECT * FROM danhmucsanpham WHERE masanpham='?'";
            PreparedStatement pst = (PreparedStatement) con.prepareStatement(sql);
            pst.setString(1, t);
            ResultSet rs = (ResultSet) pst.executeQuery();
            while (rs.next()) {
                int madm = rs.getInt("madmsp");
                String tendm = rs.getString("tendmsp");
                String hinhanh = rs.getString("hinhanh");
                String xuatxu = rs.getString("xuatxu");
                String chipxuly = rs.getString("chipxuly");
                int dungluongpin = rs.getInt("dungluongpin");
                double kichthuocman = rs.getDouble("kichthuocman");
                String hedieuhanh = rs.getString("hedieuhanh");
                int phienbanhdh = rs.getInt("phienbanhdh");
                String camerasau = rs.getString("camerasau");
                String cameratruoc = rs.getString("cameratruoc");
                int thoigianbaohanh = rs.getInt("thoigianbaohanh");
                int thuonghieu = rs.getInt("thuonghieu");
                int khuvuckho = rs.getInt("khuvuckho");
                int soluongton = rs.getInt("soluongton");
                result = new DanhMucSanPhamDTO(madm, tendm, hinhanh, xuatxu, chipxuly, dungluongpin, kichthuocman, hedieuhanh, phienbanhdh, camerasau, cameratruoc, thoigianbaohanh, thuonghieu, khuvuckho, soluongton);
            }
            JDBCUtil.closeConnection(con);
        } catch (SQLException e) {
        }
        return result;
    }

    @Override
    public int getAutoIncrement() {
        int result = -1;
        try {
            Connection con = (Connection) JDBCUtil.getConnection();
            String sql = "SELECT `AUTO_INCREMENT` FROM  INFORMATION_SCHEMA.TABLES WHERE TABLE_SCHEMA = 'quanlikhohang' AND   TABLE_NAME   = 'danhmucsanpham'";
            PreparedStatement pst = (PreparedStatement) con.prepareStatement(sql);
            ResultSet rs2 = pst.executeQuery(sql);
            if (!rs2.isBeforeFirst()) {
                System.out.println("No data");
            } else {
                while (rs2.next()) {
                    result = rs2.getInt("AUTO_INCREMENT");

                }
            }
        } catch (SQLException ex) {
            Logger.getLogger(TaiKhoanDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return result;
    }

}