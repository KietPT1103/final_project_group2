/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dal;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import model.Account;
import model.Cart;
import model.Product;
import model.Categories;
import model.Item;
import model.OrderDetail;

/**
 *
 * @author LENOVO
 */
public class DAO extends DBContext {

    public Account checkAccount(String userName, String password) {
        String sql = "SELECT [username]\n"
                + "      ,[password]\n"
                + "      ,[fullname]\n"
                + "      ,[phone]\n"
                + "      ,[picture]\n"
                + "      ,[created_at]\n"
                + "      ,[updated_at]\n"
                + "      ,[role]\n"
                + "      ,[Address]\n"
                + "  FROM [dbo].[account]"
                + "  WHERE username = ? and password = ?";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setString(1, userName);
            st.setString(2, password);
            ResultSet rs = st.executeQuery();
            if (rs.next()) {
                Account a = new Account(rs.getString("username"),
                        rs.getString("password"),
                        rs.getString("fullname"),
                        rs.getString("phone"),
                        rs.getString("address"),
                        rs.getString("picture"),
                        rs.getDate("created_at"),
                        rs.getDate("updated_at"),
                        rs.getInt("role"));
                return a;
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
        return null;
    }

    /**
     * Hàm lấy ra tất cả product trong database
     *
     * @return all product
     */
    public List<Product> getAll() {
        List<Product> list = new ArrayList<>();
        String sql = "select * from product";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                // Các tham số phải trùng tên với các cột trong bảng trong SQL, nếu không không tạo được
                Categories cate = getCateById(rs.getString(7));
                Product p = new Product(rs.getString(1),
                        rs.getString(2),
                        rs.getString(3),
                        rs.getLong(4),
                        rs.getInt(5),
                        rs.getString(6),
                        cate);
                list.add(p);
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
        return list;
    }

    /**
     * Hàm lấy ra Category theo id
     *
     * @param cate_id id truyền vào để tìm cate
     * @return Category theo id
     */
    public Categories getCateById(String cate_id) {
        String sql = "select * from categories where cate_id = ?";
        try {
            PreparedStatement st = connection.prepareStatement(sql);

            st.setString(1, cate_id);
            ResultSet rs = st.executeQuery();
            if (rs.next()) {
                // Các tham số phải trùng tên với các cột trong bảng trong SQL, nếu không không tạo được
                Categories c = new Categories(rs.getString(1), rs.getString(2), rs.getString(3));
                return c;
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
        return null;
    }

    /**
     * Hàm trả về một product dựa vào id
     *
     * @param id id do người dùng truyền vào
     * @return trả về một product có id trùng với id truyền vào
     */
    public Product getProductById(String id) {
        String sql = "select * from product where pro_id = ?";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setString(1, id);
            ResultSet rs = st.executeQuery();
            if (rs.next()) {
                Categories cate = getCateById(rs.getString(7));
                Product p = new Product(rs.getString(1),
                        rs.getString(2),
                        rs.getString(3),
                        rs.getLong(4),
                        rs.getInt(5),
                        rs.getString(6),
                        cate);
                return p;
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
        return null;
    }

    /**
     * Hàm lấy ra tất cả các product có cate id trùng nhau
     *
     * @param id id do người dùng nhập vào
     * @return list các product có cùng cate_id
     */
    public List<Product> getProductByCategoryId(String id) {
        List<Product> list = new ArrayList<>();
        String sql = "SELECT [pro_id]\n"
                + "      ,[pro_name]\n"
                + "      ,[pro_description]\n"
                + "      ,[pro_price]\n"
                + "      ,[pro_quantity]\n"
                + "      ,[pro_picture]\n"
                + "      ,[cate_id]\n"
                + "  FROM [dbo].[product]";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            if (!id.isEmpty() && !id.equals("")) {
                sql += "WHERE cate_id = ?";
                st.setString(1, id);
            }
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                Product p = new Product();
                p.setId(rs.getString("pro_id"));
                p.setName(rs.getString("pro_name"));
                p.setDescription(rs.getString("pro_description"));
                p.setPrice(rs.getLong("pro_price"));
                p.setQuantity(rs.getInt("pro_quantity"));
                p.setPicture(rs.getString("pro_picture"));
                Categories cate = getCateById(rs.getString("cate_id"));
                p.setCatergory(cate);
                list.add(p);
            }
        } catch (SQLException e) {
            System.out.print(e);
        }
        return list;
    }

    /**
     * Hàm lấy ra tất cả các product có cate id trùng nhau
     *
     * @param id id do người dùng nhập vào
     * @return list các product có cùng cate_id
     */
    public List<Product> getRandomProduct() {
        List<Product> list = new ArrayList<>();
        try {
            String sql = "SELECT TOP 4 * FROM [product] ORDER BY NEWID()";
            PreparedStatement st = connection.prepareStatement(sql);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                Product p = new Product();
                p.setId(rs.getString("pro_id"));
                p.setName(rs.getString("pro_name"));
                p.setDescription(rs.getString("pro_description"));
                p.setPrice(rs.getLong("pro_price"));
                p.setQuantity(rs.getInt("pro_quantity"));
                p.setPicture(rs.getString("pro_picture"));
                Categories cate = getCateById(rs.getString("cate_id"));
                p.setCatergory(cate);
                list.add(p);
            }
        } catch (SQLException e) {
            System.out.print(e);
        }
        return list;
    }

    /**
     * Hàm đếm tất cả các sản phẩm có tên trùng với từ khóa người dùng nhập vào
     *
     * Hàm dùng để đếm xem ta cần chia các sản phẩm kia ra bao nhiêu trang
     *
     * @param txtSearch Từ khóa do người dung nhập vào
     * @return trả về số lượng các sản phẩm được tìm thấy
     */
    public int countProduct(String txtSearch) {
        try {
            String sql = "select count(*) from product where pro_name like ?";
            PreparedStatement st = connection.prepareStatement(sql);
            st.setString(1, "%" + txtSearch + "%");
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                return rs.getInt(1);
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
        return 0;
    }

    /**
     * Select ra một bảng ảo chứa số lượng sản phẩm muốn lấy ra, chứa một cột ảo
     * x chứa số thứ tự ảo của các prouct vừa select ra. Sau đó chọn ra các sản
     * phẩm trong khoảng mong muốn rồi luu nó vào list. trả về list đó
     *
     * Hàm dùng để lấy ra các sản phẩm có thứ tự theo khoảng nhất định để in ra
     * màn hình product
     *
     * @param txtSearch Từ khóa do người dùng nhập vào
     * @param index
     * @param size
     * @return list chứa số lượng sản phẩm ta muốn
     */
    public List<Product> search(String txtSearch, int index, int size) {
        List<Product> list = new ArrayList<>();
        try {
            String sql = "with x as(select ROW_NUMBER() over (order by pro_id) as r\n"
                    + ",* from product where pro_name like ?)\n"
                    + "select * from x where r between ? * 8-7 and ? * 8";
            PreparedStatement st = connection.prepareStatement(sql);
            st.setString(1, "%" + txtSearch + "%");
            st.setInt(2, index);
            st.setInt(3, index);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                // Các tham số phải trùng tên với các cột trong bảng trong SQL, nếu không không tạo được
                Categories cate = getCateById(rs.getString(8));
                Product p = new Product(rs.getString(2),
                        rs.getString(3),
                        rs.getString(4),
                        rs.getLong(5),
                        rs.getInt(6),
                        rs.getString(7),
                        cate);
                list.add(p);
            }
        } catch (Exception e) {
        }
        return list;
    }

    /**
     * Select ra một bảng ảo chứa số lượng sản phẩm muốn lấy ra, chứa một cột ảo
     * x chứa số thứ tự ảo của các prouct vừa select ra. Sau đó chọn ra các sản
     * phẩm trong khoảng mong muốn rồi luu nó vào list. trả về list đó
     *
     * Hàm dùng để lấy ra các sản phẩm có thứ tự theo khoảng nhất định để in ra
     * màn hình product
     *
     * @param txtSearch Từ khóa do người dùng nhập vào
     * @param index
     * @param size
     * @return list chứa số lượng sản phẩm ta muốn
     */
    public List<Product> searchAsc(String txtSearch, int index, int size) {
        List<Product> list = new ArrayList<>();
        try {
            String sql = "with x as(select ROW_NUMBER() over (ORDER BY pro_price ASC) as r\n"
                    + ",* from product where pro_name like ?)\n"
                    + "select * from x where r between ? * 8-7 and ? * 8";
            PreparedStatement st = connection.prepareStatement(sql);
            st.setString(1, "%" + txtSearch + "%");
            st.setInt(2, index);
            st.setInt(3, index);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                // Các tham số phải trùng tên với các cột trong bảng trong SQL, nếu không không tạo được
                Categories cate = getCateById(rs.getString(8));
                Product p = new Product(rs.getString(2),
                        rs.getString(3),
                        rs.getString(4),
                        rs.getLong(5),
                        rs.getInt(6),
                        rs.getString(7),
                        cate);
                list.add(p);
            }
        } catch (Exception e) {
        }
        return list;
    }

    /**
     * Select ra một bảng ảo chứa số lượng sản phẩm muốn lấy ra, chứa một cột ảo
     * x chứa số thứ tự ảo của các prouct vừa select ra. Sau đó chọn ra các sản
     * phẩm trong khoảng mong muốn rồi luu nó vào list. trả về list đó
     *
     * Hàm dùng để lấy ra các sản phẩm có thứ tự theo khoảng nhất định để in ra
     * màn hình product
     *
     * @param txtSearch Từ khóa do người dùng nhập vào
     * @param index
     * @param size
     * @return list chứa số lượng sản phẩm ta muốn
     */
    public List<Product> searchDesc(String txtSearch, int index, int size) {
        List<Product> list = new ArrayList<>();
        try {
            String sql = "with x as(select ROW_NUMBER() over (ORDER BY pro_price Desc) as r\n"
                    + ",* from product where pro_name like ?)\n"
                    + "select * from x where r between ? * 8-7 and ? * 8";
            PreparedStatement st = connection.prepareStatement(sql);
            st.setString(1, "%" + txtSearch + "%");
            st.setInt(2, index);
            st.setInt(3, index);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                // Các tham số phải trùng tên với các cột trong bảng trong SQL, nếu không không tạo được
                Categories cate = getCateById(rs.getString(8));
                Product p = new Product(rs.getString(2),
                        rs.getString(3),
                        rs.getString(4),
                        rs.getLong(5),
                        rs.getInt(6),
                        rs.getString(7),
                        cate);
                list.add(p);
            }
        } catch (Exception e) {
        }
        return list;
    }

    /**
     * Hàm thêm đơn hàng người dùng đặt vào bảng cart, sau đó lưu lại từng
     * product người dùng dặt vào bảng cartdetail
     *
     * @param acc tài khoảng của người dùng
     * @param cart đơn hàng của người dùng, chứa các item(sản phẩm do người dùng
     * mua)
     */
    public void addOrder(Account acc, Cart cart) {
        try {
            //add order
            String sql = "INSERT INTO [dbo].[cart]\n"
                    + "           ([username]\n"
                    + "           ,[or_totalmoney])\n"
                    + "     VALUES (?,?)";
            PreparedStatement st = connection.prepareStatement(sql);
            st.setString(1, acc.getUserName());
            st.setDouble(2, cart.getTotalMoney());
            st.executeUpdate();

            //lấy order id vừa add
            String sql1 = "select top 1 or_id from [cart] order by or_id desc";
            PreparedStatement st1 = connection.prepareStatement(sql1);
            ResultSet rs = st1.executeQuery();

            //add dữ liệu vào bảng order detail
            if (rs.next()) {
                int oid = rs.getInt("or_id");
                for (Item i : cart.getItems()) {  //Lấy tất cả các item có trong cart hiện tại add dô database
                    String sql2 = "insert into [cartdetail] values (?,?,?,?)";
                    PreparedStatement st2 = connection.prepareStatement(sql2);
                    st2.setInt(1, oid); //Lấy id của 1 order
                    System.out.println("oid: " + oid);
                    st2.setString(2, i.getProduct().getId());  // lấy ra id của sản phẩm trong cart đó
                    System.out.println("pid: " + i.getProduct().getId());
                    st2.setInt(3, i.getQuantity()); //Lấy ra số lượng mua
                    System.out.println("quantity: " + i.getQuantity());
                    st2.setDouble(4, i.getPrice()); //Lấy ra tổng giá của sản phẩm mua đó
                    System.out.println("price: " + i.getPrice());
                    st2.executeUpdate();
                }
            }

            //Cập nhật lại số lượng sản phẩm còn lại trong kho
            String sql3 = "update product set pro_quantity = pro_quantity - ? where pro_id = ?";
            PreparedStatement st3 = connection.prepareStatement(sql3);
            for (Item i : cart.getItems()) {
                st3.setInt(1, i.getQuantity());
                st3.setString(2, i.getProduct().getId());
                st3.executeUpdate();
            }

        } catch (SQLException e) {
            System.out.println(e);
        }
    }

    /**
     * Hàm lấy ra các sản phẩm khách hàng đã mua trong cùng 1 giỏ hàng
     *
     * @param oid id của giỏ hàng đó
     * @return list chứa tất cả các mặt hàng khách mua
     */
    public List<OrderDetail> getOrderDetailByOrderId(int oid) {
        List<OrderDetail> list = null;
        try {
            String sql = "select * from cartdetail where oid = ?";
            PreparedStatement st = connection.prepareStatement(sql);
            st.setInt(1, oid);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                OrderDetail od = new OrderDetail(rs.getInt(1),
                        rs.getString(2),
                        rs.getInt(3),
                        rs.getLong(4));
                list.add(od);
            }
        } catch (Exception e) {
        }
        return list;
    }

//    public static void main(String[] args) {
//        DAO d = new DAO();
//        List<Product> list = d.getProductByCategoryId("");
//        for (Product product : list) {
//            System.out.println(product.getName());
//        }
//    }
}
