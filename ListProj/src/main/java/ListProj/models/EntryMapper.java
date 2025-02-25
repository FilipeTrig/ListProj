package ListProj.models;

import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

import org.springframework.jdbc.core.RowMapper;


public class EntryMapper implements RowMapper<EntryModel> {

    @Override
    public EntryModel mapRow(ResultSet rs, int rowNum) throws SQLException {
        
        EntryModel entry = new EntryModel();        
        ResultSetMetaData rsmd = rs.getMetaData(); 
        int columnCount = rsmd.getColumnCount();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        // column count can't be the number of items needs changing for number of items
        // number of columsn will be thr parameters of each Entry 
        ArrayList<Boolean> items = new ArrayList<Boolean>(54);
        //entry.setDate(LocalDate.parse(rs.getString("date"),formatter));
        entry.setDate(rs.getDate("date").toLocalDate());
        entry.setId(rs.getInt("ID"));
        entry.setWeight(rs.getInt("weight"));
        for (int i = 1; i <= 55; i++) {
            items.add(rs.getBoolean("item" + i));
        }
        entry.setItems(items);
        return entry;
        
        /*
        EntryJSONModel entryJSON = new EntryJSONModel(); 
        EntryModel entry = new EntryModel(); 
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        entryJSON.setDate(LocalDate.parse(rs.getString("date"),formatter));
        ArrayList<Boolean> items = new ArrayList<Boolean>(54);
        String itemsString="";
        for (int i = 1; i <= 54; i++) {             // needs to get String (JsonToken is a String) and convert Strings to Boolean
            itemsString+=(rs.getString("item" + i));  
        }
        entryJSON.setItems(itemsString);
        entryJSON.setWeight(rs.getInt("weight"));
        entryJSON.setId(rs.getInt("id"));
        System.out.println(entryJSON.toString());
        return entryJSON;
        */
    }
}
