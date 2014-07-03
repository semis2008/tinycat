package com.tinycat.util;

import java.lang.reflect.Method;

import org.apache.log4j.Logger;

public class SortUtil {
	public static int swapCount = 0;
	public static int arrSize = 0;
	public static int actCount = 0;
	public static Logger logger = Logger.getLogger(SortUtil.class);

	/**
	 * 插入排序（假定i前面的元素都已经排序完毕，依次比较i和他前面的元素，比i大，则和i交换，保证前i个元素是有序的）
	 * 
	 * @autor: wn 2014-6-3 下午5:35:13
	 * @param dest
	 */
	public void insertionSort(int[] dest) {
		for (int i = 0; i < dest.length; i++) {
			for (int j = i; j > 0 && dest[j - 1] > dest[j]; j--) {
				actCount++;
				swap(dest, j, j - 1);
			}
		}
	}

	/**
	 * 思想：对数组中元素从一边开始两两比较大小，将较大的后移，这样一次遍历之后，最大的就沉到了数组的结尾。对前面的n-1个元素继续遍历，直到n=1.
	 * 优化策略： 1.增加标志，当一次遍历没有进行过交换时，说明已经完成了排序，直接结束。
	 * 2.记录最后一次交换的位置，表示在该位置之后的数组部分已经完成了排序，只对位置前面的元素进行排序。这在数组 部分有序 的时候，会提高效率
	 * 
	 * @autor: wn 2014-6-4 上午10:07:01
	 * @param dest
	 */
	public void bubbleSort(int[] dest) {
		boolean swapFlag = false;
		int lastSwapIndex = dest.length - 1;
		int temp = 0;
		while (lastSwapIndex > 0) {
			for (int j = 0; j < lastSwapIndex; j++) {
				actCount++;
				if (dest[j] > dest[j + 1]) {
					swap(dest, j, j + 1);
					swapFlag = true;
					temp = j;
				}
			}
			if (!swapFlag)
				break;
			lastSwapIndex = temp;
		}
	}

	/**
	 * 希尔排序 分组的插入排序。设定步长，进行分组，对每一个小组进行插入排序 步长设定为
	 * 步长序列{1,4,13,40...}==gap*3+1，效率O(n^(3/2))
	 * 
	 * @autor: wn 2014-6-5 下午1:27:14
	 * @param dest
	 */
	public void shellSort(int[] dest) {
		int h = 1;
		while (h < dest.length / 3) {
			h = h * 3 + 1; // <O(n^(3/2)) by Knuth,1973>: 1, 4, 13, 40, 121, ...
		}
		for (; h >= 1; h /= 3) {
			for (int k = 0; k < h; k++) {
				for (int i = h + k; i < dest.length; i += h) {
					for (int j = i; j >= h && dest[j] < dest[j - h]; j -= h) {
						swap(dest, j, j - h);
					}
				}
			}
		}
	}

	/**
	 * 选择排序。 和插入排序相反，选择排序是从无序序列中找到最小的，放到有序序列的末尾，因为会交换位置，所以不是一个稳定的算法。
	 * 
	 * @autor: wn 2014-6-5 下午1:35:04
	 * @param dest
	 */
	public void selectSort(int[] dest) {
		for (int i = 0; i < dest.length; i++) {
			int minIndex = i;
			for (int j = i + 1; j < dest.length; j++) {
				actCount++;
				if (dest[j] < dest[minIndex])
					minIndex = j;
			}
			swap(dest, i, minIndex);
		}
	}

	/**
	 * 归并排序 把数组不断的分成两部分，分别进行排序，这两部分有序之后，进行合并，完成排序
	 * 
	 * @autor: wn 2014-6-5 下午1:44:22
	 * @param dest
	 */
	public void mergeSort(int[] src, int[] dest, int low, int high, int off) {
		int length = high - low;
		// 在数组元素较少的时候，使用插入排序算法进行排序（元素小于7个）
		if (length < 7) {
			for (int i = low; i < high; i++)
				for (int j = i; j > low && dest[j - 1] > dest[j]; j--)
					swap(dest, j, j - 1);
			return;
		}
		// 把src分割成两部分，分别归并排序
		int destLow = low;
		int destHigh = high;
		low += off;
		high += off;
		int mid = (low + high) >>> 1; // 无符号右移1位，相当于除以2的1次方
		mergeSort(dest, src, low, mid, -off); // 左边归并完毕，变为有序
		mergeSort(dest, src, mid, high, -off); // 右边归并完毕，变为有序
		// 如果左侧的最大值<=右侧的最小值，则表示list已经完成排序，直接将排序好的src
		// 拷贝到dest中即可
		if (src[mid - 1] <= src[mid]) {
			System.arraycopy(src, low, dest, destLow, length);
			return;
		}
		// 把src归并到dest中。src左右list，分别取值进行对比，将其中较小的元素放入dest.
		for (int i = destLow, p = low, q = mid; i < destHigh; i++) {
			if (q >= high || p < mid && src[p] <= src[q])
				dest[i] = src[p++];
			else
				dest[i] = src[q++];
		}
	}

	/**
	 * 快速排序 1.从数列中选择一个基准数 2.在一次调整中，将比基准数大的数移动到基准数后面，比基准数小的移动到基准数前面
	 * 3.调整完后，依据基准数现在的位置，将数组分成两部分，重复第二步，直到各区间只有一个数
	 * 
	 * @autor: wn 2014-6-5 下午1:59:35
	 * @param dest
	 * @param l
	 * @param r
	 */
	public void quickSort(int[] s) {
		doQuickSort(s, 0, s.length - 1);
	}

	private void doQuickSort(int[] s, int l, int r) {
		actCount++;
		if (l < r) {
			// Swap(s[l], s[(l + r) / 2]); //将中间的这个数和第一个数交换
			int i = l, j = r, x = s[l];
			while (i < j) {
				while (i < j && s[j] >= x)
					// 从右向左找第一个小于x的数
					j--;
				if (i < j)
					s[i++] = s[j];
				while (i < j && s[i] < x)
					// 从左向右找第一个大于等于x的数
					i++;
				if (i < j)
					s[j--] = s[i];
			}
			s[i] = x;
			doQuickSort(s, l, i - 1); // 递归调用
			doQuickSort(s, i + 1, r);
		}
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	

	static int multiply(int n) {
		if (n == 1 || n == 0)
			return n;
		else
			return n * multiply(n - 1);
	}

	private void swap(int[] list, int i, int j) {
		swapCount++;
		int temp = list[i];
		list[i] = list[j];
		list[j] = temp;
	}

	public static void main(String args[]) {
		int[] descArr = { 9, 8, 7, 6, 5, 4, 3, 2, 1 };
		int[] ascArr = { 1, 2, 3, 4, 5, 6, 7, 8, 9 };
		// doSort(descArr,"insertionSort");
		doSort(descArr, "qSort");
	}

	private static void doSort(int[] arr, String method) {
		arrSize = arr.length;
		printArray(arr, "before");
		try {
			Class<?> sortClass = Class.forName("com.wnJava.util.SortUtil");
			SortUtil sortUtil = (SortUtil) sortClass.newInstance();
			Method sortMethord = sortClass.getMethod(method, int[].class);
			sortMethord.invoke(sortUtil, arr);
		} catch (Exception e) {
			e.printStackTrace();
		}
		printArray(arr, "after");
		printInfo();
	}

	private static void printArray(int[] arr, String info) {
		logger.info(info);
		for (int i : arr) {
			System.out.print(i + "-");
		}
		System.out.println("");
	}

	private static void printInfo() {
		logger.info("数组大小：" + arrSize);
		logger.info("迭代次数：" + actCount);
		logger.info("交换次数：" + swapCount);
	}
}
