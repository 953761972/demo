package com.example.demo.elastic.lucene;

import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.apache.lucene.document.Document;
import org.apache.lucene.document.Field;
import org.apache.lucene.document.TextField;
import org.apache.lucene.index.DirectoryReader;
import org.apache.lucene.index.IndexWriter;
import org.apache.lucene.index.IndexWriterConfig;
import org.apache.lucene.queryparser.classic.ParseException;
import org.apache.lucene.queryparser.classic.QueryParser;
import org.apache.lucene.search.IndexSearcher;
import org.apache.lucene.search.Query;
import org.apache.lucene.search.ScoreDoc;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.FSDirectory;
import org.apache.lucene.util.IOUtils;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import static org.junit.Assert.assertEquals;

/**
 * @Author XZQ
 * @Date 2021/11/13 19:34
 **/
public class luceneTest {

    private String filePath="/Volumes/FastSSD/files/lucene/luceneTest";
    public static void main(String[] args) throws IOException, ParseException {
        luceneTest lt=new luceneTest();
        lt.createIndex();
        lt.search();
    }
    public void createIndex() throws IOException {
        Analyzer analyzer = new StandardAnalyzer();
        String text = "This is the text to be indexed.";
        File f=new File(filePath);
        f.mkdirs();
        System.out.println(f.getAbsolutePath());
        Directory directory = FSDirectory.open(f.toPath());
        IndexWriterConfig config = new IndexWriterConfig(analyzer);
        IndexWriter iwriter = new IndexWriter(directory, config);
        Document doc = new Document();
        doc.add(new TextField("fieldname", text, Field.Store.YES));
        doc.add(new TextField("fieldname1", text, Field.Store.YES));
        doc.add(new TextField("fieldname2", text, Field.Store.YES));
        iwriter.addDocument(doc);
        iwriter.commit();
        iwriter.close();
    }
    public void search() throws IOException, ParseException {
        Analyzer analyzer = new StandardAnalyzer();
        File f=new File(filePath);
        f.mkdirs();
        System.out.println(f.getAbsolutePath());
        Directory directory = FSDirectory.open(f.toPath());
        // Now search the index:
        DirectoryReader ireader = DirectoryReader.open(directory);
        IndexSearcher isearcher = new IndexSearcher(ireader);
        // Parse a simple query that searches for "text":
        QueryParser parser = new QueryParser("fieldname", analyzer);
        Query query = parser.parse("text");
        ScoreDoc[] hits = isearcher.search(query, 10).scoreDocs;
        System.out.println("hits:"+hits.length);
        // Iterate through the results:
        for (int i = 0; i < hits.length; i++) {
            Document hitDoc = isearcher.doc(hits[i].doc);
            System.out.println(hitDoc.get("fieldname"));
        }
        ireader.close();
        directory.close();
    }

}
