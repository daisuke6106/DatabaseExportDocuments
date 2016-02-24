package jp.co.dk.databaseexportdocuments;

import java.util.List;

import org.apache.poi.ss.usermodel.Sheet;

import jp.co.dk.datastoremanager.exception.DataStoreManagerException;
import jp.co.dk.datastoremanager.rdb.AbstractDataBaseAccessObject;
import jp.co.dk.datastoremanager.rdb.ColumnMetaData;
import jp.co.dk.datastoremanager.rdb.DataBaseRecord;
import jp.co.dk.datastoremanager.rdb.DataConvertable;
import jp.co.dk.datastoremanager.rdb.Sql;
import jp.co.dk.document.excel.book.WorkBook;
import jp.co.dk.document.excel.cell.CellBorderType;
import jp.co.dk.document.excel.cell.CellColorType;
import jp.co.dk.document.excel.exception.ExcelDocumentException;
import jp.co.dk.document.excel.row.Row;
import jp.co.dk.document.excel.sheet.WorkSheet;

public class DBWorkSheet extends WorkSheet {

	protected AbstractDataBaseAccessObject dao;
	
	public DBWorkSheet(WorkBook workBook, Sheet sheet, AbstractDataBaseAccessObject dao) {
		super(workBook, sheet);
		this.dao = dao;
	}

	public void write(Sql sql) throws DataStoreManagerException, ExcelDocumentException {
		this.getLastBrankRow().addCell(sql.toString());
		this.dao.selectMulti(sql, new DataConvertable() {
			public DataConvertable convert(DataBaseRecord dataBaseRecord) throws DataStoreManagerException {
				if (dataBaseRecord.getRowIndex() == 1) {
					Row lastbrankrow = getLastBrankRow();
					for (ColumnMetaData columnMetaData : dataBaseRecord.getColumns()) {
						lastbrankrow.addCell(columnMetaData.getColumnname())
							.setFillForegroundColor(CellColorType.BLUE)
							.setBorder(CellBorderType.BORDER_DASH_DOT);
					}
				}
				Row lastbrankrow = getLastBrankRow();
				for (ColumnMetaData columnMetaData : dataBaseRecord.getColumns()) {
					lastbrankrow.addCell(dataBaseRecord.getString(columnMetaData.getColumnname())).setBorder(CellBorderType.BORDER_DASH_DOT);
				}
				return null;
			}
		});
	}
}
