/**
 * 
 */
package com.tinycat.search.test;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import org.apache.lucene.search.IndexSearcher;
import org.apache.lucene.search.Query;
import org.apache.lucene.search.Sort;

import com.sun.org.apache.bcel.internal.generic.FNEG;
import com.tinycat.search.IndexHolder;

/**
 * 测试索引过程
 * 
 * @author Winter Lau
 */
public class LuceneTester {
	/**
	 * 测试添加索引
	 * 
	 * @param args
	 * @throws IOException
	 * @throws ParseException
	 */
	public static void main(String[] args) throws IOException {
		IndexHolder holder = IndexHolder.getInstance();
		
//		DiaryVO bo=  new DiaryVO();
//		bo.setId(2l);
//		bo.setAuthor_name("123");
//		bo.setTitle("ggggggggg");
//		
//		holder.optimize(bo.getClass());
//		holder.add(Arrays.asList(bo));
//		holder.delete(Arrays.asList(bo));
		
		
//		Query q = SearchHelper.makeQueryAll(1.0f);
//		List<Searchable> hits = holder.find(DiaryVO.class, q, null, new Sort(), 1, 100);
//		for (Searchable searchable : hits) {
//			DiaryBO d = (DiaryBO) searchable;
//			System.out.println(d.getId() + ";" + d.getTitle() + ";" + d.getContent());
//		}
		
//		//测试数据
//		IndexSearcher indexSearcher = IndexHolder.getInstance().getSearcher(DiaryVO.class);
//		System.out.print(indexSearcher.getIndexReader().maxDoc());//总数
//		System.out.print(indexSearcher.getIndexReader().numDeletedDocs());//删除数
//		System.out.print(indexSearcher.getIndexReader().numDocs());//有效数
		
		
	}
}
