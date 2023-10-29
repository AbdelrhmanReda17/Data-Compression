import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Vector;

public class LZ77 {
    private int WINDOW_SIZE = 20000;
    private int BUFFER_SIZE = 10000;

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
            Vector<LZ77Tag> data = compressHandler(result.toString());
            try (FileWriter writer = new FileWriter(output)) {
                for (int i = 0; i < data.size(); i++) {
                    LZ77Tag tag = data.elementAt(i);
                    String nextSymbol = String.valueOf(tag.getNextSymbol());
                    if (nextSymbol.equals("\n")) {
                        writer.write("<" + tag.getPosition() + "/" + tag.getLength() + "/" + "\\n" + ">  ");
                    } else {
                        writer.write("<" + tag.getPosition() + "/" + tag.getLength() + "/" + nextSymbol + ">  ");
                    }
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
            System.out.println("Done compressing the file to " + output);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
        public void decompress(File input, File output) {
            try (BufferedReader reader = new BufferedReader(new FileReader(input))) {
                String line;
                Vector<LZ77Tag> result = new Vector<>();
        
                while ((line = reader.readLine()) != null) {
                    String[] tokens = line.split("  ");
                    for(int i = 0 ; i < tokens.length; i++){
                        String token = tokens[i].substring(1 , tokens[i].length() - 1);
                        String[] Tag = token.split("/");
                        if(Tag[2].equals("\\n") ){
                            result.add(new LZ77Tag(Integer.parseInt(Tag[0]), Integer.parseInt(Tag[1]), '\n'));
                        }else{
                            result.add(new LZ77Tag(Integer.parseInt(Tag[0]), Integer.parseInt(Tag[1]), Tag[2].charAt(0)));
                        }
                    } 
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
    public Vector <LZ77Tag> compressHandler(String data) {
        Vector<LZ77Tag> v = new Vector<>();
        int curr = 0;
        while (curr < data.length()) {
            int position = 0;
            int maxLength = 0;
            char nextSymbol = data.charAt(maxLength + curr);
            for (int i = 1; i <= WINDOW_SIZE && i <= curr; i++)
            {
                int length = 0;
                while (curr + length < data.length() && length < BUFFER_SIZE && curr + length - i < curr + length
                        && data.charAt(curr + length) == data.charAt(curr + length - i))
                {
                    length++;
                }
                if (length > maxLength)
                {
                    position = i;
                    maxLength = length;
                    if (curr + length < data.length())
                        nextSymbol = data.charAt(curr + length);
                    else
                        nextSymbol = 0;
                }
            }
            curr += maxLength + 1;
            v.add(new LZ77Tag(position, maxLength, nextSymbol));
        }
        return  v;
    }
    public String decompressHandler (Vector <LZ77Tag> data)
    {
        String result = "";
         for (int i = 0; i < data.size(); i++)
         {
            LZ77Tag tag = data.elementAt(i);
            if (tag.getLength() == 0)
                result += tag.getNextSymbol();
            else
            {
                int j = result.length() - tag.getPosition();
                int length = tag.getLength();
                while (length > 0)
                {
                    result += result.charAt(j);
                    length--;
                    j++;
                }
                if (tag.getNextSymbol() != 0)
                    result += tag.getNextSymbol();
            }
         }
         return result;
    }
}
