package com.example.demo.elastic.lucene;

import com.example.demo.elastic.lucene.utils.LuceneUtil;
import com.mysql.cj.xdevapi.CreateIndexParams;
import org.apache.lucene.document.Document;
import org.apache.lucene.document.Field;
import org.apache.lucene.document.StringField;
import org.apache.lucene.document.TextField;
import org.apache.lucene.index.IndexWriter;
import org.apache.lucene.index.Term;
import org.apache.lucene.search.IndexSearcher;
import org.apache.lucene.search.ScoreDoc;
import org.apache.lucene.search.TermQuery;
import org.apache.lucene.search.TopDocs;
import org.junit.Test;

import java.io.IOException;

public class TestIndexCreateAndSearch {
    @Test
    public void testCreateIndex() {
        IndexWriter indexWriter = LuceneUtil.getIndexWriter();
        try {
            for (int i = 0; i < 10; i++) {
                Document document = new Document();
                document.add(new StringField("id", String.valueOf(i), Field.Store.YES));
                document.add(new StringField("title", "带秀TV四大门派围攻光明顶", Field.Store.YES));
                document.add(new TextField("title2", "不断开发贷款方面都快v的", Field.Store.YES));
                document.add(new StringField("title3", "的奋斗奋斗奋斗", Field.Store.YES));
                document.add(new TextField("content", "今晚，是我最开心，也最难过的晚上。开心是因为小梅，难过也是", Field.Store.YES));
                document.add(new StringField("date", "2019-1-2", Field.Store.YES));
                indexWriter.addDocument(document);
            }
        } catch (IOException e) {
            e.printStackTrace();
            LuceneUtil.rollback(indexWriter);
        }
        LuceneUtil.commit(indexWriter);
    }

    @Test
    public void testSearchIndex() {
        IndexSearcher indexSearcher = LuceneUtil.getIndexSearcher();
        try {
            TopDocs topDocs = indexSearcher.search(new TermQuery(new Term("content", "难过")), 100);
            ScoreDoc[] scoreDocs = topDocs.scoreDocs;
            for (int i = 0; i < scoreDocs.length; i++) {
                ScoreDoc scoreDoc = scoreDocs[i];
                int doc = scoreDoc.doc;
                Document document = indexSearcher.doc(doc);
                System.out.println("this is 分数======>" + scoreDoc.score);
                System.out.println("this is 编号======>" + document.get("id"));
                System.out.println("this is 标题======>" + document.get("title"));
                System.out.println("this is 内容======>" + document.get("content"));
                System.out.println("this is 日期======>" + document.get("date"));
                System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    @Test
    public void testDelete() {
        IndexWriter indexWriter = LuceneUtil.getIndexWriter();
        //indexWriter.deleteAll();  //删除所有
        try {
            indexWriter.deleteDocuments(new Term("id", "0"));
            LuceneUtil.commit(indexWriter);
        } catch (IOException e) {
            e.printStackTrace();
            LuceneUtil.rollback(indexWriter);
        }

    }

    //修改 是先删除再添加
    @Test
    public void testUpdate() {
        IndexWriter indexWriter = LuceneUtil.getIndexWriter();

        Document document = new Document();
        document.add(new StringField("id", String.valueOf(0), Field.Store.YES));
        document.add(new StringField("title", "瘸子", Field.Store.YES));
        document.add(new TextField("content", "与队长的博弈第二季", Field.Store.YES));
        document.add(new StringField("date", "2019-1-2", Field.Store.YES));

        try {
            indexWriter.updateDocument(new Term("id", "1"), document);
            LuceneUtil.commit(indexWriter);
        } catch (IOException e) {
            e.printStackTrace();
            LuceneUtil.rollback(indexWriter);
        }
    }
}
