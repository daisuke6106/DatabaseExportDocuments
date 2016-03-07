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

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result + ((type == null) ? 0 : type.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Variable other = (Variable) obj;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		if (type != other.type)
			return false;
		return true;
	}
	
}


