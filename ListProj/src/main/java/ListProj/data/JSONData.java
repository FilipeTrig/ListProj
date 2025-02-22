package ListProj.data;

import java.security.KeyStore.Entry;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import ListProj.models.EntryModel;

public class JSONData {
    /*
    public String convertItemsJSON(ArrayList<Boolean> items) { //converts the items to JSON
        ArrayList<Boolean> returnItems = new ArrayList<Boolean>();
        String[] itemsArray = items.toString().split(",");
        String returnString = "";
        for (int i = 0; i < itemsArray.length; i++) {
            returnItems.add(Boolean.parseBoolean(itemsArray[i]));
            returnString += itemsArray[i] + ",";
        }
        return returnString;
    }

    public String convertItemsJSON2(ArrayList<Boolean> items) { //converts the items to JSON
        ArrayList<Boolean> returnItems = new ArrayList<Boolean>();
        String[] itemsArray = items.toString().split(",");
        String returnString = items.toString().toJSONString();
        for (int i = 0; i < itemsArray.length; i++) {
            returnItems.add(Boolean.parseBoolean(itemsArray[i]));
            returnString += itemsArray[i] + ",";
        }
        return returnString;
    }

    public EntryModel unpackJSONItems(EntryModel entry) { //gets a Entry model with the items as JSON and returns an Entry model with the items as an ArrayList
        EntryModel resultEntry = new EntryModel();
        ArrayList<Boolean> items = new ArrayList<Boolean>();
        String[] itemsArray = entry.getItems().toString().split(",");
        //entry.setItems(entry.getItems().toString().replace("[", "").replace("]", "").split(","));
        for (int i = 0; i < itemsArray.length; i++) {
            items.add(entry.getItems().get(i));
        }
        //items = items.substring(0, items.length() - 1);
        resultEntry.setDate(entry.getDate());
        resultEntry.setItems(items);
        return resultEntry;
    }

    */

    public ArrayList<Boolean> readJSON(ArrayList<Boolean> items) throws JsonMappingException, JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();
        ArrayList<Boolean> resuList = objectMapper.readerFor(Boolean.class).readValue(items.toString());
        //ArrayList<Boolean> resuList = new ArrayList<Boolean>();
        //for (int i = 0; i < items.size(); i++) {
            //resuList.add(items.get(i));
        //}
        return resuList;
    }


    // write items in JSON format so Entry can be stored in database
    public ArrayList<Boolean> writeJSON(ArrayList<Boolean> items) throws JsonMappingException, JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();
        ArrayList<Boolean> resuList = objectMapper.readValue(items.toString(), new TypeReference<ArrayList<Boolean>>(){});
        return resuList;
    }

     /*
     * public class Main {  
  	public String convertItemsJSON(ArrayList<Boolean> items) { //converts the items to JSON
        ArrayList<Boolean> returnItems = new ArrayList<Boolean>();
        String[] itemsArray = items.toString().replace("[", "").replace("]", "").replace(" ", "").split(",");
        String returnString = "";
        for (int i = 0; i < itemsArray.length; i++) {
            returnItems.add(Boolean.parseBoolean(itemsArray[i]));
            returnString += itemsArray[i] + ",";
        }
        for (int i = 0; i < itemsArray.length; i++) {
        	System.out.println(itemsArray[i]);
        }
        return returnString;
    } 
  public static void main(String[] args) {  
    ArrayList<Boolean> returnItems = new ArrayList<Boolean>();
    returnItems.add(false);
    returnItems.add(false);
    returnItems.add(false);
    returnItems.add(false);
    returnItems.add(false);
    Main entryDataService = new Main();
    System.out.println(entryDataService.convertItemsJSON(returnItems));
  }
}
 
     */
    
}
