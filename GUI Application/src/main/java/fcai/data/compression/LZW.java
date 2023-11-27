package fcai.data.compression;
import java.util.HashMap;
import java.util.Map;
import java.util.Vector;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

public class LZW implements Compression<Vector<String> , Vector<String>> {
    @Override
    public String compress(JFrame Main , String result) {
        Pattern pattern = Pattern.compile("<.*>|[0-9 ]+");
        Matcher matcher = pattern.matcher(result);
        if (matcher.find()) {
            JOptionPane.showMessageDialog(Main, "Cannot Compress the file twice", "Error", JOptionPane.ERROR_MESSAGE);
            return null;
        }
        try{
            Vector<String> data = compressHandler(result);
            String CompressedData = "";
            for (int i = 0; i < data.size(); i++) {
               CompressedData += data.get(i) + " ";
            }
             return CompressedData;
        }catch(Exception e){
            JOptionPane.showMessageDialog(Main, "Cannot Compress the file : " + e.getMessage() , "Error" , 0);
        }
        return null;
    }
    @Override
    public String decompress(JFrame Main ,String data) {
        try{
            Vector<String> result = new Vector<>();
            String[] tokens = data.split(" ");
            for(var token : tokens) {
                result.add(token);
            }
            String asd = decompressHandler(result);
            return asd;
        } catch (Exception e) {
            JOptionPane.showMessageDialog(Main, "Cannot Compress the file : " + e.getMessage() , "Error" , 0);
        }
        return null;
    }
    
    @Override
    public Vector<String> compressHandler(String data){
        Map<String, Integer> dictionary = new HashMap<>();
        Vector<String> result = new Vector<>();
        for(int i = 0 ; i < 256 ; i++){
            dictionary.put(Character.toString((char)i), i);
        }
        int code = 256;
        String currCode = "";
        for(int i = 0 ; i < data.length() ; i++){
            currCode += data.charAt(i);
            if(!dictionary.containsKey(currCode)){
                dictionary.put(currCode, code++);
                result.add(String.valueOf(dictionary.get(currCode.substring(0, currCode.length() - 1))));
                currCode = "";
                i--;
            }
        }
        if(currCode.length() > 0){
            if(!dictionary.containsKey(currCode))
                dictionary.put(currCode, code++);
            result.add(String.valueOf(dictionary.get(currCode)));
        }
        return result;
    }
    
    @Override
    public String decompressHandler(Vector<String> data){
        Map<Integer, String> dictionary = new HashMap<>();
        for(int i = 0 ; i < 256 ; i++){
            dictionary.put(i, Character.toString((char)i));
        }
        int startCode = 256;
        String result = "";
        String current;
        String prev = "";
        for(int i = 0 ; i < data.size() ; i++){
            int currCode = Integer.parseInt(data.get(i));
            current = dictionary.get(currCode);

            if (current == null ){
                current = prev + prev.charAt(0);
                dictionary.put(startCode++, current);
            }
            else if (dictionary.containsKey(currCode) && i != 0)
                dictionary.put(startCode++, prev + current.charAt(0));
            result += current;
            prev = current;
        }
        return result;
    }
}
