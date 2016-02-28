package jp.co.dk.databaseexportdocuments;

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
	
	protected int lastIndex;
	
	protected int spanSize;
	
	protected AbstractDataBaseAccessObject dao;
	
	public DBWorkSheet(WorkBook workBook, Sheet sheet, AbstractDataBaseAccessObject dao) {
		super(workBook, sheet);
		this.dao = dao;
		this.lastIndex = this.getLastRowNum();
		this.spanSize  = 1;
	}
	
	public void setRowSpan(int spanSize) {
		this.spanSize = spanSize;
	}
	
	private int getLastIndex() {
		int nowIndex = this.lastIndex;
		this.lastIndex = this.lastIndex + spanSize;
		return nowIndex;
	}
	
	public void write(Sql sql) throws DataStoreManagerException, ExcelDocumentException {
		this.getRow(lastIndex++).addCell(sql.toString());
		this.dao.selectMulti(sql, new DataConvertable() {
			public DataConvertable convert(DataBaseRecord dataBaseRecord) throws DataStoreManagerException {
				if (dataBaseRecord.getRowIndex() == 1) {
					Row lastbrankrow = getRow(getLastIndex());
					for (ColumnMetaData columnMetaData : dataBaseRecord.getColumns()) {
						lastbrankrow.addCell(columnMetaData.getColumnname())
							.setFillForegroundColor(CellColorType.BLUE)
							.setBorder(CellBorderType.BORDER_DASH_DOT);
					}
				}
				Row lastbrankrow = getRow(getLastIndex());
				for (ColumnMetaData columnMetaData : dataBaseRecord.getColumns()) {
					lastbrankrow.addCell(dataBaseRecord.getString(columnMetaData.getColumnname())).setBorder(CellBorderType.BORDER_DASH_DOT);
				}
				return null;
			}
		});
	}
}
