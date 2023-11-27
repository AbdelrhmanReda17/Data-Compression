public class HuffmanNode implements Comparable<HuffmanNode> {
        public int freq;
        public char ch;
        public HuffmanNode left;
        public HuffmanNode right;

        public HuffmanNode(char c, int f) {
            this.ch = c;
            this.freq = f;
        }

        @Override
        public int compareTo(HuffmanNode o) {
            return this.freq - o.freq;
        }
   }
