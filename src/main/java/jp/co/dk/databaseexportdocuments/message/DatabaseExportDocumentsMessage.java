package jp.co.dk.databaseexportdocuments.message;

import java.io.Serializable;

import jp.co.dk.message.AbstractMessage;

/**
 * DataStoreManagerMessageは、データストアの操作にて使用するメッセージを定義する定数クラスです。
 * 
 * @version 1.0
 * @author D.Kanno
 */
public class DatabaseExportDocumentsMessage extends AbstractMessage implements Serializable{
	
	/** シリアルバージョンID */
	private static final long serialVersionUID = -9014515546963143220L;

	/** 変数値が設定されていません。 */
	public static final DatabaseExportDocumentsMessage VALIABLE_IS_NOT_SET = new DatabaseExportDocumentsMessage("E001");
	
	/** 変数型に不明な値が設定されています。 */
	public static final DatabaseExportDocumentsMessage VALIABLE_TYPE_IS_UNKNOWN = new DatabaseExportDocumentsMessage("E002");
	
	protected DatabaseExportDocumentsMessage(String messageId) {
		super(messageId);
	}

}
