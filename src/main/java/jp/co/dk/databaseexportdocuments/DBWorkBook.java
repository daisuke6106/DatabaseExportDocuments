package jp.co.dk.databaseexportdocuments;

import java.io.File;

import org.apache.poi.ss.usermodel.Sheet;

import jp.co.dk.datastoremanager.rdb.AbstractDataBaseAccessObject;
import jp.co.dk.document.excel.book.WorkBook;
import jp.co.dk.document.excel.exception.ExcelDocumentException;
import jp.co.dk.document.excel.sheet.WorkSheet;

public class DBWorkBook extends WorkBook {
	
	protected AbstractDataBaseAccessObject dao;
	
	public DBWorkBook(File file, AbstractDataBaseAccessObject dao) throws ExcelDocumentException {
		super(file);
		this.dao = dao;
	}
	
	protected WorkSheet createSheet(WorkBook workBook, Sheet sheet) {
		return new DBWorkSheet(workBook, sheet, dao);
	}
}
