package jp.co.dk.databaseexportdocuments;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import jp.co.dk.databaseexportdocuments.exception.DatabaseExportDocumentsException;
import static jp.co.dk.databaseexportdocuments.message.DatabaseExportDocumentsMessage.*;

public enum VariableType {
	
	/** 文字列 */
	CHAR("CHAR", "^.*$"),
	
	/** 数値 */
	NUM ("NUM" , "^[0-9]+$"),
	
	/** 日付 */
	DATE("DATE", "^\\d{4}/\\d{1,2}/\\d{1,2}$"),
	
	;
	
	private String typeStr;
	
	private Pattern regex;
	
	private VariableType(String typeStr, String regexStr) {
		this.typeStr = typeStr;
		this.regex   = Pattern.compile(regexStr);
	}
	
	public static VariableType getType(String typeStr) throws DatabaseExportDocumentsException {
		for (VariableType type : VariableType.values()) if (type.typeStr.equals(typeStr)) return type;
		throw new DatabaseExportDocumentsException(VALIABLE_TYPE_IS_UNKNOWN);
	}
	
	public void check(String target) throws DatabaseExportDocumentsException {
		Matcher matcher = this.regex.matcher(target);
		if (!matcher.find()) throw new DatabaseExportDocumentsException(VALIABLE_IS_CHECK_ERROR);
	}
}
