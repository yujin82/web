package kr.mjc.yujin.web.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class UserDao {

  private static final String LIST_USERS =
      "select userId, email, name from user order by userId desc limit ?,?";

  private static final String ADD_USER =
      "insert user(email, password, name) values(:email, sha2(:password,256), :name)";

  private static final String LOGIN =
      "select userId, email, name from user where (email, password) = (?, sha2(?,256))";

  private static final String GET_USER =
      "select userId, email, name from user where userId=?";

  private static final String UPDATE_EMAIL =
      "update user set email=:email where userId=:userId";

  private static final String UPDATE_PASSWORD =
      "update user set password=sha2(:newPassword,256) where userId=:userId and password=sha2(:password,256)";

  private JdbcTemplate jdbcTemplate;

  private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

  private RowMapper<User> rowMapper = new BeanPropertyRowMapper<>(User.class);

  @Autowired
  public UserDao(JdbcTemplate jdbcTemplate,
                 NamedParameterJdbcTemplate namedParameterJdbcTemplate) {
    this.jdbcTemplate = jdbcTemplate;
    this.namedParameterJdbcTemplate = namedParameterJdbcTemplate;
  }

  /**
   * 사용자 목록
   */
  public List<User> listUsers(int offset, int count) {
    return jdbcTemplate.query(LIST_USERS, rowMapper, offset, count);
  }

  /**
   * 로그인
   *
   * @return 로그인한 사용자 정보
   * @throws EmptyResultDataAccessException 로그인에 실패할 경우
   */
  public User login(String email, String password) throws
      EmptyResultDataAccessException {
    return jdbcTemplate.queryForObject(LOGIN, rowMapper, email, password);
  }

  /**
   * 사용자 조회
   */
  public User getUser(int userId) {
    return jdbcTemplate.queryForObject(GET_USER, rowMapper, userId);
  }

  /**
   * 사용자 등록
   *
   * @throws DuplicateKeyException 이메일이 중복되어 사용자 등록에 실패할 경우
   */
  public void addUser(User user) throws DuplicateKeyException {
    namedParameterJdbcTemplate
        .update(ADD_USER, new BeanPropertySqlParameterSource(user));
  }

  /**
   * 이메일 수정
   *
   * @return 수정한 행의 갯수
   * @throws DuplicateKeyException 이메일이 중복되어 이메일 수정에 실패할 경우
   */
  public int updateEmail(int userId, String email)
      throws DuplicateKeyException {
    Map<String, Object> params = new HashMap<>();
    params.put("userId", userId);
    params.put("email", email);
    return namedParameterJdbcTemplate.update(UPDATE_EMAIL, params);
  }

  /**
   * 비밀번호 수정
   *
   * @param userId      사용자 아이디
   * @param password    현재 비밀번호
   * @param newPassword 새 비밀번호
   * @return 수정한 행의 갯수. 0은 아이디와 비밀번호가 틀려 수정을 못한 경우
   */
  public int updatePassword(int userId, String password, String newPassword) {
    Map<String, Object> params = new HashMap<>();
    params.put("userId", userId);
    params.put("password", password);
    params.put("newPassword", newPassword);
    return namedParameterJdbcTemplate.update(UPDATE_PASSWORD, params);
  }
}
