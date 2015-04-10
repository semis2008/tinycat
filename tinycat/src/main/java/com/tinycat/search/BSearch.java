package com.tinycat.search;

import org.hibernate.validator.cfg.defs.MinDef;

/**
 * 
 * 二分查找
 * 
 * @author wn
 * @version 1.0.0 BSearch.java 2014-9-17 下午2:37:30
 */
public class BSearch {
	
	public static void main(String args[]) {
		int[] dest = {1};
		int destInt = 1;
		
		int resIndex = 0;
		resIndex = bS(dest, 0, dest.length-1, destInt);
		 
		System.out.print("the destInt index is ："+resIndex); 
		
		
	}
	public static int bS(int[] arr,int start,int end,int val) {
		if(end<start)
			return -1;
		int mid = (start+end)/2;
		if(val==arr[mid])
			return mid;
		else if(val>arr[mid]) 
			return bS(arr, mid+1, end, val);
		else if(val<arr[mid]) 
			return bS(arr, start, mid-1, val);
		return -1;
		
	}
}
 