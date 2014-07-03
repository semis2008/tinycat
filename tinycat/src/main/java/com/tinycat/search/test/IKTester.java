package com.tinycat.search.test;

import com.tinycat.search.SearchHelper;


/**
 * 测试 IK 分词器
 * User: Winter Lau
 * Date: 13-1-10
 * Time: 上午11:48
 */
public class IKTester {

    public static void main(String[] args) {
//    	test_highlight();
    	test_split();
    }

    protected static void test_highlight() {
        String text = "SQL server 是最好的 数据库 应用服务器";
        System.out.println("RESULT:" + SearchHelper.highlight(text, "sql server"));
    }

    protected static void test_split(){
        String text = "android 刷机这是一个什么研发的世界啊？我去昨天看型芯";
        long ct = System.currentTimeMillis();
        for(String word : SearchHelper.splitKeywords(text)){
            System.out.println(word);
        }
        System.out.printf("TIME %d\n", (System.currentTimeMillis() - ct));
    }

}
