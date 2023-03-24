import java.util.ArrayList;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.Stack;

public class Trie {
    TrieNode root;

    public Trie() {
        root = new TrieNode();
    }

    public Trie(TrieNode root) {
        this.root = root;
    }

    // Setters & Getters
    public TrieNode getRoot() {
        return this.root;
    }

    public void setRoot(TrieNode root) {
        this.root = root;
    }

    // Actual methods -- part of Lab7
    // TODO:
//    Imported from Lab7 (Never got tested or graded.
    void insert(String word) {
        insertHelper(root, word);
    }

    private void insertHelper(TrieNode node, String word) {
        if (!word.equals("")) { //Word is not empty
            char nextChar = word.charAt(0); //next character
            String nextWord = word.substring(1); //Should reduce word till empty

            TrieNode nextChild;

            if (node.children.get(nextChar) == null) {
                nextChild = new TrieNode(); //Make New Node
                node.children.put(nextChar, nextChild);
            } else {
                nextChild = node.children.get(nextChar); //Set nextChild to next character
                nextChild.frequency++; //TODO CHECK THIS LINE FOR FREQUENCY ERRORS
            }

            insertHelper(nextChild, nextWord); //Go Next
        } else {
            node.isWord = true; //Only reach here if we have hit the last character.
        }
    }

    // TODO:
//    Imported from Lab7
    boolean search(String word) {
        return searchHelper(root, word);
    }

    private boolean searchHelper(TrieNode node, String word) {
        if (!word.equals("")) {
            char nextChar = word.charAt(0); //next character
            String nextWord = word.substring(1); //Should reduce word till empty

            TrieNode nextChild;

            if (node.children.get(nextChar) == null) {
                return false;
            } else {
                nextChild = node.children.get(nextChar);
            }

            return searchHelper(nextChild, nextWord);
        } else {
            return node.isWord;
        }
    }

    /*
    TODO: Remove the TrieNodes associated with the word. There are 3 cases to be concerned with.
        - key is unique: no part of key contains another key nor is the key itself a prefix of another key in the trie: remove all nodes
        - key is prefix key of another key: unmark the leaf node
        - key has at least one other word as a prefix: delete the nodes from the end of the key :p
        This is NOT the delete you implemented in lab.
 */
    void delete(String word) {
        TrieNode currentNode = this.root;
        char[] wordChars = word.toCharArray();
        ArrayList<TrieNode> nodePath = new ArrayList<>();
        for (char currentCharacter : wordChars) {
            currentNode = currentNode.children.get(currentCharacter);
            nodePath.add(currentNode);
        }
        boolean hasChildren = !currentNode.isLeaf();
        if (hasChildren) {
            currentNode.isWord = false;
        } else {
            leafDelete(currentNode, wordChars, nodePath);
        }
    }

    private void leafDelete(TrieNode currentNode, char[] wordChars, ArrayList<TrieNode> nodePath) {
        for (int i = wordChars.length - 1; i > 0; i--) {
            char currentCharacter = wordChars[i];
            TrieNode parent = nodePath.get(i - 1);
            parent.children.remove(currentCharacter);
            if (parent.isLeaf()) {
                currentNode = parent;
            } else {
                break;
            }
        }
    }


    // TODO: Gets all possible words with the prefix through traversing the Trie. If it's a word, then turn it into an Entry.
    //       If not, ignore. Put the Entry's into a list and return.
    //       Hint: Look at your MazeSolver with a stack for inspiration for the traversal.
    //       EX: If you have prefix "ca", then it should look at all combinations of the words starting with "ca".
    public ArrayList<Entry> generateWordsFromPrefix(String prefix) {
        ArrayList<Entry> autoWords = new ArrayList<>();
        TrieNode currentNode = this.root;
        char[] prefixChars = prefix.toCharArray();
        for (char currentCharacter : prefixChars) {
            currentNode = currentNode.children.get(currentCharacter);
            if (currentNode == null){
                return autoWords; //Prefix does not exist
            }
        }
        generateWordsFromNode(currentNode, prefix, autoWords);
        return autoWords;
    }

    private void generateWordsFromNode(TrieNode node, String prefix, ArrayList<Entry> autoWords){
        if (node.isWord){
            autoWords.add(new Entry(node.frequency, prefix));
        }
        for (char postCharacters : node.children.keySet()){
            TrieNode childNode = node.children.get(postCharacters);
            generateWordsFromNode(childNode, prefix + postCharacters, autoWords);
        }
    }

}