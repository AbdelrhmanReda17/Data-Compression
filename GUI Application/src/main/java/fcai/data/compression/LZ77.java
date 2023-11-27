package fcai.data.compression;

import java.util.Vector;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

public class LZ77 implements Compression<Vector<LZ77Tag> , Vector<LZ77Tag>>  {
    private final int WINDOW_SIZE = 20000;
    private final int BUFFER_SIZE = 10000;

    @Override
    public String compress(JFrame Main , String result) {
        try{
            Pattern pattern = Pattern.compile("<.*>|[0-9 ]+");
            Matcher matcher = pattern.matcher(result);

            if (matcher.find()) {
                JOptionPane.showMessageDialog(Main, "Cannot Compress the file twice", "Error", JOptionPane.ERROR_MESSAGE);
                return null;
            }
            Vector<LZ77Tag> data = compressHandler(result);
            String CompressedData = "";
            for (int i = 0; i < data.size(); i++) {
                LZ77Tag tag = data.elementAt(i);
                String nextSymbol = String.valueOf(tag.getNextSymbol());
                if (nextSymbol.equals("\n")) {
                    CompressedData += "<" + tag.getPosition() + "," + tag.getLength() + "," + "\\n" + ">  ";
                } else {
                    CompressedData += "<" + tag.getPosition() + "," + tag.getLength() + "," + nextSymbol + ">  ";
                }
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
                Vector<LZ77Tag> result = new Vector<>();
                String[] tokens = data.split("  ");
                for (String token1 : tokens) {
                    String token = token1.substring(1, token1.length() - 1);
                    String[] Tag = token.split(",", 3);
                    if(Tag[2].equals("\\n") ){
                        result.add(new LZ77Tag(Integer.parseInt(Tag[0]), Integer.parseInt(Tag[1]), '\n'));
                    }else{
                        result.add(new LZ77Tag(Integer.parseInt(Tag[0]), Integer.parseInt(Tag[1]), Tag[2].charAt(0)));
                    }
                } 
                return decompressHandler(result);
            }catch(Exception e){
                JOptionPane.showMessageDialog(Main, "Cannot Decompress the file " , "Error" , 0);
            }
        return null;
    }
    @Override
    public Vector<LZ77Tag> compressHandler(String data) {
        Vector<LZ77Tag> v = new Vector<>();
        int curr = 0;
        while (curr < data.length()) {
            int position = 0;
            int maxLength = 0;   // maximum length of substring
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
    
    @Override
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
