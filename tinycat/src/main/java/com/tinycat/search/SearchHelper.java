package com.tinycat.search;

import org.apache.commons.beanutils.PropertyUtils;
import org.apache.commons.io.IOUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.math.NumberUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.lucene.analysis.TokenStream;
import org.apache.lucene.document.*;
import org.apache.lucene.index.Term;
import org.apache.lucene.queryparser.classic.QueryParser;
import org.apache.lucene.search.BooleanQuery;
import org.apache.lucene.search.PhraseQuery;
import org.apache.lucene.search.Query;
import org.apache.lucene.search.TermQuery;
import org.apache.lucene.search.highlight.*;
import org.apache.lucene.util.Version;
import org.wltea.analyzer.core.IKSegmenter;
import org.wltea.analyzer.core.Lexeme;
import org.wltea.analyzer.lucene.IKAnalyzer;

import java.io.IOException;
import java.io.StringReader;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * 搜索工具类 User: Winter Lau Date: 13-1-10 Time: 上午11:54
 */
public class SearchHelper {

	private final static Log log = LogFactory.getLog(SearchHelper.class);
	private final static IKAnalyzer analyzer = new IKAnalyzer();
	private final static BooleanQuery nullQuery = new BooleanQuery();
	private final static Formatter highlighter_formatter = new SimpleHTMLFormatter("<span class=\"highlight\">", "</span>");

	public final static String FN_ID = "___id";
	public final static String FN_CLASSNAME = "___class";
	public final static String FN_ALL_HAVE = "all_have";
	public final static String FN_ALL_HAVE_CONTENT = "allhave";

	private final static List<String> nowords = new ArrayList<String>() {
		{
			try {
				addAll(IOUtils.readLines(SearchHelper.class.getResourceAsStream("/stopword.dic")));
			} catch (IOException e) {
				log.error("Unabled to read stopword file", e);
			}
		}
	};

	private final static List<String> ReserveKeys = new ArrayList<String>() {
		{
			try {
				addAll(IOUtils.readLines(SearchHelper.class.getResourceAsStream("/keywords.dic")));
			} catch (IOException e) {
				log.error("Unabled to read keywords file", e);
			}
		}
	};

	/**
	 * 重整搜索关键短语
	 * 
	 * @param key
	 * @return
	 */
	public static String cleanupKey(String key) {
		if (ReserveKeys.contains(key.trim().toLowerCase()))
			return key;

		StringBuilder sb = new StringBuilder();
		List<String> keys = splitKeywords(key);
		for (String word : keys) {
			if (sb.length() > 0)
				sb.append(' ');
			sb.append(word);
		}

		return sb.toString();
	}

	/**
	 * 生成查询
	 * 
	 * @autor: wn 2014-5-22 下午6:03:45
	 * @param field
	 * @param q
	 * @return
	 */
	public static Query makeQuery(String field, String q) {
		if (StringUtils.isBlank(q) || StringUtils.isBlank(field))
			return nullQuery;
		QueryParser parser = new QueryParser(Version.LUCENE_40, field, analyzer);
		parser.setDefaultOperator(QueryParser.AND_OPERATOR);
		try {
			Query querySinger = parser.parse(q);
			return querySinger;
		} catch (Exception e) {
			TermQuery queryTerm = new TermQuery(new Term(field, q));
			return queryTerm;
		}
	}

	/**
	 * 
	  * 生成整体查询
	  *
	  * @autor: wn  2014-6-12 上午11:21:58
	  * @param boost
	  * @return
	 */
	public static Query makeQueryAll(float boost) {
		return makeQuery(SearchHelper.FN_ALL_HAVE, SearchHelper.FN_ALL_HAVE_CONTENT , 1.0f);
	}
		
		
	
	/**
	 * 生成查询条件
	 * 
	 * @param field
	 * @param q
	 * @param boost
	 * @param machine_action
	 * @return
	 */
	public static Query makeQuery(String field, String q, float boost) {
		if (StringUtils.isBlank(q) || StringUtils.isBlank(field))
			return nullQuery;
		QueryParser parser = new QueryParser(Version.LUCENE_40, field, analyzer);
		parser.setDefaultOperator(QueryParser.AND_OPERATOR);
		try {
			Query querySinger = parser.parse(q);
			querySinger.setBoost(boost);
			return querySinger;
		} catch (Exception e) {
			TermQuery queryTerm = new TermQuery(new Term(field, q));
			queryTerm.setBoost(boost);
			return queryTerm;
		}
	}

	/**
	 * 关键字切分
	 * 
	 * @param sentence
	 *            要分词的句子
	 * @return 返回分词结果
	 */
	public static List<String> splitKeywords(String sentence) {

		List<String> keys = new ArrayList<String>();

		if (StringUtils.isNotBlank(sentence)) {
			StringReader reader = new StringReader(sentence);
			IKSegmenter ikseg = new IKSegmenter(reader, true);
			try {
				do {
					Lexeme me = ikseg.next();
					if (me == null)
						break;
					String term = me.getLexemeText();
					// if(term.length() == 1)
					// continue;
					// if(StringUtils.isNumeric(StringUtils.remove(term,'.')))
					// continue;
					if (nowords.contains(term.toLowerCase()))
						continue;
					keys.add(term);
				} while (true);
			} catch (IOException e) {
				log.error("Unable to split keywords", e);
			}
		}

		return keys;
	}

	/**
	 * 对一段文本执行语法高亮处理
	 * 
	 * @param text
	 *            要处理高亮的文本
	 * @param key
	 *            高亮的关键字
	 * @return 返回格式化后的HTML文本
	 */
	public static String highlight(String text, String key) {
		if (StringUtils.isBlank(key) || StringUtils.isBlank(text))
			return text;
		String result = null;
		try {
			PhraseQuery pquery = new PhraseQuery();
			for (String sk : splitKeywords(key)) {
				pquery.add(new Term("", QueryParser.escape(sk)));
			}
			QueryScorer scorer = new QueryScorer(pquery);
			Highlighter hig = new Highlighter(highlighter_formatter, scorer);
			TokenStream tokens = analyzer.tokenStream(null, new StringReader(text));
			result = hig.getBestFragment(tokens, text);
		} catch (Exception e) {
			log.error("Unabled to hightlight text", e);
		}
		return (result != null) ? result : text;
	}

	/**
	 * 返回文档中保存的对象 id
	 * 
	 * @param doc
	 * @return
	 */
	public static long docid(Document doc) {
		return NumberUtils.toLong(doc.get(FN_ID), 0);
	}

	/**
	 * 获取文档对应的对象类
	 * 
	 * @param doc
	 * @return
	 * @throws ClassNotFoundException
	 */
	public static Searchable doc2obj(Document doc) {
		try {
			long id = docid(doc);
			if (id <= 0)
				return null;
			Searchable obj = (Searchable) Class.forName(doc.get(FN_CLASSNAME)).newInstance();
			obj.setId(id);
			// 属性设置
			List<String> fields = obj.storeFields();
			for (String field : fields) {
				PropertyUtils.setProperty(obj, field, doc.get(field));
			}
			return obj;
		} catch (Exception e) {
			log.error("Unabled generate object from document#id=" + doc.toString(), e);
			return null;
		}
	}

	/**
	 * 将对象转成 Lucene 的文档
	 * 
	 * @param obj
	 *            Java 对象
	 * @return 返回Lucene文档
	 * @throws NoSuchMethodException 
	 * @throws InvocationTargetException 
	 * @throws IllegalAccessException 
	 */
	public static Document obj2doc(Searchable obj) {
		if (obj == null)
			return null;

		Document doc = new Document();
		doc.add(new LongField(FN_ID, obj.id(), Field.Store.YES));
		doc.add(new StoredField(FN_CLASSNAME, obj.getClass().getName()));
		try {
			PropertyUtils.setProperty(obj, FN_ALL_HAVE, FN_ALL_HAVE_CONTENT);
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (NoSuchMethodException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		// 存储字段
		List<String> fields = obj.storeFields();
		if (fields != null)
			for (String fn : fields) {
				Object fv = readField(obj, fn);
				if (fv != null)
					doc.add(obj2field(fn, fv, true));
			}

		// 扩展存储字段
		Map<String, String> eDatas = obj.extendStoreDatas();
		if (eDatas != null)
			for (String fn : eDatas.keySet()) {
				if (fields != null && fields.contains(fn))
					continue;
				String fv = eDatas.get(fn);
				if (fv != null)
					doc.add(obj2field(fn, fv, true));
			}

		// 索引字段
		fields = obj.indexFields();
		if (fields != null)
			for (String fn : fields) {
				String fv = (String) readField(obj, fn);
				if (fv != null) {
					TextField tf = new TextField(fn, fv, Field.Store.NO);
					tf.setBoost(obj.boost());
					doc.add(tf);
				}
			}

		// 扩展索引字段
		eDatas = obj.extendIndexDatas();
		if (eDatas != null)
			for (String fn : eDatas.keySet()) {
				if (fields != null && fields.contains(fn))
					continue;
				String fv = eDatas.get(fn);
				if (fv != null) {
					TextField tf = new TextField(fn, fv, Field.Store.NO);
					tf.setBoost(obj.boost());
					doc.add(tf);
				}
			}

		return doc;
	}

	/**
	 * 访问对象某个属性的值
	 * 
	 * @param obj
	 *            对象
	 * @param field
	 *            属性名
	 * @return Lucene 文档字段
	 */
	private static Object readField(Object obj, String field) {
		try {
			return PropertyUtils.getProperty(obj, field);
		} catch (Exception e) {
			log.error("Unabled to get property '" + field + "' of " + obj.getClass().getName(), e);
			return null;
		}

	}

	private static Field obj2field(String field, Object fieldValue, boolean store) {
		if (fieldValue == null)
			return null;

		if (fieldValue instanceof Date) // 日期
			return new LongField(field, ((Date) fieldValue).getTime(), store ? Field.Store.YES : Field.Store.NO);
		if (fieldValue instanceof Number) // 其他数值
			return new StringField(field, String.valueOf(((Number) fieldValue).longValue()), store ? Field.Store.YES : Field.Store.NO);
		// 其他默认当字符串处理
		return new StringField(field, (String) fieldValue, store ? Field.Store.YES : Field.Store.NO);
	}

}
