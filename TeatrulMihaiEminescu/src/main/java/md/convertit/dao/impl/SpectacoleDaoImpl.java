package md.convertit.dao.impl;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;
import java.util.logging.Logger;

import md.convertit.dao.SpectacoleDao;
import md.convertit.spectacol.domain.Spectacole;
import md.convertit.util.ConnectionUtil;

public class SpectacoleDaoImpl implements SpectacoleDao{

	private static final Logger log = Logger.getLogger(SpectacoleDao.class.getName());
	private Connection conn;
	private PreparedStatement ps;
	
	@Override
	public boolean save(Spectacole spectacole) {
		
		try {
			conn =ConnectionUtil.getConnection();
			String sql = "INSERT INTO `spectacole` (`name`, `seatsAvailable`, `premiere`, `data`) VALUES (?, ?, ?, ?);";

			ps = conn.prepareStatement(sql);
			ps.setString(1, spectacole.getName());
			ps.setInt(2, spectacole.getSeatsAvailable());
			ps.setBoolean(3,spectacole.isPremiere());
			ps.setDate(4, (Date) spectacole.getData());
			
			
		} catch (SQLException e) {
			
			
			e.printStackTrace();
		}
		return false;
	}

	@Override
	public List<Spectacole> findAll() {
		// TODO Auto-generated method stub
		return null;
	}

}
