package jp.co.dk.databaseexportdocuments;

import jp.co.dk.databaseexportdocuments.exception.DatabaseExportDocumentsException;

import static jp.co.dk.databaseexportdocuments.message.DatabaseExportDocumentsMessage.*;

public enum VariableType {
	/** 文字列 */
	CHAR("CHAR"),
	
	/** 数値 */
	NUM("NUM"),
	
	/** 日付 */
	DATE("DATE"),
	
	/** 時間 */
	TIME("DATE"),
	;
	
	private String typeStr;
	
	private VariableType(String typeStr) {
		this.typeStr = typeStr;
	}
	
	public static VariableType getType(String typeStr) throws DatabaseExportDocumentsException {
		for (VariableType type : VariableType.values()) if (type.typeStr.equals(typeStr)) return type;
		throw new DatabaseExportDocumentsException(VALIABLE_TYPE_IS_UNKNOWN);
	}
}
