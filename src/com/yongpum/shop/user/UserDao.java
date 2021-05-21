package com.yongpum.shop.user;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.Properties;

import javax.sql.DataSource;
import org.apache.commons.dbcp2.BasicDataSource;

public class UserDao {
	
	private DataSource dataSource;
	
	public UserDao() throws Exception {
		Properties properties = new Properties();
		properties.load(this.getClass().getResourceAsStream("/com/yongpum/shop/db.properties"));
		BasicDataSource basicDataSource = new BasicDataSource();
		basicDataSource.setDriverClassName(properties.getProperty("driverClass"));
		basicDataSource.setUrl(properties.getProperty("url"));
		basicDataSource.setUsername(properties.getProperty("user"));
		basicDataSource.setPassword(properties.getProperty("password"));
		dataSource = basicDataSource;
	}
	
	public int create(User user) throws Exception {
		Connection con = null;
		PreparedStatement pstmt = null;
		int insertRowCount = 0;
		
		try {
			con = dataSource.getConnection();
			pstmt = con.prepareStatement(UserSQL.USER_INSERT);
			pstmt.setString(1,  user.getUserId());
			pstmt.setString(2,  user.getPassword());
			pstmt.setString(3,  user.getName());
			pstmt.setString(4,  user.getEmail());
			insertRowCount = pstmt.executeUpdate();
			return insertRowCount;
		} finally {
			if (pstmt != null) {
				pstmt.close();
			}
			if (con != null) {
				con.close();
			}
		}
	}
	
	public int update(User user) throws Exception {
		Connection con = null;
		PreparedStatement pstmt = null;
		int updateRowCount = 0;
		
		try {
			con = dataSource.getConnection();
			pstmt = con.prepareStatement(UserSQL.USER_UPDATE);
			pstmt.setString(1, user.getUserId());
			pstmt.setString(2, user.getPassword());
			pstmt.setString(3, user.getName());
			pstmt.setString(4, user.getEmail());
			updateRowCount = pstmt.executeUpdate();
		} finally {
			if (pstmt != null) {
				pstmt.close();
			}
			if (con != null) {
				con.close();
			}
		}
		return updateRowCount;
	}

}
