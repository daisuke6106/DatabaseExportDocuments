package jp.co.dk.databaseexportdocuments.exception;

import java.util.List;

import jp.co.dk.message.MessageInterface;
import jp.co.dk.message.exception.AbstractMessageFatalException;

/**
 * DatabaseExportDocumentsFatalExceptionは、データベースエクスポート処理にて致命的例外が発生したことを通知する例外クラスです。
 * 
 * @version 1.0
 * @author D.Kanno
 */
class DatabaseExportDocumentsFatalException extends AbstractMessageFatalException{
	
	/**
	 * シリアルバージョンID
	 */
	private static final long serialVersionUID = 1683723327772623796L;

	/**
	 * コンストラクタ<p>
	 * 
	 * 指定のメッセージで例外を生成します。
	 * 
	 * @param msg メッセージ定数インスタンス
	 * @since 1.0
	 */
	public DatabaseExportDocumentsFatalException(MessageInterface msg){
		super(msg);
	}
	
	/**
	 * コンストラクタ<p>
	 * 
	 * 指定のメッセージで例外を生成します。
	 * 
	 * @param msg メッセージ定数インスタンス
	 * @param str メッセージ埋め込み文字列
	 * @since 1.0
	 */
	public DatabaseExportDocumentsFatalException(MessageInterface msg, String str){
		super(msg, str);
	}
	
	/**
	 * コンストラクタ<p>
	 * 
	 * 指定のメッセージ、例外で例外を生成します。
	 * 
	 * @param msg メッセージ定数インスタンス
	 * @param throwable 例外インスタンス
	 * @since 1.0
	 */
	public DatabaseExportDocumentsFatalException(MessageInterface msg, Throwable throwable){
		super(msg, throwable);
	}
	
	/**
	 * コンストラクタ<p>
	 * 
	 * 指定のメッセージ、埋め込み文字列、例外で例外を生成します。
	 * 
	 * @param msg メッセージ定数インスタンス
	 * @param str メッセージ埋め込み文字列
	 * @param throwable 例外インスタンス
	 * @since 1.0
	 */
	public DatabaseExportDocumentsFatalException(MessageInterface msg, String str, Throwable throwable){
		super(msg, str, throwable);
	}
	
	/**
	 * コンストラクタ<p>
	 * 
	 * 指定のメッセージ、埋め込み文字列一覧、例外で例外を生成します。
	 * 
	 * @param msg メッセージ定数インスタンス
	 * @param list メッセージ埋め込み文字列一覧
	 * @param throwable 例外インスタンス
	 * @since 1.0
	 */
	public DatabaseExportDocumentsFatalException(MessageInterface msg, List<String> list,Throwable throwable){
		super(msg, list, throwable);
	}
	
	/**
	 * コンストラクタ<p>
	 * 
	 * 指定のメッセージ、埋め込み文字列一覧、例外で例外を生成します。
	 * 
	 * @param msg メッセージ定数インスタンス
	 * @param embeddedStrList メッセージ埋め込み文字列一覧
	 * @param throwable 例外インスタンス
	 * @since 1.0
	 */
	public DatabaseExportDocumentsFatalException(MessageInterface msg, String[] str, Throwable throwable){
		super(msg, str, throwable);
	}
}
