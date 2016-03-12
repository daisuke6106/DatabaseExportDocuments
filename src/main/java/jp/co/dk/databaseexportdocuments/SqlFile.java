package jp.co.dk.databaseexportdocuments;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import jp.co.dk.databaseexportdocuments.exception.DatabaseExportDocumentsException;
import jp.co.dk.datastoremanager.exception.DataStoreManagerException;
import jp.co.dk.document.exception.DocumentException;
import jp.co.dk.document.text.TextFile;

/**
 * SqlFileファイルはSQLが記述されたファイルを表すファイルオブジェクトです。
 * 
 * このファイルには実行対象のSQLが複数記述されています。<br/>
 * コメントは「--」で表され、「--」以降は無視されます。<br/>
 * SQLは「;」で区切られます。複数行記述があった場合でも「;」が出現するまでは１つのSQLをみなします。<br/>
 * また、バインド変数は「${XXX}」で記述します。
 * 
 * @version 1.0
 * @author D.Kanno
 */
public class SqlFile extends TextFile {
	
	/** コメントフォーマット */
	protected static Pattern commentFormat = Pattern.compile("^(.*?)--.*$");
	
	/** 未入力フォーマット */
	protected static Pattern nothingFormat = Pattern.compile("^ +$");
	
	/** SQL一覧 */
	protected List<Sql> sqlList = new ArrayList<>();
	
	/** パラメータ */
	protected Parameters parameter ;
	
	/**
	 * <p>コンストラクタ</p>
	 * 読み込みファイルを元にオブジェクトを生成します。<br/>
	 * 尚、指定されたファイルの存在チェックを行い、以下のような場合は例外を送出します。<br/>
	 * ・fileオブジェクトがnullの場合
	 * ・指定されたパスにファイルが存在しない場合
	 * ・指定されたパスのファイルがディレクトリの場合
	 * 
	 * @param file 読み込みファイル
	 * @throws DocumentException 上記条件にてファイルの読み込みに失敗した場合 
	 * @throws DataStoreManagerException SQLオブジェクトの生成に失敗した場合
	 * @throws DatabaseExportDocumentsException 不正な変数定義が存在した場合
	 */
	public SqlFile(File file) throws DocumentException, DataStoreManagerException, DatabaseExportDocumentsException {
		super(file);
		List<String>  lines   = this.getLines();
		StringBuilder allSql  = new StringBuilder();
		for (String line : lines) {
			Matcher commentMatcher = commentFormat.matcher(line);
			if (commentMatcher.find()) line = commentMatcher.group(1);
			allSql.append(line).append(" ");
		}
		String[] allSqlList = allSql.toString().split(";");
		for (String sql : allSqlList) {
			if (!nothingFormat.matcher(sql).find())this.sqlList.add(new Sql(sql));
		}
	}
	
	/**
	 * SQL一覧を取得する。
	 * @return SQL一覧
	 */
	public List<Sql> getSqlList() {
		return new ArrayList<>(this.sqlList);
	}
	
	public void setParameter(Parameters parameter) throws DatabaseExportDocumentsException {
		this.parameter = parameter;
		for (Sql sql : this.sqlList) sql.setParameter(parameter);
	}
	
}
