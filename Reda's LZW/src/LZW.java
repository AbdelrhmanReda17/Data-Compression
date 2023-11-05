import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.HashMap;
import java.util.Map;
import java.util.Vector;

public class LZW {
    public void compress(File input, File output) {
        try (BufferedReader reader = new BufferedReader(new FileReader(input))) {
            String line;
            StringBuilder result = new StringBuilder();
    
            while ((line = reader.readLine()) != null) {
                result.append(line);
                if (reader.ready()) {
                    result.append("\n");
                }
            }
            Vector<String> data = compressHandler(result.toString());
            try (FileWriter writer = new FileWriter(output)) {
                for (int i = 0; i < data.size(); i++) {
                    writer.write(data.get(i) + " ");
                }
            } catch (Exception e) {
                System.out.print(e.getMessage());
            }
            System.out.println("Done compressing the file to " + output);
        }catch(Exception e){
            System.out.print(e.getMessage());
        }
    }
    public void decompress(File input, File output){
        try (BufferedReader reader = new BufferedReader(new FileReader(input))) {
            String line;
            Vector<String> result = new Vector<>();
            while((line = reader.readLine()) != null){
                String[] tokens = line.split(" ");
                for(int i = 0 ; i < tokens.length ; i++){
                    result.add(tokens[i]);
                }
            }
            String data = decompressHandler(result);
            try (FileWriter writer = new FileWriter(output)) {
                writer.write(data);
            } catch (Exception e) {
                System.out.print(e.getMessage());
            }
            System.out.println("Done decompressing the file to " + output);
        } catch (Exception e) {
            System.out.print(e.getMessage());
        }
    }
    private Vector<String> compressHandler(String data){
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
    private String decompressHandler(Vector<String> data){
        Map<Integer, String> dictionary = new HashMap<>();
        for(int i = 0 ; i < 256 ; i++){
            dictionary.put(i, Character.toString((char)i));
        }
        int startCode = 256;
        String result = "";
        String current = "";
        String prev = "";
        for(int i = 0 ; i < data.size() ; i++){
            int currCode = Integer.valueOf(data.get(i));
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
