package com.liuqh.mulittransaction.configuration;

import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;

/**   
 * LL
 * 2020年10月23日 下午6:57:36
 */

public class DynamicDataSource extends AbstractRoutingDataSource {

    @Override
    protected Object determineCurrentLookupKey() {
        DataSourceType.DataBaseType dataBaseType = DataSourceType.getDataBaseType();
        return dataBaseType;
    }

}
