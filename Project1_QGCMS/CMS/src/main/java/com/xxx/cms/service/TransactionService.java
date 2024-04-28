package com.xxx.cms.service;

import com.xxx.cms.bean.vo.AccountVO;

public interface TransactionService {
     boolean transfer(AccountVO payer, AccountVO payee, double amount);

    }
