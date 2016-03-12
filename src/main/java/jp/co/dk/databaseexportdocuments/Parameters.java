package jp.co.dk.databaseexportdocuments;

import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import jp.co.dk.databaseexportdocuments.exception.DatabaseExportDocumentsException;
import jp.co.dk.databaseexportdocuments.message.DatabaseExportDocumentsMessage;

public class Parameters {
	
	/** バインド変数フォーマット */
	protected static Pattern variableFormat = Pattern.compile("^(.+?)=(.+?)$");
	
	protected String[] parameterstr;
	
	protected Map<String, String> parameter = new HashMap<>();
	
	public Parameters(String[] parameters) throws DatabaseExportDocumentsException {
		if (parameters != null) {
			this.parameterstr = parameters;
			for(String paramStr : parameters) {
				Matcher variableMatcher = variableFormat.matcher(paramStr);
				if (variableMatcher.find()) {
					String name  = variableMatcher.group(1);
					String value = variableMatcher.group(2);
					parameter.put(name, value);
				} else {
					throw new DatabaseExportDocumentsException(DatabaseExportDocumentsMessage.PARAMETER_FORMAT_IS_INVALID);
				}
			}
		}
	}
	
	public Map<String, String> getParameter() {
		return new HashMap<>(this.parameter);
	}
}
