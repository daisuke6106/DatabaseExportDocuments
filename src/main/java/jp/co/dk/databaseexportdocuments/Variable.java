package jp.co.dk.databaseexportdocuments;

import jp.co.dk.databaseexportdocuments.exception.DatabaseExportDocumentsException;

import static jp.co.dk.databaseexportdocuments.message.DatabaseExportDocumentsMessage.*;

public class Variable {
	
	/** 変数名 */
	protected String name;
	
	/** 変数型 */
	protected VariableType type;
	
	Variable(String name, String type) throws DatabaseExportDocumentsException {
		if (name == null || name.equals("")) new DatabaseExportDocumentsException(VALIABLE_IS_NOT_SET);
		this.name = name;
		this.type = VariableType.getType(type);
	}
	
}


