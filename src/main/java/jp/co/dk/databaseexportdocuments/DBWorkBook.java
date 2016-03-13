package jp.co.dk.databaseexportdocuments;

import java.io.File;

import org.apache.poi.ss.usermodel.Sheet;

import jp.co.dk.datastoremanager.rdb.AbstractDataBaseAccessObject;
import jp.co.dk.document.excel.book.WorkBook;
import jp.co.dk.document.excel.exception.ExcelDocumentException;
import jp.co.dk.document.excel.sheet.WorkSheet;

/**
 * 
 * @version 1.0
 * @author D.Kanno
 */
public class DBWorkBook extends WorkBook {
	
	/** データアクセスオブジェクト */
	protected AbstractDataBaseAccessObject dao;
	
	/**
	 * <p>コンストラクタ</p>
	 * 出力先ファイルと、データアクセスオブジェクトを元に、データベース用ブックオブジェクトを生成します。
	 * @param file 出力先ファイル
	 * @param dao データアクセスオブジェクト
	 * @throws ExcelDocumentException すでに出力先ファイルが存在していた場合
	 */
	public DBWorkBook(File file, AbstractDataBaseAccessObject dao) throws ExcelDocumentException {
		super(file);
		this.dao = dao;
	}
	
	/**
	 * <p>ワークシート作成</p>
	 * ワークシートを生成して返却します。
	 * @param workBook ワークブック
	 * @param sheet ワークシート
	 */
	@Override
	protected WorkSheet createSheet(WorkBook workBook, Sheet workSheet) {
		return new DBWorkSheet(workBook, workSheet, dao, 1);
	}
}
