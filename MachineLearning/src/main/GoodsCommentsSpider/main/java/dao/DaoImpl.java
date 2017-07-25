package dao;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Date;
import java.util.List;

import javax.sql.rowset.serial.SerialClob;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BatchPreparedStatementSetter;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import PO.GoodComment;

/**
 * @author 凡 数据层
 */
@Component
// @Scope("prototype")
public class DaoImpl {

	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	@Transactional("JDBC")
	public void insertByJDBC(List<GoodComment> goodComments,String category) {
		
		String updateSql ="INSERT INTO `goods_comments`(`name`,`id`,`category`," + 
				"`comment`,`score`)VALUES(?,?,?,?,?)";
		jdbcTemplate.batchUpdate(updateSql, new BatchPreparedStatementSetter() {

			@Override
			public void setValues(PreparedStatement ps, int i) throws SQLException {

				ps.setString(1, goodComments.get(i).getName());
				ps.setInt(2, i);
				ps.setString(3, category);
				ps.setString(4, goodComments.get(i).getComment());
				ps.setInt(5, goodComments.get(i).getScore());

			}

			@Override
			public int getBatchSize() {
				return goodComments.size();
			}
		});
		System.out.println(goodComments.size());
		System.out.println(new Date() + "===========finish===========");

	}

}
