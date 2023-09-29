package fr.pr11dev.getsupport.shared.storage.mysql;

import java.sql.*;
import java.util.ArrayList;

public class MySQL {

    private static String database;
    private static String url;
    private static String user;
    private static String passwd;
    private static String prefix;

    private static Statement state;

    public static boolean connect(String databaseId, String urlId, String userId, String passwdnId, String prefixId) {

        database = databaseId;
        url = "jdbc:mysql://"+urlId+"/"+database+"?useSSL=false";
        user = userId;
        passwd = passwdnId;
        prefix = prefixId;

        try {

            Class.forName("com.mysql.jdbc.Driver");

        } catch (ClassNotFoundException e1) {

            e1.printStackTrace();

        }

        try {

            Connection conn = DriverManager.getConnection(url, user, passwd);
            state = conn.createStatement();

        } catch (SQLException e) {

            e.printStackTrace();

        }

        if(state == null) {

            return false;

        }else {

            return true;

        }

    }

    public static String getString(String table, String key, String id, String value) {

        String result = null;
        table = prefix+table;

        try{

            ResultSet r = state.executeQuery("SELECT * FROM `"+database+"`.`"+table+"` WHERE "+key+" = '"+id+"'");
            r.next();
            result = r.getString(value);

        }catch(SQLException e){

            e.printStackTrace();

        }

        return result;

    }

    public static int getInt(String table, String key, String id, String value) {

        int result = 0;
        table = prefix+table;

        try{

            ResultSet r = state.executeQuery("SELECT * FROM `"+database+"`.`"+table+"` WHERE "+key+" = '"+id+"'");
            r.next();
            result = r.getInt(value);

        }catch(SQLException e){

            e.printStackTrace();

        }

        return result;

    }

    public static boolean setString(String table, String key, String id, String type, String value) {

        boolean result = true;
        table = prefix+table;

        try{

            state.executeUpdate("UPDATE `"+database+"`.`"+table+"` SET `"+type+"` = '"+value+"' WHERE `"+table+"`.`"+key+"` = '"+id+"';");

        }catch(SQLException e){

            e.printStackTrace();
            result = false;

        }

        return result;

    }

    public static boolean setInt(String table, String key, String id, String type, int value) {

        boolean result = true;
        table = prefix+table;

        try{

            state.executeUpdate("UPDATE `"+database+"`.`"+table+"` SET `"+type+"` = '"+value+"' WHERE `"+table+"`.`"+key+"` = '"+id+"';");

        }catch(SQLException e){

            e.printStackTrace();
            result = false;

        }

        return result;

    }

    public static ArrayList<String> getValues(String table){

        ArrayList<String> values = new ArrayList<String>();
        table = prefix+table;

        try {

            ResultSet result = state.executeQuery("SELECT * FROM `"+table+"`");

            while(result.next()){

                values.add(result.getString(1));

            }

        }catch (SQLException e) {

            e.printStackTrace();

        }

        return values;
    }

    public static ArrayList<String> getSortValues(String table, String sortType, String order){

        ArrayList<String> values = new ArrayList<String>();
        table = prefix+table;

        try {

            ResultSet result = state.executeQuery("SELECT * FROM `"+table+"` ORDER BY "+sortType+" "+order);

            while(result.next()){

                values.add(result.getString(1));

            }

        }catch (SQLException e) {

            e.printStackTrace();

        }

        return values;
    }

    public static boolean execute(String command, boolean ignoreError) {

        boolean result = true;

        command = command.replaceAll("#database#", database);
        command = command.replaceAll("#prefix#", prefix);

        try{

            state.executeUpdate(command);

        }catch(SQLException e){

            if(!ignoreError) {

                e.printStackTrace();

            }

            result = false;

        }

        return result;

    }

    public static boolean exists(String table, String key, String id) {

        boolean result = true;
        table = prefix+table;

        try {

            ResultSet r = state.executeQuery("SELECT * FROM `"+database+"`.`"+table+"` WHERE "+key+" = '"+id+"'");
            r.next();
            r.getString(key);

        }catch (SQLException e) {

            result = false;

        }

        return result;

    }

    public static boolean isConnected(String tryCommand) {

        boolean result = true;
        tryCommand = tryCommand.replaceAll("#database#", database);
        tryCommand = tryCommand.replaceAll("#prefix#", prefix);

        try{

            ResultSet r = state.executeQuery(tryCommand);
            r.next();

        }catch(SQLException e){

            result = false;

        }


        return result;

    }

}