import javax.management.StringValueExp;
import java.io.*;
import java.util.HashMap;
import java.util.Map;
import java.util.Vector;

public class LZW {
//    private final int TABLE_SIZE = 4096;
    public void compress(File input, File output)
    {
        if (!output.exists()) {
            try {
                output.createNewFile();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
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
                        writer.write(data.elementAt(i) + " ");
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
            System.out.println("Done compressing the file to " + output);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public void decompress(File input, File output)
    {
        if (!output.exists()) {
            try {
                output.createNewFile();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
        try (BufferedReader reader = new BufferedReader(new FileReader(input))) {
            String line;
            Vector<String> result = new Vector<>();

            while ((line = reader.readLine()) != null)
            {
                String[] tokens = line.split(" ");
                for(int i = 0 ; i < tokens.length; i++)
                    result.add(tokens[i]);
            }
            String data = decompressHandler(result);
            try (FileWriter writer = new FileWriter(output)) {
                writer.write(data);
            } catch (IOException e) {
                e.printStackTrace();
            }
            System.out.println("Done decompressing the file to " + output);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public Vector <String> compressHandler(String data)
    {
        HashMap<String, Integer> table = new HashMap<>();
        Vector <String> codes = new Vector<>();
        //initalizing table
        // 0 -> 255 or  0 ->127
        for (int i = 0; i <= 255; i++)
        {
            char x = (char) i;
            String s = "" + x;
            table.put(s, i);
        }
        int startIndex = 256;
        String prevCode = "";
        String currCode = "";

        for (int i = 0; i < data.length(); i++)
        {
            currCode += data.charAt(i);
            if (!table.containsKey(currCode) )
            {
                table.put(currCode, startIndex++);
                prevCode = currCode.substring(0, currCode.length() - 1); //
                codes.add(table.get(prevCode).toString());
                currCode = "";
                i--;
            }
        }
        if (!currCode.isEmpty())
        {
            if (!table.containsKey(currCode))
                table.put(currCode, startIndex);
            codes.add(table.get(currCode).toString());
        }
        return codes;
    }
    public String decompressHandler(Vector<String> codes)
    {
        //ABAABABBAABAABAAAABABBBBBBBB
        HashMap<Integer, String> table = new HashMap<>();
        for (int i = 0; i <= 255; i++)
        {
            char x = (char) i;
            String s = "" + x;
            table.put(i, s);
        }

        int startIndex = 256;
        String result = "";
        String prev = "";
        String curr = "";
        for (int i = 0; i < codes.size(); i++)
        {
            // integer value with code
            int code = Integer.valueOf(codes.elementAt(i));
            curr = table.get(code);

            if (curr == null )
            {
                curr = prev + prev.charAt(0);
                table.put(startIndex++, curr);
            }
            else if (table.containsKey(code) && i != 0)
                table.put(startIndex++, prev + curr.charAt(0));
            result += curr;
            prev = curr;

        }
        return result;
    }
}
