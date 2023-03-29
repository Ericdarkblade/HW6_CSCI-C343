import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class TrieTest {
    Trie alphaTest;

    @BeforeEach
    void setUp() {
        String[] words = new String[]{"Cat", "Cats", "Catastrophe", "Cream", "Cremate", "Bet", "Bets", "Beta", "A", "Alpha"};
        alphaTest = new Trie();
        for(String word : words){
            alphaTest.insert(word);
        }
    }

    @Test
    void setRoot() {
        TrieNode sampleRootNode = new TrieNode();
        alphaTest.setRoot(sampleRootNode);
        assertEquals(sampleRootNode, alphaTest.getRoot());
    }

    @Test
    void insert() {
        //Character Path exists, word flag only is changed
        //TODO insert word that is within one of the strings from sample array

        //Word does not exist, add new characters
        //TODO insert a word unique word that does not overlap with any of the test strings

        //Word extends upon a word already existing in the trie
        //TODO insert a word that matches one of the test strings but deviates at some character point
    }

    @Test
    void search() {

    }

    @Test
    void delete() {
    }

    @Test
    void generateWordsFromPrefix() {
    }
    // TODO: accuracy tests
}
