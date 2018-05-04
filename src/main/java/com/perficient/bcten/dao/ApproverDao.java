package com.perficient.bcten.dao;

import java.util.List;

import com.perficient.bcten.entity.Permission;

public interface ApproverDao {
	List<Permission> findApprovers();
}
