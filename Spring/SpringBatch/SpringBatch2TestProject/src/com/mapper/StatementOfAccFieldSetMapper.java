package com.mapper;

import org.springframework.batch.item.file.mapping.FieldSetMapper;
import org.springframework.batch.item.file.transform.FieldSet;

import com.bean.StatementOfAccountBean;
import com.bean.User;

public class StatementOfAccFieldSetMapper implements FieldSetMapper<StatementOfAccountBean> {

	@Override
	public StatementOfAccountBean mapFieldSet(FieldSet fieldSet) {
		
		StatementOfAccountBean stmtBean = new StatementOfAccountBean();
		stmtBean.setDate(fieldSet.readString("DATE"));
		stmtBean.setNarration(fieldSet.readString("NARRATION"));
		stmtBean.setChqRefNo(fieldSet.readString("CHQ_REF_NO"));
		stmtBean.setValueDt(fieldSet.readString("VALUE_DT"));
		stmtBean.setWithdrawalAmt(fieldSet.readString("WITHDRAWAL_AMT"));
		stmtBean.setDepositAmt(fieldSet.readString("DEPOSIT_AMT"));
		stmtBean.setClosingBalance(fieldSet.readString("CLOSING_BALANCE"));

		return stmtBean;
	}

}
