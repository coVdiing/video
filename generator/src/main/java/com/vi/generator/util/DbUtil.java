package com.vi.generator.util;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 数据库工具类
 */
public class DbUtil {

    public static Connection getConnection() {
        Connection conn = null;
        try {
            Class.forName("com.mysql.jdbc.Driver");
            String url = "jdbc:mysql://localhost:3306/video";
            String username = "root";
            String password = "root";
            conn = DriverManager.getConnection(url, username, password);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return conn;
    }

    public static String getTableComment(String tableName) throws Exception {
        Connection conn = getConnection();
        String sql = "SELECT table_comment FROM information_schema.tables WHERE table_name = ?";
        PreparedStatement stmt = conn.prepareStatement(sql);
        String tableNameCH = "";
        stmt.setString(1, tableName);
        ResultSet rs = stmt.executeQuery();
        if (rs != null) {
            while (rs.next()) {
                tableNameCH = rs.getString(1);
                break;
            }
        }
        rs.close();
        stmt.close();
        conn.close();
        System.out.println("表名: " + tableNameCH);
        return tableNameCH;
    }

    public static List<Field> getColumnByTableName(String tableName) throws Exception {
        List<Field> fieldList = new ArrayList<>();
        Connection conn = getConnection();
        Statement stmt = conn.createStatement();
        String sql = "SHOW FULL COLUMNS FROM "+tableName;
        ResultSet rs = stmt.executeQuery(sql);
        if (rs != null) {
            while (rs.next()) {
                String columnName = rs.getString("Field");
                String type = rs.getString("Type");
                String comment = rs.getString("Comment");
                Field field = new Field();
                field.setName(columnName);
                field.setNameHump(lineToHump(columnName));
                field.setNameBigHump(lineToBigHump(columnName));
                field.setType(type);
                field.setJavaType(DbUtil.sqlTypeToJavaType(type));
                field.setComment(comment);
                if (comment.contains("|")) {
                    field.setNameCn(comment.substring(0, comment.indexOf("|")));
                } else {
                    field.setNameCn(comment);
                }
                fieldList.add(field);
            }
        }
        return fieldList;
    }

    private static String sqlTypeToJavaType(String type) {
        return null;
    }

    /**
     * 下划线转大驼峰
     *
     * @param columnName
     * @return
     */
    private static String lineToBigHump(String columnName) {
        String[] columnNames = columnName.split("_");
        if (columnNames.length > 1) {
            String prefix = columnNames[0];
            String suffix = columnNames[1];
            char pre = prefix.charAt(0);
            char suf = suffix.charAt(0);
            String upperPre = String.valueOf(pre).toUpperCase();
            String upperSuf = String.valueOf(suf).toUpperCase();
            prefix = prefix.replace(pre, upperPre.toCharArray()[0]);
            suffix = suffix.replace(suf, upperSuf.toCharArray()[0]);
            return prefix + suffix;
        } else {
            return columnName;
        }
    }

    /**
     * 下划线转小驼峰
     *
     * @param columnName
     * @return
     */
    private static String lineToHump(String columnName) {
        String[] columnNames = columnName.split("_");
        if (columnNames.length > 1) {
            String suffix = columnNames[1];
            char c = suffix.charAt(0);
            String upper = String.valueOf(c).toUpperCase();
            System.out.println(upper);
            suffix = suffix.replace(c, upper.toCharArray()[0]);
            return columnNames[0] + suffix;
        } else {
            return columnName;
        }
    }

    public static void main(String[] args) throws Exception {
        System.out.println(lineToHump("course_id"));
        System.out.println(lineToBigHump("course_id"));
        System.out.println(getColumnByTableName("section"));
    }
}