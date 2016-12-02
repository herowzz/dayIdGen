package com.github.herowzz.dayIdGen.center.common.entity;

import java.time.LocalDateTime;

import com.github.herowzz.dayIdGen.center.common.entity.reference.GenerateIdType;

public class Bucket {

	private String id;

	private String name;

	private GenerateIdType generateIdType;

	private String remark;

	private LocalDateTime createTime;

	private LocalDateTime updateTime;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public GenerateIdType getGenerateIdType() {
		return generateIdType;
	}

	public void setGenerateIdType(GenerateIdType generateIdType) {
		this.generateIdType = generateIdType;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public LocalDateTime getCreateTime() {
		return createTime;
	}

	public void setCreateTime(LocalDateTime createTime) {
		this.createTime = createTime;
	}

	public LocalDateTime getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(LocalDateTime updateTime) {
		this.updateTime = updateTime;
	}

}
