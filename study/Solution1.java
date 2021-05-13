/**
 * @author wangcan
 * @createdate 2021/5/12
 */
package com.succez.study;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
/**
 * 2021/05/12 wangcan 基础开发的第一道练习题:对流的操作
 * 学习目标：掌握对流的基本操作和各个方法的使用区别；熟悉对资源释放的代码规范。
 */
public class Solution1 {
	
	/**
	 * 2021/05/12 wangcan 读取文件的字节流，把文件的数据读取为字节的形式存入byte数组
	 * 
	 * @param fobj 所要转换为byte数组的源文件
	 * 
	 * @return 整个文件数据的byte数组,如果文件不存在或在读取时发生错误则为null
	 */
	public static byte[] file2buf(File fobj) {
		if(fobj == null || !fobj.exists()) {
			return null;
		}
//		FileInputStream fis = null;
		ByteArrayOutputStream bos = null;
		byte[] res = null;
		byte[] buffer = new byte[1024];
		int len = -1;
		/* 2021/05/12 wangcan
		 * 在过去的 try-catch-finally 结构中，通常关闭资源是放在finally中的
		 * 但是如果在try和finally中都抛出异常，那么finally中的异常会压制try中的异常
		 * 注意点 ：实现了AutoCloseable接口的类才会在try结束时自动调用close方法，
		 * 而这个动作会早于finally里调用的方法 
		 */
		try (
				FileInputStream fis = new FileInputStream(fobj)
			){
//			fis = new FileInputStream(fobj);
				bos = new ByteArrayOutputStream();
				//将读入缓冲区的字节到字节输出流中
				while(-1!=(len = fis.read(buffer))) {
					bos.write(buffer,0,len);
			}
				res = bos.toByteArray();
		} catch (IOException e) {
			return null;
		}
		return res;
	}

}
