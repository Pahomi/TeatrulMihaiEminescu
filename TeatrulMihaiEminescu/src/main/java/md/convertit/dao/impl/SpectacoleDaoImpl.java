package md.convertit.dao.impl;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

import md.convertit.dao.SpectacoleDao;
import md.convertit.spectacol.domain.Spectacole;
import md.convertit.util.ConnectionUtil;

public class SpectacoleDaoImpl implements SpectacoleDao {

	private static final Logger log = Logger.getLogger(SpectacoleDao.class.getName());
	private Connection conn;
	private PreparedStatement ps;

	@Override
	public boolean save(Spectacole spectacole) {

		try {
			conn = ConnectionUtil.getConnection();
			String sql = "INSERT INTO `spectacole` (`name`, `seatsAvailable`, `premiere`, `data`) VALUES (?, ?, ?, ?);";

			ps = conn.prepareStatement(sql);
			ps.setString(1, spectacole.getName());
			ps.setInt(2, spectacole.getSeatsAvailable());
			ps.setBoolean(3, spectacole.isPremiere());
			ps.setDate(4, new Date(spectacole.getData().getTime()));

			int affectedRows = ps.executeUpdate();
			log.info(String.format("Saved object, total affected rows: %d", affectedRows));
			return true;
		} catch (SQLException e) {

			log.severe(String.format("Exception: %s", e.getMessage()));
			// e.printStackTrace();
		}
		return false;
	}

	@Override
	public List<Spectacole> findAll() {
		List<Spectacole> spectList = new ArrayList<>();
		try {
			conn = ConnectionUtil.getConnection();
			String sql = "SELECT * FROM spectacole";
			ps = conn.prepareStatement(sql);
			ResultSet set = ps.executeQuery();
			while (set.next()) {
				long id = set.getInt("id");

				Spectacole spectacole = new Spectacole();
				String name = set.getString("name");
				spectacole.setName(name);
				int seatsAvailable = set.getInt("seatsAvailable");
				spectacole.setSeatsAvailable(seatsAvailable);
				boolean premiere = set.getBoolean("premiere");
				spectacole.setPremiere(premiere);
				Date data = set.getDate("data");
				spectacole.setData(data);

				spectList.add(spectacole);

				log.info(String.format("Added new user to list: %s", spectacole.toString()));
			}
		} catch (SQLException e) {
			log.severe(String.format("Fatal error: %s", e.getMessage()));
			e.printStackTrace();
		}
		log.info(String.format("Retrieved from database %d users", spectList.size()));
		return spectList;
	}

	@Override
	public boolean update(Spectacole newSpectacole, Long id) {
		try {
			conn = ConnectionUtil.getConnection();
			String sql = "UPDATE `spectacole` SET `name`='?', `seatsAvailable`='?', `premiere`='?', `data`='?' WHERE `id`='?'";
			ps = conn.prepareStatement(sql);
			ps.setString(1, newSpectacole.getName());
			ps.setInt(2, newSpectacole.getSeatsAvailable());
			ps.setBoolean(3, newSpectacole.isPremiere());
			ps.setDate(4, new Date(newSpectacole.getData().getTime()));
			ps.setLong(4, id);
			int affectedRows = ps.executeUpdate();
			log.info(String.format("Update object, total affected rows: %d", affectedRows));
			return true;
			
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
	}

	@Override
	public boolean delete(Long id) {
		// TODO Auto-generated method stub
		return false;
	}
}
