
import javax.annotation.sql.DataSourceDefinition;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author caio
 */
@DataSourceDefinition(name="java:module/onlinejobs-db-free",
  className="com.mysql.jdbc.jdbc2.optional.MysqlDataSource",
  url="jdbc:mysql://db4free.net:3306/dsonlinejobs?zeroDateTimeBehavior=convertToNull",
  user="onlinejobsuser",
  password="dsjobs2019",
  databaseName="dsonlinejobs",
  serverName="db4free.net"
)// DO NOT DO THIS!!!
    
public class DBConfig {

}
