package com.shansun.demo.guava.collections;

import java.util.List;
import java.util.Map;
import java.util.Set;

import com.google.common.base.Predicate;
import com.google.common.base.Predicates;
import com.google.common.collect.Iterables;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.google.common.collect.Sets;
import com.google.common.primitives.Ints;

/**
 * ���ü��Ϲ�����
 * 
 * @author: lanbo <br>
 * @version: 1.0 <br>
 * @date: 2012-7-2
 */
public class UtilityClassesUsage {

	static List<String>			list;
	static Map<String, Object>	map;
	static Set<String>			set;

	static List<String>			exactly100;

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		useStaticConstructors();

		useIterables();
	}

	static void useStaticConstructors() {
		// ����ʹ�þ�̬���췽��������ָ�����϶����������
		list = Lists.newArrayList();
		map = Maps.newLinkedHashMap();
		set = Sets.newHashSet();
	}

	static void useIterables() {
		Iterable<Integer> concatenated = Iterables.concat(Ints.asList(1, 2, 3), Ints.asList(4, 5, 6), Ints.asList(7, 8, 9));

		System.out.println(concatenated);

		Integer last = Iterables.getLast(concatenated);

		System.out.println(last);

		Predicate<Integer> greaterThanFive = new Predicate<Integer>() {

			@Override
			public boolean apply(Integer input) {
				return input > 5;
			}
		};

		System.out.println(Iterables.all(concatenated, greaterThanFive));

		System.out.println(Iterables.any(concatenated, greaterThanFive));

		// ����
		System.out.println(Iterables.filter(concatenated, greaterThanFive));

		// ����Ƶ��
		System.out.println(Iterables.frequency(concatenated, 5));
	}
}
