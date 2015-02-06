package com.smzh.fastsort;

import junit.framework.TestCase;

public class SortUtilsTest extends TestCase {

	public void testArraysSort() {
		SortUtils.readRrandomFile();
		SortUtils.arraysSort();
	}

	public void testForkJoinSort() {
		SortUtils.readRrandomFile();
		SortUtils.forkJoinSort();
	}

	public void testSort() {
		SortUtils.readRrandomFile();
		SortUtils.sort();
	}


}
