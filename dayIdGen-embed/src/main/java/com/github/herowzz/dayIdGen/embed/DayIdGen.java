package com.github.herowzz.dayIdGen.embed;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import com.github.herowzz.dayIdGen.embed.dao.IdDao;

public class DayIdGen {

	private IdDao idDao;

	/**
	 * name to generate db table name
	 */
	private String name;

	/**
	 * prefix string to begin with the id<br>
	 * if the id is 3 and prefix is FK, than the final id is FK3
	 */
	private String prefix = "";

	/**
	 * id with the min digit, will begin with 0<br>
	 * if the id is 3 and minDigit is 3, than the final id is 003
	 */
	private int minDigit = 3;

	public String getNextId() throws Exception {
		long id = idDao.getNextId(name);
		return prefix + String.format("%0" + minDigit + "d", id);
	}

	public String getNextDateId() throws Exception {
		LocalDate now = LocalDate.now();
		long id = idDao.getNextDateId(name, now);
		return prefix + now.format(DateTimeFormatter.ofPattern("yyMMdd")) + String.format("%0" + minDigit + "d", id);
	}

	public int clearTables() throws Exception {
		return idDao.clearIdTables();
	}

	public void setPrefix(String prefix) {
		this.prefix = prefix;
	}

	public void setMinDigit(int minDigit) {
		this.minDigit = minDigit;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setIdDao(IdDao idDao) {
		this.idDao = idDao;
	}

}
