package com.shansun.demo.guava;

import static com.google.common.base.Preconditions.checkArgument;
import static com.google.common.collect.Constraints.constrainedList;

import java.util.List;

import com.google.common.collect.Constraint;
import com.google.common.collect.Lists;

/**
 * Լ��
 * 
 * @author: lanbo <br>
 * @version: 1.0 <br>
 * @date: 2012-6-28
 */
public class ConstraintUsage {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		Constraint<String> checkListStr = new Constraint<String>() {

			@Override
			public String checkElement(String element) {
				checkArgument(!element.startsWith("h"), "��������h��ͷ������, [" + element + "]");

				return element;
			}
		};
		
		List<String> list = Lists.newArrayList("li", "hao", "a");
		
		List<String> constraintList = constrainedList(list, checkListStr);
		
		constraintList.add("soso");
		constraintList.add("hanhan"); // throw java.lang.IllegalArgumentException
		
		for(String s : list) {
			System.out.println(s);
		}
	}
}
