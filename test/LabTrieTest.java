import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class LabTrieTest {

    Trie knownGoodTrie;
    @BeforeEach
    public void initKnownGoodTrie() {
        knownGoodTrie = new Trie();
        knownGoodTrie.root.children.put('a', new TrieNode()); // "a"
        knownGoodTrie.root.children.get('a').isWord = true;

        knownGoodTrie.root.children.get('a').children.put('b', new TrieNode()); // "abz"
        knownGoodTrie.root.children.get('a').children.get('b').children.put('z', new TrieNode());
        knownGoodTrie.root.children.get('a').children.get('b').children.get('z').isWord = true;

        knownGoodTrie.root.children.get('a').children.get('b').children.put('d', new TrieNode());
        knownGoodTrie.root.children.get('a').children.get('b').children.get('d').children.put('c', new TrieNode());
        knownGoodTrie.root.children.get('a').children.get('b').children.get('d').children.get('c').children.put('y',new TrieNode());
        knownGoodTrie.root.children.get('a').children.get('b').children.get('d').children.get('c').children.get('y').isWord = true;
    }

    @Test
    // 0 pts
    public void TrieWithHashTableConstructor() {
        Trie testTrie = new Trie();
        assertFalse(testTrie.root.isWord);
        assertTrue(testTrie.root.children.isEmpty());
    }

    @Test
    // 2 pts
    public void insert() {
        Trie testTrie = new Trie();

        testTrie.insert("a");
        assertEquals(true, testTrie.root.children.get('a').isWord,
                "did not properly insert \"a\"");
        for (int i = 'a'; i < 'z'; i++) {
            assertNull(testTrie.root.children.get(i), "added too many children to the root");
        }

        testTrie.insert("abz");
        assertEquals(true, testTrie.root.children.get('a').isWord,
                "expected isWord to be false");
        assertEquals(false, testTrie.root.children.get('a').children.get('b').isWord,
                "expected isWord to be false");
        assertEquals(true, testTrie.root.children.get('a').children.get('b').children.get('z').isWord,
                "expected isWord to be true");

        testTrie.insert("abdcy");
        assertEquals(true, testTrie.root.children.get('a').isWord,
                "expected isWord to be false");
        assertEquals(false, testTrie.root.children.get('a').children.get('b').isWord,
                "expected isWord to be false");
        assertEquals(true, testTrie.root.children.get('a').children.get('b').children.get('z').isWord, // z from last insert
                "expected isWord to be true");
        assertEquals(false, testTrie.root.children.get('a').children.get('b').children.get('d').isWord,
                "expected isWord to be true");
        assertEquals(false, testTrie.root.children.get('a').children.get('b').children.get('d').children.get('c').isWord,
                "expected isWord to be true");
        assertEquals(true, testTrie.root.children.get('a').children.get('b').children.get('d').children.get('c').children.get('y').isWord,
                "expected isWord to be true");
    }

    @Test
    // 2 pts
    public void search() {
        assertEquals(true, knownGoodTrie.search("a"));
        assertEquals(true, knownGoodTrie.search("abz"));
        assertEquals(true, knownGoodTrie.search("abdcy"));

        assertEquals(false, knownGoodTrie.search("af"));
        assertEquals(false, knownGoodTrie.search("ballon"));
    }

}