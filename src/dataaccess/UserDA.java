/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dataaccess;

import constant.Constant_var;
import pojo.UserPOJO;
import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author TIN
 */
public class UserDA {

    private Constant_var constant_var;

    public List<UserPOJO> getAllAccount() {
        List<UserPOJO> ans = null;
        Connection connection = null;
        Statement statement = null;
        ResultSet rs = null;
        try {
            ans = new ArrayList<>();
            connection = MyConnection.create();
            statement = connection.createStatement();
            String query = "SELECT * FROM users";
            rs = statement.executeQuery(query);
            while (rs.next()) {
                int id = rs.getInt("id");
                String username = rs.getString("username");
                String password = rs.getString("password");
                String fullname = rs.getString("fullname");
                LocalDate dob = rs.getDate("dob").toLocalDate();
                String role = rs.getString("role");
                Boolean isActive = rs.getBoolean("isActive");
                UserPOJO user = new UserPOJO(id, username, password, fullname, dob, role, isActive);
                ans.add(user);
            }
        } catch (SQLException ex) {
            Logger.getLogger(UserDA.class.getName()).log(Level.SEVERE, null, ex);
            ans = null;
        } finally {
            try {
                connection.close();
                statement.close();
                rs.close();
            } catch (SQLException ex) {
                Logger.getLogger(UserDA.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return ans;
    }

    public UserPOJO get_acc(String mail, String pass) {
        UserPOJO ans = null;
        Connection connection = null;
        Statement statement = null;
        PreparedStatement pr_statement = null;
        ResultSet rs = null;
        try {

            connection = MyConnection.create();
            statement = connection.createStatement();
            String query = "SELECT * FROM users where username =  ? and password = ?";
            pr_statement = connection.prepareStatement(query);
            pr_statement.setString(1, mail);
            pr_statement.setString(2, pass);
            rs = pr_statement.executeQuery();
            while (rs.next()) {
                int id = rs.getInt("id");
                String username = rs.getString("username");
                String password = rs.getString("password");
                String fullname = rs.getString("fullname");
                LocalDate dob = rs.getDate("dob").toLocalDate();
                String role = rs.getString("role");
                Boolean isActive = rs.getBoolean("isActive");
                UserPOJO user = new UserPOJO(id, username, password, fullname, dob, role, isActive);
                ans = user;
            }
        } catch (SQLException ex) {
            Logger.getLogger(UserDA.class.getName()).log(Level.SEVERE, null, ex);
            ans = null;
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
        return ans;
    }

    public void update_name(String fullname, int id) {

        Connection connection = null;
        Statement statement = null;

        PreparedStatement pr_statement = null;
        try {
            connection = MyConnection.create();
            statement = connection.createStatement();
            String query = "UPDATE users SET fullname = ? WHERE id = ?";
            pr_statement = connection.prepareStatement(query);
            pr_statement.setString(1, fullname);
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

    public void update_dob(String dob, int id) {

        Connection connection = null;
        Statement statement = null;

        PreparedStatement pr_statement = null;
        try {
            connection = MyConnection.create();
            statement = connection.createStatement();
            String query = "UPDATE users set dob = ? where id = ?";
            pr_statement = connection.prepareStatement(query);
            pr_statement.setString(1, dob);
            pr_statement.setString(2, Integer.toString(id));
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

    public void change_pass(String pass, int id) {
        Connection connection = null;
        Statement statement = null;
        PreparedStatement pr_statement = null;
        try {
            connection = MyConnection.create();
            statement = connection.createStatement();
            String query = "UPDATE users set password = ? where id = ?";
            pr_statement = connection.prepareStatement(query);
            pr_statement.setString(1, pass);
            pr_statement.setString(2, Integer.toString(id));
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

    public List<UserPOJO> searchAccount(String name) {
        List<UserPOJO> ans = null;
        Connection connection = null;
        Statement statement = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        try {
            ans = new ArrayList<>();
            connection = MyConnection.create();
            statement = connection.createStatement();
            String query = "SELECT * FROM users WHERE fullname like ?";
            pstmt = connection.prepareStatement(query);
            pstmt.setString(1, String.format("%%%s%%", name));
            rs = pstmt.executeQuery();
            while (rs.next()) {
                int id = rs.getInt("id");
                String username = rs.getString("username");
                String password = rs.getString("password");
                String fullname = rs.getString("fullname");
                LocalDate dob = rs.getDate("dob").toLocalDate();
                String role = rs.getString("role");
                Boolean isActive = rs.getBoolean("isActive");
                UserPOJO user = new UserPOJO(id, username, password, fullname, dob, role, isActive);
                ans.add(user);
            }
        } catch (SQLException ex) {
            Logger.getLogger(UserDA.class.getName()).log(Level.SEVERE, null, ex);
            ans = null;
        } finally {
            try {
                connection.close();
                statement.close();
                pstmt.close();
                rs.close();
            } catch (SQLException ex) {
                Logger.getLogger(UserDA.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return ans;
    }


    public int insertAccount(UserPOJO newAccount) {
        // if insertedId = 0: get LAST_INSERT_ID() fail
        int insertedId = 0;
        Connection connection = null;
        Statement statement = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        String query;
        try {
            connection = MyConnection.create();
            statement = connection.createStatement();
            query = "SELECT * FROM users WHERE username = ?;";
            pstmt = connection.prepareStatement(query);
            pstmt.setString(1, newAccount.getUsername());
            rs = pstmt.executeQuery();
            if (rs.next()) {
                // The username already exists
                insertedId = -2;
            } else {
                query = "INSERT INTO users (fullname, username, password, dob, role) VALUES (?, ?, ?, ?, ?);";
                pstmt = connection.prepareStatement(query);
                pstmt.setString(1, newAccount.getFullname());
                pstmt.setString(2, newAccount.getUsername());
                pstmt.setString(3, newAccount.getPassword());
                pstmt.setDate(4, Date.valueOf(newAccount.getDob()));
                pstmt.setString(5, newAccount.getRole());

                // Execute the statement
                int rowsInserted = pstmt.executeUpdate();
                if (rowsInserted > 0) {
                    Statement idStatement = null;
                    ResultSet idResult = null;
                    idStatement = connection.createStatement();
                    idResult = idStatement.executeQuery("SELECT LAST_INSERT_ID()");
                    if (idResult.next()) {
                        insertedId = idResult.getInt(1);
                        System.out.println("insertedId: " + insertedId);
                    }
                } else {
                    // User added failed
                    insertedId = -3;
                }
            }
        } catch (SQLException ex) {
            Logger.getLogger(UserDA.class.getName()).log(Level.SEVERE, null, ex);
            // Error: SQLException
            insertedId = -1;
        } finally {
            try {
                connection.close();
                statement.close();
                pstmt.close();
                rs.close();
            } catch (SQLException ex) {
                Logger.getLogger(UserDA.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return insertedId;
    }

    public String enableAccount(int account_id) {
        String status = "";
        Connection connection = null;
        Statement statement = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        String query;
        try {
            connection = MyConnection.create();
            statement = connection.createStatement();
            query = "SELECT * FROM users WHERE id = ?;";
            pstmt = connection.prepareStatement(query);
            pstmt.setInt(1, account_id);
            rs = pstmt.executeQuery();
            if (!rs.next()) {
                // Account not found
                status = "Account not exists!";
            } else {
                Boolean isActive = rs.getBoolean("isActive");
                if (isActive) {
                    // Account already enable
                    status = "Account already enable!";
                } else {
                    query = "UPDATE users SET isActive = 1 WHERE id = ?;";
                    pstmt = connection.prepareStatement(query);
                    pstmt.setInt(1, account_id);

                    // Execute the statement
                    int rowsUpdated = pstmt.executeUpdate();
                    if (rowsUpdated > 0) {
                        status = "Enable account successfully!";
                    } else {
                        status = "Enable account fail!";
                    }
                }
            }
        } catch (SQLException ex) {
            Logger.getLogger(UserDA.class.getName()).log(Level.SEVERE, null, ex);
            status = "Error: SQLException";
        } finally {
            try {
                connection.close();
                statement.close();
                pstmt.close();
                rs.close();
            } catch (SQLException ex) {
                Logger.getLogger(UserDA.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return status;
    }

    public String disableAccount(int account_id) {
        String status = "";
        Connection connection = null;
        Statement statement = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        String query;
        try {
            connection = MyConnection.create();
            statement = connection.createStatement();
            query = "SELECT * FROM users WHERE id = ?;";
            pstmt = connection.prepareStatement(query);
            pstmt.setInt(1, account_id);
            rs = pstmt.executeQuery();
            if (!rs.next()) {
                // Account not found
                status = "Account not exists!";
            } else {
                Boolean isActive = rs.getBoolean("isActive");
                if (!isActive) {
                    // Account already enable
                    status = "Account already disable!";
                } else {
                    query = "UPDATE users SET isActive = 0 WHERE id = ?;";
                    pstmt = connection.prepareStatement(query);
                    pstmt.setInt(1, account_id);

                    // Execute the statement
                    int rowsUpdated = pstmt.executeUpdate();
                    if (rowsUpdated > 0) {
                        status = "Disable account successfully!";
                    } else {
                        status = "Disable account fail!";
                    }
                }
            }
        } catch (SQLException ex) {
            Logger.getLogger(UserDA.class.getName()).log(Level.SEVERE, null, ex);
            status = "Error: SQLException";
        } finally {
            try {
                connection.close();
                statement.close();
                pstmt.close();
                rs.close();
            } catch (SQLException ex) {
                Logger.getLogger(UserDA.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return status;
    }

    public String resetPassword(int account_id) {
        constant_var = new Constant_var();
        String status = "";
        Connection connection = null;
        Statement statement = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        String query;
        try {
            connection = MyConnection.create();
            statement = connection.createStatement();
            query = "SELECT * FROM users WHERE id = ?;";
            pstmt = connection.prepareStatement(query);
            pstmt.setInt(1, account_id);
            rs = pstmt.executeQuery();
            if (!rs.next()) {
                // Account not found
                status = "Account not exists!";
            } else {
                query = "UPDATE users SET password = ? WHERE id = ?;";
                pstmt = connection.prepareStatement(query);
                pstmt.setString(1, constant_var.getDefault_password());
                pstmt.setInt(2, account_id);

                // Execute the statement
                int rowsUpdated = pstmt.executeUpdate();
                if (rowsUpdated > 0) {
                    status = "Reset password successfully!";
                } else {
                    status = "Reset password fail!";
                }
            }
        } catch (SQLException ex) {
            Logger.getLogger(UserDA.class.getName()).log(Level.SEVERE, null, ex);
            status = "Error: SQLException";
        } finally {
            try {
                connection.close();
                statement.close();
                pstmt.close();
                rs.close();
            } catch (SQLException ex) {
                Logger.getLogger(UserDA.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return status;
    }

    public int updateAccountInfo(UserPOJO updateAccount){
        int status = 1;
        Connection connection = null;
        Statement statement = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        String query;
        try {
            connection = MyConnection.create();
            statement = connection.createStatement();
            query = "SELECT * FROM users WHERE id = ?;";
            pstmt = connection.prepareStatement(query);
            pstmt.setInt(1, updateAccount.getId());
            rs = pstmt.executeQuery();
            if (!rs.next()) {
                // Account not found
                status = -2;
            } else {
                query = "UPDATE users SET username = ?, fullname = ?, dob = ?, role = ? WHERE id = ?;";
                pstmt = connection.prepareStatement(query);
                pstmt.setString(1, updateAccount.getUsername());
                pstmt.setString(2, updateAccount.getFullname());
                pstmt.setDate(3, Date.valueOf(updateAccount.getDob()));
                pstmt.setString(4, updateAccount.getRole());
                pstmt.setInt(5, updateAccount.getId());

                // Execute the statement
                int rowsUpdated = pstmt.executeUpdate();
                System.out.println(rowsUpdated + " rows affected");
                if (rowsUpdated == 0) {
                    // Update account's info failed! OR The account's new info is the same as the old one
                    status = -3;
                }
            }
        } catch (SQLException ex) {
            Logger.getLogger(UserDA.class.getName()).log(Level.SEVERE, null, ex);
            // Error: SQLException
            status = -1;
        } finally {
            try {
                connection.close();
                statement.close();
                pstmt.close();
                rs.close();
            } catch (SQLException ex) {
                Logger.getLogger(UserDA.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return status;
    }
}
