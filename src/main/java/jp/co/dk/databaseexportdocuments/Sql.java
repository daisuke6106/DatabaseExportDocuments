package jp.co.dk.databaseexportdocuments;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import jp.co.dk.databaseexportdocuments.exception.DatabaseExportDocumentsException;
import jp.co.dk.datastoremanager.exception.DataStoreManagerException;

public class Sql extends jp.co.dk.datastoremanager.rdb.Sql {

	/** バインド変数フォーマット */
	protected static Pattern variableFormat = Pattern.compile("\\$\\{(.+?)@(.+?)\\}");
	
	public Sql(String sql) throws DataStoreManagerException {
		super(sql);
	}

	public List<Variable> getVariableList() throws DatabaseExportDocumentsException {
		List<Variable> varList = new ArrayList<>();
		String sql = this.getSql();
		Matcher variableMatcher = variableFormat.matcher(sql);
		while(variableMatcher.find()) {
			String variableName = variableMatcher.group(0);
			String variableType = variableMatcher.group(1);
			Variable var = new Variable(variableName, variableType);
			varList.add(var);
		}
		return varList;
	}
	 
}
