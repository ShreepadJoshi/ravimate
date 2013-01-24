package com.writer.fileWriter;

import java.util.List;

import org.springframework.batch.item.ItemWriter;

public class ConsoleWriterForStatementOfAccount<StatementOfAccountBean>
		implements ItemWriter<StatementOfAccountBean> {

	@Override
	public void write(
			List<? extends StatementOfAccountBean> stmtBean)
			throws Exception {
		System.out.println("In write " + stmtBean);
	}

}
