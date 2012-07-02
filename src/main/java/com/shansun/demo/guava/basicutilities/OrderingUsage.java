package com.shansun.demo.guava.basicutilities;

import java.util.List;

import javax.annotation.Nullable;

import com.google.common.base.Function;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.Lists;
import com.google.common.collect.Ordering;
import com.google.common.primitives.Ints;

/**
 * ������÷�
 * 
 * @author: lanbo <br>
 * @version: 1.0 <br>
 * @date: 2012-7-2
 */
public class OrderingUsage {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// �����������
		Ordering<String> byLengthOrdering = new Ordering<String>() {

			@Override
			public int compare(String left, String right) {
				return Ints.compare(left.length(), right.length());
			}
		}.reverse().nullsFirst();

		List<String> list = Lists.newArrayList("hello", "world", "lan", "bo", "shansun");

		// ��ָ����������
		System.out.println(byLengthOrdering.isOrdered(list));

		ImmutableList<String> immutableList = byLengthOrdering.immutableSortedCopy(list);

		System.out.println(immutableList);

		// ��Ĭ�Ϲ�������
		ImmutableList<String> immutableList2 = Ordering.natural().immutableSortedCopy(list);

		// ImmutableList�������޸�: java.lang.UnsupportedOperationException
		// immutableList2.add("sfg");

		System.out.println(immutableList2);

		List<OrderingObject> list2 = Lists.newArrayList(new OrderingObject("hello"), new OrderingObject("world"), new OrderingObject("shansun"));

		// ʹ��toString����Ĭ�Ϲ�������
		@SuppressWarnings("static-access")
		ImmutableList<OrderingObject> immutableList3 = Ordering.natural().usingToString().immutableSortedCopy(list2);

		System.out.println(immutableList3);

		// ʹ��onResultOf��Function���ʹ��
		Ordering<Foo> ordering = Ordering.natural().nullsFirst().onResultOf(new Function<Foo, String>() {

			@Override
			public String apply(Foo input) {
				return input.sortedBy;
			}
		});

		List<Foo> list3 = Lists.newArrayList(new Foo("abc"), new Foo("defg"), new Foo("hijkl"));

		System.out.println(ordering.immutableSortedCopy(list3));

		// ��ȡ����ǰk����ֵ
		System.out.println(ordering.greatestOf(list3, 2));

		// ��ȡ������ֵ
		System.out.println(ordering.max(list3));

		// ��ȡ���޸�List
		List<Foo> mutableList = ordering.sortedCopy(list3);

		mutableList.add(new Foo("12345"));

		System.out.println(mutableList);
	}

	static class Foo {
		@Nullable
		String	sortedBy;
		int		notSortedBy;

		public Foo(String sortedBy, int notSortedBy) {
			super();
			this.sortedBy = sortedBy;
			this.notSortedBy = notSortedBy;
		}

		public Foo(String sortedBy) {
			super();
			this.sortedBy = sortedBy;
		}

		@Override
		public String toString() {
			return "Foo [sortedBy=" + sortedBy + "]";
		}
	}

	static class OrderingObject {
		private String	value;

		public OrderingObject(String val) {
			this.value = val;
		}

		@Override
		public String toString() {
			return value;
		}
	}
}
