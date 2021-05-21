import java.io.*;
import opennlp.tools.namefind.NameFinderME;
import opennlp.tools.namefind.TokenNameFinderModel;
import opennlp.tools.tokenize.TokenizerME;
import opennlp.tools.tokenize.TokenizerModel;
import opennlp.tools.util.Span;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
public class Main {
    public static void main(String[] args) throws IOException {
        Document doc = Jsoup.connect("https://opennlp.apache.org/books-tutorials-and-talks.html").get();
        String text = doc.body().text();
        File pathOfNERPerson = new File("src\\main\\resources\\en-ner-person.bin");
        File pathOfToken = new File("src\\main\\resources\\en-token.bin");
        TokenNameFinderModel nameFinderModel = new TokenNameFinderModel(pathOfNERPerson);
        TokenizerModel tokenizerModel = new TokenizerModel(pathOfToken);
        NameFinderME nameFinder = new NameFinderME(nameFinderModel);
        TokenizerME tokenizerME = new TokenizerME(tokenizerModel);
        String[] teksti = tokenizerME.tokenize(text);
        Span[] tekstSpan = nameFinder.find(teksti);
        System.out.println("Names found:");
        for(Span s: tekstSpan)
            System.out.println(teksti[s.getStart()] + " " +  teksti[s.getStart()+1]);
    }
}

