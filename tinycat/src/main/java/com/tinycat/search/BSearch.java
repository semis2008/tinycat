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
		int[] dest = {1,2,3,4,5,6,7,8,9};
		int destInt = 12;
		
		int resIndex = 0;
		
		int midIndex = dest.length>>>1;
		int midVal = dest[midIndex];
		
		if(midVal==destInt) {
			resIndex = midIndex;
		}else if(midVal>destInt){
			resIndex = doBSearch(dest, destInt, 0, midIndex);
		}else if(midVal<destInt) {
			resIndex = doBSearch(dest, destInt, midIndex, dest.length);
		}
			
		
		System.out.print("the destInt index is ："+resIndex); 
		
		
	}
	
	
	private static int doBSearch(int[] arr,int destInt,int l,int r) {
		if(l<=r) {
		
			int resIndex = -1;
			int midIndex = (l+r)>>>1;
			int midVal = arr[midIndex];
			if(midVal==destInt) {
				return midIndex;
			}else if(midVal>destInt){
				resIndex = doBSearch(arr, destInt, l, midIndex);
			}else if(midVal<destInt) {
				resIndex = doBSearch(arr, destInt, midIndex, r);
			}
			return resIndex;
		}else {
			return -1;
		}
		
	}
}
