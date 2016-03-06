package jp.co.dk.databaseexportdocuments;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

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
	
	/** 終了行フォーマット */
	protected static Pattern endFormat     = Pattern.compile("^(.*?);.*$");
	
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
	 */
	public SqlFile(File file) throws DocumentException {
		super(file);
	}

	/**
	 * SQLファイルに記述されたＳＱＬの一覧を取得します。
	 * 
	 * @return SQL一覧
	 * @throws DocumentException 読み込みに失敗した場合
	 * @throws DataStoreManagerException SQLオブジェクトの生成に失敗した場合
	 */
	public List<Sql> getSqlList() throws DocumentException, DataStoreManagerException {
		List<Sql>     sqlList = new ArrayList<>();
		List<String>  lines   = this.getLines();
		StringBuilder sql     = new StringBuilder();
		for (String line : lines) {
			Matcher commentMatcher = commentFormat.matcher(line);
			if (commentMatcher.find()) line = commentMatcher.group(1);
			Matcher endMatcher     = endFormat.matcher(line);
			if (endMatcher.find()) {
				sql.append(endMatcher.group(1));
				sqlList.add(new Sql(sql.toString()));
				sql = new StringBuilder();
			} else {
				sql.append(line);
			}
		}
		return sqlList;
	}
}
