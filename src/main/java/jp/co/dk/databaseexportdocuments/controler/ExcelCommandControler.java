package jp.co.dk.databaseexportdocuments.controler;

import jp.co.dk.datastoremanager.DataStoreManager;
import jp.co.dk.datastoremanager.exception.DataStoreManagerException;
import jp.co.dk.datastoremanager.property.DataStoreManagerProperty;
import jp.co.dk.datastoremanager.rdb.AbstractDataBaseAccessObject;
import jp.co.dk.datastoremanager.rdb.DataBaseRecord;
import jp.co.dk.datastoremanager.rdb.DataConvertable;
import jp.co.dk.datastoremanager.rdb.Sql;
import jp.co.dk.property.exception.PropertyException;

import org.apache.commons.cli.Options;

public class ExcelCommandControler extends AbtractCommandControler {

	public static void main(String[] args) {
		ExcelCommandControler controler = new ExcelCommandControler();
		controler.execute(args);
	}

	@Override
	public void execute() {
		try {
			DataStoreManager dsm = new DataStoreManager(new DataStoreManagerProperty());
			dsm.startTrunsaction();
			AbstractDataBaseAccessObject dao = (AbstractDataBaseAccessObject)dsm.getDataAccessObject("default");
			dao.selectMulti(new Sql("select * from USERS;"), new DataConvertable() {
				public DataConvertable convert(DataBaseRecord dataBaseRecord) throws DataStoreManagerException {
					for (String columnName : dataBaseRecord.getColumns()) {
						System.out.print(columnName);
						System.out.print(":");
						System.out.print(dataBaseRecord.getString(columnName));
						System.out.println();
					}
					return null;
				}
			});
			dsm.finishTrunsaction();
		} catch (DataStoreManagerException e) {
			System.out.println(e.toString());
			System.exit(1);
		} catch (PropertyException e) {
			System.out.println(e.toString());
			System.exit(1);
		}
	}

	@Override
	protected String getCommandName() {
		return "db_to_excel";
	}

	@Override
	protected void getOptions(Options options) {
//		options.addOption(
//			OptionBuilder
//				.isRequired(true)
//				.hasArg(true)
//				.withArgName("file")
//				.withDescription("実行対象のSQLが記載されたファイル")
//				.withLongOpt("sql_file")
//				.create("f")
//		);
	}

}
