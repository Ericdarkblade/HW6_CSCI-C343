import java.util.ArrayList;
public class Trie {
    TrieNode root;

    public Trie(){
        root = new TrieNode();
    }

    public Trie(TrieNode root){
        this.root = root;
    }

    // Setters & Getters
    public TrieNode getRoot(){
        return this.root;
    }

    public void setRoot(TrieNode root){
        this.root = root;
    }

    // Actual methods -- part of Lab7
    // TODO:
    void insert(String word) {

    }

    // TODO:
    boolean search(String word) {
        return false;
    }

    /*
    TODO: Remove the TrieNodes associated with the word. There are 3 cases to be concerned with.
        - key is unique: no part of key contains another key nor is the key itself a prefix of another key in the trie: remove all nodes
        - key is prefix key of another key: unmark the leaf node
        - key has at least one other word as a prefix: delete the nodes from the end of the key :p
        This is NOT the delete you implemented in lab.
 */
    void delete(String word){

    }

    // TODO: Gets all possible words with the prefix through traversing the Trie. If it's a word, then turn it into an Entry.
    //       If not, ignore. Put the Entry's into a list and return.
    //       Hint: Look at your MazeSolver with a stack for inspiration for the traversal.
    //       EX: If you have prefix "ca", then it should look at all combinations of the words starting with "ca".
    public ArrayList<Entry> generateWordsFromPrefix(String prefix){
        ArrayList<Entry> ls = new ArrayList<>();
        return ls;
    }

}
