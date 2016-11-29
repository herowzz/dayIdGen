package com.github.herowzz.dayIdGen.test;

import java.util.concurrent.CountDownLatch;

import com.alibaba.druid.pool.DruidDataSource;
import com.github.herowzz.dayIdGen.embed.DayIdGen;
import com.github.herowzz.dayIdGen.embed.dao.IdDao;

public class TestDayIdGen {

	public static final String DRIVER_MYSQL = "com.mysql.cj.jdbc.Driver";
	public static final String URL = "jdbc:mysql://127.0.0.1:3306/test111?createDatabaseIfNotExist=true&useUnicode=true&characterEncoding=utf-8&useSSL=false&useLegacyDatetimecode=false&serverTimezone=PRC";
	public static final String USER = "root";
	public static final String PASSWORD = "caiot";

	public static void main(String[] args) throws Exception {
		DruidDataSource dataSource = new DruidDataSource();
		dataSource.setUrl(URL);
		dataSource.setUsername(USER);
		dataSource.setPassword(PASSWORD);
		dataSource.setInitialSize(1);
		dataSource.setMinIdle(1);
		dataSource.setMaxActive(20);
		dataSource.setMaxWait(60000);
		dataSource.setTimeBetweenEvictionRunsMillis(60000);
		dataSource.setMinEvictableIdleTimeMillis(300000);
		dataSource.init();

		IdDao idDao = new IdDao();
		idDao.setDataSource(dataSource);

		DayIdGen idGen = new DayIdGen();
		idGen.setIdDao(idDao);
		idGen.setMinDigit(5);
		idGen.setPrefix("RK");
		idGen.setName("ccc");

		CountDownLatch latch = new CountDownLatch(200);
		long begin = System.currentTimeMillis();
		for (int i = 0; i < 200; i++) {
			new Thread(() -> {
				for (int j = 0; j < 10000; j++) {
					try {
						String id = idGen.getNextDateId();
						System.out.println(id);
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
				latch.countDown();
			}).start();
		}
		try {
			latch.await();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		long end = System.currentTimeMillis();
		System.out.println("执行耗时:" + (end - begin) / 1000 + "s");
	}

}
