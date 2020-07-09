package org.home.settings;

import lombok.Data;

@Data
public class ConnSettings {
    String DB_CONNECTION_URL = "jdbc:oracle:thin:@localhost:1521/orcl";
    String DB_USER = "abs";
    String DB_PASSWORD = "qaz";
}