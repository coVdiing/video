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
    private final static String TYPE_CHAR = "char";
    private final static String TYPE_VARCHAR = "varchar";
    private final static String TYPE_TEXT = "text";
    private final static String TYPE_DATETIME = "datetime";
    private final static String TYPE_INT = "int";
    private final static String TYPE_BIG_DECIMAL = "decimal";
    private final static String TYPE_LONG = "long";

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
        String tableNameCn = "";
        stmt.setString(1, tableName);
        ResultSet rs = stmt.executeQuery();
        if (rs != null) {
            while (rs.next()) {
                tableNameCn = rs.getString(1);
                break;
            }
        }
        rs.close();
        stmt.close();
        conn.close();
        System.out.println("表名: " + tableNameCn);
        return tableNameCn;
    }

    public static List<Field> getColumnByTableName(String tableName) throws Exception {
        List<Field> fieldList = new ArrayList<>();
        Connection conn = getConnection();
        Statement stmt = conn.createStatement();
        String sql = "SHOW FULL COLUMNS FROM " + tableName;
        ResultSet rs = stmt.executeQuery(sql);
        if (rs != null) {
            while (rs.next()) {
                String columnName = rs.getString("Field");
                String type = rs.getString("Type");
                String comment = rs.getString("Comment");
                String nullAble = rs.getString("Null");
                Field field = new Field();
                field.setName(columnName);
                field.setNameHump(lineToHump(columnName));
                field.setNameBigHump(lineToBigHump(columnName));
                field.setType(type);
                field.setJavaType(DbUtil.sqlTypeToJavaType(type));
                field.setComment(comment);
                field.setNullAble("YES".equals(nullAble.toUpperCase()));

                if (type.toLowerCase().contains(TYPE_VARCHAR)) {
                    // 如果是varchar类型就需要设置length
                    String lengthVal = type.substring(type.indexOf("(") + 1, type.length() - 1);
                    field.setLength(Integer.valueOf(lengthVal));
                } else {
                    field.setLength(0);
                }

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

    /**
     * 数据库类型转成Java类型
     *
     * @param type
     * @return
     */
    private static String sqlTypeToJavaType(String type) {
        String sqlType = type.toLowerCase();
        if (sqlType.contains(TYPE_VARCHAR) || sqlType.contains(TYPE_CHAR) || sqlType.contains(TYPE_TEXT)) {
            return "String";
        } else if (sqlType.contains(TYPE_DATETIME)) {
            return "Date";
        } else if (sqlType.contains(TYPE_INT)) {
            return "Integer";
        } else if (sqlType.contains(TYPE_LONG)) {
            return "Long";
        } else if (sqlType.contains(TYPE_BIG_DECIMAL)) {
            return "BigDecimal";
        } else {
            return "String";
        }
    }

    /**
     * 转大驼峰
     *
     * @param columnName
     * @return
     */
    private static String lineToBigHump(String columnName) {
        String[] columnNames = columnName.split("_");
        if (columnNames.length > 1) {
            String prefix = columnNames[0];
            String suffix = columnNames[1];
            String upperPre = prefix.substring(0,1).toUpperCase();
            String upperSuf = suffix.substring(0,1).toUpperCase();
            prefix = upperPre + prefix.substring(1);
            suffix = upperSuf + suffix.substring(1);
            return prefix + suffix;
        } else {
            return columnName.substring(0,1).toUpperCase()+columnName.substring(1);
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
        System.out.println(lineToBigHump("title"));
//        System.out.println(getColumnByTableName("section"));
    }
}
