/*
 * Licensed to the Apache Software Foundation (ASF) under one
 * or more contributor license agreements.  See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership.  The ASF licenses this file
 * to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License.  You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.dtstack.flinkx.phoenix5.util;

import com.dtstack.flinkx.util.ClassUtil;
import com.dtstack.flinkx.util.TelnetUtil;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * Date: 2020/02/28
 * Company: www.dtstack.com
 *
 * @author tudou
 */
public class PhoenixUtil {
    public static Connection getConnectionInternal(String url, String username, String password) throws SQLException {
        Connection dbConn;
        synchronized (ClassUtil.LOCK_STR){
            DriverManager.setLoginTimeout(10);

            // telnet
            TelnetUtil.telnet(url);

            if (username == null) {
                dbConn = DriverManager.getConnection(url);
            } else {
                dbConn = DriverManager.getConnection(url, username, password);
            }
        }

        return dbConn;
    }
}
