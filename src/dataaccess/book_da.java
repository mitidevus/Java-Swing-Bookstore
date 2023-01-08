/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dataaccess;

import pojo.book;
import java.sql.*;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author TIN
 */
public class book_da {

    public List<book> getAll() {
        List<book> ans = null;
        try {
            ans = new ArrayList<>();
            Connection connection = MyConnection.create();
            Statement statement;
            statement = connection.createStatement();
            String query = "SELECT * FROM book, author, categories, publisher where book.author_id=author.id and book.category_id=categories.id and book.publisher_id = publisher.id order by book.id asc;";
            ResultSet rs = statement.executeQuery(query);
            while (rs.next()) {
                int id = rs.getInt("id");
                String name = rs.getString("name");
                double price = rs.getDouble("price");
                int quantity = rs.getInt("quantity");
                int author_id = rs.getInt("author_id");
                int publisher_id = rs.getInt("publisher_id");
                int category_id = rs.getInt("category_id");
                String category = rs.getString("categories.name");
                String publisher = rs.getString("publisher.name");
                String author = rs.getString("author.name");
                boolean isActive = rs.getBoolean("book.isActive");
                book st = new book(id, name, price, quantity, author_id, publisher_id, category_id, author, publisher, category, isActive);
                ans.add(st);
            }
            rs.close();
            statement.close();
        } catch (SQLException ex) {
            Logger.getLogger(book_da.class.getName()).log(Level.SEVERE, null, ex);
            ans = null;
        }
        return ans;
    }

    public List<book> getOutOfStock() {
        List<book> ans = null;
        try {
            ans = new ArrayList<>();
            Connection connection = MyConnection.create();
            Statement statement;
            statement = connection.createStatement();
            String query = "SELECT * FROM book, author, categories, publisher where book.author_id=author.id and book.category_id=categories.id and book.publisher_id = publisher.id and book.quantity = 0 order by book.id asc;";
            ResultSet rs = statement.executeQuery(query);
            while (rs.next()) {
                int id = rs.getInt("id");
                String name = rs.getString("name");
                double price = rs.getDouble("price");
                int quantity = rs.getInt("quantity");
                int author_id = rs.getInt("author_id");
                int publisher_id = rs.getInt("publisher_id");
                int category_id = rs.getInt("category_id");
                String category = rs.getString("categories.name");
                String publisher = rs.getString("publisher.name");
                String author = rs.getString("author.name");
                boolean isActive = rs.getBoolean("book.isActive");
                book st = new book(id, name, price, quantity, author_id, publisher_id, category_id, author, publisher, category, isActive);
                ans.add(st);
            }
            rs.close();
            statement.close();
        } catch (SQLException ex) {
            Logger.getLogger(book_da.class.getName()).log(Level.SEVERE, null, ex);
            ans = null;
        }
        return ans;
    }

    public boolean check_book_exists(String name) {
        Connection connection = null;
        Statement statement = null;
        PreparedStatement pr_statement = null;
        ResultSet rs = null;
        try {
            connection = MyConnection.create();
            statement = connection.createStatement();
            String query = "SELECT * FROM book where name =  ?";
            pr_statement = connection.prepareStatement(query);
            pr_statement.setString(1, name);
            rs = pr_statement.executeQuery();
            if (rs.next()) {
                return true;
            }

            return false;
        } catch (SQLException ex) {
            Logger.getLogger(UserDA.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                connection.close();
                statement.close();
                pr_statement.close();
                rs.close();
            } catch (SQLException ex) {
                Logger.getLogger(UserDA.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return false;
    }

    public int get_id_by_name(String name, String table) {
        Connection connection = null;
        Statement statement = null;
        PreparedStatement pr_statement = null;
        ResultSet rs = null;
        try {
            connection = MyConnection.create();
            statement = connection.createStatement();
            String query = "";

            if ("Category".equals(table)) {
                query = "SELECT * FROM categories where name = ?";
            } else if ("Author".equals(table)) {
                query = "SELECT * FROM author where name = ?";
            } else if ("Publisher".equals(table)) {
                query = "SELECT * FROM publisher where name = ?";
            } else {
                return -1;
            }
            pr_statement = connection.prepareStatement(query);
            pr_statement.setString(1, name);
            rs = pr_statement.executeQuery();
            while (rs.next()) {
                int result = rs.getInt("id");
                return result;
            }
            return -1;
        } catch (SQLException ex) {
            Logger.getLogger(UserDA.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                connection.close();
                statement.close();
                rs.close();
                pr_statement.close();
            } catch (SQLException ex) {
                Logger.getLogger(UserDA.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return -1;
    }

    public boolean add_book(String name, double price, int quant, int author, int publisher, int category) {
        Connection connection = null;
        Statement statement = null;
        PreparedStatement pr_statement = null;
        try {
            connection = MyConnection.create();
            statement = connection.createStatement();
            String query = "INSERT INTO book(name, price, quantity, author_id, publisher_id, category_id) VALUES(?, ?, ?, ?, ?, ?)";
            pr_statement = connection.prepareStatement(query);
            pr_statement.setString(1, name);
            pr_statement.setDouble(2, price);
            pr_statement.setInt(3, quant);
            pr_statement.setInt(4, author);
            pr_statement.setInt(5, publisher);
            pr_statement.setInt(6, category);
            int r = pr_statement.executeUpdate();
            return r > 0;
        } catch (SQLException ex) {
            Logger.getLogger(UserDA.class.getName()).log(Level.SEVERE, null, ex);

        } finally {
            try {
                connection.close();
                statement.close();
                pr_statement.close();
            } catch (SQLException ex) {
                Logger.getLogger(UserDA.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return false;
    }

    public void update_string(String name, int id) {
        Connection connection = null;
        Statement statement = null;

        PreparedStatement pr_statement = null;
        try {
            connection = MyConnection.create();
            statement = connection.createStatement();
            String query = "UPDATE book SET name = ? WHERE id = ?";
            pr_statement = connection.prepareStatement(query);
            pr_statement.setString(1, name);
            pr_statement.setInt(2, id);
            pr_statement.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(UserDA.class.getName()).log(Level.SEVERE, null, ex);

        } finally {
            try {
                connection.close();
                statement.close();
                pr_statement.close();
            } catch (SQLException ex) {
                Logger.getLogger(UserDA.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

    }

    public void update_double(Double name, int id) {
        Connection connection = null;
        Statement statement = null;

        PreparedStatement pr_statement = null;
        try {
            connection = MyConnection.create();
            statement = connection.createStatement();
            String query = "UPDATE book SET price = ? WHERE id = ?";
            pr_statement = connection.prepareStatement(query);
            pr_statement.setDouble(1, name);
            pr_statement.setInt(2, id);
            pr_statement.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(UserDA.class.getName()).log(Level.SEVERE, null, ex);

        } finally {
            try {
                connection.close();
                statement.close();
                pr_statement.close();
            } catch (SQLException ex) {
                Logger.getLogger(UserDA.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

    }

    public void update_int(int name, int id, String target) {
            Connection connection = null;
            Statement statement = null;

            PreparedStatement pr_statement = null;
        try {
            connection = MyConnection.create();
            statement = connection.createStatement();
            String query = "";
            if (target.equals("Quantity")) {
                query = "UPDATE book SET quantity = ? WHERE id = ?";
            }
            if (target.equals("Author")) {
                query = "UPDATE book SET author_id = ? WHERE id = ?";
            }
            if (target.equals("Publisher")) {
                query = "UPDATE book SET publisher_id = ? WHERE id = ?";
            }
            if (target.equals("Category")) {
                query = "UPDATE book SET category_id = ? WHERE id = ?";
            }
            pr_statement = connection.prepareStatement(query);
            pr_statement.setInt(1, name);
            pr_statement.setInt(2, id);
            pr_statement.executeUpdate();
            
        } catch (SQLException ex) {
            Logger.getLogger(UserDA.class.getName()).log(Level.SEVERE, null, ex);

        } finally {
            try {
                connection.close();

            } catch (SQLException ex) {
                Logger.getLogger(UserDA.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

    }

    public void Enable_book(int id) {
        Connection connection = null;
        Statement statement = null;
        PreparedStatement pr_statement = null;
        try {
            connection = MyConnection.create();
            statement = connection.createStatement();
            String query = "UPDATE book set isActive = 1 where id = ?";
            pr_statement = connection.prepareStatement(query);
            pr_statement.setInt(1, id);
            pr_statement.executeUpdate();

        } catch (SQLException ex) {
            Logger.getLogger(UserDA.class.getName()).log(Level.SEVERE, null, ex);

        } finally {
            try {
                connection.close();
                statement.close();
                pr_statement.close();
            } catch (SQLException ex) {
                Logger.getLogger(UserDA.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    public void Disable_book(int id) {
        Connection connection = null;
        Statement statement = null;
        PreparedStatement pr_statement = null;
        try {
            connection = MyConnection.create();
            statement = connection.createStatement();
            String query = "UPDATE book set isActive = 0 where id = ?";
            pr_statement = connection.prepareStatement(query);
            pr_statement.setInt(1, id);
            pr_statement.executeUpdate();

        } catch (SQLException ex) {
            Logger.getLogger(UserDA.class.getName()).log(Level.SEVERE, null, ex);

        } finally {
            try {
                connection.close();
                statement.close();
                pr_statement.close();
            } catch (SQLException ex) {
                Logger.getLogger(UserDA.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

}
