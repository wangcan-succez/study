package com.succez.study;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Deque;
import java.util.LinkedList;
import java.util.List;

class TNode {
	public TNode(String value) {
		this.value = value;
	}
	String  value;
    TNode   left,right;
}

public class Solution {
	
	/**
	 * 将文件内容转换成byte数组返回,如果文件不存在或者读入错误返回null
	 */
	public static byte[] file2buf(File fobj) {
		if(fobj == null || !fobj.exists()) {
			return null;
		}
		FileInputStream fis = null;
		ByteArrayOutputStream bos = null;
		byte[] res = null;
		byte[] buffer = new byte[1024];
		int len = -1;
		
		try {
			fis = new FileInputStream(fobj);
			bos = new ByteArrayOutputStream();
			while((len = fis.read(buffer))!=-1) {
				bos.write(buffer,0,len);
			}
			res = bos.toByteArray();
		} catch (IOException e) {
			return null;
			
		}finally{
			
				try {
					if(bos != null)
						bos.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
				
				try {
					if(fis != null)
						fis.close();
				}catch(IOException e) {
					e.printStackTrace();
				}
		}
		return res;
	}
	
	
	public static String intToHex(int num) {
		//内存中最大为位数为64位，所以有16个八进制位
		char[] a = new char[16];
		int len = a.length-1;
		String res = "";
		//使用辗转相除法
		while(num != 0) {
			
			int n = num%16;
			//如果n大于9，则16进制字符为A~F,否则为0~9,因为数字是反的，所以在a数组 中从后往前存。
			if(n >= 10) {
				a[len] = (char)(n-10+'A');
			}else {
				a[len] = (char)(n+'0');
			}
			len--;
			num /= 16;
		}
		//输出结果为0x形式
		res = "0x";
		//最后把字符连接成字符串
		for(int i=len+1;i<a.length;i++) {
			res+=a[i];
		}
		return res;
	}
	public static List<String> treeLevel(TNode tree, int level){
		//如果根节点为空直接返回null
		if(tree == null) return null;
		//创建两个队列queue1,queue2。queue1用来遍历二叉树，queue2用来存放同一层的节点
		Deque<TNode> queue1 = new LinkedList<TNode>();
		Deque<TNode> queue2 = new LinkedList<TNode>();
		List<String> values = new ArrayList<String>();
		//n为当前层次计数器
		int n = 1;
		queue2.add(tree);
		while(!queue2.isEmpty()) { //当queue2为空时，说明某一层的节点全为空
			//当层次计数器和参数level一样时，queue2里的节点即为第level层的所有节点
			if(n == level) break;
			if(queue1.isEmpty()) {
				queue1.addAll(queue2);
				queue2.clear();
			}
			TNode node = queue1.poll();
			if(node.left!=null) {
				queue2.add(node.left);
			}
			if(node.right!=null) {
				queue2.add(node.right);
			}
			if(queue1.isEmpty())
				n++;
		}
		for(TNode node : queue2) {
			values.add(node.value);
		}
		return values;
	}
	
	public static TNode initTree() {
		TNode root = new TNode("A");
		TNode node2 = new TNode("B");
		TNode node3 = new TNode("D");
		TNode node4 = new TNode("G");
		TNode node5 = new TNode("H");
		TNode node6 = new TNode("C");
		TNode node7 = new TNode("F");
		root.left = node2;
		root.right = node3;
		node2.left = node4;
		node2.right = node5;
		node3.left = node6;
		node4.right = node7;
		return root;
		
	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		File f1 = new File("D:\\工作区\\txt\\test1.txt");
		byte[] res = file2buf(f1);
		System.out.println(Arrays.toString(res));
		
//		System.out.println(intToHex(102));
//		System.out.println(intToHex(436789));
//		System.out.println(intToHex(987654321));
//		System.out.println(intToHex(436789637));
//		
//		TNode root = initTree();
//		List<String> values = treeLevel(root,4);
//		System.out.println(values);
		
	}

}
