package com.eqy.web.pojo;

public class ShowDateBean {
	    private String columns; //字段 a1,a2,a3.....
	    private String fieldName; //字段名
	    private String fieldVal;//字段值
	    private String description;//描述
	    private String flag;//判断   0是强转，1是存在提示
	    
		public String getFlag() {
			return flag;
		}
		public void setFlag(String flag) {
			this.flag = flag;
		}
		public String getColumns() {
			return columns;
		}
		public void setColumns(String columns) {
			this.columns = columns;
		}
		public String getFieldName() {
			return fieldName;
		}
		public void setFieldName(String  fieldName) {
			this.fieldName = fieldName;
		}
		public String getFieldVal() {
			return fieldVal;
		}
		public void setFieldVal(String fieldVal) {
			this.fieldVal = fieldVal;
		}
		public String getDescription() {
			return description;
		}
		public void setDescription(String description) {
			this.description = description;
		}
	    
	    
}
