package com.perficient.bcten.dao;

import com.perficient.bcten.model.ReEditInfo;

public interface ReEditInfoDao {
	ReEditInfo findReEditInfoByRequestId(int requestId);
}
