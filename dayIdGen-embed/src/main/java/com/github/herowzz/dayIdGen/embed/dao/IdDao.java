package com.github.herowzz.dayIdGen.embed.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

public class IdDao {

	public static final String INI_TABLE = "CREATE TABLE IF NOT EXISTS %s (id bigint(20) unsigned NOT NULL auto_increment,stub char(1) NOT NULL default '',PRIMARY KEY (id),UNIQUE KEY stub (stub)) ENGINE=MyISAM";
	public static final String REPLACE_NEXTID = "REPLACE INTO %s (stub) VALUES ('a');";
	public static final String SELECT_NEXTID = "SELECT LAST_INSERT_ID();";

	public static final String SELECT_TABLESs = "SHOW TABLES LIKE 'id_%';";
	public static final String DROP_TABLE = "DROP TABLE %s;";

	private DataSource dataSource;

	public void setDataSource(DataSource dataSource) {
		this.dataSource = dataSource;
	}

	public long getNextId(String tableName) throws SQLException {
		long id = -1;
		String fullTableName = "id_" + tableName + "_" + LocalDate.now().format(DateTimeFormatter.BASIC_ISO_DATE);
		try (Connection connection = dataSource.getConnection(); Statement statement = connection.createStatement();) {
			statement.execute(String.format(INI_TABLE, fullTableName));
			statement.execute(String.format(REPLACE_NEXTID, fullTableName));
			try (ResultSet result = statement.executeQuery(SELECT_NEXTID)) {
				if (result.next()) {
					id = result.getLong(1);
				}
			}
		}
		return id;
	}

	public long getNextDateId(String tableName, LocalDate date) throws SQLException {
		long id = -1;
		String fullTableName = "id_" + tableName + "_" + date.format(DateTimeFormatter.BASIC_ISO_DATE);
		try (Connection connection = dataSource.getConnection(); Statement statement = connection.createStatement();) {
			statement.execute(String.format(INI_TABLE, fullTableName));
			statement.execute(String.format(REPLACE_NEXTID, fullTableName));
			try (ResultSet result = statement.executeQuery(SELECT_NEXTID)) {
				if (result.next()) {
					id = result.getLong(1);
				}
			}
		}
		return id;
	}

	public int clearIdTables() throws SQLException {
		List<String> clearTableList = new ArrayList<>();
		try (Connection connection = dataSource.getConnection(); Statement statement = connection.createStatement();) {
			try (ResultSet result = statement.executeQuery(SELECT_TABLESs)) {
				while (result.next()) {
					String tableName = result.getString(1);
					String[] strs = tableName.split("_");
					String date = strs[strs.length - 1];
					LocalDate tbDate = LocalDate.parse(date, DateTimeFormatter.BASIC_ISO_DATE);
					if (tbDate.isBefore(LocalDate.now())) {
						clearTableList.add(tableName);
					}
				}
			}
			for (String tableName : clearTableList) {
				statement.execute(String.format(DROP_TABLE, tableName));
			}
		}
		return clearTableList.size();
	}

}
