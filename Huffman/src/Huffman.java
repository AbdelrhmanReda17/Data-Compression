import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;

public class Huffman {
    public class Node implements Comparable<Node> {
        public int freq;
        public char ch;
        public Node left;
        public Node right;

        public Node(char c, int f) {
            this.ch = c;
            this.freq = f;
        }

        @Override
        public int compareTo(Node o) {
            return this.freq - o.freq;
        }
    }

    private class HuffmanTree {
        public Node root;

        public HuffmanTree(Node x) {
            this.root = x;
        }

        public String getCode(Node current, char c, String code) {
            if (current.left == null && current.right == null) {
                if (current.ch == c)
                    return code;
                else
                    return code + "2";
            }
            String s1 = "";
            String s2 = "";
            if (current.left != null)
                s1 = getCode(current.left, c, code + "0");
            if (current.right != null)
                s2 = getCode(current.right, c, code + "1");

            if (s1.charAt(s1.length() - 1) != '2')
                return s1;
            else
                return s2;
        }
    }


    public void compress(String fileName1, String fileName2) throws IOException {
        String text = readFromFile(fileName1);
        String encodedText = encodeText(text);
        writeToBinaryFile(fileName2, encodedText);
//        return; n5leha btreturn el string 3shan law 3yzha lel gui?
    }

    public String encodeText(String text) {
        //el frequency feh char + el freq  kda el overhead?
        Map<Character, Integer> frequency = getFrequency(text);
        Map<Character, String> huffmanTable = getCodingHuffmanTable(frequency);
//      hena el overhead
//      el overhead by5ls 3nd $
//      char,frequency&char,frequency$
        StringBuilder overhead = new StringBuilder();
        for (Map.Entry<Character, String> entry : huffmanTable.entrySet()) {
            overhead.append(entry.getKey());
            overhead.append(entry.getValue());
            overhead.append("&&");
        }
        overhead.append("$$");
        StringBuilder encodedText = new StringBuilder();
        for (char ch : text.toCharArray()) {
            encodedText.append(huffmanTable.get(ch));
        }
        return overhead.toString() + encodedText.toString();
    }

    public void decompress(String fileName1, String fileName2) throws IOException {
        String text = readFromBinaryFile(fileName1);
        String decodedText = decodeText(text);
        writeToFile(fileName2, decodedText);
//        return; n5leha btreturn el string 3shan law 3yzha lel gui?
    }

    public String decodeText(String text) {
        String[] tokens = text.split("\\$\\$");
        String binary = tokens[1];
        //contains 1char and number
        String[] row = tokens[0].split("&&");
        Map<String, Character> huffmanTable = new HashMap<>();
        for (String s : row) {
            char c = s.charAt(0);
            huffmanTable.put(s.substring(1),s.charAt(0));
        }

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

    //UTILITY FUNCTIONS
    public String readFromFile(String fileName) throws IOException {
        File file = new File(fileName);
        BufferedReader reader = new BufferedReader(new FileReader(file));
        String text;
        StringBuilder sb = new StringBuilder();
        int i = 0;
        while ((text = reader.readLine()) != null) {
            sb.append(text);
            if (i > 0)
                sb.append("\n");
            i++;
        }
        return sb.toString();
    }

    public String readFromBinaryFile(String fileName) throws IOException {
        StringBuilder sb = new StringBuilder();
        try (FileInputStream fis = new FileInputStream(fileName);
             InputStreamReader isr = new InputStreamReader(fis, StandardCharsets.UTF_8)) {
            int b;
            while ((b = isr.read()) != -1) {
                if ((char) b == '$' && sb.charAt(sb.length() - 1) == '$') {
                    sb.append((char) b);
                    break;
                }
                sb.append((char) b);
            }
        }
        try (FileInputStream fis = new FileInputStream(fileName)) {
            fis.skip(sb.length());
            int decimalValue;
            while ((decimalValue = fis.read()) != -1) {
                String binaryRepresentation = Integer.toBinaryString(decimalValue);
                sb.append(binaryRepresentation.substring(1));
            }
        }
        return sb.toString();
    }

    public void writeToFile(String fileName, String text) throws IOException {
        try (FileWriter fileWriter = new FileWriter(fileName)) {
            fileWriter.write(text);
        }
    }

    public void writeToBinaryFile(String fileName, String text) throws IOException {
        String tokens[] = text.split("\\$\\$");
        String codedString = tokens[1];
        tokens[0] = tokens[0] + "$$";
        int i = 0;
        try (FileOutputStream fos = new FileOutputStream(fileName);
             DataOutputStream dos = new DataOutputStream(fos)) {
            fos.write(tokens[0].getBytes(StandardCharsets.UTF_8));
            while (i < codedString.length()) {
                String binary = "1";
                i += 7;
                if (i >= codedString.length()) {
                    binary = binary + codedString.substring(i - 7);
                } else {
                    binary = binary + codedString.substring(i - 7, i);
                }
                int decimalValue = Integer.parseInt(binary, 2);
                byte b = (byte) decimalValue;
                fos.write(b);
            }
        }
    }

    public Map<Character, Integer> getFrequency(String text) {
        Map<Character, Integer> freq = new HashMap<>();
        for (char c : text.toCharArray()) {
            freq.putIfAbsent(c, 0);
            freq.put(c, freq.get(c) + 1);
        }
        return freq;
    }

    public Map<Character, String> getCodingHuffmanTable(Map<Character, Integer> freq) {
        Map<Character, String> table = new HashMap<>();
        // add to priority queue and make it min heap by implementing comparable
        PriorityQueue<Node> pq = new PriorityQueue<>();
        for (Map.Entry<Character, Integer> entry : freq.entrySet()) {
            Node x = new Node(entry.getKey(), entry.getValue());
            pq.add(x);
        }
        while (pq.size() > 1) {
            // smallest in pQueue
            Node right = pq.poll();
            // 2nd smallest pQueue
            Node left = pq.poll();
            // make parent containing the difference in the frequency between left and right
            // and add it to the queue
            Node parent = new Node('\0', left.freq + right.freq);
            parent.right = right;
            parent.left = left;
            pq.add(parent);
        }
        HuffmanTree tree = new HuffmanTree(pq.poll());

        //assign code to each character
        for (Map.Entry<Character, Integer> entry : freq.entrySet()) {
            char c = entry.getKey();
            table.put(c, tree.getCode(tree.root, c, ""));
        }
        return table;
    }

}
