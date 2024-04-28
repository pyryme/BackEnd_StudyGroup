package com.xxx.cms.service.impl;


import com.xxx.cms.bean.PageBean;
import com.xxx.cms.bean.Result;
import com.xxx.cms.bean.vo.AccountVO;
import com.xxx.cms.bean.vo.GroupVO;
import com.xxx.cms.dao.GroupDao;
import com.xxx.cms.dao.impl.AllMemberDaoImpl;
import com.xxx.cms.dao.impl.GroupDaoImpl;
//import com.xxx.cms.model.Group;
import com.xxx.cms.dao.impl.UserDaoImpl;
import com.xxx.cms.service.GroupService;
import com.xxx.cms.service.TransactionService;

import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.util.concurrent.ThreadLocalRandom;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.sql.*;


public class TransactionServiceImpl implements TransactionService {

    private final AllMemberDaoImpl allMemberDao = new AllMemberDaoImpl();
    private final UserDaoImpl userDao = new UserDaoImpl();


    //这里要静下心来思考每一个过程，分析好每一个过程就可以完成了。但是
    @Override
    public  boolean transfer(AccountVO payer, AccountVO payee, double amount) {
/////////////////////////////////////////这个是模拟的，不是真实的

        // 模拟网络问题
        if (ThreadLocalRandom.current().nextBoolean()) {
            System.out.println("网络连接失败，交易失败。");
            return false;
        }

        // 资金先从支付者转到第三方
//        double thirdPartyAmount = amount / 2;
//        payer.withdraw(amount);
//        payee.deposit(thirdPartyAmount);


        double thirdPartyAmount = amount ;
        payer.withdraw(thirdPartyAmount);
        //***检测是否数据库扣钱了***，没有扣钱直接

        // 模拟网络问题
        if (ThreadLocalRandom.current().nextBoolean()) {
            System.out.println("网络连接失败，交易失败。资金已退回到支付者账户。");
            payer.deposit(amount);
            return false;
        }

        // 第三方再转账给收款方
        payee.withdraw(thirdPartyAmount);
        payee.deposit(amount);

        System.out.println("交易成功，资金已从支付者账户转到收款方账户。");
        return true;
    }
    //        Connection conn = null;
//        PreparedStatement pstmt = null;
//
//        try {
//            // 模拟网络问题
//            if (ThreadLocalRandom.current().nextBoolean()) {
//                System.out.println("网络连接失败，交易失败。");
//                return false;
//            }
//
//            // 连接数据库
//            conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
//
//            // 开启事务
//            conn.setAutoCommit(false);
//
//            // 更新支付者账户余额（从数据库中扣款）
//            String updatePayerSql = "UPDATE accounts SET balance = balance - ? WHERE owner = ?";
//            pstmt = conn.prepareStatement(updatePayerSql);
//            pstmt.setDouble(1, amount);
//            pstmt.setString(2, payer.getOwner());
//            int rowsUpdated = pstmt.executeUpdate();
//            if (rowsUpdated != 1) {
//                throw new SQLException("更新支付者账户余额失败。");
//            }
//
//            // 更新收款方账户余额（向数据库中加款）
//            String updatePayeeSql = "UPDATE accounts SET balance = balance + ? WHERE owner = ?";
//            pstmt = conn.prepareStatement(updatePayeeSql);
//            pstmt.setDouble(1, amount);
//            pstmt.setString(2, payee.getOwner());
//            rowsUpdated = pstmt.executeUpdate();
//            if (rowsUpdated != 1) {
//                throw new SQLException("更新收款方账户余额失败。");
//            }
//
//            // 提交事务
//            conn.commit();
//            return true;
//
//        } catch (SQLException e) {
//            // 回滚事务
//            if (conn != null) {
//                try {
//                    conn.rollback();
//                } catch (SQLException ex) {
//                    ex.printStackTrace();
//                }
//            }
//            e.printStackTrace();
//            return false;
//
//        } finally {
//            // 关闭连接和语句
//            if (pstmt != null) {
//                try {
//                    pstmt.close();
//                } catch (SQLException e) {
//                    e.printStackTrace();
//                }
//            }
//            if (conn != null) {
//                try {
//                    conn.close();
//                } catch (SQLException e) {
//                    e.printStackTrace();
//                }
//            }
//        }







    //
//    @Override
//    public Result getCreatedGroups_toTCHTML(String groupId) throws SQLException {
//
//        Result result_allGroupName;
//        //先拿groupName的数组
//        result_allGroupName = allMemberDao.getCreatedGroups(userId);//这个data的数据是ArrayList<String>,含义是name
//        ArrayList<String> array_allGroupName = (ArrayList<String>) result_allGroupName.getData();
//
//
//    }
}