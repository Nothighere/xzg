package com.cm.xzg.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import com.cm.xzg.bean.UserDo;

import java.util.ArrayList;
import java.util.List;

@Repository
public class UserDao {
    @Autowired
    private JdbcTemplate jdbcTemplate;

    // add
    public String insert(UserDo user) {
        try {
            jdbcTemplate.update("insert into user(parentName, childName, idCardNumber, phoneNumber, regeisteDate, shopType)values(?,?,?,?,?)",
                    user.getParentName(), user.getChildName(), user.getIdCardNumber(), user.getPhoneNumber(), user.getRegeisteDate());
            return "Susses";
        } catch (DataAccessException error)
        {
            return error.getMessage();
        }
    }


    /**
     * 查询所有用户信息
     * @return
     */
    public List<UserDo> getAllUsers() throws Exception {
        List<UserDo> userList = new ArrayList<>();
        String sql = "select * from user";
        try {
            userList = jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(UserDo.class));

        } catch (Exception error){
            throw new Exception(error);
        }
        return userList;
    }

    /**
     * 查询指定日期报名人数
     * @param signUpDate
     * @return
     * @throws Exception
     */
    public int getSumOfSignUp(String signUpDate) throws Exception{
        String sql = "select count(1) from user where DATE_FORMAT(user.regeiste_date,'%Y-%m-%d') = DATE_FORMAT("+signUpDate+",'%Y-%m-%d')";
        int signUp = 0;
        try{
            signUp = jdbcTemplate.queryForObject(sql,Integer.class);
        }catch (Exception e){
            throw new Exception(e);
        }
        return signUp;
    }

    /**
     * 根据电话号码校验用户是否已经注册
     * @return
     */
    public int regeistedCheckByPhoneNum(String phoneNum) throws Exception{
        String sql = "select count(1) from user where user.phone_number ="+phoneNum;
        int num = 0;
        try{
            num = jdbcTemplate.queryForObject(sql,Integer.class);

        }catch (Exception e){
            throw new Exception(e);
        }
        return num;
    }

}
/**
 * 商品数据库访问类
 */
//@Repository // 标注数据访问类
//public class GoodsDao {
//    @Autowired
//    private JdbcTemplate jdbcTemplate;
//
//    /**
//     * 新增
//     */
//    public void insert(GoodsDo goods) {
//        jdbcTemplate.update("insert into goods(name,price,pic)values(?,?,?)", goods.getName(), goods.getPrice(),
//                goods.getPic());
//    }
//
//    /**
//     * 删除
//     */
//    public void delete(Long id) {
//        jdbcTemplate.update("delete from goods where id =?", id);
//    }
//
//    /**
//     * 更新
//     */
//    public void update(GoodsDo goods) {
//        jdbcTemplate.update("update goods set name=?,price=?,pic=? where id=?", goods.getName(), goods.getPrice(),
//                goods.getPic(), goods.getId());
//    }
//
//    /**
//     * 按id查询
//     */
//    public GoodsDo getById(Long id) {
//        return jdbcTemplate.queryForObject("select * from goods where id=?", new RowMapper<GoodsDo>() {
//            @Override
//            public GoodsDo mapRow(ResultSet rs, int rowNum) throws SQLException {
//                GoodsDo goods = new GoodsDo();
//                goods.setId(rs.getLong("id"));
//                goods.setName(rs.getString("name"));
//                goods.setPrice(rs.getString("price"));
//                goods.setPic(rs.getString("pic"));
//                return goods;
//            }
//        }, id);
//    }
//
//    /**
//     * 查询商品列表
//     */
//    public List<GoodsDo> getList() {
//        return jdbcTemplate.query("select * from goods", new RowMapper<GoodsDo>() {
//            @Override
//            public GoodsDo mapRow(ResultSet rs, int rowNum) throws SQLException {
//                GoodsDo goods = new GoodsDo();
//                goods.setId(rs.getLong("id"));
//                goods.setName(rs.getString("name"));
//                goods.setPrice(rs.getString("price"));
//                goods.setPic(rs.getString("pic"));
//                return goods;
//            }
//        });
//    }
//}
