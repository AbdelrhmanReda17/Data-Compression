package fcai.data.compression;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.PriorityQueue;
import javax.swing.JFrame;
import javax.swing.JOptionPane;



public class Huffman implements Compression<Map<Character,String> , Map<String,Character>>  {
    public HuffmanNode root;
    private String binary;

    public Huffman(HuffmanNode x) {
            this.root = x;
    }
    public Huffman(){
        this.root = null;
    }
    private String getCode(char ch , String code, HuffmanNode node) {
        if(node == null)
            return "0";
        if(node.ch == ch)
            return code;
        String left = getCode(ch, code + "1", node.left);
        if(!Objects.equals(left, "0"))
            return left;
        return getCode(ch, code + "0", node.right);
    }
     public Map<Character , String> buildTree(Map<Character, Integer> FreqMap)   {
        Map<Character, String> map = new HashMap<>();
        PriorityQueue<HuffmanNode> pq = new PriorityQueue<>();
        for(Map.Entry<Character, Integer> entry : FreqMap.entrySet()){
            HuffmanNode node = new HuffmanNode(entry.getKey(), entry.getValue());
            pq.add(node);
        }
        while(pq.size() > 1){
            HuffmanNode left = pq.poll();
            HuffmanNode right = pq.poll();
            HuffmanNode parent = new HuffmanNode('\0', left.freq + right.freq);
            parent.left = left;
            parent.right = right;
            pq.add(parent);
        }
        Huffman Tree = new Huffman(pq.poll());
        for (Map.Entry<Character, Integer> entry : FreqMap.entrySet()) {
            map.put(entry.getKey(), (Tree.getCode(entry.getKey() , "", Tree.root)));
        }
        return map;
    }
     
    public Map<Character, Integer> getFrequency(String text) {
        Map<Character, Integer> freq = new HashMap<>();
        for (char c : text.toCharArray()) {
            freq.putIfAbsent(c, 0);
            freq.put(c, freq.get(c) + 1);
        }
        return freq;
    }

    @Override
    public String compress(JFrame Main, String result) {
        try{
            Map<Character, String> huffmanTable = compressHandler(result);
            StringBuilder overhead = new StringBuilder();
            StringBuilder compressedResult = new StringBuilder();
            for (Map.Entry<Character, String> entry : huffmanTable.entrySet()) {
                overhead.append(entry.getKey());
                overhead.append(entry.getValue());
                overhead.append("&&");
            }
            StringBuilder encodedText = new StringBuilder();
            for (char ch : result.toCharArray()) {
                encodedText.append(huffmanTable.get(ch));
            }
            int bytes = (encodedText.length() + 7) / 8;
            int extraBits = encodedText.length() % 8; 
            overhead.append("%%").append(bytes).append("%%").append(extraBits).append("%%$$");
            for (int i = 0; i < result.length(); i++) {
                char currentChar = result.charAt(i);
                String huffmanCode = huffmanTable.get(currentChar);

                if (huffmanCode != null) {
                    compressedResult.append(huffmanCode);
                } else {
                    System.out.println("Character not found in Huffman table: " + currentChar);
                }
            }        
            compressedResult.insert(0, overhead);
            return compressedResult.toString();
        }catch(Exception e){
            JOptionPane.showMessageDialog(Main, "Cannot Compress the file " , "Error" , 0);
            return null;
        }
    }
    @Override
    public String decompress(JFrame Main, String data) {
        try{
            String[] tokens = data.split("\\$\\$");
            this.binary = tokens[1];
            //contains 1char and number
            String[] info = tokens[0].split("%%");
            String[] row = info[0].split("&&");
            Map<String, Character> huffmanTable = new HashMap<>();
            for (String s : row) {
                huffmanTable.put(s.substring(1), s.charAt(0));
            }
            return decompressHandler(huffmanTable);
        }catch(Exception e){
            JOptionPane.showMessageDialog(Main, "Cannot Decompress the file " , "Error" , 0);
            return null;
        }
    }

    @Override
    public Map<Character, String> compressHandler(String data) {
        Map<Character, Integer> frequency = getFrequency(data);
        return buildTree(frequency);
    }

    @Override
    public String decompressHandler(Map<String, Character> huffmanTable ) {        
        StringBuilder sb = new StringBuilder();
        String code = "";
        for (char c : binary.toCharArray()) {
            code += c;
            if (huffmanTable.containsKey(code)) {
                sb.append(huffmanTable.get(code));
                code = "";
            }
        }
        return sb.toString();
    }
}
