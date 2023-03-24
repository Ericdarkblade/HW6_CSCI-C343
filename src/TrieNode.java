import java.util.ArrayList;
import java.util.HashMap;

public class TrieNode {
    HashMap<Character, TrieNode> children;
    boolean isWord;
    int frequency;

    // TODO: initialize the TrieNode's properties
    public TrieNode() {
        isWord = false;
        frequency = 1;
        children = new HashMap<>();
    }

    boolean isLeaf(){
        return children.size() == 0;
    }

    public String toString () {
        return children.toString();
    }

}
