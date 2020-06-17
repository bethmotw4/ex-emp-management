package jp.co.sample.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;

import jp.co.sample.domain.Administrator;

/**
 * administratorsテーブルを操作するリポジトリ.
 * 
 * @author yoshiki.morimoto
 *
 */
@Repository
public class AdministratorRepository {
	
	@Autowired
	private NamedParameterJdbcTemplate template;
	
	private static final RowMapper<Administrator> ADMINISTRATOR_ROW_MAPPER = (rs, i) -> {
		Administrator administrator = new Administrator();
		administrator.setId(rs.getInt("id"));
		administrator.setName(rs.getString("name"));
		administrator.setMailAddress(rs.getString("mail_address"));
		administrator.setPassword(rs.getString("password"));
		return administrator;
	};
	
	/**
	 * 管理者情報を挿入する.
	 * 
	 * @param administrator 管理者情報
	 */
	public void insert(Administrator administrator) {
		SqlParameterSource source = new BeanPropertySqlParameterSource(administrator);
		String sql = "INSERT INTO administrators(name, mail_address, password) VALUES(:name, :meilAddress, :passwoed);";
		template.update(sql, source);
	}
	
	/**
	 * メールアドレスとパスワードから管理者情報を取得する.
	 * 
	 * @param mailAddress メールアドレス
	 * @param password パスワード
	 * @return 取得した管理者情報
	 */
	public Administrator findByMaillAddressAndPassword(String mailAddress, String password) {
		String sql = "SELECT id, name, mail_address, password FROM Administrators "
				+ "WHERE mail_address=:mailAddress AND password=:password;";
		SqlParameterSource source = new MapSqlParameterSource().addValue("mailAddress", mailAddress).addValue("password", password);
		try {
//			1件も検索されなかったら例外が発生する
			return template.queryForObject(sql, source, ADMINISTRATOR_ROW_MAPPER);
		} catch (Exception e) {
//			例外が発生したらnullを返す
			return null;
		}
	}
}
