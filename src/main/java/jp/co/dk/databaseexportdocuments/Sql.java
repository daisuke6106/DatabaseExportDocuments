package jp.co.dk.databaseexportdocuments;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import jp.co.dk.databaseexportdocuments.exception.DatabaseExportDocumentsException;
import jp.co.dk.databaseexportdocuments.message.DatabaseExportDocumentsMessage;
import jp.co.dk.datastoremanager.exception.DataStoreManagerException;

public class Sql extends jp.co.dk.datastoremanager.rdb.Sql {

	/** バインド変数フォーマット */
	protected static Pattern variableFormat = Pattern.compile("\\$\\{(.+?)@(.+?)\\}");
	
	protected List<Variable> variableList = new ArrayList<>();
	
	public Sql(String sql) throws DataStoreManagerException, DatabaseExportDocumentsException {
		super(sql);
		Matcher variableMatcher = variableFormat.matcher(sql);
		while (variableMatcher.find()) {
			String variableName = variableMatcher.group(1);
			String variableType = variableMatcher.group(2);
			Variable var = new Variable(variableName, variableType);
			variableList.add(var);
		}
		super.sql = new StringBuilder(variableMatcher.replaceAll("?"));
	}
	
	public void setParameter(Map<String, String> parameters) throws DatabaseExportDocumentsException {
		List<String> variableValue = new ArrayList<>();
		for (int index = 0; index < variableList.size(); ++index) {
			String varName  = variableList.get(index).name;
			String varValue = parameters.get(varName);
			if (varValue != null) {
				variableValue.add(index, varValue);
			} else {
				throw new DatabaseExportDocumentsException(DatabaseExportDocumentsMessage.VALUE_IS_NOT_SET, varName);
			}
		}
		for (int index = 0; index < variableList.size(); ++index) {
			Variable var   = variableList.get(index);
			String   param = variableValue.get(index);
			var.type.setParameter(this, param);
		}
	}
	
	public void setParameter(Parameters parameters) throws DatabaseExportDocumentsException {
		this.setParameter(parameters.getParameter());
	}
}
