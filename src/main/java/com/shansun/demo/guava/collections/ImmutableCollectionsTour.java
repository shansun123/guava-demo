package com.shansun.demo.guava.collections;

import com.google.common.collect.ImmutableSet;

/**
 * Immutable Collections���
 * 
 * @author: lanbo <br>
 * @version: 1.0 <br>
 * @date: 2012-7-2
 */
public class ImmutableCollectionsTour {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// �����޸ļ���
		ImmutableSet<String> COLOR_NAMES = ImmutableSet.of("red", "orange", "yellow", "green", "blue", "purple");
		System.out.println(COLOR_NAMES);
		
		// �����޸ļ��ϵ������÷�
		ImmutableSet<String> GOOGLE_COLORS = 
			ImmutableSet.<String>builder() //
			.addAll(COLOR_NAMES) //
			.add("not-exist") //
			.build();
		
		System.out.println(GOOGLE_COLORS);
	}

}
