package com.shansun.demo.guava.basicutilities;

import com.google.common.base.Optional;

/**
 * @author: lanbo <br>
 * @version: 1.0 <br>
 * @date: 2012-7-2
 */
public class OptionalUsage {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		Optional<Integer> possible = Optional.of(5);

		// �ж�һ��ֵ�Ƿ����
		System.out.println(possible.isPresent());

		// ��ȡֵ
		System.out.println(possible.get());

		// �趨ֵΪ������
		possible = Optional.absent();

		// ���ֵ�����ڣ��򷵻�or��ָ����ֵ
		System.out.println(possible.or(0));

		// ���ֵ�����ڣ��򷵻�null
		System.out.println(possible.orNull());
	}

}
