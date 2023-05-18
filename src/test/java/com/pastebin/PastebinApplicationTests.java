//package com.pastebin;
//
//import com.pastebin.models.LimitedSnippet;
//import com.pastebin.models.PublicSnippet;
//import com.pastebin.repositories.PublicSnippetRepository;
//import org.junit.jupiter.api.Test;
//import org.springframework.boot.test.context.SpringBootTest;
//
//import static org.junit.jupiter.api.Assertions.assertSame;
//
//@SpringBootTest
//class PastebinApplicationTests {
//
//    final private PublicSnippetRepository publicSnippetRepository;
//
//    PastebinApplicationTests(PublicSnippetRepository publicSnippetRepository) {
//        this.publicSnippetRepository = publicSnippetRepository;
//    }
//
//
//    @Test
//    void addCode() throws Exception {
////        UUID randomUUID = UUID.randomUUID();
////        UUID id = snippetRestController.addNewSnippet(String.valueOf(randomUUID));
////        assertSame(String.valueOf(randomUUID), snippetRestController.getSnippetJsonById(String.valueOf(id)).getCode());
////        Snippet jdbc = new Snippet("asdf");
//        LimitedSnippet private_snippet = new LimitedSnippet(1, 1);
//        PublicSnippet public_snippet = new PublicSnippet("asdf");
//
////        publicSnippetRepository.save(private_snippet);
//    }
//}
