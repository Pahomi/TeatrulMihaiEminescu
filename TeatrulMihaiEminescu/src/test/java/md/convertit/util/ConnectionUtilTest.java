package md.convertit.util;

import java.sql.Connection;
import java.sql.SQLException;

import org.junit.Assert;
import org.junit.Test;

import md.convertit.util.ConnectionUtil;


public class ConnectionUtilTest {
	@Test
	public void testGetConnection() throws SQLException{
		//obtin conexiunea
		Connection connection = ConnectionUtil.getConnection();
		//verifica sa nu fie nula
		Assert.assertNotNull(connection);
	}
}
