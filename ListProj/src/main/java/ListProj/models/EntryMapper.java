package ListProj.models;

import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

import org.springframework.jdbc.core.RowMapper;


public class EntryMapper implements RowMapper<EntryJSONModel> {

    @Override
    public EntryJSONModel mapRow(ResultSet rs, int rowNum) throws SQLException {
        /*/
        EntryModel entry = new EntryModel();        
        ResultSetMetaData rsmd = rs.getMetaData(); 
        int columnCount = rsmd.getColumnCount();
        ArrayList<Boolean> items = new ArrayList<Boolean>(columnCount);
        entry.setDate(rs.getDate("date").toLocalDate());
        for (int i = 1; i <= columnCount; i++) {
            items.add(rs.getBoolean("item" + i));
        }
        entry.setItems(items);
        */
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
    }
}
