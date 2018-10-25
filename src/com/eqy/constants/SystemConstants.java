package com.eqy.constants;

import java.util.ArrayList;
import java.util.Arrays;

public class SystemConstants {

	public static final String WEB_SUCCESS = "0";

	public static final String WEB_FAILURE = "1";
	
	public static final String Data_SUCCESS = "1";

	public static final String RULE_TYPE_FORCE = "0";

	public static final String RULE_TYPE_TIP = "1";

	public static final int MAX_THREAD_POOL_SIZE = 20;

	public static final String[] TEMPLATE_RECOGNITON_COORDINATE = new String[]{
			"243,1713,440,1752", "240,1765,440,1801", "240,1809,442,1852", "240,1859,444,1900",
			"241,1909,443,1950", "240,1958,442,1999", "243,2007,443,2048",

			"243,3110,439,3150", "241,3158,439,3197", "241,3209,437,3250", "240,3257,436,3297",
			"241,3304,443,3342", "241,3350,439,3390", "240,3398,443,3438"
	};
	public static final ArrayList<String> TEMPLATE_RECOGNITON_FIELD = new ArrayList<>(
			Arrays.asList("FHΑ","FHΑM","FΑ","FFΑ","CΑ","FFU","FKO",
							"FHΒM","FHΒ","FΒ","FFΒ","CΒ","FU","FO"));

	public static final String Data_flag1="flag1"; //data表中字段flag1

	public static final int Data_flag1_Success=1;//data表中字段flag1,为1是已确认，0是未确认

	public static final String flag2_Data_changed = "0";
	public static final String flag2_Data_unchanged = "1";

	public static final String TASK_FINISHED = "1";
	public static final String TASK_UNFINISHED = "0";

	public static final String FTP_TASK_CREATOR = "system";
	public static final int FTP_TASK_FACTORY_ID = 2;

    public static final String flag_DataMark_error = "1";//提示规则
    public static final String flag_DataMark_fine = "0";
    

}
