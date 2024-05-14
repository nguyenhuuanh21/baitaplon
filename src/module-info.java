module AAA {
	requires javafx.controls;
	requires javafx.fxml;
	requires javafx.graphics;
	requires java.sql;
	requires javafx.base;
	requires com.microsoft.sqlserver.jdbc;
	requires java.desktop;
	requires javafx.swing;
	//requires com.microsoft.sqlserver.jdbc;
	
	opens application to javafx.graphics, javafx.fxml;
	opens controller to javafx.graphics,javafx.fxml;
	opens connection to javafx.graphics,javafx.fxml;
	opens model to javafx.graphics,javafx.fxml,javafx.base;
	opens image to javafx.graphics,javafx.fxml,javafx.base;
	opens controllerE to javafx.graphics,javafx.fxml;
}
